package com.example.autoloc.web.controller;

import com.example.autoloc.domain.Equipement;
import com.example.autoloc.service.IEquipementService;
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
@RequestMapping("/equipements")
@RequiredArgsConstructor
@Tag(name = "Equipements", description = "Gestion des equipements")
public class EquipementController {

    private final IEquipementService equipementService;

    @GetMapping
    @Operation(summary = "Lister tous les equipements")
    @ApiResponses(@ApiResponse(responseCode = "200", description = "Liste retournee"))
    public List<Equipement> findAll() {
        return equipementService.findAll();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Consulter un equipement")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Equipement trouve"),
            @ApiResponse(responseCode = "404", description = "Equipement introuvable")
    })
    public Equipement findById(@Parameter(description = "Identifiant de l'equipement") @PathVariable Long id) {
        return equipementService.findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Creer un equipement")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Equipement cree"),
            @ApiResponse(responseCode = "400", description = "Donnees invalides")
    })
    public Equipement create(@RequestBody Equipement equipement) {
        return equipementService.create(equipement);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Modifier un equipement")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Equipement modifie"),
            @ApiResponse(responseCode = "404", description = "Equipement introuvable"),
            @ApiResponse(responseCode = "400", description = "Donnees invalides")
    })
    public Equipement update(@PathVariable Long id, @RequestBody Equipement equipement) {
        return equipementService.update(id, equipement);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "Supprimer un equipement")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Equipement supprime"),
            @ApiResponse(responseCode = "404", description = "Equipement introuvable")
    })
    public void deleteById(@PathVariable Long id) {
        equipementService.deleteById(id);
    }
}
