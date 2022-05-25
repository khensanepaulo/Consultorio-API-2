package br.com.consultorio.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Digits;
import java.math.BigDecimal;

@Entity
@Table(name = "medicos", schema = "public")
public class Medico extends Pessoas {

     @Getter @Setter
     @Column(name = "CRM", nullable = false, length = 30)
     String CRM;

    @Getter @Setter
    @Digits(integer = 3, fraction = 2)
    @Column(name = "porcParticicao", nullable = false)
     BigDecimal porcParticicao;


     @Getter @Setter
     @Column(name = "consultorio", nullable = false, length = 30)
     String consultorio;

     @Getter @Setter
     @ManyToOne
     private Especialidade especialidade;

     @Getter @Setter
     @Column(name = "valorConsulta")
     private BigDecimal valorConsulta;

}
