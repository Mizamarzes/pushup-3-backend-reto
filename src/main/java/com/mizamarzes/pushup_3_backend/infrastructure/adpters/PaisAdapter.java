package com.mizamarzes.pushup_3_backend.infrastructure.adpters;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.mizamarzes.pushup_3_backend.application.services.PaisService;
import com.mizamarzes.pushup_3_backend.domain.models.Pais;
import com.mizamarzes.pushup_3_backend.infrastructure.exception.dto.BusinessException;
import com.mizamarzes.pushup_3_backend.infrastructure.exception.dto.RequestException;
import com.mizamarzes.pushup_3_backend.infrastructure.repositories.PaisRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PaisAdapter implements PaisService {

    @Autowired
    private PaisRepository paisRepository;

    @Override
    public Optional<Pais> findById(Long id) {

        if (!paisRepository.findById(id).isPresent()) {
            throw new BusinessException("P-404", HttpStatus.NOT_FOUND, "El pais no existe");
        }

        return paisRepository.findById(id);
    }

    @Override
    public List<Pais> getAll() {
        List<Pais> paises = paisRepository.findAll();

        if (paises.isEmpty()) {
            throw new RequestException("P-404", "No hay paises registrados");
        }

        return paisRepository.findAll();
    }

    @Override
    public Pais save(Pais pais) {

        Optional<Pais> existingPais = paisRepository.findByNombre(pais.getNombre());

        if (existingPais.isPresent()) {
            throw new BusinessException("P-300", HttpStatus.CONFLICT, "The country name already exists");
        }

        return paisRepository.save(pais);
    }

    @Override
    public Pais update(Long id, Pais pais) {
        return paisRepository.findById(id)
                .map(existingPais -> {
                    existingPais.setNombre(pais.getNombre());
                    return paisRepository.save(existingPais);
                })
                .orElseThrow(() -> new RequestException("P-404", "El país con ID " + id + " no existe"));
    }

    @Override
    public void deleteById(Long id) {

        if (!paisRepository.findById(id).isPresent()) {
            throw new BusinessException("P-404", HttpStatus.NOT_FOUND, "El pais no existe");
        }

        paisRepository.deleteById(id);
    }
}
