package br.com.consultorio.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class Pessoas extends AbstractEntity{

    @Getter @Setter
    @Column(name = "nome", length = 50)
     String nome;

    @Getter @Setter
    @Column(name = "telefone", length = 50)
     String telefone;

    @Getter @Setter
    @Column(name = "celular", length = 50)
     String celular;

    @Getter @Setter
    @Column(name = "nacionalidade", length = 50)
     String nacionalidade;

    @Getter @Setter
    @Column(name = "cpf",  length = 50)
     String cpf;

    @Getter @Setter
    @Column(name = "rg",  length = 50)
     String rg;

    @Getter @Setter
    @Column(name = "email",  length = 50)
     String email;

    @Getter @Setter
    @Column(name = "login",  length = 50)
     String login;

    @Getter @Setter
    @Column(name = "senha", length = 50)
     String senha;

    @Getter @Setter
    @Column(name = "sexo")
     Sexo sexo;


}
