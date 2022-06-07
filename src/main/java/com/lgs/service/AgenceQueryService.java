package com.lgs.service;

import java.util.List;

import javax.persistence.criteria.JoinType;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import io.github.jhipster.service.QueryService;

import com.lgs.domain.Agence;
import com.lgs.domain.*; // for static metamodels
import com.lgs.repository.AgenceRepository;
import com.lgs.service.dto.AgenceCriteria;
import com.lgs.service.dto.AgenceDTO;
import com.lgs.service.mapper.AgenceMapper;

/**
 * Service for executing complex queries for {@link Agence} entities in the database.
 * The main input is a {@link AgenceCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link AgenceDTO} or a {@link Page} of {@link AgenceDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class AgenceQueryService extends QueryService<Agence> {

    private final Logger log = LoggerFactory.getLogger(AgenceQueryService.class);

    private final AgenceRepository agenceRepository;

    private final AgenceMapper agenceMapper;

    public AgenceQueryService(AgenceRepository agenceRepository, AgenceMapper agenceMapper) {
        this.agenceRepository = agenceRepository;
        this.agenceMapper = agenceMapper;
    }

    /**
     * Return a {@link List} of {@link AgenceDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<AgenceDTO> findByCriteria(AgenceCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<Agence> specification = createSpecification(criteria);
        return agenceMapper.toDto(agenceRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link AgenceDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<AgenceDTO> findByCriteria(AgenceCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<Agence> specification = createSpecification(criteria);
        return agenceRepository.findAll(specification, page)
            .map(agenceMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(AgenceCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<Agence> specification = createSpecification(criteria);
        return agenceRepository.count(specification);
    }

    /**
     * Function to convert {@link AgenceCriteria} to a {@link Specification}
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching {@link Specification} of the entity.
     */
    protected Specification<Agence> createSpecification(AgenceCriteria criteria) {
        Specification<Agence> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getId(), Agence_.id));
            }
            if (criteria.getCodeAgence() != null) {
                specification = specification.and(buildStringSpecification(criteria.getCodeAgence(), Agence_.codeAgence));
            }
            if (criteria.getDenominationAgence() != null) {
                specification = specification.and(buildStringSpecification(criteria.getDenominationAgence(), Agence_.denominationAgence));
            }
            if (criteria.getTypeAgence() != null) {
                specification = specification.and(buildStringSpecification(criteria.getTypeAgence(), Agence_.typeAgence));
            }
            if (criteria.getTelephone() != null) {
                specification = specification.and(buildStringSpecification(criteria.getTelephone(), Agence_.telephone));
            }
            if (criteria.getNumeroWhatsapp() != null) {
                specification = specification.and(buildStringSpecification(criteria.getNumeroWhatsapp(), Agence_.numeroWhatsapp));
            }
            if (criteria.getEmail() != null) {
                specification = specification.and(buildStringSpecification(criteria.getEmail(), Agence_.email));
            }
            if (criteria.getAutreContact() != null) {
                specification = specification.and(buildStringSpecification(criteria.getAutreContact(), Agence_.autreContact));
            }
            if (criteria.getQuartier() != null) {
                specification = specification.and(buildStringSpecification(criteria.getQuartier(), Agence_.quartier));
            }
            if (criteria.getArrondissement() != null) {
                specification = specification.and(buildStringSpecification(criteria.getArrondissement(), Agence_.arrondissement));
            }
            if (criteria.getCommune() != null) {
                specification = specification.and(buildStringSpecification(criteria.getCommune(), Agence_.commune));
            }
            if (criteria.getProvince() != null) {
                specification = specification.and(buildStringSpecification(criteria.getProvince(), Agence_.province));
            }
            if (criteria.getRegion() != null) {
                specification = specification.and(buildStringSpecification(criteria.getRegion(), Agence_.region));
            }
            if (criteria.getUserId() != null) {
                specification = specification.and(buildSpecification(criteria.getUserId(),
                    root -> root.join(Agence_.user, JoinType.LEFT).get(User_.id)));
            }
            if (criteria.getClientId() != null) {
                specification = specification.and(buildSpecification(criteria.getClientId(),
                    root -> root.join(Agence_.client, JoinType.LEFT).get(Client_.id)));
            }
        }
        return specification;
    }
}
