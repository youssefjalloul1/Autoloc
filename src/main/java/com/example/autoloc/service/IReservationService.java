package com.example.autoloc.service;

import com.example.autoloc.domain.Client;
import com.example.autoloc.domain.Reservation;
import com.example.autoloc.web.dto.ReservationRequestDTO;
import com.example.autoloc.web.dto.ReservationResponseDTO;
import java.util.List;

public interface IReservationService {
    ReservationResponseDTO creer(ReservationRequestDTO dto);
    ReservationResponseDTO findById(Long id);
    List<ReservationResponseDTO> findAll();
    void deleteById(Long id);
    // Observer demo methods
    Reservation creerReservation(Client client);
    void annulerReservation(Reservation reservation);
}
