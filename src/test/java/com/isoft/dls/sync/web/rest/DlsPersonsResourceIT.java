package com.isoft.dls.sync.web.rest;

import com.isoft.dls.sync.DlssyncApp;
import com.isoft.dls.sync.domain.DlsPersons;
import com.isoft.dls.sync.repository.DlsPersonsRepository;
import com.isoft.dls.sync.service.DlsPersonsService;
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
 * Integration tests for the {@link DlsPersonsResource} REST controller.
 */
@SpringBootTest(classes = DlssyncApp.class)
public class DlsPersonsResourceIT {

    private static final String DEFAULT_FULL_NAME = "AAAAAAAAAA";
    private static final String UPDATED_FULL_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_MOBILE_NO = "AAAAAAAAAA";
    private static final String UPDATED_MOBILE_NO = "BBBBBBBBBB";

    private static final Integer DEFAULT_LICENSE_CATEGORY = 1;
    private static final Integer UPDATED_LICENSE_CATEGORY = 2;

    private static final String DEFAULT_NATIONAL_ID = "AAAAAAAAAA";
    private static final String UPDATED_NATIONAL_ID = "BBBBBBBBBB";

    private static final String DEFAULT_PASSPORT_KEY = "AAAAAAAAAA";
    private static final String UPDATED_PASSPORT_KEY = "BBBBBBBBBB";

    private static final Integer DEFAULT_TRANSACTION_TYPE = 1;
    private static final Integer UPDATED_TRANSACTION_TYPE = 2;

    private static final Boolean DEFAULT_EXPORTED = false;
    private static final Boolean UPDATED_EXPORTED = true;

    @Autowired
    private DlsPersonsRepository dlsPersonsRepository;

    @Autowired
    private DlsPersonsService dlsPersonsService;

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

    private MockMvc restDlsPersonsMockMvc;

    private DlsPersons dlsPersons;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final DlsPersonsResource dlsPersonsResource = new DlsPersonsResource(dlsPersonsService);
        this.restDlsPersonsMockMvc = MockMvcBuilders.standaloneSetup(dlsPersonsResource)
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
    public static DlsPersons createEntity(EntityManager em) {
        DlsPersons dlsPersons = new DlsPersons()
            .fullName(DEFAULT_FULL_NAME)
            .mobileNo(DEFAULT_MOBILE_NO)
            .licenseCategory(DEFAULT_LICENSE_CATEGORY)
            .nationalId(DEFAULT_NATIONAL_ID)
            .passportKey(DEFAULT_PASSPORT_KEY)
            .transactionType(DEFAULT_TRANSACTION_TYPE)
            .exported(DEFAULT_EXPORTED);
        return dlsPersons;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static DlsPersons createUpdatedEntity(EntityManager em) {
        DlsPersons dlsPersons = new DlsPersons()
            .fullName(UPDATED_FULL_NAME)
            .mobileNo(UPDATED_MOBILE_NO)
            .licenseCategory(UPDATED_LICENSE_CATEGORY)
            .nationalId(UPDATED_NATIONAL_ID)
            .passportKey(UPDATED_PASSPORT_KEY)
            .transactionType(UPDATED_TRANSACTION_TYPE)
            .exported(UPDATED_EXPORTED);
        return dlsPersons;
    }

    @BeforeEach
    public void initTest() {
        dlsPersons = createEntity(em);
    }

    @Test
    @Transactional
    public void createDlsPersons() throws Exception {
        int databaseSizeBeforeCreate = dlsPersonsRepository.findAll().size();

        // Create the DlsPersons
        restDlsPersonsMockMvc.perform(post("/api/dls-persons")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(dlsPersons)))
            .andExpect(status().isCreated());

        // Validate the DlsPersons in the database
        List<DlsPersons> dlsPersonsList = dlsPersonsRepository.findAll();
        assertThat(dlsPersonsList).hasSize(databaseSizeBeforeCreate + 1);
        DlsPersons testDlsPersons = dlsPersonsList.get(dlsPersonsList.size() - 1);
        assertThat(testDlsPersons.getFullName()).isEqualTo(DEFAULT_FULL_NAME);
        assertThat(testDlsPersons.getMobileNo()).isEqualTo(DEFAULT_MOBILE_NO);
        assertThat(testDlsPersons.getLicenseCategory()).isEqualTo(DEFAULT_LICENSE_CATEGORY);
        assertThat(testDlsPersons.getNationalId()).isEqualTo(DEFAULT_NATIONAL_ID);
        assertThat(testDlsPersons.getPassportKey()).isEqualTo(DEFAULT_PASSPORT_KEY);
        assertThat(testDlsPersons.getTransactionType()).isEqualTo(DEFAULT_TRANSACTION_TYPE);
        assertThat(testDlsPersons.isExported()).isEqualTo(DEFAULT_EXPORTED);
    }

