package com.example.autoloc.web.controller;

import com.example.autoloc.domain.Reservation;
import com.example.autoloc.service.IReservationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/reservations")
@RequiredArgsConstructor
@Tag(name = "Reservations", description = "Gestion des reservations")
public class ReservationController {

    private final IReservationService reservationService;

    @GetMapping
    @Operation(summary = "Lister toutes les reservations")
    @ApiResponses(@ApiResponse(responseCode = "200", description = "Liste retournee"))
    public List<Reservation> findAll() {
        return reservationService.findAll();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Consulter une reservation")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Reservation trouvee"),
            @ApiResponse(responseCode = "404", description = "Reservation introuvable")
    })
    public Reservation findById(@Parameter(description = "Identifiant de la reservation") @PathVariable Long id) {
        return reservationService.findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Creer une reservation")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Reservation creee"),
            @ApiResponse(responseCode = "400", description = "Donnees invalides")
    })
    public Reservation create(@RequestBody Reservation reservation) {
        return reservationService.create(reservation);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Modifier une reservation")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Reservation modifiee"),
            @ApiResponse(responseCode = "404", description = "Reservation introuvable"),
            @ApiResponse(responseCode = "400", description = "Donnees invalides")
    })
    public Reservation update(@PathVariable Long id, @RequestBody Reservation reservation) {
        return reservationService.update(id, reservation);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "Supprimer une reservation")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Reservation supprimee"),
            @ApiResponse(responseCode = "404", description = "Reservation introuvable")
    })
    public void deleteById(@PathVariable Long id) {
        reservationService.deleteById(id);
    }
}
