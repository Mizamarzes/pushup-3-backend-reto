package com.mizamarzes.pushup_3_backend.infrastructure.repositories;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

import com.mizamarzes.pushup_3_backend.domain.models.Persona;

public interface PersonRepository extends JpaRepository<Persona, Long> {
    Optional<Persona> findByUsername(String username);
}
