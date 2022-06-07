package com.lgs.web.rest;

import com.lgs.service.AgenceService;
import com.lgs.web.rest.errors.BadRequestAlertException;
import com.lgs.service.dto.AgenceDTO;
import com.lgs.service.dto.AgenceCriteria;
import com.lgs.service.AgenceQueryService;

import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.PaginationUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing {@link com.lgs.domain.Agence}.
 */
@RestController
@RequestMapping("/api")
public class AgenceResource {

    private final Logger log = LoggerFactory.getLogger(AgenceResource.class);

    private static final String ENTITY_NAME = "agence";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final AgenceService agenceService;

    private final AgenceQueryService agenceQueryService;

    public AgenceResource(AgenceService agenceService, AgenceQueryService agenceQueryService) {
        this.agenceService = agenceService;
        this.agenceQueryService = agenceQueryService;
    }

    /**
     * {@code POST  /agences} : Create a new agence.
     *
     * @param agenceDTO the agenceDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new agenceDTO, or with status {@code 400 (Bad Request)} if the agence has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/agences")
    public ResponseEntity<AgenceDTO> createAgence(@RequestBody AgenceDTO agenceDTO) throws URISyntaxException {
        log.debug("REST request to save Agence : {}", agenceDTO);
        if (agenceDTO.getId() != null) {
            throw new BadRequestAlertException("A new agence cannot already have an ID", ENTITY_NAME, "idexists");
        }
        AgenceDTO result = agenceService.save(agenceDTO);
        return ResponseEntity.created(new URI("/api/agences/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /agences} : Updates an existing agence.
     *
     * @param agenceDTO the agenceDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated agenceDTO,
     * or with status {@code 400 (Bad Request)} if the agenceDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the agenceDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/agences")
    public ResponseEntity<AgenceDTO> updateAgence(@RequestBody AgenceDTO agenceDTO) throws URISyntaxException {
        log.debug("REST request to update Agence : {}", agenceDTO);
        if (agenceDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        AgenceDTO result = agenceService.save(agenceDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, agenceDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /agences} : get all the agences.
     *
     * @param pageable the pagination information.
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of agences in body.
     */
    @GetMapping("/agences")
    public ResponseEntity<List<AgenceDTO>> getAllAgences(AgenceCriteria criteria, Pageable pageable) {
        log.debug("REST request to get Agences by criteria: {}", criteria);
        Page<AgenceDTO> page = agenceQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /agences/count} : count all the agences.
     *
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
     */
    @GetMapping("/agences/count")
    public ResponseEntity<Long> countAgences(AgenceCriteria criteria) {
        log.debug("REST request to count Agences by criteria: {}", criteria);
        return ResponseEntity.ok().body(agenceQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /agences/:id} : get the "id" agence.
     *
     * @param id the id of the agenceDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the agenceDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/agences/{id}")
    public ResponseEntity<AgenceDTO> getAgence(@PathVariable Long id) {
        log.debug("REST request to get Agence : {}", id);
        Optional<AgenceDTO> agenceDTO = agenceService.findOne(id);
        return ResponseUtil.wrapOrNotFound(agenceDTO);
    }

    /**
     * {@code DELETE  /agences/:id} : delete the "id" agence.
     *
     * @param id the id of the agenceDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/agences/{id}")
    public ResponseEntity<Void> deleteAgence(@PathVariable Long id) {
        log.debug("REST request to delete Agence : {}", id);
        agenceService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }

}
