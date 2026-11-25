package com.example.autoloc.web.dto;

import com.example.autoloc.domain.enums.StatutReservation;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class ReservationResponseDTO {
    private Long id;
    private LocalDate dateDebut;
    private LocalDate dateFin;
    private StatutReservation statut;
    private Long clientId;
    private String clientNomComplet;
    private Long vehiculeId;
    private String vehiculeImmatriculation;
    private BigDecimal tarifEstime;
}
