package com.example.autoloc.web.controller;

import com.example.autoloc.service.IPaiementService;
import com.example.autoloc.web.dto.PaiementDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/paiements")
@Tag(name = "Paiements")
public class PaiementController {

    private final IPaiementService paiementService;

    public PaiementController(IPaiementService paiementService) {
        this.paiementService = paiementService;
    }

    @PostMapping
    @Operation(summary = "Créer un paiement")
    public ResponseEntity<PaiementDTO> creer(@Valid @RequestBody PaiementDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(paiementService.creer(dto));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtenir un paiement par id")
    public ResponseEntity<PaiementDTO> findById(@PathVariable Long id) {
        return ResponseEntity.ok(paiementService.findById(id));
    }

    @GetMapping
    @Operation(summary = "Lister tous les paiements")
    public ResponseEntity<List<PaiementDTO>> findAll() {
        return ResponseEntity.ok(paiementService.findAll());
    }

    @PutMapping("/{id}")
    @Operation(summary = "Modifier un paiement")
    public ResponseEntity<PaiementDTO> update(@PathVariable Long id, @Valid @RequestBody PaiementDTO dto) {
        return ResponseEntity.ok(paiementService.update(id, dto));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Supprimer un paiement")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        paiementService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
