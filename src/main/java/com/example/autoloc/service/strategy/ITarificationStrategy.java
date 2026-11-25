package com.example.autoloc.service.strategy;

import com.example.autoloc.domain.Reservation;
import com.example.autoloc.domain.Vehicule;

public interface ITarificationStrategy {
    double calculerTarif(Vehicule vehicule, Reservation reservation);
    String getCode();
}
