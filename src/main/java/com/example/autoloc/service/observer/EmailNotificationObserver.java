package com.example.autoloc.service.observer;

import com.example.autoloc.domain.Reservation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class EmailNotificationObserver implements IReservationObserver {

    @Override
    public void onReservationConfirmee(Reservation reservation) {
        log.info("[EMAIL] Confirmation envoyée à {} pour reservation #{}",
                reservation.getClient().getEmail(), reservation.getIdReservation());
    }

    @Override
    public void onReservationAnnulee(Reservation reservation) {
        log.info("[EMAIL] Annulation envoyée à {} pour reservation #{}",
                reservation.getClient().getEmail(), reservation.getIdReservation());
    }
}
