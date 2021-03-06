package br.com.consultorio.service;

import br.com.consultorio.entity.*;
import br.com.consultorio.repository.AgendaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


import javax.transaction.Transactional;
import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class AgendaService {

    @Autowired
    private AgendaRepository agendaRepository;

    public Optional<Agenda> findById(Long id){
        return this.agendaRepository.findById(id);
    }

    public Page<Agenda> listAll(Pageable pageable){
        return this.agendaRepository.findAll(pageable);
    }

    public boolean isDataMenorQueAtual(LocalDateTime localDateTimeDe, LocalDateTime localDateTimeAte){
        return  localDateTimeDe.compareTo(LocalDateTime.now()) <= 0
                &&
                localDateTimeAte.compareTo(LocalDateTime.now()) <= 0;
    }

    public boolean isDataAteMaiorQueDataDe(LocalDateTime localDateTimeDe, LocalDateTime localDateTimeAte){
        return localDateTimeAte.compareTo(localDateTimeDe) >= 0;
    }

    public boolean isOnHorarioDeAtendimento(LocalDateTime localDateTimeDe, LocalDateTime localDateTimeAte){
        return  localDateTimeDe.getHour() >= 8 && localDateTimeDe.getHour() <= 11
                &&
                localDateTimeAte.getHour() >= 9 || localDateTimeAte.getHour() <= 12
                ||
                localDateTimeDe.getHour() >= 14 || localDateTimeDe.getHour() <= 17
                &&
                localDateTimeAte.getHour() >= 15 || localDateTimeAte.getHour() <= 18;
    }

    public boolean isFimDeSemana(LocalDateTime localDateTimeDe){
        return  localDateTimeDe.getDayOfWeek() != DayOfWeek.SATURDAY
                &&
                localDateTimeDe.getDayOfWeek() != DayOfWeek.SUNDAY;
    }

    public boolean isDataDisponivel(LocalDateTime localDateTimeDe, LocalDateTime localDateTimeAte, Medico medico, Paciente paciente, Long idAgenda){
        return agendaRepository.isDataDisponivel(localDateTimeDe, localDateTimeAte, medico, paciente, idAgenda);
    }

    public boolean isEncaixe(Agenda agenda){
        if(agenda.getEncaixe() == true) return true;
        else return false;
    }

    public void cadastrarAgendaSecretaria(Agenda agenda, Secretaria secretaria){
        if(isEncaixe(agenda) == true){

            if(secretaria != null &&
                    isDataMenorQueAtual(agenda.getDataDe(), agenda.getDataAte()) == false &&
                    isDataAteMaiorQueDataDe(agenda.getDataDe(), agenda.getDataAte()) == false &&
                    isOnHorarioDeAtendimento(agenda.getDataDe(), agenda.getDataAte()) == true &&
                    isFimDeSemana(agenda.getDataDe()) == false) {

                agenda.setStatus(StatusAgendamento.aprovado);
                agendaRepository.save(agenda);

            } else {
                throw new RuntimeException("Condi????es b??sicas n??o atendidas, verifique os dados.");
            }

        } else if (isEncaixe(agenda) == false) {

            if(secretaria != null &&
                    isDataMenorQueAtual(agenda.getDataDe(), agenda.getDataAte()) == false &&
                    isDataAteMaiorQueDataDe(agenda.getDataDe(), agenda.getDataAte()) == false &&
                    isOnHorarioDeAtendimento(agenda.getDataDe(), agenda.getDataAte()) == true &&
                    isFimDeSemana(agenda.getDataDe()) == false &&
                    isDataDisponivel(agenda.getDataDe(), agenda.getDataAte(), agenda.getMedico(), agenda.getPaciente(), agenda.getId()) == true) {

                agenda.setStatus(StatusAgendamento.aprovado);
                agendaRepository.save(agenda);

            } else {
                throw new RuntimeException("Condi????es b??sicas n??o atendidas, verifique os dados.");
            }
        }
    }


    public void cadastrarAgendaPaciente(Agenda agenda, Secretaria secretaria){
        if(isEncaixe(agenda) == false){

            if(secretaria == null &&
                    isDataMenorQueAtual(agenda.getDataDe(), agenda.getDataAte()) == false &&
                    isDataAteMaiorQueDataDe(agenda.getDataDe(), agenda.getDataAte()) == false &&
                    isOnHorarioDeAtendimento(agenda.getDataDe(), agenda.getDataAte()) == true &&
                    isFimDeSemana(agenda.getDataDe()) == false &&
                    isDataDisponivel(agenda.getDataDe(), agenda.getDataAte(), agenda.getMedico(), agenda.getPaciente(), agenda.getId()) == true) {

                agenda.setStatus(StatusAgendamento.pendente);
                agendaRepository.save(agenda);

            } else {
                throw new RuntimeException("Condi????es b??sicas n??o atendidas, verifique os dados.");
            }

        } else if (isEncaixe(agenda) == true) {
            throw new RuntimeException("Voc?? n??o tem permiss??o para realizar um encaixe.");
        }
    }

    @Transactional
    public void updateStatusRemovido(Long id, Agenda agenda) {
        if (id == agenda.getId()) {
            this.agendaRepository.updateStatusRemovido(agenda.getId());
        } else {
            throw new RuntimeException();
        }
    }

    public void updateStatusAgendaAprovado(Agenda agenda, Secretaria secretaria) {
        if(agenda.getStatus().equals(StatusAgendamento.pendente) && secretaria != null){
            updateStatusAgenda(agenda, StatusAgendamento.aprovado);
        }
    }

    public void updateStatusAgendaRejeitado(Agenda agenda, Secretaria secretaria) {
        if(agenda.getStatus().equals(StatusAgendamento.pendente) && secretaria != null){
            updateStatusAgenda(agenda, StatusAgendamento.rejeitado);
        }
    }

    public void updateStatusAgendaCancelado(Agenda agenda, Secretaria secretaria) {
        if((agenda.getStatus().equals(StatusAgendamento.aprovado)) || (agenda.getStatus().equals(StatusAgendamento.pendente))
                &&
                secretaria != null || agenda.getPaciente() != null){
            updateStatusAgenda(agenda, StatusAgendamento.cancelado);
        }
    }

    public void updateStatusAgendaCompareceu(Agenda agenda, Secretaria secretaria) {
        if(agenda.getStatus().equals(StatusAgendamento.aprovado) &&
                secretaria != null &&
                isDataMenorQueAtual(agenda.getDataDe(), agenda.getDataAte()) == false){
            updateStatusAgenda(agenda, StatusAgendamento.compareceu);
        }
    }

    public void updateStatusAgendaNaoCompareceu(Agenda agenda, Secretaria secretaria) {
        if(agenda.getStatus().equals(StatusAgendamento.aprovado) &&
                secretaria != null &&
                isDataMenorQueAtual(agenda.getDataDe(), agenda.getDataAte()) == false){
            updateStatusAgenda(agenda, StatusAgendamento.nao_compareceu);
        }
    }

    @Transactional
    public void updateStatusAgenda(Agenda agenda, StatusAgendamento statusAgenda){
        agenda.setStatus(statusAgenda);
        agendaRepository.save(agenda);
    }

}