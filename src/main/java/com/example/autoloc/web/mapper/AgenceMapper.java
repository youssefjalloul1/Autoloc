package com.example.autoloc.web.mapper;

import com.example.autoloc.domain.Agence;
import com.example.autoloc.web.dto.AgenceDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface AgenceMapper {
    @Mapping(target = "id", source = "idAgence")
    AgenceDTO toDto(Agence agence);

    @Mapping(target = "idAgence", source = "id")
    @Mapping(target = "vehicules", ignore = true)
    @Mapping(target = "employes", ignore = true)
    Agence toEntity(AgenceDTO dto);
}
