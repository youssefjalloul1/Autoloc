package com.example.autoloc;

import com.example.autoloc.domain.Reservation;
import com.example.autoloc.service.factory.TarificationStrategyFactory;
import com.example.autoloc.service.strategy.ITarificationStrategy;
import com.example.autoloc.service.strategy.TarifLongueDureeStrategy;
import com.example.autoloc.service.strategy.TarifStandardStrategy;
import com.example.autoloc.service.strategy.TarifWeekendStrategy;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TarificationStrategyFactoryTest {

    private TarificationStrategyFactory factory;

    @BeforeEach
    void setUp() {
        List<ITarificationStrategy> strategies = List.of(
                new TarifStandardStrategy(),
                new TarifWeekendStrategy(),
                new TarifLongueDureeStrategy()
        );
        factory = new TarificationStrategyFactory(strategies);
    }

    @Test
    void testLongueDuree_7Jours() {
        // Wednesday to Wednesday+7 = 7 days => LONGUE_DUREE
        Reservation r = new Reservation();
        r.setDateDebut(LocalDate.of(2024, 3, 6)); // Wednesday
        r.setDateFin(LocalDate.of(2024, 3, 13));
        ITarificationStrategy strategy = factory.resoudre(r);
        assertEquals("LONGUE_DUREE", strategy.getCode());
    }

    @Test
    void testLongueDuree_ExactSept() {
        // 7 days exactly => LONGUE_DUREE
        Reservation r = new Reservation();
        r.setDateDebut(LocalDate.of(2024, 3, 1));
        r.setDateFin(LocalDate.of(2024, 3, 8));
        ITarificationStrategy strategy = factory.resoudre(r);
        assertEquals("LONGUE_DUREE", strategy.getCode());
    }

    @Test
    void testWeekend_2JoursVendredi() {
        // 2 days starting Friday => WEEKEND
        Reservation r = new Reservation();
        r.setDateDebut(LocalDate.of(2024, 3, 8)); // Friday
        r.setDateFin(LocalDate.of(2024, 3, 10));
        ITarificationStrategy strategy = factory.resoudre(r);
        assertEquals("WEEKEND", strategy.getCode());
    }

    @Test
    void testStandard_4JoursLundi() {
        // 4 days starting Monday => STANDARD
        Reservation r = new Reservation();
        r.setDateDebut(LocalDate.of(2024, 3, 4)); // Monday
        r.setDateFin(LocalDate.of(2024, 3, 8));
        ITarificationStrategy strategy = factory.resoudre(r);
        assertEquals("STANDARD", strategy.getCode());
    }
}
