package com.example.autoloc.service.strategy;

import com.example.autoloc.domain.Reservation;
import com.example.autoloc.domain.Vehicule;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.temporal.ChronoUnit;

@Component
public class TarifStandardStrategy implements ITarificationStrategy {

    @Override
    public double calculerTarif(Vehicule vehicule, Reservation reservation) {
        long jours = ChronoUnit.DAYS.between(reservation.getDateDebut(), reservation.getDateFin());
        return vehicule.getTarifJournalier().multiply(BigDecimal.valueOf(jours)).doubleValue();
    }

    @Override
    public String getCode() {
        return "STANDARD";
    }
}
