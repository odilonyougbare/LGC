package com.lgs.web.rest;

import com.lgs.LgcApp;
import com.lgs.domain.Client;
import com.lgs.domain.Agence;
import com.lgs.domain.User;
import com.lgs.repository.ClientRepository;
import com.lgs.service.ClientService;
import com.lgs.service.dto.ClientDTO;
import com.lgs.service.mapper.ClientMapper;
import com.lgs.service.dto.ClientCriteria;
import com.lgs.service.ClientQueryService;

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
 * Integration tests for the {@link ClientResource} REST controller.
 */
@SpringBootTest(classes = LgcApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class ClientResourceIT {

    private static final String DEFAULT_CODE_CLIENT = "AAAAAAAAAA";
    private static final String UPDATED_CODE_CLIENT = "BBBBBBBBBB";

    private static final String DEFAULT_DENOMINATION = "AAAAAAAAAA";
    private static final String UPDATED_DENOMINATION = "BBBBBBBBBB";

    private static final String DEFAULT_DOMAINE_ACTIVITE = "AAAAAAAAAA";
    private static final String UPDATED_DOMAINE_ACTIVITE = "BBBBBBBBBB";

    private static final String DEFAULT_SITE_WEB = "AAAAAAAAAA";
    private static final String UPDATED_SITE_WEB = "BBBBBBBBBB";

    private static final String DEFAULT_COMPTE_FACE_BOOK = "AAAAAAAAAA";
    private static final String UPDATED_COMPTE_FACE_BOOK = "BBBBBBBBBB";

    private static final String DEFAULT_COMPTE_TWITTER = "AAAAAAAAAA";
    private static final String UPDATED_COMPTE_TWITTER = "BBBBBBBBBB";

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private ClientMapper clientMapper;

    @Autowired
    private ClientService clientService;

    @Autowired
    private ClientQueryService clientQueryService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restClientMockMvc;

    private Client client;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Client createEntity(EntityManager em) {
        Client client = new Client()
            .codeClient(DEFAULT_CODE_CLIENT)
            .denomination(DEFAULT_DENOMINATION)
            .domaineActivite(DEFAULT_DOMAINE_ACTIVITE)
            .siteWeb(DEFAULT_SITE_WEB)
            .compteFaceBook(DEFAULT_COMPTE_FACE_BOOK)
            .compteTwitter(DEFAULT_COMPTE_TWITTER);
        return client;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Client createUpdatedEntity(EntityManager em) {
        Client client = new Client()
            .codeClient(UPDATED_CODE_CLIENT)
            .denomination(UPDATED_DENOMINATION)
            .domaineActivite(UPDATED_DOMAINE_ACTIVITE)
            .siteWeb(UPDATED_SITE_WEB)
            .compteFaceBook(UPDATED_COMPTE_FACE_BOOK)
            .compteTwitter(UPDATED_COMPTE_TWITTER);
        return client;
    }

    @BeforeEach
    public void initTest() {
        client = createEntity(em);
    }

    @Test
    @Transactional
    public void createClient() throws Exception {
        int databaseSizeBeforeCreate = clientRepository.findAll().size();
        // Create the Client
        ClientDTO clientDTO = clientMapper.toDto(client);
        restClientMockMvc.perform(post("/api/clients")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(clientDTO)))
            .andExpect(status().isCreated());

        // Validate the Client in the database
        List<Client> clientList = clientRepository.findAll();
        assertThat(clientList).hasSize(databaseSizeBeforeCreate + 1);
        Client testClient = clientList.get(clientList.size() - 1);
        assertThat(testClient.getCodeClient()).isEqualTo(DEFAULT_CODE_CLIENT);
        assertThat(testClient.getDenomination()).isEqualTo(DEFAULT_DENOMINATION);
        assertThat(testClient.getDomaineActivite()).isEqualTo(DEFAULT_DOMAINE_ACTIVITE);
        assertThat(testClient.getSiteWeb()).isEqualTo(DEFAULT_SITE_WEB);
        assertThat(testClient.getCompteFaceBook()).isEqualTo(DEFAULT_COMPTE_FACE_BOOK);
        assertThat(testClient.getCompteTwitter()).isEqualTo(DEFAULT_COMPTE_TWITTER);
    }

    @Test
    @Transactional
    public void createClientWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = clientRepository.findAll().size();

        // Create the Client with an existing ID
        client.setId(1L);
        ClientDTO clientDTO = clientMapper.toDto(client);

        // An entity with an existing ID cannot be created, so this API call must fail
        restClientMockMvc.perform(post("/api/clients")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(clientDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Client in the database
        List<Client> clientList = clientRepository.findAll();
        assertThat(clientList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllClients() throws Exception {
        // Initialize the database
        clientRepository.saveAndFlush(client);

        // Get all the clientList
        restClientMockMvc.perform(get("/api/clients?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(client.getId().intValue())))
            .andExpect(jsonPath("$.[*].codeClient").value(hasItem(DEFAULT_CODE_CLIENT)))
            .andExpect(jsonPath("$.[*].denomination").value(hasItem(DEFAULT_DENOMINATION)))
            .andExpect(jsonPath("$.[*].domaineActivite").value(hasItem(DEFAULT_DOMAINE_ACTIVITE)))
            .andExpect(jsonPath("$.[*].siteWeb").value(hasItem(DEFAULT_SITE_WEB)))
            .andExpect(jsonPath("$.[*].compteFaceBook").value(hasItem(DEFAULT_COMPTE_FACE_BOOK)))
            .andExpect(jsonPath("$.[*].compteTwitter").value(hasItem(DEFAULT_COMPTE_TWITTER)));
    }
    
    @Test
    @Transactional
    public void getClient() throws Exception {
        // Initialize the database
        clientRepository.saveAndFlush(client);

        // Get the client
        restClientMockMvc.perform(get("/api/clients/{id}", client.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(client.getId().intValue()))
            .andExpect(jsonPath("$.codeClient").value(DEFAULT_CODE_CLIENT))
            .andExpect(jsonPath("$.denomination").value(DEFAULT_DENOMINATION))
            .andExpect(jsonPath("$.domaineActivite").value(DEFAULT_DOMAINE_ACTIVITE))
            .andExpect(jsonPath("$.siteWeb").value(DEFAULT_SITE_WEB))
            .andExpect(jsonPath("$.compteFaceBook").value(DEFAULT_COMPTE_FACE_BOOK))
            .andExpect(jsonPath("$.compteTwitter").value(DEFAULT_COMPTE_TWITTER));
    }


    @Test
    @Transactional
    public void getClientsByIdFiltering() throws Exception {
        // Initialize the database
        clientRepository.saveAndFlush(client);

        Long id = client.getId();

        defaultClientShouldBeFound("id.equals=" + id);
        defaultClientShouldNotBeFound("id.notEquals=" + id);

        defaultClientShouldBeFound("id.greaterThanOrEqual=" + id);
        defaultClientShouldNotBeFound("id.greaterThan=" + id);

        defaultClientShouldBeFound("id.lessThanOrEqual=" + id);
        defaultClientShouldNotBeFound("id.lessThan=" + id);
    }


    @Test
    @Transactional
    public void getAllClientsByCodeClientIsEqualToSomething() throws Exception {
        // Initialize the database
        clientRepository.saveAndFlush(client);

        // Get all the clientList where codeClient equals to DEFAULT_CODE_CLIENT
        defaultClientShouldBeFound("codeClient.equals=" + DEFAULT_CODE_CLIENT);

        // Get all the clientList where codeClient equals to UPDATED_CODE_CLIENT
        defaultClientShouldNotBeFound("codeClient.equals=" + UPDATED_CODE_CLIENT);
    }

    @Test
    @Transactional
    public void getAllClientsByCodeClientIsNotEqualToSomething() throws Exception {
        // Initialize the database
        clientRepository.saveAndFlush(client);

        // Get all the clientList where codeClient not equals to DEFAULT_CODE_CLIENT
        defaultClientShouldNotBeFound("codeClient.notEquals=" + DEFAULT_CODE_CLIENT);

        // Get all the clientList where codeClient not equals to UPDATED_CODE_CLIENT
        defaultClientShouldBeFound("codeClient.notEquals=" + UPDATED_CODE_CLIENT);
    }

    @Test
    @Transactional
    public void getAllClientsByCodeClientIsInShouldWork() throws Exception {
        // Initialize the database
        clientRepository.saveAndFlush(client);

        // Get all the clientList where codeClient in DEFAULT_CODE_CLIENT or UPDATED_CODE_CLIENT
        defaultClientShouldBeFound("codeClient.in=" + DEFAULT_CODE_CLIENT + "," + UPDATED_CODE_CLIENT);

        // Get all the clientList where codeClient equals to UPDATED_CODE_CLIENT
        defaultClientShouldNotBeFound("codeClient.in=" + UPDATED_CODE_CLIENT);
    }

    @Test
    @Transactional
    public void getAllClientsByCodeClientIsNullOrNotNull() throws Exception {
        // Initialize the database
        clientRepository.saveAndFlush(client);

        // Get all the clientList where codeClient is not null
        defaultClientShouldBeFound("codeClient.specified=true");

        // Get all the clientList where codeClient is null
        defaultClientShouldNotBeFound("codeClient.specified=false");
    }
                @Test
    @Transactional
    public void getAllClientsByCodeClientContainsSomething() throws Exception {
        // Initialize the database
        clientRepository.saveAndFlush(client);

        // Get all the clientList where codeClient contains DEFAULT_CODE_CLIENT
        defaultClientShouldBeFound("codeClient.contains=" + DEFAULT_CODE_CLIENT);

        // Get all the clientList where codeClient contains UPDATED_CODE_CLIENT
        defaultClientShouldNotBeFound("codeClient.contains=" + UPDATED_CODE_CLIENT);
    }

    @Test
    @Transactional
    public void getAllClientsByCodeClientNotContainsSomething() throws Exception {
        // Initialize the database
        clientRepository.saveAndFlush(client);

        // Get all the clientList where codeClient does not contain DEFAULT_CODE_CLIENT
        defaultClientShouldNotBeFound("codeClient.doesNotContain=" + DEFAULT_CODE_CLIENT);

        // Get all the clientList where codeClient does not contain UPDATED_CODE_CLIENT
        defaultClientShouldBeFound("codeClient.doesNotContain=" + UPDATED_CODE_CLIENT);
    }


    @Test
    @Transactional
    public void getAllClientsByDenominationIsEqualToSomething() throws Exception {
        // Initialize the database
        clientRepository.saveAndFlush(client);

        // Get all the clientList where denomination equals to DEFAULT_DENOMINATION
        defaultClientShouldBeFound("denomination.equals=" + DEFAULT_DENOMINATION);

        // Get all the clientList where denomination equals to UPDATED_DENOMINATION
        defaultClientShouldNotBeFound("denomination.equals=" + UPDATED_DENOMINATION);
    }

    @Test
    @Transactional
    public void getAllClientsByDenominationIsNotEqualToSomething() throws Exception {
        // Initialize the database
        clientRepository.saveAndFlush(client);

        // Get all the clientList where denomination not equals to DEFAULT_DENOMINATION
        defaultClientShouldNotBeFound("denomination.notEquals=" + DEFAULT_DENOMINATION);

        // Get all the clientList where denomination not equals to UPDATED_DENOMINATION
        defaultClientShouldBeFound("denomination.notEquals=" + UPDATED_DENOMINATION);
    }

    @Test
    @Transactional
    public void getAllClientsByDenominationIsInShouldWork() throws Exception {
        // Initialize the database
        clientRepository.saveAndFlush(client);

        // Get all the clientList where denomination in DEFAULT_DENOMINATION or UPDATED_DENOMINATION
        defaultClientShouldBeFound("denomination.in=" + DEFAULT_DENOMINATION + "," + UPDATED_DENOMINATION);

        // Get all the clientList where denomination equals to UPDATED_DENOMINATION
        defaultClientShouldNotBeFound("denomination.in=" + UPDATED_DENOMINATION);
    }

    @Test
    @Transactional
    public void getAllClientsByDenominationIsNullOrNotNull() throws Exception {
        // Initialize the database
        clientRepository.saveAndFlush(client);

        // Get all the clientList where denomination is not null
        defaultClientShouldBeFound("denomination.specified=true");

        // Get all the clientList where denomination is null
        defaultClientShouldNotBeFound("denomination.specified=false");
    }
                @Test
    @Transactional
    public void getAllClientsByDenominationContainsSomething() throws Exception {
        // Initialize the database
        clientRepository.saveAndFlush(client);

        // Get all the clientList where denomination contains DEFAULT_DENOMINATION
        defaultClientShouldBeFound("denomination.contains=" + DEFAULT_DENOMINATION);

        // Get all the clientList where denomination contains UPDATED_DENOMINATION
        defaultClientShouldNotBeFound("denomination.contains=" + UPDATED_DENOMINATION);
    }

    @Test
    @Transactional
    public void getAllClientsByDenominationNotContainsSomething() throws Exception {
        // Initialize the database
        clientRepository.saveAndFlush(client);

        // Get all the clientList where denomination does not contain DEFAULT_DENOMINATION
        defaultClientShouldNotBeFound("denomination.doesNotContain=" + DEFAULT_DENOMINATION);

        // Get all the clientList where denomination does not contain UPDATED_DENOMINATION
        defaultClientShouldBeFound("denomination.doesNotContain=" + UPDATED_DENOMINATION);
    }


    @Test
    @Transactional
    public void getAllClientsByDomaineActiviteIsEqualToSomething() throws Exception {
        // Initialize the database
        clientRepository.saveAndFlush(client);

        // Get all the clientList where domaineActivite equals to DEFAULT_DOMAINE_ACTIVITE
        defaultClientShouldBeFound("domaineActivite.equals=" + DEFAULT_DOMAINE_ACTIVITE);

        // Get all the clientList where domaineActivite equals to UPDATED_DOMAINE_ACTIVITE
        defaultClientShouldNotBeFound("domaineActivite.equals=" + UPDATED_DOMAINE_ACTIVITE);
    }

    @Test
    @Transactional
    public void getAllClientsByDomaineActiviteIsNotEqualToSomething() throws Exception {
        // Initialize the database
        clientRepository.saveAndFlush(client);

        // Get all the clientList where domaineActivite not equals to DEFAULT_DOMAINE_ACTIVITE
        defaultClientShouldNotBeFound("domaineActivite.notEquals=" + DEFAULT_DOMAINE_ACTIVITE);

        // Get all the clientList where domaineActivite not equals to UPDATED_DOMAINE_ACTIVITE
        defaultClientShouldBeFound("domaineActivite.notEquals=" + UPDATED_DOMAINE_ACTIVITE);
    }

    @Test
    @Transactional
    public void getAllClientsByDomaineActiviteIsInShouldWork() throws Exception {
        // Initialize the database
        clientRepository.saveAndFlush(client);

        // Get all the clientList where domaineActivite in DEFAULT_DOMAINE_ACTIVITE or UPDATED_DOMAINE_ACTIVITE
        defaultClientShouldBeFound("domaineActivite.in=" + DEFAULT_DOMAINE_ACTIVITE + "," + UPDATED_DOMAINE_ACTIVITE);

        // Get all the clientList where domaineActivite equals to UPDATED_DOMAINE_ACTIVITE
        defaultClientShouldNotBeFound("domaineActivite.in=" + UPDATED_DOMAINE_ACTIVITE);
    }

    @Test
    @Transactional
    public void getAllClientsByDomaineActiviteIsNullOrNotNull() throws Exception {
        // Initialize the database
        clientRepository.saveAndFlush(client);

        // Get all the clientList where domaineActivite is not null
        defaultClientShouldBeFound("domaineActivite.specified=true");

        // Get all the clientList where domaineActivite is null
        defaultClientShouldNotBeFound("domaineActivite.specified=false");
    }
                @Test
    @Transactional
    public void getAllClientsByDomaineActiviteContainsSomething() throws Exception {
        // Initialize the database
        clientRepository.saveAndFlush(client);

        // Get all the clientList where domaineActivite contains DEFAULT_DOMAINE_ACTIVITE
        defaultClientShouldBeFound("domaineActivite.contains=" + DEFAULT_DOMAINE_ACTIVITE);

        // Get all the clientList where domaineActivite contains UPDATED_DOMAINE_ACTIVITE
        defaultClientShouldNotBeFound("domaineActivite.contains=" + UPDATED_DOMAINE_ACTIVITE);
    }

    @Test
    @Transactional
    public void getAllClientsByDomaineActiviteNotContainsSomething() throws Exception {
        // Initialize the database
        clientRepository.saveAndFlush(client);

        // Get all the clientList where domaineActivite does not contain DEFAULT_DOMAINE_ACTIVITE
        defaultClientShouldNotBeFound("domaineActivite.doesNotContain=" + DEFAULT_DOMAINE_ACTIVITE);

        // Get all the clientList where domaineActivite does not contain UPDATED_DOMAINE_ACTIVITE
        defaultClientShouldBeFound("domaineActivite.doesNotContain=" + UPDATED_DOMAINE_ACTIVITE);
    }


    @Test
    @Transactional
    public void getAllClientsBySiteWebIsEqualToSomething() throws Exception {
        // Initialize the database
        clientRepository.saveAndFlush(client);

        // Get all the clientList where siteWeb equals to DEFAULT_SITE_WEB
        defaultClientShouldBeFound("siteWeb.equals=" + DEFAULT_SITE_WEB);

        // Get all the clientList where siteWeb equals to UPDATED_SITE_WEB
        defaultClientShouldNotBeFound("siteWeb.equals=" + UPDATED_SITE_WEB);
    }

    @Test
    @Transactional
    public void getAllClientsBySiteWebIsNotEqualToSomething() throws Exception {
        // Initialize the database
        clientRepository.saveAndFlush(client);

        // Get all the clientList where siteWeb not equals to DEFAULT_SITE_WEB
        defaultClientShouldNotBeFound("siteWeb.notEquals=" + DEFAULT_SITE_WEB);

        // Get all the clientList where siteWeb not equals to UPDATED_SITE_WEB
        defaultClientShouldBeFound("siteWeb.notEquals=" + UPDATED_SITE_WEB);
    }

    @Test
    @Transactional
    public void getAllClientsBySiteWebIsInShouldWork() throws Exception {
        // Initialize the database
        clientRepository.saveAndFlush(client);

        // Get all the clientList where siteWeb in DEFAULT_SITE_WEB or UPDATED_SITE_WEB
        defaultClientShouldBeFound("siteWeb.in=" + DEFAULT_SITE_WEB + "," + UPDATED_SITE_WEB);

        // Get all the clientList where siteWeb equals to UPDATED_SITE_WEB
        defaultClientShouldNotBeFound("siteWeb.in=" + UPDATED_SITE_WEB);
    }

    @Test
    @Transactional
    public void getAllClientsBySiteWebIsNullOrNotNull() throws Exception {
        // Initialize the database
        clientRepository.saveAndFlush(client);

        // Get all the clientList where siteWeb is not null
        defaultClientShouldBeFound("siteWeb.specified=true");

        // Get all the clientList where siteWeb is null
        defaultClientShouldNotBeFound("siteWeb.specified=false");
    }
                @Test
    @Transactional
    public void getAllClientsBySiteWebContainsSomething() throws Exception {
        // Initialize the database
        clientRepository.saveAndFlush(client);

        // Get all the clientList where siteWeb contains DEFAULT_SITE_WEB
        defaultClientShouldBeFound("siteWeb.contains=" + DEFAULT_SITE_WEB);

        // Get all the clientList where siteWeb contains UPDATED_SITE_WEB
        defaultClientShouldNotBeFound("siteWeb.contains=" + UPDATED_SITE_WEB);
    }

    @Test
    @Transactional
    public void getAllClientsBySiteWebNotContainsSomething() throws Exception {
        // Initialize the database
        clientRepository.saveAndFlush(client);

        // Get all the clientList where siteWeb does not contain DEFAULT_SITE_WEB
        defaultClientShouldNotBeFound("siteWeb.doesNotContain=" + DEFAULT_SITE_WEB);

        // Get all the clientList where siteWeb does not contain UPDATED_SITE_WEB
        defaultClientShouldBeFound("siteWeb.doesNotContain=" + UPDATED_SITE_WEB);
    }


    @Test
    @Transactional
    public void getAllClientsByCompteFaceBookIsEqualToSomething() throws Exception {
        // Initialize the database
        clientRepository.saveAndFlush(client);

        // Get all the clientList where compteFaceBook equals to DEFAULT_COMPTE_FACE_BOOK
        defaultClientShouldBeFound("compteFaceBook.equals=" + DEFAULT_COMPTE_FACE_BOOK);

        // Get all the clientList where compteFaceBook equals to UPDATED_COMPTE_FACE_BOOK
        defaultClientShouldNotBeFound("compteFaceBook.equals=" + UPDATED_COMPTE_FACE_BOOK);
    }

    @Test
    @Transactional
    public void getAllClientsByCompteFaceBookIsNotEqualToSomething() throws Exception {
        // Initialize the database
        clientRepository.saveAndFlush(client);

        // Get all the clientList where compteFaceBook not equals to DEFAULT_COMPTE_FACE_BOOK
        defaultClientShouldNotBeFound("compteFaceBook.notEquals=" + DEFAULT_COMPTE_FACE_BOOK);

        // Get all the clientList where compteFaceBook not equals to UPDATED_COMPTE_FACE_BOOK
        defaultClientShouldBeFound("compteFaceBook.notEquals=" + UPDATED_COMPTE_FACE_BOOK);
    }

    @Test
    @Transactional
    public void getAllClientsByCompteFaceBookIsInShouldWork() throws Exception {
        // Initialize the database
        clientRepository.saveAndFlush(client);

        // Get all the clientList where compteFaceBook in DEFAULT_COMPTE_FACE_BOOK or UPDATED_COMPTE_FACE_BOOK
        defaultClientShouldBeFound("compteFaceBook.in=" + DEFAULT_COMPTE_FACE_BOOK + "," + UPDATED_COMPTE_FACE_BOOK);

        // Get all the clientList where compteFaceBook equals to UPDATED_COMPTE_FACE_BOOK
        defaultClientShouldNotBeFound("compteFaceBook.in=" + UPDATED_COMPTE_FACE_BOOK);
    }

    @Test
    @Transactional
    public void getAllClientsByCompteFaceBookIsNullOrNotNull() throws Exception {
        // Initialize the database
        clientRepository.saveAndFlush(client);

        // Get all the clientList where compteFaceBook is not null
        defaultClientShouldBeFound("compteFaceBook.specified=true");

        // Get all the clientList where compteFaceBook is null
        defaultClientShouldNotBeFound("compteFaceBook.specified=false");
    }
                @Test
    @Transactional
    public void getAllClientsByCompteFaceBookContainsSomething() throws Exception {
        // Initialize the database
        clientRepository.saveAndFlush(client);

        // Get all the clientList where compteFaceBook contains DEFAULT_COMPTE_FACE_BOOK
        defaultClientShouldBeFound("compteFaceBook.contains=" + DEFAULT_COMPTE_FACE_BOOK);

        // Get all the clientList where compteFaceBook contains UPDATED_COMPTE_FACE_BOOK
        defaultClientShouldNotBeFound("compteFaceBook.contains=" + UPDATED_COMPTE_FACE_BOOK);
    }

    @Test
    @Transactional
    public void getAllClientsByCompteFaceBookNotContainsSomething() throws Exception {
        // Initialize the database
        clientRepository.saveAndFlush(client);

        // Get all the clientList where compteFaceBook does not contain DEFAULT_COMPTE_FACE_BOOK
        defaultClientShouldNotBeFound("compteFaceBook.doesNotContain=" + DEFAULT_COMPTE_FACE_BOOK);

        // Get all the clientList where compteFaceBook does not contain UPDATED_COMPTE_FACE_BOOK
        defaultClientShouldBeFound("compteFaceBook.doesNotContain=" + UPDATED_COMPTE_FACE_BOOK);
    }


    @Test
    @Transactional
    public void getAllClientsByCompteTwitterIsEqualToSomething() throws Exception {
        // Initialize the database
        clientRepository.saveAndFlush(client);

        // Get all the clientList where compteTwitter equals to DEFAULT_COMPTE_TWITTER
        defaultClientShouldBeFound("compteTwitter.equals=" + DEFAULT_COMPTE_TWITTER);

        // Get all the clientList where compteTwitter equals to UPDATED_COMPTE_TWITTER
        defaultClientShouldNotBeFound("compteTwitter.equals=" + UPDATED_COMPTE_TWITTER);
    }

    @Test
    @Transactional
    public void getAllClientsByCompteTwitterIsNotEqualToSomething() throws Exception {
        // Initialize the database
        clientRepository.saveAndFlush(client);

        // Get all the clientList where compteTwitter not equals to DEFAULT_COMPTE_TWITTER
        defaultClientShouldNotBeFound("compteTwitter.notEquals=" + DEFAULT_COMPTE_TWITTER);

        // Get all the clientList where compteTwitter not equals to UPDATED_COMPTE_TWITTER
        defaultClientShouldBeFound("compteTwitter.notEquals=" + UPDATED_COMPTE_TWITTER);
    }

    @Test
    @Transactional
    public void getAllClientsByCompteTwitterIsInShouldWork() throws Exception {
        // Initialize the database
        clientRepository.saveAndFlush(client);

        // Get all the clientList where compteTwitter in DEFAULT_COMPTE_TWITTER or UPDATED_COMPTE_TWITTER
        defaultClientShouldBeFound("compteTwitter.in=" + DEFAULT_COMPTE_TWITTER + "," + UPDATED_COMPTE_TWITTER);

        // Get all the clientList where compteTwitter equals to UPDATED_COMPTE_TWITTER
        defaultClientShouldNotBeFound("compteTwitter.in=" + UPDATED_COMPTE_TWITTER);
    }

    @Test
    @Transactional
    public void getAllClientsByCompteTwitterIsNullOrNotNull() throws Exception {
        // Initialize the database
        clientRepository.saveAndFlush(client);

        // Get all the clientList where compteTwitter is not null
        defaultClientShouldBeFound("compteTwitter.specified=true");

        // Get all the clientList where compteTwitter is null
        defaultClientShouldNotBeFound("compteTwitter.specified=false");
    }
                @Test
    @Transactional
    public void getAllClientsByCompteTwitterContainsSomething() throws Exception {
        // Initialize the database
        clientRepository.saveAndFlush(client);

        // Get all the clientList where compteTwitter contains DEFAULT_COMPTE_TWITTER
        defaultClientShouldBeFound("compteTwitter.contains=" + DEFAULT_COMPTE_TWITTER);

        // Get all the clientList where compteTwitter contains UPDATED_COMPTE_TWITTER
        defaultClientShouldNotBeFound("compteTwitter.contains=" + UPDATED_COMPTE_TWITTER);
    }

    @Test
    @Transactional
    public void getAllClientsByCompteTwitterNotContainsSomething() throws Exception {
        // Initialize the database
        clientRepository.saveAndFlush(client);

        // Get all the clientList where compteTwitter does not contain DEFAULT_COMPTE_TWITTER
        defaultClientShouldNotBeFound("compteTwitter.doesNotContain=" + DEFAULT_COMPTE_TWITTER);

        // Get all the clientList where compteTwitter does not contain UPDATED_COMPTE_TWITTER
        defaultClientShouldBeFound("compteTwitter.doesNotContain=" + UPDATED_COMPTE_TWITTER);
    }


    @Test
    @Transactional
    public void getAllClientsByAgenceIsEqualToSomething() throws Exception {
        // Initialize the database
        clientRepository.saveAndFlush(client);
        Agence agence = AgenceResourceIT.createEntity(em);
        em.persist(agence);
        em.flush();
        client.addAgence(agence);
        clientRepository.saveAndFlush(client);
        Long agenceId = agence.getId();

        // Get all the clientList where agence equals to agenceId
        defaultClientShouldBeFound("agenceId.equals=" + agenceId);

        // Get all the clientList where agence equals to agenceId + 1
        defaultClientShouldNotBeFound("agenceId.equals=" + (agenceId + 1));
    }


    @Test
    @Transactional
    public void getAllClientsByUserIsEqualToSomething() throws Exception {
        // Initialize the database
        clientRepository.saveAndFlush(client);
        User user = UserResourceIT.createEntity(em);
        em.persist(user);
        em.flush();
        client.setUser(user);
        clientRepository.saveAndFlush(client);
        Long userId = user.getId();

        // Get all the clientList where user equals to userId
        defaultClientShouldBeFound("userId.equals=" + userId);

        // Get all the clientList where user equals to userId + 1
        defaultClientShouldNotBeFound("userId.equals=" + (userId + 1));
    }

    /**
     * Executes the search, and checks that the default entity is returned.
     */
    private void defaultClientShouldBeFound(String filter) throws Exception {
        restClientMockMvc.perform(get("/api/clients?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(client.getId().intValue())))
            .andExpect(jsonPath("$.[*].codeClient").value(hasItem(DEFAULT_CODE_CLIENT)))
            .andExpect(jsonPath("$.[*].denomination").value(hasItem(DEFAULT_DENOMINATION)))
            .andExpect(jsonPath("$.[*].domaineActivite").value(hasItem(DEFAULT_DOMAINE_ACTIVITE)))
            .andExpect(jsonPath("$.[*].siteWeb").value(hasItem(DEFAULT_SITE_WEB)))
            .andExpect(jsonPath("$.[*].compteFaceBook").value(hasItem(DEFAULT_COMPTE_FACE_BOOK)))
            .andExpect(jsonPath("$.[*].compteTwitter").value(hasItem(DEFAULT_COMPTE_TWITTER)));

        // Check, that the count call also returns 1
        restClientMockMvc.perform(get("/api/clients/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned.
     */
    private void defaultClientShouldNotBeFound(String filter) throws Exception {
        restClientMockMvc.perform(get("/api/clients?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restClientMockMvc.perform(get("/api/clients/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(content().string("0"));
    }

    @Test
    @Transactional
    public void getNonExistingClient() throws Exception {
        // Get the client
        restClientMockMvc.perform(get("/api/clients/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateClient() throws Exception {
        // Initialize the database
        clientRepository.saveAndFlush(client);

        int databaseSizeBeforeUpdate = clientRepository.findAll().size();

        // Update the client
        Client updatedClient = clientRepository.findById(client.getId()).get();
        // Disconnect from session so that the updates on updatedClient are not directly saved in db
        em.detach(updatedClient);
        updatedClient
            .codeClient(UPDATED_CODE_CLIENT)
            .denomination(UPDATED_DENOMINATION)
            .domaineActivite(UPDATED_DOMAINE_ACTIVITE)
            .siteWeb(UPDATED_SITE_WEB)
            .compteFaceBook(UPDATED_COMPTE_FACE_BOOK)
            .compteTwitter(UPDATED_COMPTE_TWITTER);
        ClientDTO clientDTO = clientMapper.toDto(updatedClient);

        restClientMockMvc.perform(put("/api/clients")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(clientDTO)))
            .andExpect(status().isOk());

        // Validate the Client in the database
        List<Client> clientList = clientRepository.findAll();
        assertThat(clientList).hasSize(databaseSizeBeforeUpdate);
        Client testClient = clientList.get(clientList.size() - 1);
        assertThat(testClient.getCodeClient()).isEqualTo(UPDATED_CODE_CLIENT);
        assertThat(testClient.getDenomination()).isEqualTo(UPDATED_DENOMINATION);
        assertThat(testClient.getDomaineActivite()).isEqualTo(UPDATED_DOMAINE_ACTIVITE);
        assertThat(testClient.getSiteWeb()).isEqualTo(UPDATED_SITE_WEB);
        assertThat(testClient.getCompteFaceBook()).isEqualTo(UPDATED_COMPTE_FACE_BOOK);
        assertThat(testClient.getCompteTwitter()).isEqualTo(UPDATED_COMPTE_TWITTER);
    }

    @Test
    @Transactional
    public void updateNonExistingClient() throws Exception {
        int databaseSizeBeforeUpdate = clientRepository.findAll().size();

        // Create the Client
        ClientDTO clientDTO = clientMapper.toDto(client);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restClientMockMvc.perform(put("/api/clients")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(clientDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Client in the database
        List<Client> clientList = clientRepository.findAll();
        assertThat(clientList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteClient() throws Exception {
        // Initialize the database
        clientRepository.saveAndFlush(client);

        int databaseSizeBeforeDelete = clientRepository.findAll().size();

        // Delete the client
        restClientMockMvc.perform(delete("/api/clients/{id}", client.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Client> clientList = clientRepository.findAll();
        assertThat(clientList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
