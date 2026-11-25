package com.example.autoloc.web.controller;

import com.example.autoloc.service.IContratService;
import com.example.autoloc.web.dto.ContratDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/contrats")
@Tag(name = "Contrats")
public class ContratController {

    private final IContratService contratService;

    public ContratController(IContratService contratService) {
        this.contratService = contratService;
    }

    @PostMapping
    @Operation(summary = "Créer un contrat")
    public ResponseEntity<ContratDTO> creer(@Valid @RequestBody ContratDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(contratService.creer(dto));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtenir un contrat par id")
    public ResponseEntity<ContratDTO> findById(@PathVariable Long id) {
        return ResponseEntity.ok(contratService.findById(id));
    }

    @GetMapping
    @Operation(summary = "Lister tous les contrats")
    public ResponseEntity<List<ContratDTO>> findAll() {
        return ResponseEntity.ok(contratService.findAll());
    }

    @PutMapping("/{id}")
    @Operation(summary = "Modifier un contrat")
    public ResponseEntity<ContratDTO> update(@PathVariable Long id, @Valid @RequestBody ContratDTO dto) {
        return ResponseEntity.ok(contratService.update(id, dto));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Supprimer un contrat")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        contratService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
