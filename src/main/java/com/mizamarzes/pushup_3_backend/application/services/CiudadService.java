package com.mizamarzes.pushup_3_backend.application.services;

import java.util.List;
import java.util.Optional;

import com.mizamarzes.pushup_3_backend.domain.models.Ciudad;

public interface CiudadService {

    Optional<Ciudad> findById(Long id);

    List<Ciudad> getAll();

    Ciudad save(Ciudad ciudad);

    Ciudad update(Long id, Ciudad ciudad);

    void deleteById(Long id);
}
