package com.mizamarzes.pushup_3_backend.application.services;

import java.util.List;
import java.util.Optional;

import com.mizamarzes.pushup_3_backend.domain.models.TipoPersona;

public interface TipoPersonaService {

    Optional<TipoPersona> findById(Long id);

    List<TipoPersona> getAll();
    
    TipoPersona save(TipoPersona tipoPersona);
}

