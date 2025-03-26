package hn.unah.lenguajes.examen_2.dtos;

import java.math.BigDecimal;
import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PrestamosDTO {

    private Date fechaApertura;

    private BigDecimal monto;

    private BigDecimal interes;

    private int plazo;
}
