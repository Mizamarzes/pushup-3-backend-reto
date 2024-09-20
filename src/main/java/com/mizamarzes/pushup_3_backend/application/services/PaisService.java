package com.mizamarzes.pushup_3_backend.application.services;

import java.util.List;
import java.util.Optional;

import com.mizamarzes.pushup_3_backend.domain.models.Pais;

public interface PaisService {

    Optional<Pais> findById(Long id);

    List<Pais> getAll();

    Pais save(Pais pais);

    Pais update(Long id, Pais pais);

    void deleteById(Long id);
}
