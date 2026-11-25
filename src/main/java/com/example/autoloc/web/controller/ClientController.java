package com.example.autoloc.web.controller;

import com.example.autoloc.service.IClientService;
import com.example.autoloc.web.dto.ClientDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/clients")
@Tag(name = "Clients")
public class ClientController {

    private final IClientService clientService;

    public ClientController(IClientService clientService) {
        this.clientService = clientService;
    }

    @PostMapping
    @Operation(summary = "Créer un client")
    public ResponseEntity<ClientDTO> creer(@Valid @RequestBody ClientDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(clientService.creer(dto));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtenir un client par id")
    public ResponseEntity<ClientDTO> findById(@PathVariable Long id) {
        return ResponseEntity.ok(clientService.findById(id));
    }

    @GetMapping
    @Operation(summary = "Lister tous les clients")
    public ResponseEntity<List<ClientDTO>> findAll() {
        return ResponseEntity.ok(clientService.findAll());
    }

    @PutMapping("/{id}")
    @Operation(summary = "Modifier un client")
    public ResponseEntity<ClientDTO> update(@PathVariable Long id, @Valid @RequestBody ClientDTO dto) {
        return ResponseEntity.ok(clientService.update(id, dto));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Supprimer un client")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        clientService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
