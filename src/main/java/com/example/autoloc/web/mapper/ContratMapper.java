package com.example.autoloc.web.mapper;

import com.example.autoloc.domain.Contrat;
import com.example.autoloc.web.dto.ContratDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ContratMapper {
    @Mapping(target = "id", source = "idContrat")
    @Mapping(target = "reservationId", source = "reservation.idReservation")
    ContratDTO toDto(Contrat contrat);

    @Mapping(target = "idContrat", source = "id")
    @Mapping(target = "reservation", ignore = true)
    @Mapping(target = "paiements", ignore = true)
    Contrat toEntity(ContratDTO dto);
}
