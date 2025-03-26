package hn.unah.lenguajes.examen_2.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hn.unah.lenguajes.examen_2.dtos.PrestamosDTO;
import hn.unah.lenguajes.examen_2.entities.Cliente;
import hn.unah.lenguajes.examen_2.repositories.ClienteRepository;
import hn.unah.lenguajes.examen_2.repositories.PrestamosRepository;

@Service
public class PrestamosService {
    @Autowired
    private PrestamosRepository prestamosRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    public String crearPrestamoConDNI(PrestamosDTO prestamosDTO, String dni){
        Optional<Cliente> cliente = clienteRepository.findById(dni);
        if (cliente.isPresent()) {
            //Agregar el cliente :)
        }
        return "Cliente no es valido para un prestamo";
    }
}
