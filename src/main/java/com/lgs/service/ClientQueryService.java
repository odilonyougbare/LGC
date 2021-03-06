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

import com.lgs.domain.Client;
import com.lgs.domain.*; // for static metamodels
import com.lgs.repository.ClientRepository;
import com.lgs.service.dto.ClientCriteria;
import com.lgs.service.dto.ClientDTO;
import com.lgs.service.mapper.ClientMapper;

/**
 * Service for executing complex queries for {@link Client} entities in the database.
 * The main input is a {@link ClientCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link ClientDTO} or a {@link Page} of {@link ClientDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class ClientQueryService extends QueryService<Client> {

    private final Logger log = LoggerFactory.getLogger(ClientQueryService.class);

    private final ClientRepository clientRepository;

    private final ClientMapper clientMapper;

    public ClientQueryService(ClientRepository clientRepository, ClientMapper clientMapper) {
        this.clientRepository = clientRepository;
        this.clientMapper = clientMapper;
    }

    /**
     * Return a {@link List} of {@link ClientDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<ClientDTO> findByCriteria(ClientCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<Client> specification = createSpecification(criteria);
        return clientMapper.toDto(clientRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link ClientDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<ClientDTO> findByCriteria(ClientCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<Client> specification = createSpecification(criteria);
        return clientRepository.findAll(specification, page)
            .map(clientMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(ClientCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<Client> specification = createSpecification(criteria);
        return clientRepository.count(specification);
    }

    /**
     * Function to convert {@link ClientCriteria} to a {@link Specification}
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching {@link Specification} of the entity.
     */
    protected Specification<Client> createSpecification(ClientCriteria criteria) {
        Specification<Client> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getId(), Client_.id));
            }
            if (criteria.getCodeClient() != null) {
                specification = specification.and(buildStringSpecification(criteria.getCodeClient(), Client_.codeClient));
            }
            if (criteria.getDenomination() != null) {
                specification = specification.and(buildStringSpecification(criteria.getDenomination(), Client_.denomination));
            }
            if (criteria.getDomaineActivite() != null) {
                specification = specification.and(buildStringSpecification(criteria.getDomaineActivite(), Client_.domaineActivite));
            }
            if (criteria.getSiteWeb() != null) {
                specification = specification.and(buildStringSpecification(criteria.getSiteWeb(), Client_.siteWeb));
            }
            if (criteria.getCompteFaceBook() != null) {
                specification = specification.and(buildStringSpecification(criteria.getCompteFaceBook(), Client_.compteFaceBook));
            }
            if (criteria.getCompteTwitter() != null) {
                specification = specification.and(buildStringSpecification(criteria.getCompteTwitter(), Client_.compteTwitter));
            }
            if (criteria.getAgenceId() != null) {
                specification = specification.and(buildSpecification(criteria.getAgenceId(),
                    root -> root.join(Client_.agences, JoinType.LEFT).get(Agence_.id)));
            }
            if (criteria.getUserId() != null) {
                specification = specification.and(buildSpecification(criteria.getUserId(),
                    root -> root.join(Client_.user, JoinType.LEFT).get(User_.id)));
            }
        }
        return specification;
    }
}
