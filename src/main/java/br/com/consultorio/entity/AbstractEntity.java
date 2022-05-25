package br.com.consultorio.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@MappedSuperclass
@NoArgsConstructor
public abstract class AbstractEntity {

    @Getter @Setter
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @Getter @Setter
    @Column(name = "cadastro", nullable = false)
    private LocalDateTime cadastro;

    @Getter @Setter
    @Column(name = "atualizado", nullable = false)
    private LocalDateTime atualizado;

    @Getter @Setter
    @Column(name = "excluido", nullable = false)
    private LocalDateTime excluido;

    public AbstractEntity(Long id) {this.id = id;}

    @PrePersist
    public void atualizarDataCadastro(){
        this.cadastro = LocalDateTime.now();
    }

    @PreUpdate
    public void dataAtualizar(){
        this.atualizado = LocalDateTime.now();
    }

    @PreRemove
    public void dataExcluido(){
        this.excluido = LocalDateTime.now();
    }

}
