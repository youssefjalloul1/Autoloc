package com.example.autoloc.repository;

import com.example.autoloc.domain.Vehicule;
import com.example.autoloc.domain.enums.CategorieVehicule;
import com.example.autoloc.domain.enums.StatutVehicule;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public interface IVehiculeRepository extends JpaRepository<Vehicule, Long> {

    // Ex 1 — Query Methods
    List<Vehicule> findByAgenceVille(String ville);

    List<Vehicule> findByStatutAndAgence_IdAgence(StatutVehicule statut, Long agenceId);

    Page<Vehicule> findByStatut(StatutVehicule statut, Pageable pageable);

    List<Vehicule> findByAgence_VilleAndCategorie(String ville, CategorieVehicule categorie);

    boolean existsByIdVehiculeAndStatut(Long vehiculeId, StatutVehicule statut);

    long countByStatut(StatutVehicule statut);

    List<Vehicule> findByTarifJournalierLessThan(BigDecimal tarif);

    List<Vehicule> findByTarifJournalierGreaterThan(BigDecimal tarif);

    List<Vehicule> findAllByOrderByTarifJournalierAsc();

    // Ex 7 — JPQL
    @Query("SELECT v FROM Vehicule v " +
           "WHERE v.agence.ville = :ville " +
           "AND v.categorie = :categorie " +
           "AND v.statut = com.example.autoloc.domain.enums.StatutVehicule.DISPONIBLE " +
           "AND v.idVehicule NOT IN (" +
           "  SELECT r.vehicule.idVehicule FROM Reservation r " +
           "  WHERE r.statut IN (com.example.autoloc.domain.enums.StatutReservation.EN_ATTENTE, " +
           "                     com.example.autoloc.domain.enums.StatutReservation.CONFIRMEE) " +
           "  AND r.dateDebut < :dateFin AND r.dateFin > :dateDebut" +
           ")")
    List<Vehicule> rechercherVehiculesDisponibles(
            @Param("ville") String ville,
            @Param("categorie") CategorieVehicule categorie,
            @Param("dateDebut") LocalDate dateDebut,
            @Param("dateFin") LocalDate dateFin);

    @Query("SELECT v FROM Vehicule v " +
           "WHERE EXISTS (" +
           "  SELECT r FROM Reservation r " +
           "  WHERE r.vehicule = v " +
           "  AND r.statut = com.example.autoloc.domain.enums.StatutReservation.CONFIRMEE " +
           "  AND r.dateFin = :aujourdhui" +
           ")")
    List<Vehicule> trouverVehiculesAReleaser(@Param("aujourdhui") LocalDate aujourdhui);
}