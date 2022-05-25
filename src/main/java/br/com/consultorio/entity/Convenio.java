package br.com.consultorio.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.Digits;
import java.math.BigDecimal;

@Entity
@Table(name = "convenios", schema = "public")
@NoArgsConstructor

public class Convenio extends  AbstractEntity{

    @Getter @Setter
    @Column(name = "nome", nullable = false, unique = true, length = 50)
    String nome;

    @Getter @Setter
    @Digits(integer = 5, fraction = 3)
    @Column(name = "custo", nullable = false)
    BigDecimal custo;


}
