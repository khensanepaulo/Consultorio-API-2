package br.com.consultorio.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.Digits;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "secretarias", schema = "public")
public class Secretaria extends Pessoas{

    @Getter @Setter
    @Digits(integer = 6, fraction = 3)
    @Column(name = "salario", nullable = false)
     BigDecimal salario;

    @Getter @Setter
    @Column(name = "dataContratacao")
     LocalDateTime dataContratacao;


}
 