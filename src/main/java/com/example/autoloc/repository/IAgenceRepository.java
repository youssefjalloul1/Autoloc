package com.example.autoloc.repository;

import com.example.autoloc.domain.Agence;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IAgenceRepository extends JpaRepository<Agence, Long> {

    // Ex 5 — Query Methods
    Optional<Agence> findByVille(String ville);

    Optional<Agence> findByNomContaining(String nom);
}