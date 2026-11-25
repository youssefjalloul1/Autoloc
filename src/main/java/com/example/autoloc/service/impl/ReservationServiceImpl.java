package com.example.autoloc.service.impl;

import com.example.autoloc.domain.Client;
import com.example.autoloc.domain.Reservation;
import com.example.autoloc.domain.enums.StatutReservation;
import com.example.autoloc.exception.ResourceNotFoundException;
import com.example.autoloc.repository.IClientRepository;
import com.example.autoloc.repository.IReservationRepository;
import com.example.autoloc.repository.IVehiculeRepository;
import com.example.autoloc.service.IReservationService;
import com.example.autoloc.service.observer.ReservationSubject;
import com.example.autoloc.web.dto.ReservationRequestDTO;
import com.example.autoloc.web.dto.ReservationResponseDTO;
import com.example.autoloc.web.mapper.ReservationMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
@Transactional
public class ReservationServiceImpl implements IReservationService {

    private final IReservationRepository reservationRepository;
    private final IClientRepository clientRepository;
    private final IVehiculeRepository vehiculeRepository;
    private final ReservationMapper reservationMapper;
    private final ReservationSubject reservationSubject;

    public ReservationServiceImpl(IReservationRepository reservationRepository,
                                   IClientRepository clientRepository,
                                   IVehiculeRepository vehiculeRepository,
                                   ReservationMapper reservationMapper,
                                   ReservationSubject reservationSubject) {
        this.reservationRepository = reservationRepository;
        this.clientRepository = clientRepository;
        this.vehiculeRepository = vehiculeRepository;
        this.reservationMapper = reservationMapper;
        this.reservationSubject = reservationSubject;
    }

    @Override
    public ReservationResponseDTO creer(ReservationRequestDTO dto) {
        var client = clientRepository.findById(dto.getClientId())
                .orElseThrow(() -> new ResourceNotFoundException("Client", dto.getClientId()));
        var vehicule = vehiculeRepository.findById(dto.getVehiculeId())
                .orElseThrow(() -> new ResourceNotFoundException("Vehicule", dto.getVehiculeId()));
        Reservation reservation = new Reservation();
        reservation.setClient(client);
        reservation.setVehicule(vehicule);
        reservation.setDateDebut(dto.getDateDebut());
        reservation.setDateFin(dto.getDateFin());
        reservation.setStatut(StatutReservation.EN_ATTENTE);
        return reservationMapper.toDto(reservationRepository.save(reservation));
    }

    @Override
    @Transactional(readOnly = true)
    public ReservationResponseDTO findById(Long id) {
        return reservationMapper.toDto(reservationRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Reservation", id)));
    }

    @Override
    @Transactional(readOnly = true)
    public List<ReservationResponseDTO> findAll() {
        return reservationRepository.findAll().stream().map(reservationMapper::toDto).toList();
    }

    @Override
    public void deleteById(Long id) {
        reservationRepository.deleteById(id);
    }

    // Observer demo: creates in-memory reservation and notifies
    @Override
    public Reservation creerReservation(Client client) {
        Reservation reservation = Reservation.builder()
                .client(client)
                .dateDebut(LocalDate.now())
                .dateFin(LocalDate.now().plusDays(3))
                .statut(StatutReservation.CONFIRMEE)
                .build();
        reservationSubject.notifierConfirmation(reservation);
        return reservation;
    }

    // Observer demo: sets statut ANNULEE and notifies
    @Override
    public void annulerReservation(Reservation reservation) {
        reservation.setStatut(StatutReservation.ANNULEE);
        reservationSubject.notifierAnnulation(reservation);
    }
}
