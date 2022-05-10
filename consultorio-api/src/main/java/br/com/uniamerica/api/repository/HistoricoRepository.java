package br.com.uniamerica.api.repository;

import br.com.uniamerica.api.entity.Historico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

/**
 * @author Eduardo Sganderla
 *
 * @since 1.0.0, 05/04/2022
 * @version 1.0.0
 */
@Repository
public interface HistoricoRepository extends JpaRepository<Historico, Long> {

    @Modifying
    @Query("UPDATE Historico historico " +
            "SET historico.excluido = :excluido " +
            "WHERE historico.id = :historico")

    public void updateDataExcluido(@Param("excluido") LocalDateTime excluido, @Param("historico") Long idHistorico);
}
