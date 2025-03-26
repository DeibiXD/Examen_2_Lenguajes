package hn.unah.lenguajes.examen_2.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ClienteDTO {

    private String dni;

    private String nombre;

    private String apellido;

    private String telefono;

    private PrestamosDTO prestamos;
}
