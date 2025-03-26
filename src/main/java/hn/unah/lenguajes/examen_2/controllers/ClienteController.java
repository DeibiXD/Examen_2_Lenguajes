package hn.unah.lenguajes.examen_2.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import hn.unah.lenguajes.examen_2.dtos.ClienteDTO;
import hn.unah.lenguajes.examen_2.services.ClienteService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/cliente")
public class ClienteController {
    
    @Autowired
    private ClienteService clienteService;

    @PostMapping("/crear")
    public String crearCliente(@RequestBody ClienteDTO clienteDTO) {
        return clienteService.crearCliente(clienteDTO);
    }
    
}
