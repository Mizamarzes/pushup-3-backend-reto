package com.mizamarzes.pushup_3_backend.infrastructure.adpters;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import com.mizamarzes.pushup_3_backend.application.services.PersonaService;
import com.mizamarzes.pushup_3_backend.domain.models.Persona;
import com.mizamarzes.pushup_3_backend.domain.models.TipoPersona;
import com.mizamarzes.pushup_3_backend.infrastructure.repositories.PersonRepository;
import com.mizamarzes.pushup_3_backend.infrastructure.repositories.TipoPersonRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PersonAdapter implements PersonaService{

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private TipoPersonRepository tipoPersonRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public Optional<Persona> findById(Long id) {
        return personRepository.findById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Persona> findAll() {
        return (List<Persona>) personRepository.findAll();
    }

    @Override
    @Transactional
    public Persona save(Persona person) {
        Optional<TipoPersona> optionalTipoPersona = tipoPersonRepository.findByTipo("USER");
        List<TipoPersona> TipoPersonas = new ArrayList<>();

        optionalTipoPersona.ifPresent(TipoPersonas::add);

        if (person.isAdmin()) {
            Optional<TipoPersona> optionalTipoPersonaAdmin = tipoPersonRepository.findByTipo("ADMIN");
            optionalTipoPersonaAdmin.ifPresent(TipoPersonas::add);
        }

        person.setTipos(TipoPersonas);
        person.setPassword(passwordEncoder.encode(person.getPassword()));
        return personRepository.save(person);
    }

}
