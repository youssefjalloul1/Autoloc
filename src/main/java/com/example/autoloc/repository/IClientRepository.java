package com.example.autoloc.repository;

import com.example.autoloc.domain.Client;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface IClientRepository extends JpaRepository<Client, Long> {

    // Ex 2 — Query Methods
    Optional<Client> findByEmail(String email);

    Optional<Client> findByNumPermis(String numPermis);

    List<Client> findByNomContaining(String nom);

    boolean existsByEmail(String email);
}