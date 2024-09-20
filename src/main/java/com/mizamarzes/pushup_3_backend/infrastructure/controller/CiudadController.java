package com.mizamarzes.pushup_3_backend.infrastructure.controller;

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

import com.mizamarzes.pushup_3_backend.application.services.CiudadService;
import com.mizamarzes.pushup_3_backend.domain.models.Ciudad;

import java.util.List;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/ciudad")
public class CiudadController {

    @Autowired
    private CiudadService ciudadService;

    @GetMapping
    public ResponseEntity<List<Ciudad>> listCiudades() {
        List<Ciudad> Ciudades = ciudadService.getAll();
        return new ResponseEntity<>(Ciudades, HttpStatus.OK);
    } 

    @GetMapping("/{id}")
    public ResponseEntity<Ciudad> showCiudad(@PathVariable Long id){
        return ciudadService.findById(id)
            .map(Ciudad -> new ResponseEntity<>(Ciudad, HttpStatus.OK))
            .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public ResponseEntity<Ciudad> saveCiudad(@Valid @RequestBody Ciudad Ciudad) {
        Ciudad newCiudad = ciudadService.save(Ciudad);
        return new ResponseEntity<>(newCiudad, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Ciudad> updateCiudad(@PathVariable Long id, @RequestBody Ciudad Ciudad) {
        Ciudad updatedCiudad = ciudadService.update(id, Ciudad);
        if (updatedCiudad != null) {
            return new ResponseEntity<>(updatedCiudad, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCiudad(@PathVariable Long id) {
        if (ciudadService.findById(id).isPresent()) {
            ciudadService.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
