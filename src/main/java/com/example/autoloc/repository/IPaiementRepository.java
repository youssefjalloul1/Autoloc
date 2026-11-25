package com.example.autoloc.repository;

import com.example.autoloc.domain.Paiement;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface IPaiementRepository extends JpaRepository<Paiement, Long> {

    // Ex 6 — Query Methods
    List<Paiement> findByContrat_IdContrat(Long contratId);

    List<Paiement> findByDatePaiementBetween(LocalDate debut, LocalDate fin);
}