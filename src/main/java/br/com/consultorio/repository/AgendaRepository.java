package br.com.consultorio.repository;

import br.com.consultorio.entity.Agenda;
import br.com.consultorio.entity.Medico;
import br.com.consultorio.entity.Paciente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

@Repository
public interface AgendaRepository extends JpaRepository<Agenda, Long> {
    @Modifying
    @Query("UPDATE Agenda agenda " +
            "SET agenda.excluido = now() " +
            "WHERE agenda.id = :agenda")
    public void updateStatusRemovido(@Param("agenda") Long idAgenda);

    @Query("FROM Agenda agenda " +
            "WHERE (" +
            "   :dataDe BETWEEN agenda.dataDe AND agenda.dataAte " +
            "   OR " +
            "   :dataAte BETWEEN agenda.dataDe AND agenda.dataAte" +
            ") " +
            "AND (:medico = agenda.medico OR :paciente = agenda.paciente)" +
            "AND :agenda <> agenda")
    public boolean isDataDisponivel(@Param("dataDe") LocalDateTime dataDe,
                                    @Param("dataAte") LocalDateTime dataAte,
                                    @Param("medico") Medico medico,
                                    @Param("dataAte") Paciente paciente,
                                    @Param("idAgenda") Long idAgenda);

}
