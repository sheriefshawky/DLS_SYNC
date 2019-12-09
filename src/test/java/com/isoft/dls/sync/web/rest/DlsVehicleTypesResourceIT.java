package com.isoft.dls.sync.web.rest;

import com.isoft.dls.sync.DlssyncApp;
import com.isoft.dls.sync.domain.DlsVehicleTypes;
import com.isoft.dls.sync.repository.DlsVehicleTypesRepository;
import com.isoft.dls.sync.service.DlsVehicleTypesService;
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
 * Integration tests for the {@link DlsVehicleTypesResource} REST controller.
 */
@SpringBootTest(classes = DlssyncApp.class)
public class DlsVehicleTypesResourceIT {

    private static final String DEFAULT_CODE = "AAAAAAAAAA";
    private static final String UPDATED_CODE = "BBBBBBBBBB";

    private static final String DEFAULT_NAME_AR = "AAAAAAAAAA";
    private static final String UPDATED_NAME_AR = "BBBBBBBBBB";

    private static final String DEFAULT_NAME_EN = "AAAAAAAAAA";
    private static final String UPDATED_NAME_EN = "BBBBBBBBBB";

    @Autowired
    private DlsVehicleTypesRepository dlsVehicleTypesRepository;

    @Autowired
    private DlsVehicleTypesService dlsVehicleTypesService;

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

    private MockMvc restDlsVehicleTypesMockMvc;

    private DlsVehicleTypes dlsVehicleTypes;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final DlsVehicleTypesResource dlsVehicleTypesResource = new DlsVehicleTypesResource(dlsVehicleTypesService);
        this.restDlsVehicleTypesMockMvc = MockMvcBuilders.standaloneSetup(dlsVehicleTypesResource)
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
    public static DlsVehicleTypes createEntity(EntityManager em) {
        DlsVehicleTypes dlsVehicleTypes = new DlsVehicleTypes()
            .code(DEFAULT_CODE)
            .nameAr(DEFAULT_NAME_AR)
            .nameEn(DEFAULT_NAME_EN);
        return dlsVehicleTypes;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static DlsVehicleTypes createUpdatedEntity(EntityManager em) {
        DlsVehicleTypes dlsVehicleTypes = new DlsVehicleTypes()
            .code(UPDATED_CODE)
            .nameAr(UPDATED_NAME_AR)
            .nameEn(UPDATED_NAME_EN);
        return dlsVehicleTypes;
    }

    @BeforeEach
    public void initTest() {
        dlsVehicleTypes = createEntity(em);
    }

    @Test
    @Transactional
    public void createDlsVehicleTypes() throws Exception {
        int databaseSizeBeforeCreate = dlsVehicleTypesRepository.findAll().size();

        // Create the DlsVehicleTypes
        restDlsVehicleTypesMockMvc.perform(post("/api/dls-vehicle-types")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(dlsVehicleTypes)))
            .andExpect(status().isCreated());

        // Validate the DlsVehicleTypes in the database
        List<DlsVehicleTypes> dlsVehicleTypesList = dlsVehicleTypesRepository.findAll();
        assertThat(dlsVehicleTypesList).hasSize(databaseSizeBeforeCreate + 1);
        DlsVehicleTypes testDlsVehicleTypes = dlsVehicleTypesList.get(dlsVehicleTypesList.size() - 1);
        assertThat(testDlsVehicleTypes.getCode()).isEqualTo(DEFAULT_CODE);
        assertThat(testDlsVehicleTypes.getNameAr()).isEqualTo(DEFAULT_NAME_AR);
        assertThat(testDlsVehicleTypes.getNameEn()).isEqualTo(DEFAULT_NAME_EN);
    }

    @Test
    @Transactional
    public void createDlsVehicleTypesWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = dlsVehicleTypesRepository.findAll().size();

