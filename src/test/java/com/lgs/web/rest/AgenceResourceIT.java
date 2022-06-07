package com.lgs.web.rest;

import com.lgs.LgcApp;
import com.lgs.domain.Agence;
import com.lgs.domain.User;
import com.lgs.domain.Client;
import com.lgs.repository.AgenceRepository;
import com.lgs.service.AgenceService;
import com.lgs.service.dto.AgenceDTO;
import com.lgs.service.mapper.AgenceMapper;
import com.lgs.service.dto.AgenceCriteria;
import com.lgs.service.AgenceQueryService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;
import javax.persistence.EntityManager;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Integration tests for the {@link AgenceResource} REST controller.
 */
@SpringBootTest(classes = LgcApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class AgenceResourceIT {

    private static final String DEFAULT_CODE_AGENCE = "AAAAAAAAAA";
    private static final String UPDATED_CODE_AGENCE = "BBBBBBBBBB";

    private static final String DEFAULT_DENOMINATION_AGENCE = "AAAAAAAAAA";
    private static final String UPDATED_DENOMINATION_AGENCE = "BBBBBBBBBB";

    private static final String DEFAULT_TYPE_AGENCE = "AAAAAAAAAA";
    private static final String UPDATED_TYPE_AGENCE = "BBBBBBBBBB";

    private static final String DEFAULT_TELEPHONE = "AAAAAAAAAA";
    private static final String UPDATED_TELEPHONE = "BBBBBBBBBB";

    private static final String DEFAULT_NUMERO_WHATSAPP = "AAAAAAAAAA";
    private static final String UPDATED_NUMERO_WHATSAPP = "BBBBBBBBBB";

    private static final String DEFAULT_EMAIL = "AAAAAAAAAA";
    private static final String UPDATED_EMAIL = "BBBBBBBBBB";

    private static final String DEFAULT_AUTRE_CONTACT = "AAAAAAAAAA";
    private static final String UPDATED_AUTRE_CONTACT = "BBBBBBBBBB";

    private static final String DEFAULT_QUARTIER = "AAAAAAAAAA";
    private static final String UPDATED_QUARTIER = "BBBBBBBBBB";

    private static final String DEFAULT_ARRONDISSEMENT = "AAAAAAAAAA";
    private static final String UPDATED_ARRONDISSEMENT = "BBBBBBBBBB";

    private static final String DEFAULT_COMMUNE = "AAAAAAAAAA";
    private static final String UPDATED_COMMUNE = "BBBBBBBBBB";

    private static final String DEFAULT_PROVINCE = "AAAAAAAAAA";
    private static final String UPDATED_PROVINCE = "BBBBBBBBBB";

    private static final String DEFAULT_REGION = "AAAAAAAAAA";
    private static final String UPDATED_REGION = "BBBBBBBBBB";

    @Autowired
    private AgenceRepository agenceRepository;

    @Autowired
    private AgenceMapper agenceMapper;

    @Autowired
    private AgenceService agenceService;

    @Autowired
    private AgenceQueryService agenceQueryService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restAgenceMockMvc;

    private Agence agence;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Agence createEntity(EntityManager em) {
        Agence agence = new Agence()
            .codeAgence(DEFAULT_CODE_AGENCE)
            .denominationAgence(DEFAULT_DENOMINATION_AGENCE)
            .typeAgence(DEFAULT_TYPE_AGENCE)
            .telephone(DEFAULT_TELEPHONE)
            .numeroWhatsapp(DEFAULT_NUMERO_WHATSAPP)
            .email(DEFAULT_EMAIL)
            .autreContact(DEFAULT_AUTRE_CONTACT)
            .quartier(DEFAULT_QUARTIER)
            .arrondissement(DEFAULT_ARRONDISSEMENT)
            .commune(DEFAULT_COMMUNE)
            .province(DEFAULT_PROVINCE)
            .region(DEFAULT_REGION);
        return agence;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Agence createUpdatedEntity(EntityManager em) {
        Agence agence = new Agence()
            .codeAgence(UPDATED_CODE_AGENCE)
            .denominationAgence(UPDATED_DENOMINATION_AGENCE)
            .typeAgence(UPDATED_TYPE_AGENCE)
            .telephone(UPDATED_TELEPHONE)
            .numeroWhatsapp(UPDATED_NUMERO_WHATSAPP)
            .email(UPDATED_EMAIL)
            .autreContact(UPDATED_AUTRE_CONTACT)
            .quartier(UPDATED_QUARTIER)
            .arrondissement(UPDATED_ARRONDISSEMENT)
            .commune(UPDATED_COMMUNE)
            .province(UPDATED_PROVINCE)
            .region(UPDATED_REGION);
        return agence;
    }

    @BeforeEach
    public void initTest() {
        agence = createEntity(em);
    }

    @Test
    @Transactional
    public void createAgence() throws Exception {
        int databaseSizeBeforeCreate = agenceRepository.findAll().size();
        // Create the Agence
        AgenceDTO agenceDTO = agenceMapper.toDto(agence);
        restAgenceMockMvc.perform(post("/api/agences")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(agenceDTO)))
            .andExpect(status().isCreated());

        // Validate the Agence in the database
        List<Agence> agenceList = agenceRepository.findAll();
        assertThat(agenceList).hasSize(databaseSizeBeforeCreate + 1);
        Agence testAgence = agenceList.get(agenceList.size() - 1);
        assertThat(testAgence.getCodeAgence()).isEqualTo(DEFAULT_CODE_AGENCE);
        assertThat(testAgence.getDenominationAgence()).isEqualTo(DEFAULT_DENOMINATION_AGENCE);
        assertThat(testAgence.getTypeAgence()).isEqualTo(DEFAULT_TYPE_AGENCE);
        assertThat(testAgence.getTelephone()).isEqualTo(DEFAULT_TELEPHONE);
        assertThat(testAgence.getNumeroWhatsapp()).isEqualTo(DEFAULT_NUMERO_WHATSAPP);
        assertThat(testAgence.getEmail()).isEqualTo(DEFAULT_EMAIL);
        assertThat(testAgence.getAutreContact()).isEqualTo(DEFAULT_AUTRE_CONTACT);
        assertThat(testAgence.getQuartier()).isEqualTo(DEFAULT_QUARTIER);
        assertThat(testAgence.getArrondissement()).isEqualTo(DEFAULT_ARRONDISSEMENT);
        assertThat(testAgence.getCommune()).isEqualTo(DEFAULT_COMMUNE);
        assertThat(testAgence.getProvince()).isEqualTo(DEFAULT_PROVINCE);
        assertThat(testAgence.getRegion()).isEqualTo(DEFAULT_REGION);
    }

    @Test
    @Transactional
    public void createAgenceWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = agenceRepository.findAll().size();

        // Create the Agence with an existing ID
        agence.setId(1L);
        AgenceDTO agenceDTO = agenceMapper.toDto(agence);

        // An entity with an existing ID cannot be created, so this API call must fail
        restAgenceMockMvc.perform(post("/api/agences")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(agenceDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Agence in the database
        List<Agence> agenceList = agenceRepository.findAll();
        assertThat(agenceList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllAgences() throws Exception {
        // Initialize the database
        agenceRepository.saveAndFlush(agence);

        // Get all the agenceList
        restAgenceMockMvc.perform(get("/api/agences?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(agence.getId().intValue())))
            .andExpect(jsonPath("$.[*].codeAgence").value(hasItem(DEFAULT_CODE_AGENCE)))
            .andExpect(jsonPath("$.[*].denominationAgence").value(hasItem(DEFAULT_DENOMINATION_AGENCE)))
            .andExpect(jsonPath("$.[*].typeAgence").value(hasItem(DEFAULT_TYPE_AGENCE)))
            .andExpect(jsonPath("$.[*].telephone").value(hasItem(DEFAULT_TELEPHONE)))
            .andExpect(jsonPath("$.[*].numeroWhatsapp").value(hasItem(DEFAULT_NUMERO_WHATSAPP)))
            .andExpect(jsonPath("$.[*].email").value(hasItem(DEFAULT_EMAIL)))
            .andExpect(jsonPath("$.[*].autreContact").value(hasItem(DEFAULT_AUTRE_CONTACT)))
            .andExpect(jsonPath("$.[*].quartier").value(hasItem(DEFAULT_QUARTIER)))
            .andExpect(jsonPath("$.[*].arrondissement").value(hasItem(DEFAULT_ARRONDISSEMENT)))
            .andExpect(jsonPath("$.[*].commune").value(hasItem(DEFAULT_COMMUNE)))
            .andExpect(jsonPath("$.[*].province").value(hasItem(DEFAULT_PROVINCE)))
            .andExpect(jsonPath("$.[*].region").value(hasItem(DEFAULT_REGION)));
    }
    
    @Test
    @Transactional
    public void getAgence() throws Exception {
        // Initialize the database
        agenceRepository.saveAndFlush(agence);

        // Get the agence
        restAgenceMockMvc.perform(get("/api/agences/{id}", agence.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(agence.getId().intValue()))
            .andExpect(jsonPath("$.codeAgence").value(DEFAULT_CODE_AGENCE))
            .andExpect(jsonPath("$.denominationAgence").value(DEFAULT_DENOMINATION_AGENCE))
            .andExpect(jsonPath("$.typeAgence").value(DEFAULT_TYPE_AGENCE))
            .andExpect(jsonPath("$.telephone").value(DEFAULT_TELEPHONE))
            .andExpect(jsonPath("$.numeroWhatsapp").value(DEFAULT_NUMERO_WHATSAPP))
            .andExpect(jsonPath("$.email").value(DEFAULT_EMAIL))
            .andExpect(jsonPath("$.autreContact").value(DEFAULT_AUTRE_CONTACT))
            .andExpect(jsonPath("$.quartier").value(DEFAULT_QUARTIER))
            .andExpect(jsonPath("$.arrondissement").value(DEFAULT_ARRONDISSEMENT))
            .andExpect(jsonPath("$.commune").value(DEFAULT_COMMUNE))
            .andExpect(jsonPath("$.province").value(DEFAULT_PROVINCE))
            .andExpect(jsonPath("$.region").value(DEFAULT_REGION));
    }


    @Test
    @Transactional
    public void getAgencesByIdFiltering() throws Exception {
        // Initialize the database
        agenceRepository.saveAndFlush(agence);

        Long id = agence.getId();

        defaultAgenceShouldBeFound("id.equals=" + id);
        defaultAgenceShouldNotBeFound("id.notEquals=" + id);

        defaultAgenceShouldBeFound("id.greaterThanOrEqual=" + id);
        defaultAgenceShouldNotBeFound("id.greaterThan=" + id);

        defaultAgenceShouldBeFound("id.lessThanOrEqual=" + id);
        defaultAgenceShouldNotBeFound("id.lessThan=" + id);
    }


    @Test
    @Transactional
    public void getAllAgencesByCodeAgenceIsEqualToSomething() throws Exception {
        // Initialize the database
        agenceRepository.saveAndFlush(agence);

        // Get all the agenceList where codeAgence equals to DEFAULT_CODE_AGENCE
        defaultAgenceShouldBeFound("codeAgence.equals=" + DEFAULT_CODE_AGENCE);

        // Get all the agenceList where codeAgence equals to UPDATED_CODE_AGENCE
        defaultAgenceShouldNotBeFound("codeAgence.equals=" + UPDATED_CODE_AGENCE);
    }

    @Test
    @Transactional
    public void getAllAgencesByCodeAgenceIsNotEqualToSomething() throws Exception {
        // Initialize the database
        agenceRepository.saveAndFlush(agence);

        // Get all the agenceList where codeAgence not equals to DEFAULT_CODE_AGENCE
        defaultAgenceShouldNotBeFound("codeAgence.notEquals=" + DEFAULT_CODE_AGENCE);

        // Get all the agenceList where codeAgence not equals to UPDATED_CODE_AGENCE
        defaultAgenceShouldBeFound("codeAgence.notEquals=" + UPDATED_CODE_AGENCE);
    }

    @Test
    @Transactional
    public void getAllAgencesByCodeAgenceIsInShouldWork() throws Exception {
        // Initialize the database
        agenceRepository.saveAndFlush(agence);

        // Get all the agenceList where codeAgence in DEFAULT_CODE_AGENCE or UPDATED_CODE_AGENCE
        defaultAgenceShouldBeFound("codeAgence.in=" + DEFAULT_CODE_AGENCE + "," + UPDATED_CODE_AGENCE);

        // Get all the agenceList where codeAgence equals to UPDATED_CODE_AGENCE
        defaultAgenceShouldNotBeFound("codeAgence.in=" + UPDATED_CODE_AGENCE);
    }

    @Test
    @Transactional
    public void getAllAgencesByCodeAgenceIsNullOrNotNull() throws Exception {
        // Initialize the database
        agenceRepository.saveAndFlush(agence);

        // Get all the agenceList where codeAgence is not null
        defaultAgenceShouldBeFound("codeAgence.specified=true");

        // Get all the agenceList where codeAgence is null
        defaultAgenceShouldNotBeFound("codeAgence.specified=false");
    }
                @Test
    @Transactional
    public void getAllAgencesByCodeAgenceContainsSomething() throws Exception {
        // Initialize the database
        agenceRepository.saveAndFlush(agence);

        // Get all the agenceList where codeAgence contains DEFAULT_CODE_AGENCE
        defaultAgenceShouldBeFound("codeAgence.contains=" + DEFAULT_CODE_AGENCE);

        // Get all the agenceList where codeAgence contains UPDATED_CODE_AGENCE
        defaultAgenceShouldNotBeFound("codeAgence.contains=" + UPDATED_CODE_AGENCE);
    }

    @Test
    @Transactional
    public void getAllAgencesByCodeAgenceNotContainsSomething() throws Exception {
        // Initialize the database
        agenceRepository.saveAndFlush(agence);

        // Get all the agenceList where codeAgence does not contain DEFAULT_CODE_AGENCE
        defaultAgenceShouldNotBeFound("codeAgence.doesNotContain=" + DEFAULT_CODE_AGENCE);

        // Get all the agenceList where codeAgence does not contain UPDATED_CODE_AGENCE
        defaultAgenceShouldBeFound("codeAgence.doesNotContain=" + UPDATED_CODE_AGENCE);
    }


    @Test
    @Transactional
    public void getAllAgencesByDenominationAgenceIsEqualToSomething() throws Exception {
        // Initialize the database
        agenceRepository.saveAndFlush(agence);

        // Get all the agenceList where denominationAgence equals to DEFAULT_DENOMINATION_AGENCE
        defaultAgenceShouldBeFound("denominationAgence.equals=" + DEFAULT_DENOMINATION_AGENCE);

        // Get all the agenceList where denominationAgence equals to UPDATED_DENOMINATION_AGENCE
        defaultAgenceShouldNotBeFound("denominationAgence.equals=" + UPDATED_DENOMINATION_AGENCE);
    }

    @Test
    @Transactional
    public void getAllAgencesByDenominationAgenceIsNotEqualToSomething() throws Exception {
        // Initialize the database
        agenceRepository.saveAndFlush(agence);

        // Get all the agenceList where denominationAgence not equals to DEFAULT_DENOMINATION_AGENCE
        defaultAgenceShouldNotBeFound("denominationAgence.notEquals=" + DEFAULT_DENOMINATION_AGENCE);

        // Get all the agenceList where denominationAgence not equals to UPDATED_DENOMINATION_AGENCE
        defaultAgenceShouldBeFound("denominationAgence.notEquals=" + UPDATED_DENOMINATION_AGENCE);
    }

    @Test
    @Transactional
    public void getAllAgencesByDenominationAgenceIsInShouldWork() throws Exception {
        // Initialize the database
        agenceRepository.saveAndFlush(agence);

        // Get all the agenceList where denominationAgence in DEFAULT_DENOMINATION_AGENCE or UPDATED_DENOMINATION_AGENCE
        defaultAgenceShouldBeFound("denominationAgence.in=" + DEFAULT_DENOMINATION_AGENCE + "," + UPDATED_DENOMINATION_AGENCE);

        // Get all the agenceList where denominationAgence equals to UPDATED_DENOMINATION_AGENCE
        defaultAgenceShouldNotBeFound("denominationAgence.in=" + UPDATED_DENOMINATION_AGENCE);
    }

    @Test
    @Transactional
    public void getAllAgencesByDenominationAgenceIsNullOrNotNull() throws Exception {
        // Initialize the database
        agenceRepository.saveAndFlush(agence);

        // Get all the agenceList where denominationAgence is not null
        defaultAgenceShouldBeFound("denominationAgence.specified=true");

        // Get all the agenceList where denominationAgence is null
        defaultAgenceShouldNotBeFound("denominationAgence.specified=false");
    }
                @Test
    @Transactional
    public void getAllAgencesByDenominationAgenceContainsSomething() throws Exception {
        // Initialize the database
        agenceRepository.saveAndFlush(agence);

        // Get all the agenceList where denominationAgence contains DEFAULT_DENOMINATION_AGENCE
        defaultAgenceShouldBeFound("denominationAgence.contains=" + DEFAULT_DENOMINATION_AGENCE);

        // Get all the agenceList where denominationAgence contains UPDATED_DENOMINATION_AGENCE
        defaultAgenceShouldNotBeFound("denominationAgence.contains=" + UPDATED_DENOMINATION_AGENCE);
    }

    @Test
    @Transactional
    public void getAllAgencesByDenominationAgenceNotContainsSomething() throws Exception {
        // Initialize the database
        agenceRepository.saveAndFlush(agence);

        // Get all the agenceList where denominationAgence does not contain DEFAULT_DENOMINATION_AGENCE
        defaultAgenceShouldNotBeFound("denominationAgence.doesNotContain=" + DEFAULT_DENOMINATION_AGENCE);

        // Get all the agenceList where denominationAgence does not contain UPDATED_DENOMINATION_AGENCE
        defaultAgenceShouldBeFound("denominationAgence.doesNotContain=" + UPDATED_DENOMINATION_AGENCE);
    }


    @Test
    @Transactional
    public void getAllAgencesByTypeAgenceIsEqualToSomething() throws Exception {
        // Initialize the database
        agenceRepository.saveAndFlush(agence);

        // Get all the agenceList where typeAgence equals to DEFAULT_TYPE_AGENCE
        defaultAgenceShouldBeFound("typeAgence.equals=" + DEFAULT_TYPE_AGENCE);

        // Get all the agenceList where typeAgence equals to UPDATED_TYPE_AGENCE
        defaultAgenceShouldNotBeFound("typeAgence.equals=" + UPDATED_TYPE_AGENCE);
    }

    @Test
    @Transactional
    public void getAllAgencesByTypeAgenceIsNotEqualToSomething() throws Exception {
        // Initialize the database
        agenceRepository.saveAndFlush(agence);

        // Get all the agenceList where typeAgence not equals to DEFAULT_TYPE_AGENCE
        defaultAgenceShouldNotBeFound("typeAgence.notEquals=" + DEFAULT_TYPE_AGENCE);

        // Get all the agenceList where typeAgence not equals to UPDATED_TYPE_AGENCE
        defaultAgenceShouldBeFound("typeAgence.notEquals=" + UPDATED_TYPE_AGENCE);
    }

    @Test
    @Transactional
    public void getAllAgencesByTypeAgenceIsInShouldWork() throws Exception {
        // Initialize the database
        agenceRepository.saveAndFlush(agence);

        // Get all the agenceList where typeAgence in DEFAULT_TYPE_AGENCE or UPDATED_TYPE_AGENCE
        defaultAgenceShouldBeFound("typeAgence.in=" + DEFAULT_TYPE_AGENCE + "," + UPDATED_TYPE_AGENCE);

        // Get all the agenceList where typeAgence equals to UPDATED_TYPE_AGENCE
        defaultAgenceShouldNotBeFound("typeAgence.in=" + UPDATED_TYPE_AGENCE);
    }

    @Test
    @Transactional
    public void getAllAgencesByTypeAgenceIsNullOrNotNull() throws Exception {
        // Initialize the database
        agenceRepository.saveAndFlush(agence);

        // Get all the agenceList where typeAgence is not null
        defaultAgenceShouldBeFound("typeAgence.specified=true");

        // Get all the agenceList where typeAgence is null
        defaultAgenceShouldNotBeFound("typeAgence.specified=false");
    }
                @Test
    @Transactional
    public void getAllAgencesByTypeAgenceContainsSomething() throws Exception {
        // Initialize the database
        agenceRepository.saveAndFlush(agence);

        // Get all the agenceList where typeAgence contains DEFAULT_TYPE_AGENCE
        defaultAgenceShouldBeFound("typeAgence.contains=" + DEFAULT_TYPE_AGENCE);

        // Get all the agenceList where typeAgence contains UPDATED_TYPE_AGENCE
        defaultAgenceShouldNotBeFound("typeAgence.contains=" + UPDATED_TYPE_AGENCE);
    }

    @Test
    @Transactional
    public void getAllAgencesByTypeAgenceNotContainsSomething() throws Exception {
        // Initialize the database
        agenceRepository.saveAndFlush(agence);

        // Get all the agenceList where typeAgence does not contain DEFAULT_TYPE_AGENCE
        defaultAgenceShouldNotBeFound("typeAgence.doesNotContain=" + DEFAULT_TYPE_AGENCE);

        // Get all the agenceList where typeAgence does not contain UPDATED_TYPE_AGENCE
        defaultAgenceShouldBeFound("typeAgence.doesNotContain=" + UPDATED_TYPE_AGENCE);
    }


    @Test
    @Transactional
    public void getAllAgencesByTelephoneIsEqualToSomething() throws Exception {
        // Initialize the database
        agenceRepository.saveAndFlush(agence);

        // Get all the agenceList where telephone equals to DEFAULT_TELEPHONE
        defaultAgenceShouldBeFound("telephone.equals=" + DEFAULT_TELEPHONE);

        // Get all the agenceList where telephone equals to UPDATED_TELEPHONE
        defaultAgenceShouldNotBeFound("telephone.equals=" + UPDATED_TELEPHONE);
    }

    @Test
    @Transactional
    public void getAllAgencesByTelephoneIsNotEqualToSomething() throws Exception {
        // Initialize the database
        agenceRepository.saveAndFlush(agence);

        // Get all the agenceList where telephone not equals to DEFAULT_TELEPHONE
        defaultAgenceShouldNotBeFound("telephone.notEquals=" + DEFAULT_TELEPHONE);

        // Get all the agenceList where telephone not equals to UPDATED_TELEPHONE
        defaultAgenceShouldBeFound("telephone.notEquals=" + UPDATED_TELEPHONE);
    }

    @Test
    @Transactional
    public void getAllAgencesByTelephoneIsInShouldWork() throws Exception {
        // Initialize the database
        agenceRepository.saveAndFlush(agence);

        // Get all the agenceList where telephone in DEFAULT_TELEPHONE or UPDATED_TELEPHONE
        defaultAgenceShouldBeFound("telephone.in=" + DEFAULT_TELEPHONE + "," + UPDATED_TELEPHONE);

        // Get all the agenceList where telephone equals to UPDATED_TELEPHONE
        defaultAgenceShouldNotBeFound("telephone.in=" + UPDATED_TELEPHONE);
    }

    @Test
    @Transactional
    public void getAllAgencesByTelephoneIsNullOrNotNull() throws Exception {
        // Initialize the database
        agenceRepository.saveAndFlush(agence);

        // Get all the agenceList where telephone is not null
        defaultAgenceShouldBeFound("telephone.specified=true");

        // Get all the agenceList where telephone is null
        defaultAgenceShouldNotBeFound("telephone.specified=false");
    }
                @Test
    @Transactional
    public void getAllAgencesByTelephoneContainsSomething() throws Exception {
        // Initialize the database
        agenceRepository.saveAndFlush(agence);

        // Get all the agenceList where telephone contains DEFAULT_TELEPHONE
        defaultAgenceShouldBeFound("telephone.contains=" + DEFAULT_TELEPHONE);

        // Get all the agenceList where telephone contains UPDATED_TELEPHONE
        defaultAgenceShouldNotBeFound("telephone.contains=" + UPDATED_TELEPHONE);
    }

    @Test
    @Transactional
    public void getAllAgencesByTelephoneNotContainsSomething() throws Exception {
        // Initialize the database
        agenceRepository.saveAndFlush(agence);

        // Get all the agenceList where telephone does not contain DEFAULT_TELEPHONE
        defaultAgenceShouldNotBeFound("telephone.doesNotContain=" + DEFAULT_TELEPHONE);

        // Get all the agenceList where telephone does not contain UPDATED_TELEPHONE
        defaultAgenceShouldBeFound("telephone.doesNotContain=" + UPDATED_TELEPHONE);
    }


    @Test
    @Transactional
    public void getAllAgencesByNumeroWhatsappIsEqualToSomething() throws Exception {
        // Initialize the database
        agenceRepository.saveAndFlush(agence);

        // Get all the agenceList where numeroWhatsapp equals to DEFAULT_NUMERO_WHATSAPP
        defaultAgenceShouldBeFound("numeroWhatsapp.equals=" + DEFAULT_NUMERO_WHATSAPP);

        // Get all the agenceList where numeroWhatsapp equals to UPDATED_NUMERO_WHATSAPP
        defaultAgenceShouldNotBeFound("numeroWhatsapp.equals=" + UPDATED_NUMERO_WHATSAPP);
    }

    @Test
    @Transactional
    public void getAllAgencesByNumeroWhatsappIsNotEqualToSomething() throws Exception {
        // Initialize the database
        agenceRepository.saveAndFlush(agence);

        // Get all the agenceList where numeroWhatsapp not equals to DEFAULT_NUMERO_WHATSAPP
        defaultAgenceShouldNotBeFound("numeroWhatsapp.notEquals=" + DEFAULT_NUMERO_WHATSAPP);

        // Get all the agenceList where numeroWhatsapp not equals to UPDATED_NUMERO_WHATSAPP
        defaultAgenceShouldBeFound("numeroWhatsapp.notEquals=" + UPDATED_NUMERO_WHATSAPP);
    }

    @Test
    @Transactional
    public void getAllAgencesByNumeroWhatsappIsInShouldWork() throws Exception {
        // Initialize the database
        agenceRepository.saveAndFlush(agence);

        // Get all the agenceList where numeroWhatsapp in DEFAULT_NUMERO_WHATSAPP or UPDATED_NUMERO_WHATSAPP
        defaultAgenceShouldBeFound("numeroWhatsapp.in=" + DEFAULT_NUMERO_WHATSAPP + "," + UPDATED_NUMERO_WHATSAPP);

        // Get all the agenceList where numeroWhatsapp equals to UPDATED_NUMERO_WHATSAPP
        defaultAgenceShouldNotBeFound("numeroWhatsapp.in=" + UPDATED_NUMERO_WHATSAPP);
    }

    @Test
    @Transactional
    public void getAllAgencesByNumeroWhatsappIsNullOrNotNull() throws Exception {
        // Initialize the database
        agenceRepository.saveAndFlush(agence);

        // Get all the agenceList where numeroWhatsapp is not null
        defaultAgenceShouldBeFound("numeroWhatsapp.specified=true");

        // Get all the agenceList where numeroWhatsapp is null
        defaultAgenceShouldNotBeFound("numeroWhatsapp.specified=false");
    }
                @Test
    @Transactional
    public void getAllAgencesByNumeroWhatsappContainsSomething() throws Exception {
        // Initialize the database
        agenceRepository.saveAndFlush(agence);

        // Get all the agenceList where numeroWhatsapp contains DEFAULT_NUMERO_WHATSAPP
        defaultAgenceShouldBeFound("numeroWhatsapp.contains=" + DEFAULT_NUMERO_WHATSAPP);

        // Get all the agenceList where numeroWhatsapp contains UPDATED_NUMERO_WHATSAPP
        defaultAgenceShouldNotBeFound("numeroWhatsapp.contains=" + UPDATED_NUMERO_WHATSAPP);
    }

    @Test
    @Transactional
    public void getAllAgencesByNumeroWhatsappNotContainsSomething() throws Exception {
        // Initialize the database
        agenceRepository.saveAndFlush(agence);

        // Get all the agenceList where numeroWhatsapp does not contain DEFAULT_NUMERO_WHATSAPP
        defaultAgenceShouldNotBeFound("numeroWhatsapp.doesNotContain=" + DEFAULT_NUMERO_WHATSAPP);

        // Get all the agenceList where numeroWhatsapp does not contain UPDATED_NUMERO_WHATSAPP
        defaultAgenceShouldBeFound("numeroWhatsapp.doesNotContain=" + UPDATED_NUMERO_WHATSAPP);
    }


    @Test
    @Transactional
    public void getAllAgencesByEmailIsEqualToSomething() throws Exception {
        // Initialize the database
        agenceRepository.saveAndFlush(agence);

        // Get all the agenceList where email equals to DEFAULT_EMAIL
        defaultAgenceShouldBeFound("email.equals=" + DEFAULT_EMAIL);

        // Get all the agenceList where email equals to UPDATED_EMAIL
        defaultAgenceShouldNotBeFound("email.equals=" + UPDATED_EMAIL);
    }

    @Test
    @Transactional
    public void getAllAgencesByEmailIsNotEqualToSomething() throws Exception {
        // Initialize the database
        agenceRepository.saveAndFlush(agence);

        // Get all the agenceList where email not equals to DEFAULT_EMAIL
        defaultAgenceShouldNotBeFound("email.notEquals=" + DEFAULT_EMAIL);

        // Get all the agenceList where email not equals to UPDATED_EMAIL
        defaultAgenceShouldBeFound("email.notEquals=" + UPDATED_EMAIL);
    }

    @Test
    @Transactional
    public void getAllAgencesByEmailIsInShouldWork() throws Exception {
        // Initialize the database
        agenceRepository.saveAndFlush(agence);

        // Get all the agenceList where email in DEFAULT_EMAIL or UPDATED_EMAIL
        defaultAgenceShouldBeFound("email.in=" + DEFAULT_EMAIL + "," + UPDATED_EMAIL);

        // Get all the agenceList where email equals to UPDATED_EMAIL
        defaultAgenceShouldNotBeFound("email.in=" + UPDATED_EMAIL);
    }

    @Test
    @Transactional
    public void getAllAgencesByEmailIsNullOrNotNull() throws Exception {
        // Initialize the database
        agenceRepository.saveAndFlush(agence);

        // Get all the agenceList where email is not null
        defaultAgenceShouldBeFound("email.specified=true");

        // Get all the agenceList where email is null
        defaultAgenceShouldNotBeFound("email.specified=false");
    }
                @Test
    @Transactional
    public void getAllAgencesByEmailContainsSomething() throws Exception {
        // Initialize the database
        agenceRepository.saveAndFlush(agence);

        // Get all the agenceList where email contains DEFAULT_EMAIL
        defaultAgenceShouldBeFound("email.contains=" + DEFAULT_EMAIL);

        // Get all the agenceList where email contains UPDATED_EMAIL
        defaultAgenceShouldNotBeFound("email.contains=" + UPDATED_EMAIL);
    }

    @Test
    @Transactional
    public void getAllAgencesByEmailNotContainsSomething() throws Exception {
        // Initialize the database
        agenceRepository.saveAndFlush(agence);

        // Get all the agenceList where email does not contain DEFAULT_EMAIL
        defaultAgenceShouldNotBeFound("email.doesNotContain=" + DEFAULT_EMAIL);

        // Get all the agenceList where email does not contain UPDATED_EMAIL
        defaultAgenceShouldBeFound("email.doesNotContain=" + UPDATED_EMAIL);
    }


    @Test
    @Transactional
    public void getAllAgencesByAutreContactIsEqualToSomething() throws Exception {
        // Initialize the database
        agenceRepository.saveAndFlush(agence);

        // Get all the agenceList where autreContact equals to DEFAULT_AUTRE_CONTACT
        defaultAgenceShouldBeFound("autreContact.equals=" + DEFAULT_AUTRE_CONTACT);

        // Get all the agenceList where autreContact equals to UPDATED_AUTRE_CONTACT
        defaultAgenceShouldNotBeFound("autreContact.equals=" + UPDATED_AUTRE_CONTACT);
    }

    @Test
    @Transactional
    public void getAllAgencesByAutreContactIsNotEqualToSomething() throws Exception {
        // Initialize the database
        agenceRepository.saveAndFlush(agence);

        // Get all the agenceList where autreContact not equals to DEFAULT_AUTRE_CONTACT
        defaultAgenceShouldNotBeFound("autreContact.notEquals=" + DEFAULT_AUTRE_CONTACT);

        // Get all the agenceList where autreContact not equals to UPDATED_AUTRE_CONTACT
        defaultAgenceShouldBeFound("autreContact.notEquals=" + UPDATED_AUTRE_CONTACT);
    }

    @Test
    @Transactional
    public void getAllAgencesByAutreContactIsInShouldWork() throws Exception {
        // Initialize the database
        agenceRepository.saveAndFlush(agence);

        // Get all the agenceList where autreContact in DEFAULT_AUTRE_CONTACT or UPDATED_AUTRE_CONTACT
        defaultAgenceShouldBeFound("autreContact.in=" + DEFAULT_AUTRE_CONTACT + "," + UPDATED_AUTRE_CONTACT);

        // Get all the agenceList where autreContact equals to UPDATED_AUTRE_CONTACT
        defaultAgenceShouldNotBeFound("autreContact.in=" + UPDATED_AUTRE_CONTACT);
    }

    @Test
    @Transactional
    public void getAllAgencesByAutreContactIsNullOrNotNull() throws Exception {
        // Initialize the database
        agenceRepository.saveAndFlush(agence);

        // Get all the agenceList where autreContact is not null
        defaultAgenceShouldBeFound("autreContact.specified=true");

        // Get all the agenceList where autreContact is null
        defaultAgenceShouldNotBeFound("autreContact.specified=false");
    }
                @Test
    @Transactional
    public void getAllAgencesByAutreContactContainsSomething() throws Exception {
        // Initialize the database
        agenceRepository.saveAndFlush(agence);

        // Get all the agenceList where autreContact contains DEFAULT_AUTRE_CONTACT
        defaultAgenceShouldBeFound("autreContact.contains=" + DEFAULT_AUTRE_CONTACT);

        // Get all the agenceList where autreContact contains UPDATED_AUTRE_CONTACT
        defaultAgenceShouldNotBeFound("autreContact.contains=" + UPDATED_AUTRE_CONTACT);
    }

    @Test
    @Transactional
    public void getAllAgencesByAutreContactNotContainsSomething() throws Exception {
        // Initialize the database
        agenceRepository.saveAndFlush(agence);

        // Get all the agenceList where autreContact does not contain DEFAULT_AUTRE_CONTACT
        defaultAgenceShouldNotBeFound("autreContact.doesNotContain=" + DEFAULT_AUTRE_CONTACT);

        // Get all the agenceList where autreContact does not contain UPDATED_AUTRE_CONTACT
        defaultAgenceShouldBeFound("autreContact.doesNotContain=" + UPDATED_AUTRE_CONTACT);
    }


    @Test
    @Transactional
    public void getAllAgencesByQuartierIsEqualToSomething() throws Exception {
        // Initialize the database
        agenceRepository.saveAndFlush(agence);

        // Get all the agenceList where quartier equals to DEFAULT_QUARTIER
        defaultAgenceShouldBeFound("quartier.equals=" + DEFAULT_QUARTIER);

        // Get all the agenceList where quartier equals to UPDATED_QUARTIER
        defaultAgenceShouldNotBeFound("quartier.equals=" + UPDATED_QUARTIER);
    }

    @Test
    @Transactional
    public void getAllAgencesByQuartierIsNotEqualToSomething() throws Exception {
        // Initialize the database
        agenceRepository.saveAndFlush(agence);

        // Get all the agenceList where quartier not equals to DEFAULT_QUARTIER
        defaultAgenceShouldNotBeFound("quartier.notEquals=" + DEFAULT_QUARTIER);

        // Get all the agenceList where quartier not equals to UPDATED_QUARTIER
        defaultAgenceShouldBeFound("quartier.notEquals=" + UPDATED_QUARTIER);
    }

    @Test
    @Transactional
    public void getAllAgencesByQuartierIsInShouldWork() throws Exception {
        // Initialize the database
        agenceRepository.saveAndFlush(agence);

        // Get all the agenceList where quartier in DEFAULT_QUARTIER or UPDATED_QUARTIER
        defaultAgenceShouldBeFound("quartier.in=" + DEFAULT_QUARTIER + "," + UPDATED_QUARTIER);

        // Get all the agenceList where quartier equals to UPDATED_QUARTIER
        defaultAgenceShouldNotBeFound("quartier.in=" + UPDATED_QUARTIER);
    }

    @Test
    @Transactional
    public void getAllAgencesByQuartierIsNullOrNotNull() throws Exception {
        // Initialize the database
        agenceRepository.saveAndFlush(agence);

        // Get all the agenceList where quartier is not null
        defaultAgenceShouldBeFound("quartier.specified=true");

        // Get all the agenceList where quartier is null
        defaultAgenceShouldNotBeFound("quartier.specified=false");
    }
                @Test
    @Transactional
    public void getAllAgencesByQuartierContainsSomething() throws Exception {
        // Initialize the database
        agenceRepository.saveAndFlush(agence);

        // Get all the agenceList where quartier contains DEFAULT_QUARTIER
        defaultAgenceShouldBeFound("quartier.contains=" + DEFAULT_QUARTIER);

        // Get all the agenceList where quartier contains UPDATED_QUARTIER
        defaultAgenceShouldNotBeFound("quartier.contains=" + UPDATED_QUARTIER);
    }

    @Test
    @Transactional
    public void getAllAgencesByQuartierNotContainsSomething() throws Exception {
        // Initialize the database
        agenceRepository.saveAndFlush(agence);

        // Get all the agenceList where quartier does not contain DEFAULT_QUARTIER
        defaultAgenceShouldNotBeFound("quartier.doesNotContain=" + DEFAULT_QUARTIER);

        // Get all the agenceList where quartier does not contain UPDATED_QUARTIER
        defaultAgenceShouldBeFound("quartier.doesNotContain=" + UPDATED_QUARTIER);
    }


    @Test
    @Transactional
    public void getAllAgencesByArrondissementIsEqualToSomething() throws Exception {
        // Initialize the database
        agenceRepository.saveAndFlush(agence);

        // Get all the agenceList where arrondissement equals to DEFAULT_ARRONDISSEMENT
        defaultAgenceShouldBeFound("arrondissement.equals=" + DEFAULT_ARRONDISSEMENT);

        // Get all the agenceList where arrondissement equals to UPDATED_ARRONDISSEMENT
        defaultAgenceShouldNotBeFound("arrondissement.equals=" + UPDATED_ARRONDISSEMENT);
    }

    @Test
    @Transactional
    public void getAllAgencesByArrondissementIsNotEqualToSomething() throws Exception {
        // Initialize the database
        agenceRepository.saveAndFlush(agence);

        // Get all the agenceList where arrondissement not equals to DEFAULT_ARRONDISSEMENT
        defaultAgenceShouldNotBeFound("arrondissement.notEquals=" + DEFAULT_ARRONDISSEMENT);

        // Get all the agenceList where arrondissement not equals to UPDATED_ARRONDISSEMENT
        defaultAgenceShouldBeFound("arrondissement.notEquals=" + UPDATED_ARRONDISSEMENT);
    }

    @Test
    @Transactional
    public void getAllAgencesByArrondissementIsInShouldWork() throws Exception {
        // Initialize the database
        agenceRepository.saveAndFlush(agence);

        // Get all the agenceList where arrondissement in DEFAULT_ARRONDISSEMENT or UPDATED_ARRONDISSEMENT
        defaultAgenceShouldBeFound("arrondissement.in=" + DEFAULT_ARRONDISSEMENT + "," + UPDATED_ARRONDISSEMENT);

        // Get all the agenceList where arrondissement equals to UPDATED_ARRONDISSEMENT
        defaultAgenceShouldNotBeFound("arrondissement.in=" + UPDATED_ARRONDISSEMENT);
    }

    @Test
    @Transactional
    public void getAllAgencesByArrondissementIsNullOrNotNull() throws Exception {
        // Initialize the database
        agenceRepository.saveAndFlush(agence);

        // Get all the agenceList where arrondissement is not null
        defaultAgenceShouldBeFound("arrondissement.specified=true");

        // Get all the agenceList where arrondissement is null
        defaultAgenceShouldNotBeFound("arrondissement.specified=false");
    }
                @Test
    @Transactional
    public void getAllAgencesByArrondissementContainsSomething() throws Exception {
        // Initialize the database
        agenceRepository.saveAndFlush(agence);

        // Get all the agenceList where arrondissement contains DEFAULT_ARRONDISSEMENT
        defaultAgenceShouldBeFound("arrondissement.contains=" + DEFAULT_ARRONDISSEMENT);

        // Get all the agenceList where arrondissement contains UPDATED_ARRONDISSEMENT
        defaultAgenceShouldNotBeFound("arrondissement.contains=" + UPDATED_ARRONDISSEMENT);
    }

    @Test
    @Transactional
    public void getAllAgencesByArrondissementNotContainsSomething() throws Exception {
        // Initialize the database
        agenceRepository.saveAndFlush(agence);

        // Get all the agenceList where arrondissement does not contain DEFAULT_ARRONDISSEMENT
        defaultAgenceShouldNotBeFound("arrondissement.doesNotContain=" + DEFAULT_ARRONDISSEMENT);

        // Get all the agenceList where arrondissement does not contain UPDATED_ARRONDISSEMENT
        defaultAgenceShouldBeFound("arrondissement.doesNotContain=" + UPDATED_ARRONDISSEMENT);
    }


    @Test
    @Transactional
    public void getAllAgencesByCommuneIsEqualToSomething() throws Exception {
        // Initialize the database
        agenceRepository.saveAndFlush(agence);

        // Get all the agenceList where commune equals to DEFAULT_COMMUNE
        defaultAgenceShouldBeFound("commune.equals=" + DEFAULT_COMMUNE);

        // Get all the agenceList where commune equals to UPDATED_COMMUNE
        defaultAgenceShouldNotBeFound("commune.equals=" + UPDATED_COMMUNE);
    }

    @Test
    @Transactional
    public void getAllAgencesByCommuneIsNotEqualToSomething() throws Exception {
        // Initialize the database
        agenceRepository.saveAndFlush(agence);

        // Get all the agenceList where commune not equals to DEFAULT_COMMUNE
        defaultAgenceShouldNotBeFound("commune.notEquals=" + DEFAULT_COMMUNE);

        // Get all the agenceList where commune not equals to UPDATED_COMMUNE
        defaultAgenceShouldBeFound("commune.notEquals=" + UPDATED_COMMUNE);
    }

    @Test
    @Transactional
    public void getAllAgencesByCommuneIsInShouldWork() throws Exception {
        // Initialize the database
        agenceRepository.saveAndFlush(agence);

        // Get all the agenceList where commune in DEFAULT_COMMUNE or UPDATED_COMMUNE
        defaultAgenceShouldBeFound("commune.in=" + DEFAULT_COMMUNE + "," + UPDATED_COMMUNE);

        // Get all the agenceList where commune equals to UPDATED_COMMUNE
        defaultAgenceShouldNotBeFound("commune.in=" + UPDATED_COMMUNE);
    }

    @Test
    @Transactional
    public void getAllAgencesByCommuneIsNullOrNotNull() throws Exception {
        // Initialize the database
        agenceRepository.saveAndFlush(agence);

        // Get all the agenceList where commune is not null
        defaultAgenceShouldBeFound("commune.specified=true");

        // Get all the agenceList where commune is null
        defaultAgenceShouldNotBeFound("commune.specified=false");
    }
                @Test
    @Transactional
    public void getAllAgencesByCommuneContainsSomething() throws Exception {
        // Initialize the database
        agenceRepository.saveAndFlush(agence);

        // Get all the agenceList where commune contains DEFAULT_COMMUNE
        defaultAgenceShouldBeFound("commune.contains=" + DEFAULT_COMMUNE);

        // Get all the agenceList where commune contains UPDATED_COMMUNE
        defaultAgenceShouldNotBeFound("commune.contains=" + UPDATED_COMMUNE);
    }

    @Test
    @Transactional
    public void getAllAgencesByCommuneNotContainsSomething() throws Exception {
        // Initialize the database
        agenceRepository.saveAndFlush(agence);

        // Get all the agenceList where commune does not contain DEFAULT_COMMUNE
        defaultAgenceShouldNotBeFound("commune.doesNotContain=" + DEFAULT_COMMUNE);

        // Get all the agenceList where commune does not contain UPDATED_COMMUNE
        defaultAgenceShouldBeFound("commune.doesNotContain=" + UPDATED_COMMUNE);
    }


    @Test
    @Transactional
    public void getAllAgencesByProvinceIsEqualToSomething() throws Exception {
        // Initialize the database
        agenceRepository.saveAndFlush(agence);

        // Get all the agenceList where province equals to DEFAULT_PROVINCE
        defaultAgenceShouldBeFound("province.equals=" + DEFAULT_PROVINCE);

        // Get all the agenceList where province equals to UPDATED_PROVINCE
        defaultAgenceShouldNotBeFound("province.equals=" + UPDATED_PROVINCE);
    }

    @Test
    @Transactional
    public void getAllAgencesByProvinceIsNotEqualToSomething() throws Exception {
        // Initialize the database
        agenceRepository.saveAndFlush(agence);

        // Get all the agenceList where province not equals to DEFAULT_PROVINCE
        defaultAgenceShouldNotBeFound("province.notEquals=" + DEFAULT_PROVINCE);

        // Get all the agenceList where province not equals to UPDATED_PROVINCE
        defaultAgenceShouldBeFound("province.notEquals=" + UPDATED_PROVINCE);
    }

    @Test
    @Transactional
    public void getAllAgencesByProvinceIsInShouldWork() throws Exception {
        // Initialize the database
        agenceRepository.saveAndFlush(agence);

        // Get all the agenceList where province in DEFAULT_PROVINCE or UPDATED_PROVINCE
        defaultAgenceShouldBeFound("province.in=" + DEFAULT_PROVINCE + "," + UPDATED_PROVINCE);

        // Get all the agenceList where province equals to UPDATED_PROVINCE
        defaultAgenceShouldNotBeFound("province.in=" + UPDATED_PROVINCE);
    }

    @Test
    @Transactional
    public void getAllAgencesByProvinceIsNullOrNotNull() throws Exception {
        // Initialize the database
        agenceRepository.saveAndFlush(agence);

        // Get all the agenceList where province is not null
        defaultAgenceShouldBeFound("province.specified=true");

        // Get all the agenceList where province is null
        defaultAgenceShouldNotBeFound("province.specified=false");
    }
                @Test
    @Transactional
    public void getAllAgencesByProvinceContainsSomething() throws Exception {
        // Initialize the database
        agenceRepository.saveAndFlush(agence);

        // Get all the agenceList where province contains DEFAULT_PROVINCE
        defaultAgenceShouldBeFound("province.contains=" + DEFAULT_PROVINCE);

        // Get all the agenceList where province contains UPDATED_PROVINCE
        defaultAgenceShouldNotBeFound("province.contains=" + UPDATED_PROVINCE);
    }

    @Test
    @Transactional
    public void getAllAgencesByProvinceNotContainsSomething() throws Exception {
        // Initialize the database
        agenceRepository.saveAndFlush(agence);

        // Get all the agenceList where province does not contain DEFAULT_PROVINCE
        defaultAgenceShouldNotBeFound("province.doesNotContain=" + DEFAULT_PROVINCE);

        // Get all the agenceList where province does not contain UPDATED_PROVINCE
        defaultAgenceShouldBeFound("province.doesNotContain=" + UPDATED_PROVINCE);
    }


    @Test
    @Transactional
    public void getAllAgencesByRegionIsEqualToSomething() throws Exception {
        // Initialize the database
        agenceRepository.saveAndFlush(agence);

        // Get all the agenceList where region equals to DEFAULT_REGION
        defaultAgenceShouldBeFound("region.equals=" + DEFAULT_REGION);

        // Get all the agenceList where region equals to UPDATED_REGION
        defaultAgenceShouldNotBeFound("region.equals=" + UPDATED_REGION);
    }

    @Test
    @Transactional
    public void getAllAgencesByRegionIsNotEqualToSomething() throws Exception {
        // Initialize the database
        agenceRepository.saveAndFlush(agence);

        // Get all the agenceList where region not equals to DEFAULT_REGION
        defaultAgenceShouldNotBeFound("region.notEquals=" + DEFAULT_REGION);

        // Get all the agenceList where region not equals to UPDATED_REGION
        defaultAgenceShouldBeFound("region.notEquals=" + UPDATED_REGION);
    }

    @Test
    @Transactional
    public void getAllAgencesByRegionIsInShouldWork() throws Exception {
        // Initialize the database
        agenceRepository.saveAndFlush(agence);

        // Get all the agenceList where region in DEFAULT_REGION or UPDATED_REGION
        defaultAgenceShouldBeFound("region.in=" + DEFAULT_REGION + "," + UPDATED_REGION);

        // Get all the agenceList where region equals to UPDATED_REGION
        defaultAgenceShouldNotBeFound("region.in=" + UPDATED_REGION);
    }

    @Test
    @Transactional
    public void getAllAgencesByRegionIsNullOrNotNull() throws Exception {
        // Initialize the database
        agenceRepository.saveAndFlush(agence);

        // Get all the agenceList where region is not null
        defaultAgenceShouldBeFound("region.specified=true");

        // Get all the agenceList where region is null
        defaultAgenceShouldNotBeFound("region.specified=false");
    }
                @Test
    @Transactional
    public void getAllAgencesByRegionContainsSomething() throws Exception {
        // Initialize the database
        agenceRepository.saveAndFlush(agence);

        // Get all the agenceList where region contains DEFAULT_REGION
        defaultAgenceShouldBeFound("region.contains=" + DEFAULT_REGION);

        // Get all the agenceList where region contains UPDATED_REGION
        defaultAgenceShouldNotBeFound("region.contains=" + UPDATED_REGION);
    }

    @Test
    @Transactional
    public void getAllAgencesByRegionNotContainsSomething() throws Exception {
        // Initialize the database
        agenceRepository.saveAndFlush(agence);

        // Get all the agenceList where region does not contain DEFAULT_REGION
        defaultAgenceShouldNotBeFound("region.doesNotContain=" + DEFAULT_REGION);

        // Get all the agenceList where region does not contain UPDATED_REGION
        defaultAgenceShouldBeFound("region.doesNotContain=" + UPDATED_REGION);
    }


    @Test
    @Transactional
    public void getAllAgencesByUserIsEqualToSomething() throws Exception {
        // Initialize the database
        agenceRepository.saveAndFlush(agence);
        User user = UserResourceIT.createEntity(em);
        em.persist(user);
        em.flush();
        agence.setUser(user);
        agenceRepository.saveAndFlush(agence);
        Long userId = user.getId();

        // Get all the agenceList where user equals to userId
        defaultAgenceShouldBeFound("userId.equals=" + userId);

        // Get all the agenceList where user equals to userId + 1
        defaultAgenceShouldNotBeFound("userId.equals=" + (userId + 1));
    }


    @Test
    @Transactional
    public void getAllAgencesByClientIsEqualToSomething() throws Exception {
        // Initialize the database
        agenceRepository.saveAndFlush(agence);
        Client client = ClientResourceIT.createEntity(em);
        em.persist(client);
        em.flush();
        agence.setClient(client);
        agenceRepository.saveAndFlush(agence);
        Long clientId = client.getId();

        // Get all the agenceList where client equals to clientId
        defaultAgenceShouldBeFound("clientId.equals=" + clientId);

        // Get all the agenceList where client equals to clientId + 1
        defaultAgenceShouldNotBeFound("clientId.equals=" + (clientId + 1));
    }

    /**
     * Executes the search, and checks that the default entity is returned.
     */
    private void defaultAgenceShouldBeFound(String filter) throws Exception {
        restAgenceMockMvc.perform(get("/api/agences?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(agence.getId().intValue())))
            .andExpect(jsonPath("$.[*].codeAgence").value(hasItem(DEFAULT_CODE_AGENCE)))
            .andExpect(jsonPath("$.[*].denominationAgence").value(hasItem(DEFAULT_DENOMINATION_AGENCE)))
            .andExpect(jsonPath("$.[*].typeAgence").value(hasItem(DEFAULT_TYPE_AGENCE)))
            .andExpect(jsonPath("$.[*].telephone").value(hasItem(DEFAULT_TELEPHONE)))
            .andExpect(jsonPath("$.[*].numeroWhatsapp").value(hasItem(DEFAULT_NUMERO_WHATSAPP)))
            .andExpect(jsonPath("$.[*].email").value(hasItem(DEFAULT_EMAIL)))
            .andExpect(jsonPath("$.[*].autreContact").value(hasItem(DEFAULT_AUTRE_CONTACT)))
            .andExpect(jsonPath("$.[*].quartier").value(hasItem(DEFAULT_QUARTIER)))
            .andExpect(jsonPath("$.[*].arrondissement").value(hasItem(DEFAULT_ARRONDISSEMENT)))
            .andExpect(jsonPath("$.[*].commune").value(hasItem(DEFAULT_COMMUNE)))
            .andExpect(jsonPath("$.[*].province").value(hasItem(DEFAULT_PROVINCE)))
            .andExpect(jsonPath("$.[*].region").value(hasItem(DEFAULT_REGION)));

        // Check, that the count call also returns 1
        restAgenceMockMvc.perform(get("/api/agences/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned.
     */
    private void defaultAgenceShouldNotBeFound(String filter) throws Exception {
        restAgenceMockMvc.perform(get("/api/agences?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restAgenceMockMvc.perform(get("/api/agences/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(content().string("0"));
    }

    @Test
    @Transactional
    public void getNonExistingAgence() throws Exception {
        // Get the agence
        restAgenceMockMvc.perform(get("/api/agences/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateAgence() throws Exception {
        // Initialize the database
        agenceRepository.saveAndFlush(agence);

        int databaseSizeBeforeUpdate = agenceRepository.findAll().size();

        // Update the agence
        Agence updatedAgence = agenceRepository.findById(agence.getId()).get();
        // Disconnect from session so that the updates on updatedAgence are not directly saved in db
        em.detach(updatedAgence);
        updatedAgence
            .codeAgence(UPDATED_CODE_AGENCE)
            .denominationAgence(UPDATED_DENOMINATION_AGENCE)
            .typeAgence(UPDATED_TYPE_AGENCE)
            .telephone(UPDATED_TELEPHONE)
            .numeroWhatsapp(UPDATED_NUMERO_WHATSAPP)
            .email(UPDATED_EMAIL)
            .autreContact(UPDATED_AUTRE_CONTACT)
            .quartier(UPDATED_QUARTIER)
            .arrondissement(UPDATED_ARRONDISSEMENT)
            .commune(UPDATED_COMMUNE)
            .province(UPDATED_PROVINCE)
            .region(UPDATED_REGION);
        AgenceDTO agenceDTO = agenceMapper.toDto(updatedAgence);

        restAgenceMockMvc.perform(put("/api/agences")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(agenceDTO)))
            .andExpect(status().isOk());

        // Validate the Agence in the database
        List<Agence> agenceList = agenceRepository.findAll();
        assertThat(agenceList).hasSize(databaseSizeBeforeUpdate);
        Agence testAgence = agenceList.get(agenceList.size() - 1);
        assertThat(testAgence.getCodeAgence()).isEqualTo(UPDATED_CODE_AGENCE);
        assertThat(testAgence.getDenominationAgence()).isEqualTo(UPDATED_DENOMINATION_AGENCE);
        assertThat(testAgence.getTypeAgence()).isEqualTo(UPDATED_TYPE_AGENCE);
        assertThat(testAgence.getTelephone()).isEqualTo(UPDATED_TELEPHONE);
        assertThat(testAgence.getNumeroWhatsapp()).isEqualTo(UPDATED_NUMERO_WHATSAPP);
        assertThat(testAgence.getEmail()).isEqualTo(UPDATED_EMAIL);
        assertThat(testAgence.getAutreContact()).isEqualTo(UPDATED_AUTRE_CONTACT);
        assertThat(testAgence.getQuartier()).isEqualTo(UPDATED_QUARTIER);
        assertThat(testAgence.getArrondissement()).isEqualTo(UPDATED_ARRONDISSEMENT);
        assertThat(testAgence.getCommune()).isEqualTo(UPDATED_COMMUNE);
        assertThat(testAgence.getProvince()).isEqualTo(UPDATED_PROVINCE);
        assertThat(testAgence.getRegion()).isEqualTo(UPDATED_REGION);
    }

    @Test
    @Transactional
    public void updateNonExistingAgence() throws Exception {
        int databaseSizeBeforeUpdate = agenceRepository.findAll().size();

        // Create the Agence
        AgenceDTO agenceDTO = agenceMapper.toDto(agence);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restAgenceMockMvc.perform(put("/api/agences")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(agenceDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Agence in the database
        List<Agence> agenceList = agenceRepository.findAll();
        assertThat(agenceList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteAgence() throws Exception {
        // Initialize the database
        agenceRepository.saveAndFlush(agence);

        int databaseSizeBeforeDelete = agenceRepository.findAll().size();

        // Delete the agence
        restAgenceMockMvc.perform(delete("/api/agences/{id}", agence.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Agence> agenceList = agenceRepository.findAll();
        assertThat(agenceList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
