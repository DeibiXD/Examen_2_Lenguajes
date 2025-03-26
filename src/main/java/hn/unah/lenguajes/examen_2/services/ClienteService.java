package hn.unah.lenguajes.examen_2.services;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import hn.unah.lenguajes.examen_2.dtos.ClienteDTO;
import hn.unah.lenguajes.examen_2.dtos.PrestamosDTO;
import hn.unah.lenguajes.examen_2.entities.Cliente;
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

        return null;
    }
}
