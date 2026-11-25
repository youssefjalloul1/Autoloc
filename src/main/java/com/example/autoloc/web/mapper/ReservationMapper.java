package com.example.autoloc.web.mapper;

import com.example.autoloc.domain.Reservation;
import com.example.autoloc.web.dto.ReservationResponseDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ReservationMapper {
    @Mapping(target = "id", source = "idReservation")
    @Mapping(target = "clientId", source = "client.idClient")
    @Mapping(target = "clientNomComplet", expression = "java(reservation.getClient().getPrenom() + \" \" + reservation.getClient().getNom())")
    @Mapping(target = "vehiculeId", source = "vehicule.idVehicule")
    @Mapping(target = "vehiculeImmatriculation", source = "vehicule.immatriculation")
    @Mapping(target = "tarifEstime", ignore = true)
    ReservationResponseDTO toDto(Reservation reservation);
}
