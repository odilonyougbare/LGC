package com.lgs.service.impl;

import com.lgs.service.AgenceService;
import com.lgs.domain.Agence;
import com.lgs.repository.AgenceRepository;
import com.lgs.service.dto.AgenceDTO;
import com.lgs.service.mapper.AgenceMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing {@link Agence}.
 */
@Service
@Transactional
public class AgenceServiceImpl implements AgenceService {

    private final Logger log = LoggerFactory.getLogger(AgenceServiceImpl.class);

    private final AgenceRepository agenceRepository;

    private final AgenceMapper agenceMapper;

    public AgenceServiceImpl(AgenceRepository agenceRepository, AgenceMapper agenceMapper) {
        this.agenceRepository = agenceRepository;
        this.agenceMapper = agenceMapper;
    }

    @Override
    public AgenceDTO save(AgenceDTO agenceDTO) {
        log.debug("Request to save Agence : {}", agenceDTO);
        Agence agence = agenceMapper.toEntity(agenceDTO);
        agence = agenceRepository.save(agence);
        return agenceMapper.toDto(agence);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<AgenceDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Agences");
        return agenceRepository.findAll(pageable)
            .map(agenceMapper::toDto);
    }


    @Override
    @Transactional(readOnly = true)
    public Optional<AgenceDTO> findOne(Long id) {
        log.debug("Request to get Agence : {}", id);
        return agenceRepository.findById(id)
            .map(agenceMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete Agence : {}", id);
        agenceRepository.deleteById(id);
    }

}
