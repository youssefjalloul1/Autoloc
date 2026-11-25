package com.example.autoloc.service.factory;

import com.example.autoloc.domain.Reservation;
import com.example.autoloc.service.strategy.ITarificationStrategy;
import org.springframework.stereotype.Component;

import java.time.DayOfWeek;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Component
public class TarificationStrategyFactory {

    private final Map<String, ITarificationStrategy> strategies;

    public TarificationStrategyFactory(List<ITarificationStrategy> strategyList) {
        this.strategies = strategyList.stream()
                .collect(Collectors.toMap(ITarificationStrategy::getCode, Function.identity()));
    }

    // Returns the right strategy based on duration and day
    public ITarificationStrategy resoudre(Reservation reservation) {
        long jours = ChronoUnit.DAYS.between(reservation.getDateDebut(), reservation.getDateFin());
        if (jours >= 7) {
            return strategies.get("LONGUE_DUREE");
        }
        if (jours <= 3 && estWeekend(reservation)) {
            return strategies.get("WEEKEND");
        }
        return strategies.get("STANDARD");
    }

    private boolean estWeekend(Reservation reservation) {
        DayOfWeek jour = reservation.getDateDebut().getDayOfWeek();
        return jour == DayOfWeek.FRIDAY || jour == DayOfWeek.SATURDAY || jour == DayOfWeek.SUNDAY;
    }
}
