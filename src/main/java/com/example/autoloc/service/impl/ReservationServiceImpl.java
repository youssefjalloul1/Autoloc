package com.example.autoloc.service.impl;

import com.example.autoloc.domain.Reservation;
import com.example.autoloc.exception.ResourceNotFoundException;
import com.example.autoloc.repository.IReservationRepository;
import com.example.autoloc.service.IReservationService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReservationServiceImpl implements IReservationService {

    private final IReservationRepository reservationRepository;

    public ReservationServiceImpl(IReservationRepository reservationRepository) {
        this.reservationRepository = reservationRepository;
    }

    @Override
    public Reservation create(Reservation reservation) {
        return reservationRepository.save(reservation);
    }

    @Override
    public Reservation findById(Long id) {
        return reservationRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Reservation", id));
    }

    @Override
    public List<Reservation> findAll() {
        return reservationRepository.findAll();
    }

    @Override
    public Reservation update(Long id, Reservation reservation) {
        Reservation existing = findById(id);
        existing.setDateDebut(reservation.getDateDebut());
        existing.setDateFin(reservation.getDateFin());
        existing.setStatut(reservation.getStatut());
        existing.setClient(reservation.getClient());
        existing.setVehicule(reservation.getVehicule());
        return reservationRepository.save(existing);
    }

    @Override
    public void deleteById(Long id) {
        reservationRepository.deleteById(id);
    }

    @Override
    public long count() {
        return reservationRepository.count();
    }

    @Override
    public boolean existsById(Long id) {
        return reservationRepository.existsById(id);
    }
}
