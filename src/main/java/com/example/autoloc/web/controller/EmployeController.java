package com.example.autoloc.web.controller;

import com.example.autoloc.domain.Employe;
import com.example.autoloc.service.IEmployeService;
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
@RequestMapping("/employes")
@RequiredArgsConstructor
@Tag(name = "Employes", description = "Gestion des employes")
public class EmployeController {

    private final IEmployeService employeService;

    @GetMapping
    @Operation(summary = "Lister tous les employes")
    @ApiResponses(@ApiResponse(responseCode = "200", description = "Liste retournee"))
    public List<Employe> findAll() {
        return employeService.findAll();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Consulter un employe")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Employe trouve"),
            @ApiResponse(responseCode = "404", description = "Employe introuvable")
    })
    public Employe findById(@Parameter(description = "Identifiant de l'employe") @PathVariable Long id) {
        return employeService.findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Creer un employe")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Employe cree"),
            @ApiResponse(responseCode = "400", description = "Donnees invalides")
    })
    public Employe create(@RequestBody Employe employe) {
        return employeService.create(employe);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Modifier un employe")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Employe modifie"),
            @ApiResponse(responseCode = "404", description = "Employe introuvable"),
            @ApiResponse(responseCode = "400", description = "Donnees invalides")
    })
    public Employe update(@PathVariable Long id, @RequestBody Employe employe) {
        return employeService.update(id, employe);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "Supprimer un employe")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Employe supprime"),
            @ApiResponse(responseCode = "404", description = "Employe introuvable")
    })
    public void deleteById(@PathVariable Long id) {
        employeService.deleteById(id);
    }
}
