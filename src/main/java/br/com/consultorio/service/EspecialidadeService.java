package br.com.consultorio.service;

import br.com.consultorio.entity.Especialidade;
import br.com.consultorio.repository.EspecialidadeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class EspecialidadeService {

    @Autowired
    private EspecialidadeRepository especialidadeRepository;


    public Optional<Especialidade> findById(Long id){
        return this.especialidadeRepository.findById(id);
    }


    public Page<Especialidade> listAll(Pageable pageable){
        return this.especialidadeRepository.findAll(pageable);
    }


    @Transactional
    public void update(Long id, Especialidade especialidade){
        if (id == especialidade.getId()) {
            this.especialidadeRepository.save(especialidade);
        }
        else {
            throw new RuntimeException();
        }
    }


    @Transactional
    public void insert(Especialidade especialidade){
        this.especialidadeRepository.save(especialidade);
    }


    @Transactional
    public void updateStatus(Long id, Especialidade especialidade){
        if (id == especialidade.getId()) {
            this.especialidadeRepository.updateStatus(LocalDateTime.now(),especialidade.getId());
        }
        else {
            throw new RuntimeException();
        }
    }



}
