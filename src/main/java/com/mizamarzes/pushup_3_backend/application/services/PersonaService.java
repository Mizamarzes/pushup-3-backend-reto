package com.mizamarzes.pushup_3_backend.application.services;

import java.util.List;
import java.util.Optional;

import com.mizamarzes.pushup_3_backend.domain.models.Persona;


public interface PersonaService {

    Optional<Persona> findById(Long id);
    
    List<Persona> findAll();

    Persona save(Persona persona);
}
