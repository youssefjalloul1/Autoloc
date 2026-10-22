package com.example.autoloc.web.controller;

import com.example.autoloc.domain.Client;
import com.example.autoloc.service.IClientService;
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
@RequestMapping("/clients")
@RequiredArgsConstructor
@Tag(name = "Clients", description = "Gestion des clients")
public class ClientController {

    private final IClientService clientService;

    @GetMapping
    @Operation(summary = "Lister tous les clients")
    @ApiResponses(@ApiResponse(responseCode = "200", description = "Liste retournee"))
    public List<Client> findAll() {
        return clientService.findAll();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Consulter un client")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Client trouve"),
            @ApiResponse(responseCode = "404", description = "Client introuvable")
    })
    public Client findById(@Parameter(description = "Identifiant du client") @PathVariable Long id) {
        return clientService.findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Creer un client")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Client cree"),
            @ApiResponse(responseCode = "400", description = "Donnees invalides")
    })
    public Client create(@RequestBody Client client) {
        return clientService.create(client);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Modifier un client")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Client modifie"),
            @ApiResponse(responseCode = "404", description = "Client introuvable"),
            @ApiResponse(responseCode = "400", description = "Donnees invalides")
    })
    public Client update(@PathVariable Long id, @RequestBody Client client) {
        return clientService.update(id, client);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "Supprimer un client")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Client supprime"),
            @ApiResponse(responseCode = "404", description = "Client introuvable")
    })
    public void deleteById(@PathVariable Long id) {
        clientService.deleteById(id);
    }
}
