package com.mizamarzes.pushup_3_backend.infrastructure.adpters;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mizamarzes.pushup_3_backend.application.services.TipoPersonaService;
import com.mizamarzes.pushup_3_backend.domain.models.TipoPersona;
import com.mizamarzes.pushup_3_backend.infrastructure.repositories.TipoPersonRepository;

@Service
public class TipoPersonaAdapter implements TipoPersonaService {
    
    @Autowired
    private TipoPersonRepository tipoPersonRepository;

    @Override
    public Optional<TipoPersona> findById(Long id) {
        return tipoPersonRepository.findById(id);
    }

    @Override
    public List<TipoPersona> getAll(){
        return tipoPersonRepository.findAll();
    }

    @Override
    public TipoPersona save(TipoPersona tipoPersona) {
        return tipoPersonRepository.save(tipoPersona);
    }
}
