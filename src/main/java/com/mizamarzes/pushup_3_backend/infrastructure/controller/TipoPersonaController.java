package com.mizamarzes.pushup_3_backend.infrastructure.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mizamarzes.pushup_3_backend.application.services.TipoPersonaService;
import com.mizamarzes.pushup_3_backend.domain.models.TipoPersona;

@RestController
@RequestMapping("/tipopersona")
public class TipoPersonaController {

    @Autowired
    private TipoPersonaService tipoPersonaService;

    @GetMapping
    public List<TipoPersona> listTipoPersonas(){
        return tipoPersonaService.getAll();
    }

    @GetMapping("/{id}")
    public Optional<TipoPersona> showTipoPersona(@PathVariable Long id){
        return tipoPersonaService.findById(id);
    }
}
