package com.mizamarzes.pushup_3_backend.infrastructure.repositories;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

import com.mizamarzes.pushup_3_backend.domain.models.Ciudad;

public interface CiudadRepository extends JpaRepository<Ciudad, Long>{
    Optional<Ciudad> findByNombre(String nombre);
}
