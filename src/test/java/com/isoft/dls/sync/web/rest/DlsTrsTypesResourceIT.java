package com.isoft.dls.sync.web.rest;

import com.isoft.dls.sync.DlssyncApp;
import com.isoft.dls.sync.domain.DlsTrsTypes;
import com.isoft.dls.sync.repository.DlsTrsTypesRepository;
import com.isoft.dls.sync.service.DlsTrsTypesService;
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
 * Integration tests for the {@link DlsTrsTypesResource} REST controller.
 */
@SpringBootTest(classes = DlssyncApp.class)
public class DlsTrsTypesResourceIT {

    private static final String DEFAULT_CODE = "AAAAAAAAAA";
    private static final String UPDATED_CODE = "BBBBBBBBBB";

    private static final String DEFAULT_NAME_AR = "AAAAAAAAAA";
    private static final String UPDATED_NAME_AR = "BBBBBBBBBB";

    private static final String DEFAULT_NAME_EN = "AAAAAAAAAA";
    private static final String UPDATED_NAME_EN = "BBBBBBBBBB";

    @Autowired
    private DlsTrsTypesRepository dlsTrsTypesRepository;

    @Autowired
    private DlsTrsTypesService dlsTrsTypesService;

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

    private MockMvc restDlsTrsTypesMockMvc;

    private DlsTrsTypes dlsTrsTypes;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final DlsTrsTypesResource dlsTrsTypesResource = new DlsTrsTypesResource(dlsTrsTypesService);
        this.restDlsTrsTypesMockMvc = MockMvcBuilders.standaloneSetup(dlsTrsTypesResource)
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
    public static DlsTrsTypes createEntity(EntityManager em) {
        DlsTrsTypes dlsTrsTypes = new DlsTrsTypes()
            .code(DEFAULT_CODE)
            .nameAr(DEFAULT_NAME_AR)
            .nameEn(DEFAULT_NAME_EN);
        return dlsTrsTypes;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static DlsTrsTypes createUpdatedEntity(EntityManager em) {
        DlsTrsTypes dlsTrsTypes = new DlsTrsTypes()
            .code(UPDATED_CODE)
            .nameAr(UPDATED_NAME_AR)
            .nameEn(UPDATED_NAME_EN);
        return dlsTrsTypes;
    }

    @BeforeEach
    public void initTest() {
        dlsTrsTypes = createEntity(em);
    }

    @Test
    @Transactional
    public void createDlsTrsTypes() throws Exception {
        int databaseSizeBeforeCreate = dlsTrsTypesRepository.findAll().size();

        // Create the DlsTrsTypes
        restDlsTrsTypesMockMvc.perform(post("/api/dls-trs-types")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(dlsTrsTypes)))
            .andExpect(status().isCreated());

        // Validate the DlsTrsTypes in the database
        List<DlsTrsTypes> dlsTrsTypesList = dlsTrsTypesRepository.findAll();
        assertThat(dlsTrsTypesList).hasSize(databaseSizeBeforeCreate + 1);
        DlsTrsTypes testDlsTrsTypes = dlsTrsTypesList.get(dlsTrsTypesList.size() - 1);
        assertThat(testDlsTrsTypes.getCode()).isEqualTo(DEFAULT_CODE);
        assertThat(testDlsTrsTypes.getNameAr()).isEqualTo(DEFAULT_NAME_AR);
        assertThat(testDlsTrsTypes.getNameEn()).isEqualTo(DEFAULT_NAME_EN);
    }

    @Test
    @Transactional
    public void createDlsTrsTypesWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = dlsTrsTypesRepository.findAll().size();

