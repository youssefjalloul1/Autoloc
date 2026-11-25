package com.example.autoloc.web.dto;

import java.math.BigDecimal;

public record ChiffreAffaireAgenceDTO(
        Long agenceId,
        String nomAgence,
        BigDecimal chiffreAffaires
) {}
