package com.mizamarzes.pushup_3_backend.infrastructure.adpters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.mizamarzes.pushup_3_backend.application.services.CiudadService;
import com.mizamarzes.pushup_3_backend.domain.models.Ciudad;
import com.mizamarzes.pushup_3_backend.infrastructure.exception.dto.BusinessException;
import com.mizamarzes.pushup_3_backend.infrastructure.exception.dto.RequestException;
import com.mizamarzes.pushup_3_backend.infrastructure.repositories.CiudadRepository;

import java.util.List;
import java.util.Optional;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CiudadAdapter implements CiudadService {

    @Autowired
    private CiudadRepository ciudadRepository;

    @Override
    public Optional<Ciudad> findById(Long id) {

        if (!ciudadRepository.findById(id).isPresent()) {
            throw new BusinessException("P-404", HttpStatus.NOT_FOUND, "La ciudad no existe");
        }

        return ciudadRepository.findById(id);
    }

    @Override
    public List<Ciudad> getAll() {
        List<Ciudad> ciudades = ciudadRepository.findAll();

        if (ciudades.isEmpty()) {
            throw new RequestException("P-404", "No hay Ciudades registradas");
        }

        return ciudadRepository.findAll();
    }

    @Override
    public Ciudad save(Ciudad ciudad) {

        Optional<Ciudad> existingCiudad = ciudadRepository.findByNombre(ciudad.getNombre());

        if (existingCiudad.isPresent()) {
            throw new BusinessException("P-300", HttpStatus.CONFLICT, "The city name already exists");
        }

        return ciudadRepository.save(ciudad);
    }

    @Override
    public Ciudad update(Long id, Ciudad ciudad) {
        return ciudadRepository.findById(id)
                .map(existingCiudad -> {
                    existingCiudad.setNombre(ciudad.getNombre());
                    return ciudadRepository.save(existingCiudad);
                })
                .orElseThrow(() -> new RequestException("P-404", "La ciudad con ID " + id + " no existe"));
    }

    @Override
    public void deleteById(Long id) {

        if (!ciudadRepository.findById(id).isPresent()) {
            throw new BusinessException("P-404", HttpStatus.NOT_FOUND, "El Ciudad no existe");
        }

        ciudadRepository.deleteById(id);
    }
}
