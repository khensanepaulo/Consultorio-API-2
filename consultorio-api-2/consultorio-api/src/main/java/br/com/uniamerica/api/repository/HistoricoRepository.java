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

    /**
     *
     * @param dataExcluido
     * @param idHistorico
     */
    @Modifying
    @Query("UPDATE Historico historico " +
            "SET historico.excluido = :dataExcluido " +
            "WHERE historico.id = :idHistorico")
    public void updateDataExcluido(
            @Param("dataExcluido") LocalDateTime dataExcluido,
            @Param("idHistorico") Long idHistorico);

}
