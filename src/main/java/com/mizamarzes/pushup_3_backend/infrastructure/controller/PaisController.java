package com.mizamarzes.pushup_3_backend.infrastructure.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mizamarzes.pushup_3_backend.application.services.PaisService;
import com.mizamarzes.pushup_3_backend.domain.models.Pais;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/pais")
public class PaisController {

    @Autowired
    private PaisService paisService;

    @GetMapping
    public ResponseEntity<List<Pais>> listPaises() {
        List<Pais> paises = paisService.getAll();
        return new ResponseEntity<>(paises, HttpStatus.OK);
    } 

    @GetMapping("/{id}")
    public ResponseEntity<Pais> showPais(@PathVariable Long id){
        return paisService.findById(id)
            .map(pais -> new ResponseEntity<>(pais, HttpStatus.OK))
            .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public ResponseEntity<Pais> savePais(@Valid @RequestBody Pais pais) {
        Pais newPais = paisService.save(pais);
        return new ResponseEntity<>(newPais, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Pais> updatePais(@PathVariable Long id, @RequestBody Pais pais) {
        Pais updatedPais = paisService.update(id, pais);
        if (updatedPais != null) {
            return new ResponseEntity<>(updatedPais, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePais(@PathVariable Long id) {
        if (paisService.findById(id).isPresent()) {
            paisService.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}
