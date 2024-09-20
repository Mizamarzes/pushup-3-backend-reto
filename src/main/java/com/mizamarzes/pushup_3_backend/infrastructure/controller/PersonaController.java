package com.mizamarzes.pushup_3_backend.infrastructure.controller;


import java.util.List;
import java.util.Optional;
import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mizamarzes.pushup_3_backend.application.services.PersonaService;
import com.mizamarzes.pushup_3_backend.domain.models.Persona;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class PersonaController {

    @Autowired
    private PersonaService personaService;

    @GetMapping
    public List<Persona> listPersonas(){
        return personaService.findAll();
    }

    @GetMapping("/{id}")
    public Optional<Persona> showPersona(@PathVariable Long id){
        return personaService.findById(id);
    }

    @PostMapping
    public ResponseEntity<Persona> createPersona(@RequestBody Persona persona) {
        return ResponseEntity.created(URI.create("/persona/personaID")).body(personaService.save(persona));
    }
}
