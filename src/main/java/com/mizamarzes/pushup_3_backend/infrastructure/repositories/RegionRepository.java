package com.mizamarzes.pushup_3_backend.infrastructure.repositories;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

import com.mizamarzes.pushup_3_backend.domain.models.Region;

public interface RegionRepository extends JpaRepository<Region, Long> {
    Optional<Region> findByNombre(String nombre);

}
