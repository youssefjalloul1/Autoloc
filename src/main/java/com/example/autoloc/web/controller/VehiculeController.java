package com.example.autoloc.web.controller;

import com.example.autoloc.domain.Vehicule;
import com.example.autoloc.service.IVehiculeService;
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
@RequestMapping("/vehicules")
@RequiredArgsConstructor
@Tag(name = "Vehicules", description = "Gestion des vehicules")
public class VehiculeController {

    private final IVehiculeService vehiculeService;

    @GetMapping
    @Operation(summary = "Lister tous les vehicules")
    @ApiResponses(@ApiResponse(responseCode = "200", description = "Liste retournee"))
    public List<Vehicule> findAll() {
        return vehiculeService.findAll();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Consulter un vehicule")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Vehicule trouve"),
            @ApiResponse(responseCode = "404", description = "Vehicule introuvable")
    })
    public Vehicule findById(@Parameter(description = "Identifiant du vehicule") @PathVariable Long id) {
        return vehiculeService.findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Creer un vehicule")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Vehicule cree"),
            @ApiResponse(responseCode = "400", description = "Donnees invalides")
    })
    public Vehicule create(@RequestBody Vehicule vehicule) {
        return vehiculeService.create(vehicule);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Modifier un vehicule")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Vehicule modifie"),
            @ApiResponse(responseCode = "404", description = "Vehicule introuvable"),
            @ApiResponse(responseCode = "400", description = "Donnees invalides")
    })
    public Vehicule update(@PathVariable Long id, @RequestBody Vehicule vehicule) {
        return vehiculeService.update(id, vehicule);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "Supprimer un vehicule")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Vehicule supprime"),
            @ApiResponse(responseCode = "404", description = "Vehicule introuvable")
    })
    public void deleteById(@PathVariable Long id) {
        vehiculeService.deleteById(id);
    }
}
