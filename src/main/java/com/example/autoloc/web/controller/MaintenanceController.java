package com.example.autoloc.web.controller;

import com.example.autoloc.domain.Maintenance;
import com.example.autoloc.service.IMaintenanceService;
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
@RequestMapping("/maintenances")
@RequiredArgsConstructor
@Tag(name = "Maintenances", description = "Gestion des maintenances de vehicules")
public class MaintenanceController {

    private final IMaintenanceService maintenanceService;

    @GetMapping
    @Operation(summary = "Lister toutes les maintenances")
    @ApiResponses(@ApiResponse(responseCode = "200", description = "Liste retournee"))
    public List<Maintenance> findAll() {
        return maintenanceService.findAll();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Consulter une maintenance")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Maintenance trouvee"),
            @ApiResponse(responseCode = "404", description = "Maintenance introuvable")
    })
    public Maintenance findById(@Parameter(description = "Identifiant de la maintenance") @PathVariable Long id) {
        return maintenanceService.findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Creer une maintenance")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Maintenance creee"),
            @ApiResponse(responseCode = "400", description = "Donnees invalides")
    })
    public Maintenance create(@RequestBody Maintenance maintenance) {
        return maintenanceService.create(maintenance);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Modifier une maintenance")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Maintenance modifiee"),
            @ApiResponse(responseCode = "404", description = "Maintenance introuvable"),
            @ApiResponse(responseCode = "400", description = "Donnees invalides")
    })
    public Maintenance update(@PathVariable Long id, @RequestBody Maintenance maintenance) {
        return maintenanceService.update(id, maintenance);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "Supprimer une maintenance")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Maintenance supprimee"),
            @ApiResponse(responseCode = "404", description = "Maintenance introuvable")
    })
    public void deleteById(@PathVariable Long id) {
        maintenanceService.deleteById(id);
    }
}
