package com.example.autoloc.web.controller;

import com.example.autoloc.domain.Agence;
import com.example.autoloc.service.IAgenceService;
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
@RequestMapping("/agences")
@RequiredArgsConstructor
@Tag(name = "Agences", description = "Gestion des agences")
public class AgenceController {

    private final IAgenceService agenceService;

    @GetMapping
    @Operation(summary = "Lister toutes les agences")
    @ApiResponses(@ApiResponse(responseCode = "200", description = "Liste retournee"))
    public List<Agence> findAll() {
        return agenceService.findAll();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Consulter une agence")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Agence trouvee"),
            @ApiResponse(responseCode = "404", description = "Agence introuvable")
    })
    public Agence findById(@Parameter(description = "Identifiant de l'agence") @PathVariable Long id) {
        return agenceService.findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Creer une agence")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Agence creee"),
            @ApiResponse(responseCode = "400", description = "Donnees invalides")
    })
    public Agence create(@RequestBody Agence agence) {
        return agenceService.create(agence);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Modifier une agence")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Agence modifiee"),
            @ApiResponse(responseCode = "404", description = "Agence introuvable"),
            @ApiResponse(responseCode = "400", description = "Donnees invalides")
    })
    public Agence update(@PathVariable Long id, @RequestBody Agence agence) {
        return agenceService.update(id, agence);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "Supprimer une agence")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Agence supprimee"),
            @ApiResponse(responseCode = "404", description = "Agence introuvable")
    })
    public void deleteById(@PathVariable Long id) {
        agenceService.deleteById(id);
    }
}
