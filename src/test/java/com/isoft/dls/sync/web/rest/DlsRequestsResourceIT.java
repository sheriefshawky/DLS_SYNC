package com.isoft.dls.sync.web.rest;

import com.isoft.dls.sync.DlssyncApp;
import com.isoft.dls.sync.domain.DlsRequests;
import com.isoft.dls.sync.repository.DlsRequestsRepository;
import com.isoft.dls.sync.service.DlsRequestsService;
import com.isoft.dls.sync.web.rest.errors.ExceptionTranslator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.Validator;

import javax.persistence.EntityManager;
import java.util.List;

import static com.isoft.dls.sync.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Integration tests for the {@link DlsRequestsResource} REST controller.
 */
@SpringBootTest(classes = DlssyncApp.class)
public class DlsRequestsResourceIT {

    private static final Integer DEFAULT_TRANSACTION_TYPE = 1;
    private static final Integer UPDATED_TRANSACTION_TYPE = 2;

    private static final Long DEFAULT_REQUEST_NO = 1L;
    private static final Long UPDATED_REQUEST_NO = 2L;

    private static final Boolean DEFAULT_EXPORTED = false;
    private static final Boolean UPDATED_EXPORTED = true;

    @Autowired
    private DlsRequestsRepository dlsRequestsRepository;

    @Autowired
    private DlsRequestsService dlsRequestsService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    @Autowired
    private Validator validator;

    private MockMvc restDlsRequestsMockMvc;

    private DlsRequests dlsRequests;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final DlsRequestsResource dlsRequestsResource = new DlsRequestsResource(dlsRequestsService);
        this.restDlsRequestsMockMvc = MockMvcBuilders.standaloneSetup(dlsRequestsResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setControllerAdvice(exceptionTranslator)
            .setConversionService(createFormattingConversionService())
            .setMessageConverters(jacksonMessageConverter)
            .setValidator(validator).build();
    }

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static DlsRequests createEntity(EntityManager em) {
        DlsRequests dlsRequests = new DlsRequests()
            .transactionType(DEFAULT_TRANSACTION_TYPE)
            .requestNo(DEFAULT_REQUEST_NO)
            .exported(DEFAULT_EXPORTED);
        return dlsRequests;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static DlsRequests createUpdatedEntity(EntityManager em) {
        DlsRequests dlsRequests = new DlsRequests()
            .transactionType(UPDATED_TRANSACTION_TYPE)
            .requestNo(UPDATED_REQUEST_NO)
            .exported(UPDATED_EXPORTED);
        return dlsRequests;
    }

    @BeforeEach
    public void initTest() {
        dlsRequests = createEntity(em);
    }

    @Test
    @Transactional
    public void createDlsRequests() throws Exception {
        int databaseSizeBeforeCreate = dlsRequestsRepository.findAll().size();

        // Create the DlsRequests
        restDlsRequestsMockMvc.perform(post("/api/dls-requests")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(dlsRequests)))
            .andExpect(status().isCreated());

        // Validate the DlsRequests in the database
        List<DlsRequests> dlsRequestsList = dlsRequestsRepository.findAll();
        assertThat(dlsRequestsList).hasSize(databaseSizeBeforeCreate + 1);
        DlsRequests testDlsRequests = dlsRequestsList.get(dlsRequestsList.size() - 1);
        assertThat(testDlsRequests.getTransactionType()).isEqualTo(DEFAULT_TRANSACTION_TYPE);
        assertThat(testDlsRequests.getRequestNo()).isEqualTo(DEFAULT_REQUEST_NO);
        assertThat(testDlsRequests.isExported()).isEqualTo(DEFAULT_EXPORTED);
    }

    @Test
    @Transactional
    public void createDlsRequestsWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = dlsRequestsRepository.findAll().size();

