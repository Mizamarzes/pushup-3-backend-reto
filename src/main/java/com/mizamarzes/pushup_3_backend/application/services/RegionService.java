package com.mizamarzes.pushup_3_backend.application.services;

import java.util.List;
import java.util.Optional;

import com.mizamarzes.pushup_3_backend.domain.models.Region;

public interface RegionService {

    Optional<Region> findById(Long id);

    List<Region> getAll();

    Region save(Region region);

    Region update(Long id, Region region);

    void deleteById(Long id);
}