        // Create the DlsVehicleTypes with an existing ID
        dlsVehicleTypes.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restDlsVehicleTypesMockMvc.perform(post("/api/dls-vehicle-types")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(dlsVehicleTypes)))
            .andExpect(status().isBadRequest());

        // Validate the DlsVehicleTypes in the database
        List<DlsVehicleTypes> dlsVehicleTypesList = dlsVehicleTypesRepository.findAll();
        assertThat(dlsVehicleTypesList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkCodeIsRequired() throws Exception {
        int databaseSizeBeforeTest = dlsVehicleTypesRepository.findAll().size();
        // set the field null
        dlsVehicleTypes.setCode(null);

        // Create the DlsVehicleTypes, which fails.

        restDlsVehicleTypesMockMvc.perform(post("/api/dls-vehicle-types")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(dlsVehicleTypes)))
            .andExpect(status().isBadRequest());

        List<DlsVehicleTypes> dlsVehicleTypesList = dlsVehicleTypesRepository.findAll();
        assertThat(dlsVehicleTypesList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkNameArIsRequired() throws Exception {
        int databaseSizeBeforeTest = dlsVehicleTypesRepository.findAll().size();
        // set the field null
        dlsVehicleTypes.setNameAr(null);

        // Create the DlsVehicleTypes, which fails.

        restDlsVehicleTypesMockMvc.perform(post("/api/dls-vehicle-types")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(dlsVehicleTypes)))
            .andExpect(status().isBadRequest());

        List<DlsVehicleTypes> dlsVehicleTypesList = dlsVehicleTypesRepository.findAll();
        assertThat(dlsVehicleTypesList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkNameEnIsRequired() throws Exception {
        int databaseSizeBeforeTest = dlsVehicleTypesRepository.findAll().size();
        // set the field null
        dlsVehicleTypes.setNameEn(null);

        // Create the DlsVehicleTypes, which fails.

        restDlsVehicleTypesMockMvc.perform(post("/api/dls-vehicle-types")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(dlsVehicleTypes)))
            .andExpect(status().isBadRequest());

        List<DlsVehicleTypes> dlsVehicleTypesList = dlsVehicleTypesRepository.findAll();
        assertThat(dlsVehicleTypesList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllDlsVehicleTypes() throws Exception {
        // Initialize the database
        dlsVehicleTypesRepository.saveAndFlush(dlsVehicleTypes);

        // Get all the dlsVehicleTypesList
        restDlsVehicleTypesMockMvc.perform(get("/api/dls-vehicle-types?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(dlsVehicleTypes.getId().intValue())))
            .andExpect(jsonPath("$.[*].code").value(hasItem(DEFAULT_CODE)))
            .andExpect(jsonPath("$.[*].nameAr").value(hasItem(DEFAULT_NAME_AR)))
            .andExpect(jsonPath("$.[*].nameEn").value(hasItem(DEFAULT_NAME_EN)));
    }
    
    @Test
    @Transactional
    public void getDlsVehicleTypes() throws Exception {
        // Initialize the database
        dlsVehicleTypesRepository.saveAndFlush(dlsVehicleTypes);

        // Get the dlsVehicleTypes
        restDlsVehicleTypesMockMvc.perform(get("/api/dls-vehicle-types/{id}", dlsVehicleTypes.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(dlsVehicleTypes.getId().intValue()))
            .andExpect(jsonPath("$.code").value(DEFAULT_CODE))
            .andExpect(jsonPath("$.nameAr").value(DEFAULT_NAME_AR))
            .andExpect(jsonPath("$.nameEn").value(DEFAULT_NAME_EN));
    }

    @Test
    @Transactional
    public void getNonExistingDlsVehicleTypes() throws Exception {
        // Get the dlsVehicleTypes
        restDlsVehicleTypesMockMvc.perform(get("/api/dls-vehicle-types/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateDlsVehicleTypes() throws Exception {
        // Initialize the database
        dlsVehicleTypesService.save(dlsVehicleTypes);

        int databaseSizeBeforeUpdate = dlsVehicleTypesRepository.findAll().size();

        // Update the dlsVehicleTypes
        DlsVehicleTypes updatedDlsVehicleTypes = dlsVehicleTypesRepository.findById(dlsVehicleTypes.getId()).get();
        // Disconnect from session so that the updates on updatedDlsVehicleTypes are not directly saved in db
        em.detach(updatedDlsVehicleTypes);
        updatedDlsVehicleTypes
            .code(UPDATED_CODE)
            .nameAr(UPDATED_NAME_AR)
            .nameEn(UPDATED_NAME_EN);

        restDlsVehicleTypesMockMvc.perform(put("/api/dls-vehicle-types")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedDlsVehicleTypes)))
            .andExpect(status().isOk());

        // Validate the DlsVehicleTypes in the database
        List<DlsVehicleTypes> dlsVehicleTypesList = dlsVehicleTypesRepository.findAll();
        assertThat(dlsVehicleTypesList).hasSize(databaseSizeBeforeUpdate);
        DlsVehicleTypes testDlsVehicleTypes = dlsVehicleTypesList.get(dlsVehicleTypesList.size() - 1);
        assertThat(testDlsVehicleTypes.getCode()).isEqualTo(UPDATED_CODE);
        assertThat(testDlsVehicleTypes.getNameAr()).isEqualTo(UPDATED_NAME_AR);
        assertThat(testDlsVehicleTypes.getNameEn()).isEqualTo(UPDATED_NAME_EN);
    }

    @Test
    @Transactional
    public void updateNonExistingDlsVehicleTypes() throws Exception {
        int databaseSizeBeforeUpdate = dlsVehicleTypesRepository.findAll().size();

        // Create the DlsVehicleTypes

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restDlsVehicleTypesMockMvc.perform(put("/api/dls-vehicle-types")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(dlsVehicleTypes)))
            .andExpect(status().isBadRequest());

        // Validate the DlsVehicleTypes in the database
        List<DlsVehicleTypes> dlsVehicleTypesList = dlsVehicleTypesRepository.findAll();
        assertThat(dlsVehicleTypesList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteDlsVehicleTypes() throws Exception {
        // Initialize the database
        dlsVehicleTypesService.save(dlsVehicleTypes);

        int databaseSizeBeforeDelete = dlsVehicleTypesRepository.findAll().size();

        // Delete the dlsVehicleTypes
        restDlsVehicleTypesMockMvc.perform(delete("/api/dls-vehicle-types/{id}", dlsVehicleTypes.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<DlsVehicleTypes> dlsVehicleTypesList = dlsVehicleTypesRepository.findAll();
        assertThat(dlsVehicleTypesList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
