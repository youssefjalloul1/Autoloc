package com.example.autoloc.web.dto;

public record TopClientDTO(
        Long clientId,
        String nom,
        String prenom,
        Long nombreReservations
) {}
