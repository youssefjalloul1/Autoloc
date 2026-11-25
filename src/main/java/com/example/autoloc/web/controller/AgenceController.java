package com.example.autoloc.web.controller;

import com.example.autoloc.service.IAgenceService;
import com.example.autoloc.web.dto.AgenceDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/agences")
@Tag(name = "Agences")
public class AgenceController {

    private final IAgenceService agenceService;

    public AgenceController(IAgenceService agenceService) {
        this.agenceService = agenceService;
    }

    @PostMapping
    @Operation(summary = "Créer une agence")
    public ResponseEntity<AgenceDTO> creer(@Valid @RequestBody AgenceDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(agenceService.creer(dto));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtenir une agence par id")
    public ResponseEntity<AgenceDTO> findById(@PathVariable Long id) {
        return ResponseEntity.ok(agenceService.findById(id));
    }

    @GetMapping
    @Operation(summary = "Lister toutes les agences")
    public ResponseEntity<List<AgenceDTO>> findAll() {
        return ResponseEntity.ok(agenceService.findAll());
    }

    @PutMapping("/{id}")
    @Operation(summary = "Modifier une agence")
    public ResponseEntity<AgenceDTO> update(@PathVariable Long id, @Valid @RequestBody AgenceDTO dto) {
        return ResponseEntity.ok(agenceService.update(id, dto));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Supprimer une agence")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        agenceService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
