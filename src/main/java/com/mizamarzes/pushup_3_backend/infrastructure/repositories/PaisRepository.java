package com.mizamarzes.pushup_3_backend.infrastructure.repositories;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

import com.mizamarzes.pushup_3_backend.domain.models.Pais;

public interface PaisRepository extends JpaRepository<Pais, Long>{
    Optional<Pais> findByNombre(String nombre);
}
