package com.example.autoloc.service.observer;

import com.example.autoloc.domain.Reservation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class SmsNotificationObserver implements IReservationObserver {

    @Override
    public void onReservationConfirmee(Reservation reservation) {
        log.info("[SMS] Confirmation envoyée au {} pour reservation #{}",
                reservation.getClient().getTelephone(), reservation.getIdReservation());
    }

    @Override
    public void onReservationAnnulee(Reservation reservation) {
        log.info("[SMS] Annulation envoyée au {} pour reservation #{}",
                reservation.getClient().getTelephone(), reservation.getIdReservation());
    }
}
