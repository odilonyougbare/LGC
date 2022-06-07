package com.lgs.service.mapper;


import com.lgs.domain.*;
import com.lgs.service.dto.ClientDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Client} and its DTO {@link ClientDTO}.
 */
@Mapper(componentModel = "spring", uses = {UserMapper.class})
public interface ClientMapper extends EntityMapper<ClientDTO, Client> {

    @Mapping(source = "user.id", target = "userId")
    @Mapping(source = "user.login", target = "userLogin")
    ClientDTO toDto(Client client);

    @Mapping(target = "agences", ignore = true)
    @Mapping(target = "removeAgence", ignore = true)
    @Mapping(source = "userId", target = "user")
    Client toEntity(ClientDTO clientDTO);

    default Client fromId(Long id) {
        if (id == null) {
            return null;
        }
        Client client = new Client();
        client.setId(id);
        return client;
    }
}
