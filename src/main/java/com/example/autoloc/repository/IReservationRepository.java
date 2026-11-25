package com.example.autoloc.repository;

import com.example.autoloc.domain.Reservation;
import com.example.autoloc.domain.enums.StatutReservation;
import com.example.autoloc.web.dto.TopClientDTO;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;

public interface IReservationRepository extends JpaRepository<Reservation, Long> {

    // Ex 3 — Query Methods
    List<Reservation> findByClientIdClient(Long idClient);

    List<Reservation> findByVehicule_IdVehiculeAndStatutNot(Long vehiculeId, StatutReservation statut);

    List<Reservation> findByStatut(StatutReservation statut);

    List<Reservation> findByDateDebutBetween(LocalDate debut, LocalDate fin);

    List<Reservation> findByDateFinAfter(LocalDate date);

    List<Reservation> findByStatutAndVehicule_Agence_IdAgence(StatutReservation statut, Long agenceId);

    boolean existsByVehicule_IdVehiculeAndStatut(Long vehiculeId, StatutReservation statut);

    // Ex 8 — Top 5 clients par nombre de réservations
    @Query("SELECT new com.example.autoloc.web.dto.TopClientDTO(" +
           "r.client.idClient, r.client.nom, r.client.prenom, COUNT(r)) " +
           "FROM Reservation r " +
           "GROUP BY r.client.idClient, r.client.nom, r.client.prenom " +
           "ORDER BY COUNT(r) DESC")
    List<TopClientDTO> topClients(Pageable pageable);
}