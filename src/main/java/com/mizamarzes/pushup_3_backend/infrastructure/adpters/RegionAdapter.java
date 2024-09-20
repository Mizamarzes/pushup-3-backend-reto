package com.mizamarzes.pushup_3_backend.infrastructure.adpters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.mizamarzes.pushup_3_backend.application.services.RegionService;
import com.mizamarzes.pushup_3_backend.domain.models.Region;
import com.mizamarzes.pushup_3_backend.infrastructure.exception.dto.BusinessException;
import com.mizamarzes.pushup_3_backend.infrastructure.exception.dto.RequestException;
import com.mizamarzes.pushup_3_backend.infrastructure.repositories.RegionRepository;

import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RegionAdapter implements RegionService{

    @Autowired
    private RegionRepository regionRepository;

    @Override
    public Optional<Region> findById(Long id) {

        if (!regionRepository.findById(id).isPresent()) {
            throw new BusinessException("P-404", HttpStatus.NOT_FOUND, "la Region no existe");
        }

        return regionRepository.findById(id);
    }

    @Override
    public List<Region> getAll() {
        List<Region> regions = regionRepository.findAll();

        if (regions.isEmpty()) {
            throw new RequestException("P-404", "No hay Regiones registradas");
        }

        return regionRepository.findAll();
    }

    @Override
    public Region save(Region region) {

        Optional<Region> existingRegion = regionRepository.findByNombre(region.getNombre());

        if (existingRegion.isPresent()) {
            throw new BusinessException("P-300", HttpStatus.CONFLICT, "The region name already exists");
        }

        return regionRepository.save(region);
    }

    @Override
    public Region update(Long id, Region region) {
        return regionRepository.findById(id)
                .map(existingRegion -> {
                    existingRegion.setNombre(region.getNombre());
                    return regionRepository.save(existingRegion);
                })
                .orElseThrow(() -> new RequestException("P-404", "La region con ID " + id + " no existe"));
    }

    @Override
    public void deleteById(Long id) {

        if (!regionRepository.findById(id).isPresent()) {
            throw new BusinessException("P-404", HttpStatus.NOT_FOUND, "La Region no existe");
        }

        regionRepository.deleteById(id);
    }
}
