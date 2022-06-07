package com.lgs.service.mapper;


import com.lgs.domain.*;
import com.lgs.service.dto.AgenceDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Agence} and its DTO {@link AgenceDTO}.
 */
@Mapper(componentModel = "spring", uses = {UserMapper.class, ClientMapper.class})
public interface AgenceMapper extends EntityMapper<AgenceDTO, Agence> {

    @Mapping(source = "user.id", target = "userId")
    @Mapping(source = "user.login", target = "userLogin")
    @Mapping(source = "client.id", target = "clientId")
    @Mapping(source = "client.denomination", target = "clientDenomination")
    AgenceDTO toDto(Agence agence);

    @Mapping(source = "userId", target = "user")
    @Mapping(source = "clientId", target = "client")
    Agence toEntity(AgenceDTO agenceDTO);

    default Agence fromId(Long id) {
        if (id == null) {
            return null;
        }
        Agence agence = new Agence();
        agence.setId(id);
        return agence;
    }
}