        // Create the DlsRequests with an existing ID
        dlsRequests.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restDlsRequestsMockMvc.perform(post("/api/dls-requests")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(dlsRequests)))
            .andExpect(status().isBadRequest());

        // Validate the DlsRequests in the database
        List<DlsRequests> dlsRequestsList = dlsRequestsRepository.findAll();
        assertThat(dlsRequestsList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkTransactionTypeIsRequired() throws Exception {
        int databaseSizeBeforeTest = dlsRequestsRepository.findAll().size();
        // set the field null
        dlsRequests.setTransactionType(null);

        // Create the DlsRequests, which fails.

        restDlsRequestsMockMvc.perform(post("/api/dls-requests")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(dlsRequests)))
            .andExpect(status().isBadRequest());

        List<DlsRequests> dlsRequestsList = dlsRequestsRepository.findAll();
        assertThat(dlsRequestsList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllDlsRequests() throws Exception {
        // Initialize the database
        dlsRequestsRepository.saveAndFlush(dlsRequests);

        // Get all the dlsRequestsList
        restDlsRequestsMockMvc.perform(get("/api/dls-requests?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(dlsRequests.getId().intValue())))
            .andExpect(jsonPath("$.[*].transactionType").value(hasItem(DEFAULT_TRANSACTION_TYPE)))
            .andExpect(jsonPath("$.[*].requestNo").value(hasItem(DEFAULT_REQUEST_NO.intValue())))
            .andExpect(jsonPath("$.[*].exported").value(hasItem(DEFAULT_EXPORTED.booleanValue())));
    }
    
    @Test
    @Transactional
    public void getDlsRequests() throws Exception {
        // Initialize the database
        dlsRequestsRepository.saveAndFlush(dlsRequests);

        // Get the dlsRequests
        restDlsRequestsMockMvc.perform(get("/api/dls-requests/{id}", dlsRequests.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(dlsRequests.getId().intValue()))
            .andExpect(jsonPath("$.transactionType").value(DEFAULT_TRANSACTION_TYPE))
            .andExpect(jsonPath("$.requestNo").value(DEFAULT_REQUEST_NO.intValue()))
            .andExpect(jsonPath("$.exported").value(DEFAULT_EXPORTED.booleanValue()));
    }

    @Test
    @Transactional
    public void getNonExistingDlsRequests() throws Exception {
        // Get the dlsRequests
        restDlsRequestsMockMvc.perform(get("/api/dls-requests/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateDlsRequests() throws Exception {
        // Initialize the database
        dlsRequestsService.save(dlsRequests);

        int databaseSizeBeforeUpdate = dlsRequestsRepository.findAll().size();

        // Update the dlsRequests
        DlsRequests updatedDlsRequests = dlsRequestsRepository.findById(dlsRequests.getId()).get();
        // Disconnect from session so that the updates on updatedDlsRequests are not directly saved in db
        em.detach(updatedDlsRequests);
        updatedDlsRequests
            .transactionType(UPDATED_TRANSACTION_TYPE)
            .requestNo(UPDATED_REQUEST_NO)
            .exported(UPDATED_EXPORTED);

        restDlsRequestsMockMvc.perform(put("/api/dls-requests")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedDlsRequests)))
            .andExpect(status().isOk());

        // Validate the DlsRequests in the database
        List<DlsRequests> dlsRequestsList = dlsRequestsRepository.findAll();
        assertThat(dlsRequestsList).hasSize(databaseSizeBeforeUpdate);
        DlsRequests testDlsRequests = dlsRequestsList.get(dlsRequestsList.size() - 1);
        assertThat(testDlsRequests.getTransactionType()).isEqualTo(UPDATED_TRANSACTION_TYPE);
        assertThat(testDlsRequests.getRequestNo()).isEqualTo(UPDATED_REQUEST_NO);
        assertThat(testDlsRequests.isExported()).isEqualTo(UPDATED_EXPORTED);
    }

    @Test
    @Transactional
    public void updateNonExistingDlsRequests() throws Exception {
        int databaseSizeBeforeUpdate = dlsRequestsRepository.findAll().size();

        // Create the DlsRequests

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restDlsRequestsMockMvc.perform(put("/api/dls-requests")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(dlsRequests)))
            .andExpect(status().isBadRequest());

        // Validate the DlsRequests in the database
        List<DlsRequests> dlsRequestsList = dlsRequestsRepository.findAll();
        assertThat(dlsRequestsList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteDlsRequests() throws Exception {
        // Initialize the database
        dlsRequestsService.save(dlsRequests);

        int databaseSizeBeforeDelete = dlsRequestsRepository.findAll().size();

        // Delete the dlsRequests
        restDlsRequestsMockMvc.perform(delete("/api/dls-requests/{id}", dlsRequests.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<DlsRequests> dlsRequestsList = dlsRequestsRepository.findAll();
        assertThat(dlsRequestsList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
