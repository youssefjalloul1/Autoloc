package com.example.autoloc.web.controller;

import com.example.autoloc.domain.Employe;
import com.example.autoloc.service.IEmployeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employes")
@Tag(name = "Employes")
public class EmployeController {

    private final IEmployeService employeService;

    public EmployeController(IEmployeService employeService) {
        this.employeService = employeService;
    }

    @PostMapping
    @Operation(summary = "Créer un employé")
    public ResponseEntity<Employe> create(@RequestBody Employe employe) {
        return ResponseEntity.status(HttpStatus.CREATED).body(employeService.create(employe));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtenir un employé par id")
    public ResponseEntity<Employe> findById(@PathVariable Long id) {
        return ResponseEntity.ok(employeService.findById(id));
    }

    @GetMapping
    @Operation(summary = "Lister tous les employés")
    public ResponseEntity<List<Employe>> findAll() {
        return ResponseEntity.ok(employeService.findAll());
    }

    @PutMapping("/{id}")
    @Operation(summary = "Modifier un employé")
    public ResponseEntity<Employe> update(@PathVariable Long id, @RequestBody Employe employe) {
        return ResponseEntity.ok(employeService.update(id, employe));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Supprimer un employé")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        employeService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{employeId}/affecter/{agenceId}")
    @Operation(summary = "Affecter un employé à une agence")
    public ResponseEntity<Void> affecter(@PathVariable Long employeId, @PathVariable Long agenceId) {
        employeService.affecterEmployeAAgence(employeId, agenceId);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{employeId}/desaffecter")
    @Operation(summary = "Désaffecter un employé de son agence")
    public ResponseEntity<Void> desaffecter(@PathVariable Long employeId) {
        employeService.desaffecterEmployeDeAgence(employeId);
        return ResponseEntity.noContent().build();
    }
}
