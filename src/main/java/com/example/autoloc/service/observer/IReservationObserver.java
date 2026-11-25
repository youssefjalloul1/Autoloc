package com.example.autoloc.service.observer;

import com.example.autoloc.domain.Reservation;

public interface IReservationObserver {
    void onReservationConfirmee(Reservation reservation);
    void onReservationAnnulee(Reservation reservation);
}
