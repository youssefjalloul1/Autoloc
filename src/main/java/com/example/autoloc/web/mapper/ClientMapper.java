package com.example.autoloc.web.mapper;

import com.example.autoloc.domain.Client;
import com.example.autoloc.web.dto.ClientDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ClientMapper {
    @Mapping(target = "id", source = "idClient")
    ClientDTO toDto(Client client);

    @Mapping(target = "idClient", source = "id")
    @Mapping(target = "reservations", ignore = true)
    Client toEntity(ClientDTO dto);
}
