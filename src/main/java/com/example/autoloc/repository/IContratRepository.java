package com.example.autoloc.repository;

import com.example.autoloc.domain.Contrat;
import com.example.autoloc.web.dto.ChiffreAffaireAgenceDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface IContratRepository extends JpaRepository<Contrat, Long> {

    // Ex 9 — Chiffre d'affaires par agence (contrats valides)
    @Query("SELECT new com.example.autoloc.web.dto.ChiffreAffaireAgenceDTO(" +
           "c.reservation.vehicule.agence.idAgence, " +
           "c.reservation.vehicule.agence.nom, " +
           "SUM(c.montantTotal)) " +
           "FROM Contrat c " +
           "WHERE c.valide = true " +
           "GROUP BY c.reservation.vehicule.agence.idAgence, c.reservation.vehicule.agence.nom")
    List<ChiffreAffaireAgenceDTO> chiffreAffaireParAgence();

    // Ex 9 — Contrats dont la réservation se termine dans l'intervalle donné
    @Query("SELECT c FROM Contrat c " +
           "WHERE c.reservation.dateFin BETWEEN :aujourdhui AND :dateLimite")
    List<Contrat> trouverContratsAEcheanceProche(
            @Param("aujourdhui") LocalDate aujourdhui,
            @Param("dateLimite") LocalDate dateLimite);
}