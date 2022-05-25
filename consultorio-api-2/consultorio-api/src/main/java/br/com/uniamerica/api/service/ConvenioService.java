package br.com.uniamerica.api.service;

import br.com.uniamerica.api.entity.Convenio;
import br.com.uniamerica.api.repository.ConvenioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

/**
*@author khensane Paulo
 */

@Service
public class ConvenioService {
    @Autowired
    ConvenioRepository convenioRepository;

    /**
    @param id
     ** @return
     */

    public Optional<Convenio> findBy(Long id) {return this.convenioRepository.findById(id);}

    /**
     @param pageable
      ** @return
     */

    public Page<Convenio> listAlly(Pageable pageable) {return this.convenioRepository.findAll(pageable);}

    /**
     @param id
     @param convenio
     */
    @Transactional
    public void update(Long id, Convenio convenio){
        if (id == convenio.getId()){
            this.convenioRepository.save(convenio);
        }
        else{
            throw new RuntimeException();
        }
    }
    /**
     * @param convenio
     */

    @Transactional
    public void insert(Convenio convenio){this.convenioRepository.save(convenio);}

    /**
     @param id
     @param convenio
     */
    @Transactional
    public void updateStaus(Long id, Convenio convenio){
        if (id == convenio.getId())
        {
            this.convenioRepository.updateStatus(convenio.getId());
        }
        else
        {
            throw new RuntimeException();
        }
    }
}
