package com.lgs.service.impl;

import com.lgs.repository.AgenceRepository;
import com.lgs.service.ClientService;
import com.lgs.domain.Client;
import com.lgs.repository.ClientRepository;
import com.lgs.service.dto.AgenceDTO;
import com.lgs.service.dto.ClientAndListAgenceDTO;
import com.lgs.service.dto.ClientDTO;
import com.lgs.service.mapper.AgenceMapper;
import com.lgs.service.mapper.ClientAndListAgencesMapper;
import com.lgs.service.mapper.ClientMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing {@link Client}.
 */
@Service
@Transactional
public class ClientServiceImpl implements ClientService {

    private final Logger log = LoggerFactory.getLogger(ClientServiceImpl.class);

    private final ClientRepository clientRepository;

    private final ClientMapper clientMapper;
    @Autowired
    ClientAndListAgencesMapper clientAndListAgencesMapper;
    @Autowired
    AgenceRepository agenceRepository;
    @Autowired
    AgenceMapper agenceMapper;

    public ClientServiceImpl(ClientRepository clientRepository, ClientMapper clientMapper) {
        this.clientRepository = clientRepository;
        this.clientMapper = clientMapper;
    }

    @Override
    public ClientDTO save(ClientDTO clientDTO) {
        log.debug("Request to save Client : {}", clientDTO);
        Client client = clientMapper.toEntity(clientDTO);
        client = clientRepository.save(client);
        return clientMapper.toDto(client);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<ClientDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Clients");
        return clientRepository.findAll(pageable)
            .map(clientMapper::toDto);
    }


    @Override
    @Transactional(readOnly = true)
    public Optional<ClientDTO> findOne(Long id) {
        log.debug("Request to get Client : {}", id);
        return clientRepository.findById(id)
            .map(clientMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete Client : {}", id);
        clientRepository.deleteById(id);
    }

    @Override
    public List<ClientAndListAgenceDTO> clientAndListAgences() {
        List<ClientAndListAgenceDTO> clientAndListAgenceDTOS = clientRepository.findAll()
            .stream()
            .map(clientAndListAgencesMapper::toDto).collect(Collectors.toList());
        clientAndListAgenceDTOS.forEach(clientAndListAgenceDTO -> {
            List<AgenceDTO> agenceDTOS = agenceRepository.findAgencesByClientId(clientAndListAgenceDTO.getId())
                .stream()
                .map(agenceMapper::toDto).collect(Collectors.toList());
            clientAndListAgenceDTO.setAgences(agenceDTOS);
        });
        return clientAndListAgenceDTOS;

    }

    @Override
    public List<AgenceDTO> findAgenceByClientId(Long id) {
        return agenceRepository.findAgencesByClientId(id)
            .stream()
            .map(agenceMapper::toDto).collect(Collectors.toList());
    }
}
