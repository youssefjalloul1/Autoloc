package com.example.autoloc.web.controller;

import com.example.autoloc.service.IContratService;
import com.example.autoloc.service.IReservationService;
import com.example.autoloc.web.dto.ChiffreAffaireAgenceDTO;
import com.example.autoloc.web.dto.ReservationRequestDTO;
import com.example.autoloc.web.dto.ReservationResponseDTO;
import com.example.autoloc.web.dto.TopClientDTO;
import com.example.autoloc.web.validator.ReservationRequestValidator;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/reservations")
@Tag(name = "Reservations")
public class ReservationController {

    private final IReservationService reservationService;
    private final IContratService contratService;
    private final ReservationRequestValidator reservationRequestValidator;

    public ReservationController(IReservationService reservationService,
                                  IContratService contratService,
                                  ReservationRequestValidator reservationRequestValidator) {
        this.reservationService = reservationService;
        this.contratService = contratService;
        this.reservationRequestValidator = reservationRequestValidator;
    }

    @PostMapping
    @Operation(summary = "Créer une réservation")
    public ResponseEntity<?> creer(@Valid @RequestBody ReservationRequestDTO dto, BindingResult result) {
        reservationRequestValidator.validate(dto, result);
        if (result.hasErrors()) {
            return ResponseEntity.badRequest().body(
                    result.getFieldErrors().stream()
                            .map(error -> error.getField() + " : " + error.getDefaultMessage())
                            .toList());
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(reservationService.creer(dto));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtenir une réservation par id")
    public ResponseEntity<ReservationResponseDTO> findById(@PathVariable Long id) {
        return ResponseEntity.ok(reservationService.findById(id));
    }

    @GetMapping
    @Operation(summary = "Lister toutes les réservations")
    public ResponseEntity<List<ReservationResponseDTO>> findAll() {
        return ResponseEntity.ok(reservationService.findAll());
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Supprimer une réservation")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        reservationService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/top-clients")
    @Operation(summary = "Top clients par nombre de réservations")
    public ResponseEntity<List<TopClientDTO>> topClients(
            @RequestParam(defaultValue = "5") int limit) {
        return ResponseEntity.ok(reservationService.topClients(PageRequest.of(0, limit)));
    }

    @GetMapping("/chiffre-affaires-agences")
    @Operation(summary = "Chiffre d'affaires par agence")
    public ResponseEntity<List<ChiffreAffaireAgenceDTO>> chiffreAffaires() {
        return ResponseEntity.ok(contratService.chiffreAffaireParAgence());
    }
}
