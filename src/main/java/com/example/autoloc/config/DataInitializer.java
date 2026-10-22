package com.example.autoloc.config;

import com.example.autoloc.domain.*;
import com.example.autoloc.domain.enums.*;
import com.example.autoloc.service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDate;

@Component
@RequiredArgsConstructor
public class DataInitializer implements CommandLineRunner {

    private final IAgenceService agenceService;
    private final IClientService clientService;
    private final IVehiculeService vehiculeService;
    private final IEmployeService employeService;
    private final IEquipementService equipementService;
    private final IReservationService reservationService;
    private final IContratService contratService;
    private final IMaintenanceService maintenanceService;

    @Override
    public void run(String... args) {

        if (!agenceService.findAll().isEmpty()) {
            System.out.println("[DataInitializer] Data already exists, skipping.");
            return;
        }

        System.out.println("[DataInitializer] Inserting sample data...");

        // --- Agences ---
        Agence a1 = new Agence();
        a1.setNom("AutoLoc Tunis");
        a1.setVille("Tunis");
        a1.setAdresse("Avenue Habib Bourguiba, Tunis");
        a1.setTelephone("71000001");
        a1 = agenceService.create(a1);

        Agence a2 = new Agence();
        a2.setNom("AutoLoc Sfax");
        a2.setVille("Sfax");
        a2.setAdresse("Rue de la Republique, Sfax");
        a2.setTelephone("74000002");
        a2 = agenceService.create(a2);

        // --- Employes ---
        Employe e1 = new Employe();
        e1.setNom("Hamdi");
        e1.setPrenom("Karim");
        e1.setRole(RoleEmploye.MANAGER);
        e1.setAgence(a1);
        employeService.create(e1);

        Employe e2 = new Employe();
        e2.setNom("Jlassi");
        e2.setPrenom("Nour");
        e2.setRole(RoleEmploye.AGENT);
        e2.setAgence(a1);
        employeService.create(e2);

        Employe e3 = new Employe();
        e3.setNom("Trabelsi");
        e3.setPrenom("Bilel");
        e3.setRole(RoleEmploye.AGENT);
        e3.setAgence(a2);
        employeService.create(e3);

        // --- Vehicules ---
        Vehicule v1 = new Vehicule();
        v1.setImmatriculation("100 TUN 1000");
        v1.setMarque("Renault");
        v1.setModele("Clio");
        v1.setCategorie(CategorieVehicule.CITADINE);
        v1.setTarifJournalier(new BigDecimal("85.00"));
        v1.setStatut(StatutVehicule.DISPONIBLE);
        v1.setAgence(a1);
        v1 = vehiculeService.create(v1);

        Vehicule v2 = new Vehicule();
        v2.setImmatriculation("200 TUN 2000");
        v2.setMarque("Peugeot");
        v2.setModele("508");
        v2.setCategorie(CategorieVehicule.BERLINE);
        v2.setTarifJournalier(new BigDecimal("130.00"));
        v2.setStatut(StatutVehicule.DISPONIBLE);
        v2.setAgence(a1);
        v2 = vehiculeService.create(v2);

        Vehicule v3 = new Vehicule();
        v3.setImmatriculation("300 TUN 3000");
        v3.setMarque("Toyota");
        v3.setModele("RAV4");
        v3.setCategorie(CategorieVehicule.SUV);
        v3.setTarifJournalier(new BigDecimal("170.00"));
        v3.setStatut(StatutVehicule.DISPONIBLE);
        v3.setAgence(a2);
        v3 = vehiculeService.create(v3);

        Vehicule v4 = new Vehicule();
        v4.setImmatriculation("400 TUN 4000");
        v4.setMarque("Ford");
        v4.setModele("Transit");
        v4.setCategorie(CategorieVehicule.UTILITAIRE);
        v4.setTarifJournalier(new BigDecimal("200.00"));
        v4.setStatut(StatutVehicule.MAINTENANCE);
        v4.setAgence(a2);
        v4 = vehiculeService.create(v4);

        // --- Equipements ---
        Equipement eq1 = new Equipement();
        eq1.setLibelle("GPS");
        equipementService.create(eq1);

        Equipement eq2 = new Equipement();
        eq2.setLibelle("Siege bebe");
        equipementService.create(eq2);

        Equipement eq3 = new Equipement();
        eq3.setLibelle("Wifi embarque");
        equipementService.create(eq3);

        // --- Clients ---
        Client c1 = new Client();
        c1.setNom("Ben Ali");
        c1.setPrenom("Sami");
        c1.setEmail("sami.benali@example.com");
        c1.setTelephone("20111222");
        c1.setNumPermis("P100001");
        c1.setDateInscription(LocalDate.of(2025, 1, 10));
        c1 = clientService.create(c1);

        Client c2 = new Client();
        c2.setNom("Mansouri");
        c2.setPrenom("Amel");
        c2.setEmail("amel.mansouri@example.com");
        c2.setTelephone("55222333");
        c2.setNumPermis("P100002");
        c2.setDateInscription(LocalDate.of(2025, 3, 20));
        c2 = clientService.create(c2);

        Client c3 = new Client();
        c3.setNom("Gharbi");
        c3.setPrenom("Yassine");
        c3.setEmail("yassine.gharbi@example.com");
        c3.setTelephone("98765432");
        c3.setNumPermis("P100003");
        c3.setDateInscription(LocalDate.of(2025, 6, 5));
        c3 = clientService.create(c3);

        // --- Maintenances ---
        Maintenance m1 = new Maintenance();
        m1.setDateDebut(LocalDate.of(2026, 10, 15));
        m1.setDateFin(LocalDate.of(2026, 10, 20));
        m1.setDescription("Vidange et revision generale");
        m1.setVehicule(v4);
        maintenanceService.create(m1);

        // --- Reservations ---
        Reservation r1 = new Reservation();
        r1.setDateDebut(LocalDate.of(2026, 11, 1));
        r1.setDateFin(LocalDate.of(2026, 11, 5));
        r1.setStatut(StatutReservation.CONFIRMEE);
        r1.setClient(c1);
        r1.setVehicule(v1);
        r1 = reservationService.create(r1);

        Reservation r2 = new Reservation();
        r2.setDateDebut(LocalDate.of(2026, 11, 10));
        r2.setDateFin(LocalDate.of(2026, 11, 15));
        r2.setStatut(StatutReservation.EN_ATTENTE);
        r2.setClient(c2);
        r2.setVehicule(v2);
        r2 = reservationService.create(r2);

        Reservation r3 = new Reservation();
        r3.setDateDebut(LocalDate.of(2026, 10, 1));
        r3.setDateFin(LocalDate.of(2026, 10, 8));
        r3.setStatut(StatutReservation.TERMINEE);
        r3.setClient(c3);
        r3.setVehicule(v3);
        r3 = reservationService.create(r3);

        // --- Contrats avec paiements en cascade ---
        Contrat contrat1 = new Contrat();
        contrat1.setDateSignature(LocalDate.of(2026, 11, 1));
        contrat1.setMontantTotal(new BigDecimal("340.00"));
        contrat1.setValide(true);
        contrat1.setReservation(r1);

        Paiement p1 = new Paiement();
        p1.setMontant(new BigDecimal("200.00"));
        p1.setDatePaiement(LocalDate.of(2026, 11, 1));
        p1.setModePaiement(ModePaiement.CARTE);
        p1.setContrat(contrat1);

        Paiement p2 = new Paiement();
        p2.setMontant(new BigDecimal("140.00"));
        p2.setDatePaiement(LocalDate.of(2026, 11, 5));
        p2.setModePaiement(ModePaiement.ESPECES);
        p2.setContrat(contrat1);

        contrat1.setPaiements(java.util.List.of(p1, p2));
        contratService.create(contrat1);

        Contrat contrat2 = new Contrat();
        contrat2.setDateSignature(LocalDate.of(2026, 10, 1));
        contrat2.setMontantTotal(new BigDecimal("1190.00"));
        contrat2.setValide(true);
        contrat2.setReservation(r3);

        Paiement p3 = new Paiement();
        p3.setMontant(new BigDecimal("1190.00"));
        p3.setDatePaiement(LocalDate.of(2026, 10, 1));
        p3.setModePaiement(ModePaiement.VIREMENT);
        p3.setContrat(contrat2);

        contrat2.setPaiements(java.util.List.of(p3));
        contratService.create(contrat2);

        System.out.println("[DataInitializer] Sample data inserted successfully.");
    }
}
