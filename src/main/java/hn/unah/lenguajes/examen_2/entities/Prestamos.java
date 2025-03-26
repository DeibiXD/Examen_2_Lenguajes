package hn.unah.lenguajes.examen_2.entities;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Prestamos {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "codigoprestamo")
    private int codigoPrestamo;

    @Column(name = "fechaapertura")
    private Date fechaApertura;

    @Column(precision = 10,scale = 2)
    private BigDecimal monto;

    @Column(precision = 14,scale = 2)
    private BigDecimal cuota;

    @Column(precision = 8,scale = 2)
    private BigDecimal interes;

    @Column(name = "plazo")
    private int plazo;

    //Relacion con Clientes
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "dni",referencedColumnName = "dni")
    private Cliente cliente;

    //Relacion con prestamos
    @OneToMany(mappedBy = "prestamos")
    private List<Cuotas> cuotas;
}
