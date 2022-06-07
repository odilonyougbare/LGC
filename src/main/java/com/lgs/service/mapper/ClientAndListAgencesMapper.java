package com.lgs.service.mapper;

import com.lgs.domain.Client;
import com.lgs.service.dto.ClientAndListAgenceDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ClientAndListAgencesMapper extends EntityMapper<ClientAndListAgenceDTO, Client> {
    ClientAndListAgenceDTO toDto(Client client);
    Client toEntity(ClientAndListAgenceDTO clientAndListAgenceDTO);
    default Client fromId(Long id) {
        if (id == null) {
            return null;
        }
        Client client = new Client();
        client.setId(id);
        return client;
    }

}
