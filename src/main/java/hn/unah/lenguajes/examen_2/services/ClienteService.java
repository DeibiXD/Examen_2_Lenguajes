package hn.unah.lenguajes.examen_2.services;

import java.math.BigDecimal;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import org.hibernate.exception.spi.ViolatedConstraintNameExtractor;
import org.springframework.beans.factory.config.CustomEditorConfigurer;
import org.springframework.stereotype.Service;

import hn.unah.lenguajes.examen_2.dtos.ClienteDTO;
import hn.unah.lenguajes.examen_2.dtos.PrestamosDTO;
import hn.unah.lenguajes.examen_2.entities.Cliente;
import hn.unah.lenguajes.examen_2.entities.Cuotas;
import hn.unah.lenguajes.examen_2.entities.Prestamos;
import hn.unah.lenguajes.examen_2.repositories.ClienteRepository;

@Service
public class ClienteService {
    private ClienteRepository clienteRepository;

    public String crearCliente(ClienteDTO clienteDTO){
        Optional<Cliente> clienteOptional = clienteRepository.findById(clienteDTO.getDni());
        if (clienteOptional.isPresent()) {
            return "Este cliente ya existe";
        }
        Cliente cliente = new Cliente();
        cliente.setDni(clienteDTO.getDni());
        cliente.setNombre(clienteDTO.getNombre());
        cliente.setApellido(clienteDTO.getApellido());
        cliente.setTelefono(clienteDTO.getTelefono());
        
        if (clienteDTO.getPrestamos()!=null) {
            LinkedList<Prestamos> prestamos = new LinkedList<>();
            //Crea el prestamo de los detalles de prestamos dto
            Prestamos prestamoCreado = this.crearPrestamo(clienteDTO.getPrestamos());
            prestamos.add(prestamoCreado);
            cliente.setPrestamos(prestamos);
        }

        clienteRepository.save(cliente);
        return "Cliente creado";
    }

    //Calcular la cuota
    public Prestamos crearPrestamo(PrestamosDTO prestamosDTO){
        //Calcular la cuota
        BigDecimal cuota;
        BigDecimal doce = new BigDecimal(12);
        BigDecimal uno = new BigDecimal(1);
        BigDecimal unoMasI = uno.add(prestamosDTO.getInteres().divide(doce));

        cuota = prestamosDTO.getMonto().multiply(prestamosDTO.getInteres().divide(doce));
        cuota = uno.subtract(unoMasI.pow(prestamosDTO.getPlazo()));

        Prestamos prestamos = new Prestamos();
        prestamos.setCuota(cuota);
        prestamos.setFechaApertura(Date.valueOf(LocalDate.now()));
        prestamos.setMonto(prestamosDTO.getMonto());
        prestamos.setInteres(prestamosDTO.getInteres());
        prestamos.setPlazo(prestamosDTO.getPlazo());

        List<Cuotas> listaCuotas = crearCuotas(prestamos);
        prestamos.setCuotas(listaCuotas);

        return prestamos;
    }

    public List<Cuotas> crearCuotas(Prestamos prestamo){
        BigDecimal doce = new BigDecimal(12);
        Cuotas primeraCuota = new Cuotas();
        primeraCuota.setMes(0);
        primeraCuota.setInteres(BigDecimal.ZERO);
        primeraCuota.setCapital(BigDecimal.ZERO);
        primeraCuota.setSaldo(prestamo.getMonto());

        int totalPagos = prestamo.getPlazo()*12;
        List<Cuotas> cuotasLista = new ArrayList<>();
        cuotasLista.add(primeraCuota);
        for (int i=1; i<totalPagos;i++){
            if (i==1) {
                Cuotas cuotas = new Cuotas();
                cuotas.setMes(i);
                cuotas.setInteres(primeraCuota.getSaldo().multiply(prestamo.getInteres().divide(doce)));
                cuotas.setCapital(prestamo.getCuota().subtract(cuotas.getInteres()));
                cuotas.setSaldo(primeraCuota.getSaldo().subtract(cuotas.getCapital()));
                cuotasLista.add(cuotas);
            } else {
                Cuotas cuotas = new Cuotas();
                cuotas.setMes(i);
                cuotas.setInteres(cuotasLista.get(i-1).getSaldo().multiply(prestamo.getInteres().divide(doce)));
                cuotas.setCapital(prestamo.getCuota().subtract(cuotas.getInteres()));
                cuotas.setSaldo(cuotasLista.get(i-1).getSaldo().subtract(cuotas.getCapital()));
                cuotasLista.add(cuotas);
            }
            
        }
        return cuotasLista;
    }
}
