package br.com.uniamerica.api.service;

import br.com.uniamerica.api.entity.Agenda;
import br.com.uniamerica.api.repository.AgendaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class AgendaService {

    @Autowired
    private AgendaRepository agendaRepository;
    /**
     * @param id
     * @return
     */
    public Optional<Agenda> findBy(Long id){return this.agendaRepository.findById(id);}

    /**
     * @param pageable
     * @return
     */
    public Page<Agenda> listAll(Pageable pageable){return this.agendaRepository.findAll(pageable);}

    /**
     * @param id
     * @param agenda
     */

    @Transactional
    public void update (Long id , Agenda agenda){
        if (id == agenda.getId()){
            this.saveTransactional(agenda);
            this.validarFormulario(agenda);
        }
        else{
            throw new RuntimeException();
        }
    }

    @Transactional
    public void saveTransactional(Agenda agenda){this.agendaRepository.save(agenda);}

    public void validarFormulario(Agenda agenda){
        if(agenda.getEncaixe() == null){
            throw new RuntimeException("Encaixe nao informado");
        }
        if(agenda.getData() == null){
            throw new RuntimeException("Data do agendamento nao informado");
        }
        if(agenda.getData().compareTo(LocalDateTime.now()) <= 0){
            throw new RuntimeException("Data do agendamento invalida");
        }
        if(agenda.getMedico() == null){
            throw new RuntimeException("Informe o Medico do agendamento");
        }
        if(agenda.getPaciente() == null){
            throw new RuntimeException("Informe o Paciente que deseja agendar");
        }
        if(agenda.getStatus() == null){
            throw new RuntimeException("Informe o Status do agendamento");
        }

    }

   public void validacao(Agenda agenda ) {
       if (agenda.getEncaixe()){}
   }

    /**
     *
     * @param agenda
     */
    @Transactional
    public void insert(Agenda agenda){this.agendaRepository.save(agenda);}

    /**
     *
     * @param id
     * @param agenda
     */
    @Transactional      
    public void updateStatus(Long id, Agenda agenda){
        if (id == agenda.getId()){
            this.agendaRepository.updateDataExcluido(agenda.getExcluido());
        }
    }

}
