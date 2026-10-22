package com.example.autoloc.web.controller;

import com.example.autoloc.domain.Paiement;
import com.example.autoloc.service.IPaiementService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/paiements")
@RequiredArgsConstructor
@Tag(name = "Paiements", description = "Consultation des paiements (lecture seule)")
public class PaiementController {

    private final IPaiementService paiementService;

    @GetMapping
    @Operation(summary = "Lister tous les paiements")
    @ApiResponses(@ApiResponse(responseCode = "200", description = "Liste retournee"))
    public List<Paiement> findAll() {
        return paiementService.findAll();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Consulter un paiement")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Paiement trouve"),
            @ApiResponse(responseCode = "404", description = "Paiement introuvable")
    })
    public Paiement findById(@Parameter(description = "Identifiant du paiement") @PathVariable Long id) {
        return paiementService.findById(id);
    }
}