    @Test
    @Transactional
    public void createDlsPersonsWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = dlsPersonsRepository.findAll().size();

        // Create the DlsPersons with an existing ID
        dlsPersons.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restDlsPersonsMockMvc.perform(post("/api/dls-persons")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(dlsPersons)))
            .andExpect(status().isBadRequest());

        // Validate the DlsPersons in the database
        List<DlsPersons> dlsPersonsList = dlsPersonsRepository.findAll();
        assertThat(dlsPersonsList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkFullNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = dlsPersonsRepository.findAll().size();
        // set the field null
        dlsPersons.setFullName(null);

        // Create the DlsPersons, which fails.

        restDlsPersonsMockMvc.perform(post("/api/dls-persons")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(dlsPersons)))
            .andExpect(status().isBadRequest());

        List<DlsPersons> dlsPersonsList = dlsPersonsRepository.findAll();
        assertThat(dlsPersonsList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkMobileNoIsRequired() throws Exception {
        int databaseSizeBeforeTest = dlsPersonsRepository.findAll().size();
        // set the field null
        dlsPersons.setMobileNo(null);

        // Create the DlsPersons, which fails.

        restDlsPersonsMockMvc.perform(post("/api/dls-persons")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(dlsPersons)))
            .andExpect(status().isBadRequest());

        List<DlsPersons> dlsPersonsList = dlsPersonsRepository.findAll();
        assertThat(dlsPersonsList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkLicenseCategoryIsRequired() throws Exception {
        int databaseSizeBeforeTest = dlsPersonsRepository.findAll().size();
        // set the field null
        dlsPersons.setLicenseCategory(null);

        // Create the DlsPersons, which fails.

        restDlsPersonsMockMvc.perform(post("/api/dls-persons")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(dlsPersons)))
            .andExpect(status().isBadRequest());

        List<DlsPersons> dlsPersonsList = dlsPersonsRepository.findAll();
        assertThat(dlsPersonsList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkTransactionTypeIsRequired() throws Exception {
        int databaseSizeBeforeTest = dlsPersonsRepository.findAll().size();
        // set the field null
        dlsPersons.setTransactionType(null);

        // Create the DlsPersons, which fails.

        restDlsPersonsMockMvc.perform(post("/api/dls-persons")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(dlsPersons)))
            .andExpect(status().isBadRequest());

        List<DlsPersons> dlsPersonsList = dlsPersonsRepository.findAll();
        assertThat(dlsPersonsList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllDlsPersons() throws Exception {
        // Initialize the database
        dlsPersonsRepository.saveAndFlush(dlsPersons);

        // Get all the dlsPersonsList
        restDlsPersonsMockMvc.perform(get("/api/dls-persons?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(dlsPersons.getId().intValue())))
            .andExpect(jsonPath("$.[*].fullName").value(hasItem(DEFAULT_FULL_NAME)))
            .andExpect(jsonPath("$.[*].mobileNo").value(hasItem(DEFAULT_MOBILE_NO)))
            .andExpect(jsonPath("$.[*].licenseCategory").value(hasItem(DEFAULT_LICENSE_CATEGORY)))
            .andExpect(jsonPath("$.[*].nationalId").value(hasItem(DEFAULT_NATIONAL_ID)))
            .andExpect(jsonPath("$.[*].passportKey").value(hasItem(DEFAULT_PASSPORT_KEY)))
            .andExpect(jsonPath("$.[*].transactionType").value(hasItem(DEFAULT_TRANSACTION_TYPE)))
            .andExpect(jsonPath("$.[*].exported").value(hasItem(DEFAULT_EXPORTED.booleanValue())));
    }
    
    @Test
    @Transactional
    public void getDlsPersons() throws Exception {
        // Initialize the database
        dlsPersonsRepository.saveAndFlush(dlsPersons);

        // Get the dlsPersons
        restDlsPersonsMockMvc.perform(get("/api/dls-persons/{id}", dlsPersons.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(dlsPersons.getId().intValue()))
            .andExpect(jsonPath("$.fullName").value(DEFAULT_FULL_NAME))
            .andExpect(jsonPath("$.mobileNo").value(DEFAULT_MOBILE_NO))
            .andExpect(jsonPath("$.licenseCategory").value(DEFAULT_LICENSE_CATEGORY))
            .andExpect(jsonPath("$.nationalId").value(DEFAULT_NATIONAL_ID))
            .andExpect(jsonPath("$.passportKey").value(DEFAULT_PASSPORT_KEY))
            .andExpect(jsonPath("$.transactionType").value(DEFAULT_TRANSACTION_TYPE))
            .andExpect(jsonPath("$.exported").value(DEFAULT_EXPORTED.booleanValue()));
    }

    @Test
    @Transactional
    public void getNonExistingDlsPersons() throws Exception {
        // Get the dlsPersons
        restDlsPersonsMockMvc.perform(get("/api/dls-persons/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateDlsPersons() throws Exception {
        // Initialize the database
        dlsPersonsService.save(dlsPersons);

        int databaseSizeBeforeUpdate = dlsPersonsRepository.findAll().size();

        // Update the dlsPersons
        DlsPersons updatedDlsPersons = dlsPersonsRepository.findById(dlsPersons.getId()).get();
        // Disconnect from session so that the updates on updatedDlsPersons are not directly saved in db
        em.detach(updatedDlsPersons);
        updatedDlsPersons
            .fullName(UPDATED_FULL_NAME)
            .mobileNo(UPDATED_MOBILE_NO)
            .licenseCategory(UPDATED_LICENSE_CATEGORY)
            .nationalId(UPDATED_NATIONAL_ID)
            .passportKey(UPDATED_PASSPORT_KEY)
            .transactionType(UPDATED_TRANSACTION_TYPE)
            .exported(UPDATED_EXPORTED);

        restDlsPersonsMockMvc.perform(put("/api/dls-persons")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedDlsPersons)))
            .andExpect(status().isOk());

        // Validate the DlsPersons in the database
        List<DlsPersons> dlsPersonsList = dlsPersonsRepository.findAll();
        assertThat(dlsPersonsList).hasSize(databaseSizeBeforeUpdate);
        DlsPersons testDlsPersons = dlsPersonsList.get(dlsPersonsList.size() - 1);
        assertThat(testDlsPersons.getFullName()).isEqualTo(UPDATED_FULL_NAME);
        assertThat(testDlsPersons.getMobileNo()).isEqualTo(UPDATED_MOBILE_NO);
        assertThat(testDlsPersons.getLicenseCategory()).isEqualTo(UPDATED_LICENSE_CATEGORY);
        assertThat(testDlsPersons.getNationalId()).isEqualTo(UPDATED_NATIONAL_ID);
        assertThat(testDlsPersons.getPassportKey()).isEqualTo(UPDATED_PASSPORT_KEY);
        assertThat(testDlsPersons.getTransactionType()).isEqualTo(UPDATED_TRANSACTION_TYPE);
        assertThat(testDlsPersons.isExported()).isEqualTo(UPDATED_EXPORTED);
    }

    @Test
    @Transactional
    public void updateNonExistingDlsPersons() throws Exception {
        int databaseSizeBeforeUpdate = dlsPersonsRepository.findAll().size();

        // Create the DlsPersons

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restDlsPersonsMockMvc.perform(put("/api/dls-persons")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(dlsPersons)))
            .andExpect(status().isBadRequest());

        // Validate the DlsPersons in the database
        List<DlsPersons> dlsPersonsList = dlsPersonsRepository.findAll();
        assertThat(dlsPersonsList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteDlsPersons() throws Exception {
        // Initialize the database
        dlsPersonsService.save(dlsPersons);

        int databaseSizeBeforeDelete = dlsPersonsRepository.findAll().size();

        // Delete the dlsPersons
        restDlsPersonsMockMvc.perform(delete("/api/dls-persons/{id}", dlsPersons.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<DlsPersons> dlsPersonsList = dlsPersonsRepository.findAll();
        assertThat(dlsPersonsList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
