package br.com.consultorio.entity;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Historico  extends AbstractEntity{

     @Getter @Setter
     @Column(name = "observacao", length = 50)
     String observacao;


     @Getter @Setter
     @JoinColumn(name = "id_secretaria")
     @ManyToOne(fetch = FetchType.EAGER)
     Secretaria secretaria;


     @Getter @Setter
     @ManyToOne
     Paciente paciente;

     @Getter @Setter
     @ManyToOne
     Agenda agenda;

     @Getter @Setter
     @Column(name = "data")
     LocalDateTime data;

     @Getter @Setter
     @Column(name = "statusAgendamento")
     StatusAgendamento statusAgendamento;


}
