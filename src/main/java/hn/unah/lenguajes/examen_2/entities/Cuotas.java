package hn.unah.lenguajes.examen_2.entities;

import java.math.BigDecimal;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Cuotas {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "codigocuota")
    private int codigoCuota;

    private int mes;

    @Column(precision = 14,scale = 2)
    private BigDecimal interes;

    @Column(precision = 14,scale = 2)
    private BigDecimal capital;

    @Column(precision = 14,scale = 2)
    private BigDecimal saldo;

    //Relacion Cliente
    @ManyToOne
    @JoinColumn(name = "codigoprestamo",referencedColumnName = "codigoprestamo")
    private Prestamos prestamos;


}
