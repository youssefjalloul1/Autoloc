package com.example.autoloc;

import com.example.autoloc.domain.Client;
import com.example.autoloc.domain.Reservation;
import com.example.autoloc.service.IReservationService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ReservationObserverTest {

    @Autowired
    private IReservationService reservationService;

    @Test
    void testConfirmationNotification() {
        Client client = new Client();
        client.setNom("Test");
        client.setPrenom("Client");
        client.setEmail("test@test.com");
        client.setTelephone("0601020304");

        Reservation reservation = reservationService.creerReservation(client);

        assertNotNull(reservation);
        // Logs [EMAIL] and [SMS] should appear in console
    }

    @Test
    void testAnnulationNotification() {
        Client client = new Client();
        client.setNom("Dupont");
        client.setPrenom("Marie");
        client.setEmail("marie@test.com");
        client.setTelephone("0612345678");

        Reservation reservation = reservationService.creerReservation(client);
        reservationService.annulerReservation(reservation);

        // Logs [EMAIL] and [SMS] annulation should appear in console
        assertEquals(com.example.autoloc.domain.enums.StatutReservation.ANNULEE, reservation.getStatut());
    }
}
