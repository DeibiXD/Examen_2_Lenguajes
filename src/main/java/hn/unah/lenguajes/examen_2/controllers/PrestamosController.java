package hn.unah.lenguajes.examen_2.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import hn.unah.lenguajes.examen_2.dtos.PrestamosDTO;
import hn.unah.lenguajes.examen_2.services.PrestamosService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/prestamos")
public class PrestamosController {
    
    @Autowired
    private PrestamosService prestamosService;

    @PostMapping("/crear")
    public String crearPrestamosConDNI(@RequestBody PrestamosDTO prestamosDTO, @RequestParam(name = "dni")String dni) {
        
        return prestamosService.crearPrestamoConDNI(prestamosDTO, dni);
    }
    
}
