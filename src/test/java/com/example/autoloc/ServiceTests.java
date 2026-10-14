package com.example.autoloc;

import com.example.autoloc.domain.*;
import com.example.autoloc.domain.enums.*;
import com.example.autoloc.service.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ServiceTests {

    // ---------------------------------------------------------------
    // Injection des services
    // ---------------------------------------------------------------
    @Autowired
    private IAgenceService agenceService;

    @Autowired
    private IClientService clientService;

    @Autowired
    private IVehiculeService vehiculeService;

    @Autowired
    private IEmployeService employeService;

    @Autowired
    private IReservationService reservationService;

    @Autowired
    private IContratService contratService;

    // ---------------------------------------------------------------
    // Partie 2 – Test guidé de AgenceService
    // ---------------------------------------------------------------
    @Test
    void testAgenceService() {
        System.out.println("===== TEST AGENCE SERVICE =====");

        // Créer et enregistrer une agence
        Agence agence = new Agence();
        agence.setNom("AutoLoc Tunis");
        agence.setVille("Tunis");
        agence.setAdresse("Avenue Habib Bourguiba");
        agence.setTelephone("71000000");

        Agence saved = agenceService.create(agence);
        System.out.println("Agence enregistrée : id=" + saved.getIdAgence()
                + ", nom=" + saved.getNom() + ", ville=" + saved.getVille());
        assertNotNull(saved.getIdAgence());

        // findAll
        List<Agence> agences = agenceService.findAll();
        System.out.println("Toutes les agences (" + agences.size() + ") :");
        agences.forEach(a -> System.out.println("  -> " + a.getNom() + " / " + a.getVille()));
        assertFalse(agences.isEmpty());

        // findById
        Agence trouvee = agenceService.findById(saved.getIdAgence());
        System.out.println("Agence retrouvée par id : " + trouvee.getNom());
        assertNotNull(trouvee);
        assertEquals("AutoLoc Tunis", trouvee.getNom());

        // count
        long total = agenceService.count();
        System.out.println("Nombre total d'agences : " + total);
        assertTrue(total >= 1);

        // deleteById + vérification
        agenceService.deleteById(saved.getIdAgence());
        boolean existe = agenceService.existsById(saved.getIdAgence());
        assertFalse(existe);
        System.out.println("Agence supprimée, existsById = " + existe);
    }

    // ---------------------------------------------------------------
    // Partie 3 – Test guidé de ClientService
    // ---------------------------------------------------------------
    @Test
    void testClientService() {
        System.out.println("===== TEST CLIENT SERVICE =====");

        // Créer deux clients
        Client c1 = new Client();
        c1.setNom("Ben Ali");
        c1.setPrenom("Sami");
        c1.setEmail("sami.benali@example.com");
        c1.setTelephone("20111222");
        c1.setNumPermis("P123456");
        c1.setDateInscription(LocalDate.now());

        Client c2 = new Client();
        c2.setNom("Trabelsi");
        c2.setPrenom("Amel");
        c2.setEmail("amel.trabelsi@example.com");
        c2.setTelephone("55333444");
        c2.setNumPermis("P654321");
        c2.setDateInscription(LocalDate.now());

        Client saved1 = clientService.create(c1);
        Client saved2 = clientService.create(c2);
        System.out.println("Client 1 enregistré : id=" + saved1.getIdClient()
                + ", " + saved1.getNom() + " " + saved1.getPrenom());
        System.out.println("Client 2 enregistré : id=" + saved2.getIdClient()
                + ", " + saved2.getNom() + " " + saved2.getPrenom());
        assertNotNull(saved1.getIdClient());
        assertNotNull(saved2.getIdClient());

        // findAll
        List<Client> clients = clientService.findAll();
        System.out.println("Tous les clients (" + clients.size() + ") :");
        clients.forEach(c -> System.out.println("  -> " + c.getNom() + " " + c.getPrenom()));
        assertTrue(clients.size() >= 2);

        // findById
        Client trouve = clientService.findById(saved1.getIdClient());
        System.out.println("Client retrouvé par id : " + trouve.getNom() + " " + trouve.getPrenom());
        assertNotNull(trouve);
        assertEquals("Ben Ali", trouve.getNom());

        // count
        long total = clientService.count();
        System.out.println("Nombre total de clients : " + total);
        assertTrue(total >= 2);

        // deleteById + vérification
        clientService.deleteById(saved1.getIdClient());
        clientService.deleteById(saved2.getIdClient());
        assertFalse(clientService.existsById(saved1.getIdClient()));
        assertFalse(clientService.existsById(saved2.getIdClient()));
        System.out.println("Clients supprimés, existsById = false");
    }

    // ---------------------------------------------------------------
    // Partie 4 – Test guidé de VehiculeService
    // ---------------------------------------------------------------
    @Test
    void testVehiculeService() {
        System.out.println("===== TEST VEHICULE SERVICE =====");

        // Créer un véhicule
        Vehicule v = new Vehicule();
        v.setImmatriculation("123 TUN 4567");
        v.setMarque("Renault");
        v.setModele("Clio");
        v.setCategorie(CategorieVehicule.CITADINE);
        v.setTarifJournalier(new BigDecimal("100.00"));
        v.setStatut(StatutVehicule.DISPONIBLE);

        Vehicule saved = vehiculeService.create(v);
        System.out.println("Véhicule enregistré : id=" + saved.getIdVehicule()
                + ", " + saved.getMarque() + " " + saved.getModele()
                + ", tarif=" + saved.getTarifJournalier()
                + ", statut=" + saved.getStatut());
        assertNotNull(saved.getIdVehicule());

        // findAll
        List<Vehicule> vehicules = vehiculeService.findAll();
        System.out.println("Tous les véhicules (" + vehicules.size() + ") :");
        vehicules.forEach(veh -> System.out.println("  -> " + veh.getMarque() + " " + veh.getModele()));
        assertFalse(vehicules.isEmpty());

        // findById
        Vehicule trouve = vehiculeService.findById(saved.getIdVehicule());
        System.out.println("Véhicule retrouvé par id : " + trouve.getMarque() + " " + trouve.getModele());
        assertNotNull(trouve);
        assertEquals("Renault", trouve.getMarque());

        // count
        long total = vehiculeService.count();
        System.out.println("Nombre total de véhicules : " + total);
        assertTrue(total >= 1);

        // deleteById + vérification
        vehiculeService.deleteById(saved.getIdVehicule());
        assertFalse(vehiculeService.existsById(saved.getIdVehicule()));
        System.out.println("Véhicule supprimé, existsById = false");
    }

    // ---------------------------------------------------------------
    // Partie 5 – Exercice autonome : EmployeService
    // ---------------------------------------------------------------
    @Test
    void testEmployeService() {
        System.out.println("===== TEST EMPLOYE SERVICE =====");

        // Créer une agence pour l'employé
        Agence agence = new Agence();
        agence.setNom("Agence Test Employe");
        agence.setVille("Sfax");
        agence.setAdresse("Rue de la Liberté");
        agence.setTelephone("74000000");
        Agence savedAgence = agenceService.create(agence);

        // Créer un employé
        Employe employe = new Employe();
        employe.setNom("Hamdi");
        employe.setPrenom("Karim");
        employe.setRole(RoleEmploye.AGENT);
        employe.setAgence(savedAgence);

        Employe saved = employeService.create(employe);
        System.out.println("Employé enregistré : id=" + saved.getIdEmploye()
                + ", " + saved.getNom() + " " + saved.getPrenom()
                + ", rôle=" + saved.getRole());
        assertNotNull(saved.getIdEmploye());

        // findAll
        List<Employe> employes = employeService.findAll();
        System.out.println("Tous les employés (" + employes.size() + ") :");
        employes.forEach(e -> System.out.println("  -> " + e.getNom() + " " + e.getPrenom() + " / " + e.getRole()));
        assertFalse(employes.isEmpty());

        // findById
        Employe trouve = employeService.findById(saved.getIdEmploye());
        assertNotNull(trouve);
        assertEquals("Hamdi", trouve.getNom());
        System.out.println("Employé retrouvé par id : " + trouve.getNom() + " " + trouve.getPrenom());

        // existsById
        assertTrue(employeService.existsById(saved.getIdEmploye()));
        assertFalse(employeService.existsById(999999L));
        System.out.println("existsById(existant)=true, existsById(999999)=false");

        // deleteById + vérification
        employeService.deleteById(saved.getIdEmploye());
        assertFalse(employeService.existsById(saved.getIdEmploye()));
        System.out.println("Employé supprimé, existsById = false");

        // Nettoyage
        agenceService.deleteById(savedAgence.getIdAgence());
    }

    // ---------------------------------------------------------------
    // Partie 5 – Exercice autonome : ReservationService
    // ---------------------------------------------------------------
    @Test
    void testReservationService() {
        System.out.println("===== TEST RESERVATION SERVICE =====");

        // Préparer un client et un véhicule
        Client client = new Client();
        client.setNom("Mrad");
        client.setPrenom("Leila");
        client.setEmail("leila.mrad@example.com");
        client.setTelephone("98765432");
        client.setNumPermis("P999000");
        client.setDateInscription(LocalDate.now());
        Client savedClient = clientService.create(client);

        Vehicule vehicule = new Vehicule();
        vehicule.setImmatriculation("999 TUN 0001");
        vehicule.setMarque("Peugeot");
        vehicule.setModele("208");
        vehicule.setCategorie(CategorieVehicule.CITADINE);
        vehicule.setTarifJournalier(new BigDecimal("90.00"));
        vehicule.setStatut(StatutVehicule.DISPONIBLE);
        Vehicule savedVehicule = vehiculeService.create(vehicule);

        // Créer une réservation
        Reservation reservation = new Reservation();
        reservation.setDateDebut(LocalDate.now());
        reservation.setDateFin(LocalDate.now().plusDays(3));
        reservation.setStatut(StatutReservation.EN_ATTENTE);
        reservation.setClient(savedClient);
        reservation.setVehicule(savedVehicule);

        Reservation saved = reservationService.create(reservation);
        System.out.println("Réservation enregistrée : id=" + saved.getIdReservation()
                + ", du " + saved.getDateDebut() + " au " + saved.getDateFin()
                + ", statut=" + saved.getStatut());
        assertNotNull(saved.getIdReservation());

        // findAll
        List<Reservation> reservations = reservationService.findAll();
        System.out.println("Toutes les réservations (" + reservations.size() + ")");
        assertFalse(reservations.isEmpty());

        // findById
        Reservation trouvee = reservationService.findById(saved.getIdReservation());
        assertNotNull(trouvee);
        assertEquals(StatutReservation.EN_ATTENTE, trouvee.getStatut());
        System.out.println("Réservation retrouvée par id, statut : " + trouvee.getStatut());

        // existsById
        assertTrue(reservationService.existsById(saved.getIdReservation()));
        System.out.println("existsById = true");

        // deleteById + vérification
        reservationService.deleteById(saved.getIdReservation());
        assertFalse(reservationService.existsById(saved.getIdReservation()));
        System.out.println("Réservation supprimée, existsById = false");

        // Nettoyage
        vehiculeService.deleteById(savedVehicule.getIdVehicule());
        clientService.deleteById(savedClient.getIdClient());
    }

    // ---------------------------------------------------------------
    // Partie 5 – Exercice autonome : ContratService
    // ---------------------------------------------------------------
    @Test
    void testContratService() {
        System.out.println("===== TEST CONTRAT SERVICE =====");

        // Créer un contrat (sans réservation pour simplifier le test)
        Contrat contrat = new Contrat();
        contrat.setDateSignature(LocalDate.now());
        contrat.setMontantTotal(new BigDecimal("450.00"));
        contrat.setValide(true);

        Contrat saved = contratService.create(contrat);
        System.out.println("Contrat enregistré : id=" + saved.getIdContrat()
                + ", montant=" + saved.getMontantTotal()
                + ", valide=" + saved.isValide());
        assertNotNull(saved.getIdContrat());

        // findAll
        List<Contrat> contrats = contratService.findAll();
        System.out.println("Tous les contrats (" + contrats.size() + ")");
        assertFalse(contrats.isEmpty());

        // findById
        Contrat trouve = contratService.findById(saved.getIdContrat());
        assertNotNull(trouve);
        assertTrue(trouve.isValide());
        assertEquals(0, new BigDecimal("450.00").compareTo(trouve.getMontantTotal()));
        System.out.println("Contrat retrouvé par id : montant=" + trouve.getMontantTotal());

        // count
        long total = contratService.count();
        System.out.println("Nombre total de contrats : " + total);
        assertTrue(total >= 1);

        // existsById
        assertTrue(contratService.existsById(saved.getIdContrat()));
        assertFalse(contratService.existsById(999999L));
        System.out.println("existsById(existant)=true, existsById(999999)=false");

        // deleteById + vérification
        contratService.deleteById(saved.getIdContrat());
        assertFalse(contratService.existsById(saved.getIdContrat()));
        System.out.println("Contrat supprimé, existsById = false");
    }
}
