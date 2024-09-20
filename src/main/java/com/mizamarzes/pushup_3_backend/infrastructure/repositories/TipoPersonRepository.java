package com.mizamarzes.pushup_3_backend.infrastructure.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mizamarzes.pushup_3_backend.domain.models.TipoPersona;

public interface TipoPersonRepository extends JpaRepository<TipoPersona, Long> {
    Optional<TipoPersona> findByTipo(String tipo);
}
