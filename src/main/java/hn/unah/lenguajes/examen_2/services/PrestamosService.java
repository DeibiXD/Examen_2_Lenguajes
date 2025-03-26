package hn.unah.lenguajes.examen_2.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hn.unah.lenguajes.examen_2.dtos.PrestamosDTO;
import hn.unah.lenguajes.examen_2.repositories.ClienteRepository;
import hn.unah.lenguajes.examen_2.repositories.PrestamosRepository;

@Service
public class PrestamosService {
    @Autowired
    private PrestamosRepository prestamosRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    public String crearPrestamoConDNI(PrestamosDTO prestamosDTO, String dni){
        
        return "";
    }
}
