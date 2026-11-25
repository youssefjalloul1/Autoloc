package com.example.autoloc.web.controller;

import com.example.autoloc.service.IVehiculeService;
import com.example.autoloc.web.dto.VehiculeDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/vehicules")
@Tag(name = "Vehicules")
public class VehiculeController {

    private final IVehiculeService vehiculeService;

    public VehiculeController(IVehiculeService vehiculeService) {
        this.vehiculeService = vehiculeService;
    }

    @PostMapping
    @Operation(summary = "Créer un véhicule")
    public ResponseEntity<VehiculeDTO> creer(@Valid @RequestBody VehiculeDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(vehiculeService.creer(dto));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtenir un véhicule par id")
    public ResponseEntity<VehiculeDTO> findById(@PathVariable Long id) {
        return ResponseEntity.ok(vehiculeService.findById(id));
    }

    @GetMapping
    @Operation(summary = "Lister tous les véhicules")
    public ResponseEntity<List<VehiculeDTO>> findAll() {
        return ResponseEntity.ok(vehiculeService.findAll());
    }

    @PutMapping("/{id}")
    @Operation(summary = "Modifier un véhicule")
    public ResponseEntity<VehiculeDTO> update(@PathVariable Long id, @Valid @RequestBody VehiculeDTO dto) {
        return ResponseEntity.ok(vehiculeService.update(id, dto));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Supprimer un véhicule")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        vehiculeService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{vehiculeId}/affecter/{agenceId}")
    @Operation(summary = "Affecter un véhicule à une agence")
    public ResponseEntity<Void> affecter(@PathVariable Long vehiculeId, @PathVariable Long agenceId) {
        vehiculeService.affecterVehiculeAAgence(vehiculeId, agenceId);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{vehiculeId}/desaffecter")
    @Operation(summary = "Désaffecter un véhicule de son agence")
    public ResponseEntity<Void> desaffecter(@PathVariable Long vehiculeId) {
        vehiculeService.desaffecterVehiculeDeAgence(vehiculeId);
        return ResponseEntity.noContent().build();
    }
}
