package com.example.autoloc.web.mapper;

import com.example.autoloc.domain.Paiement;
import com.example.autoloc.web.dto.PaiementDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface PaiementMapper {
    @Mapping(target = "id", source = "idPaiement")
    @Mapping(target = "contratId", source = "contrat.idContrat")
    PaiementDTO toDto(Paiement paiement);

    @Mapping(target = "idPaiement", source = "id")
    @Mapping(target = "contrat", ignore = true)
    Paiement toEntity(PaiementDTO dto);
}
