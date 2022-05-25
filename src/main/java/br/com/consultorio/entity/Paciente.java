package br.com.consultorio.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Entity
@Table(name = "pacientes", schema = "public")
public class Paciente extends Pessoas{


    @Getter @Setter
    @Column(name = "convenio", length = 30)
    Convenio convenio;

    @Getter @Setter
    @Column(name = "numeroCartaoConvenio", length = 20)
    String numeroCartaoConvenio;

    @Getter @Setter
    @Column(name = "dataVencimento")
    LocalDateTime dataVencimento;

    @Getter @Setter
    @Column(name = "tipoAtendimento")
    TipoAtendimento tipoAtendimento;

}