        // Create the DlsTrsTypes with an existing ID
        dlsTrsTypes.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restDlsTrsTypesMockMvc.perform(post("/api/dls-trs-types")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(dlsTrsTypes)))
            .andExpect(status().isBadRequest());

        // Validate the DlsTrsTypes in the database
        List<DlsTrsTypes> dlsTrsTypesList = dlsTrsTypesRepository.findAll();
        assertThat(dlsTrsTypesList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkCodeIsRequired() throws Exception {
        int databaseSizeBeforeTest = dlsTrsTypesRepository.findAll().size();
        // set the field null
        dlsTrsTypes.setCode(null);

        // Create the DlsTrsTypes, which fails.

        restDlsTrsTypesMockMvc.perform(post("/api/dls-trs-types")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(dlsTrsTypes)))
            .andExpect(status().isBadRequest());

        List<DlsTrsTypes> dlsTrsTypesList = dlsTrsTypesRepository.findAll();
        assertThat(dlsTrsTypesList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkNameArIsRequired() throws Exception {
        int databaseSizeBeforeTest = dlsTrsTypesRepository.findAll().size();
        // set the field null
        dlsTrsTypes.setNameAr(null);

        // Create the DlsTrsTypes, which fails.

        restDlsTrsTypesMockMvc.perform(post("/api/dls-trs-types")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(dlsTrsTypes)))
            .andExpect(status().isBadRequest());

        List<DlsTrsTypes> dlsTrsTypesList = dlsTrsTypesRepository.findAll();
        assertThat(dlsTrsTypesList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkNameEnIsRequired() throws Exception {
        int databaseSizeBeforeTest = dlsTrsTypesRepository.findAll().size();
        // set the field null
        dlsTrsTypes.setNameEn(null);

        // Create the DlsTrsTypes, which fails.

        restDlsTrsTypesMockMvc.perform(post("/api/dls-trs-types")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(dlsTrsTypes)))
            .andExpect(status().isBadRequest());

        List<DlsTrsTypes> dlsTrsTypesList = dlsTrsTypesRepository.findAll();
        assertThat(dlsTrsTypesList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllDlsTrsTypes() throws Exception {
        // Initialize the database
        dlsTrsTypesRepository.saveAndFlush(dlsTrsTypes);

        // Get all the dlsTrsTypesList
        restDlsTrsTypesMockMvc.perform(get("/api/dls-trs-types?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(dlsTrsTypes.getId().intValue())))
            .andExpect(jsonPath("$.[*].code").value(hasItem(DEFAULT_CODE)))
            .andExpect(jsonPath("$.[*].nameAr").value(hasItem(DEFAULT_NAME_AR)))
            .andExpect(jsonPath("$.[*].nameEn").value(hasItem(DEFAULT_NAME_EN)));
    }
    
    @Test
    @Transactional
    public void getDlsTrsTypes() throws Exception {
        // Initialize the database
        dlsTrsTypesRepository.saveAndFlush(dlsTrsTypes);

        // Get the dlsTrsTypes
        restDlsTrsTypesMockMvc.perform(get("/api/dls-trs-types/{id}", dlsTrsTypes.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(dlsTrsTypes.getId().intValue()))
            .andExpect(jsonPath("$.code").value(DEFAULT_CODE))
            .andExpect(jsonPath("$.nameAr").value(DEFAULT_NAME_AR))
            .andExpect(jsonPath("$.nameEn").value(DEFAULT_NAME_EN));
    }

    @Test
    @Transactional
    public void getNonExistingDlsTrsTypes() throws Exception {
        // Get the dlsTrsTypes
        restDlsTrsTypesMockMvc.perform(get("/api/dls-trs-types/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateDlsTrsTypes() throws Exception {
        // Initialize the database
        dlsTrsTypesService.save(dlsTrsTypes);

        int databaseSizeBeforeUpdate = dlsTrsTypesRepository.findAll().size();

        // Update the dlsTrsTypes
        DlsTrsTypes updatedDlsTrsTypes = dlsTrsTypesRepository.findById(dlsTrsTypes.getId()).get();
        // Disconnect from session so that the updates on updatedDlsTrsTypes are not directly saved in db
        em.detach(updatedDlsTrsTypes);
        updatedDlsTrsTypes
            .code(UPDATED_CODE)
            .nameAr(UPDATED_NAME_AR)
            .nameEn(UPDATED_NAME_EN);

        restDlsTrsTypesMockMvc.perform(put("/api/dls-trs-types")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedDlsTrsTypes)))
            .andExpect(status().isOk());

        // Validate the DlsTrsTypes in the database
        List<DlsTrsTypes> dlsTrsTypesList = dlsTrsTypesRepository.findAll();
        assertThat(dlsTrsTypesList).hasSize(databaseSizeBeforeUpdate);
        DlsTrsTypes testDlsTrsTypes = dlsTrsTypesList.get(dlsTrsTypesList.size() - 1);
        assertThat(testDlsTrsTypes.getCode()).isEqualTo(UPDATED_CODE);
        assertThat(testDlsTrsTypes.getNameAr()).isEqualTo(UPDATED_NAME_AR);
        assertThat(testDlsTrsTypes.getNameEn()).isEqualTo(UPDATED_NAME_EN);
    }

    @Test
    @Transactional
    public void updateNonExistingDlsTrsTypes() throws Exception {
        int databaseSizeBeforeUpdate = dlsTrsTypesRepository.findAll().size();

        // Create the DlsTrsTypes

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restDlsTrsTypesMockMvc.perform(put("/api/dls-trs-types")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(dlsTrsTypes)))
            .andExpect(status().isBadRequest());

        // Validate the DlsTrsTypes in the database
        List<DlsTrsTypes> dlsTrsTypesList = dlsTrsTypesRepository.findAll();
        assertThat(dlsTrsTypesList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteDlsTrsTypes() throws Exception {
        // Initialize the database
        dlsTrsTypesService.save(dlsTrsTypes);

        int databaseSizeBeforeDelete = dlsTrsTypesRepository.findAll().size();

        // Delete the dlsTrsTypes
        restDlsTrsTypesMockMvc.perform(delete("/api/dls-trs-types/{id}", dlsTrsTypes.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<DlsTrsTypes> dlsTrsTypesList = dlsTrsTypesRepository.findAll();
        assertThat(dlsTrsTypesList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
