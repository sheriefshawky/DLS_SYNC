package com.isoft.dls.sync.web.rest;

import com.isoft.dls.sync.DlssyncApp;
import com.isoft.dls.sync.domain.DlsVhlTrsExams;
import com.isoft.dls.sync.repository.DlsVhlTrsExamsRepository;
import com.isoft.dls.sync.service.DlsVhlTrsExamsService;
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
 * Integration tests for the {@link DlsVhlTrsExamsResource} REST controller.
 */
@SpringBootTest(classes = DlssyncApp.class)
public class DlsVhlTrsExamsResourceIT {

    private static final String DEFAULT_CODE = "AAAAAAAAAA";
    private static final String UPDATED_CODE = "BBBBBBBBBB";

    private static final String DEFAULT_NAME_AR = "AAAAAAAAAA";
    private static final String UPDATED_NAME_AR = "BBBBBBBBBB";

    private static final String DEFAULT_NAME_EN = "AAAAAAAAAA";
    private static final String UPDATED_NAME_EN = "BBBBBBBBBB";

    @Autowired
    private DlsVhlTrsExamsRepository dlsVhlTrsExamsRepository;

    @Autowired
    private DlsVhlTrsExamsService dlsVhlTrsExamsService;

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

    private MockMvc restDlsVhlTrsExamsMockMvc;

    private DlsVhlTrsExams dlsVhlTrsExams;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final DlsVhlTrsExamsResource dlsVhlTrsExamsResource = new DlsVhlTrsExamsResource(dlsVhlTrsExamsService);
        this.restDlsVhlTrsExamsMockMvc = MockMvcBuilders.standaloneSetup(dlsVhlTrsExamsResource)
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
    public static DlsVhlTrsExams createEntity(EntityManager em) {
        DlsVhlTrsExams dlsVhlTrsExams = new DlsVhlTrsExams()
            .code(DEFAULT_CODE)
            .nameAr(DEFAULT_NAME_AR)
            .nameEn(DEFAULT_NAME_EN);
        return dlsVhlTrsExams;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static DlsVhlTrsExams createUpdatedEntity(EntityManager em) {
        DlsVhlTrsExams dlsVhlTrsExams = new DlsVhlTrsExams()
            .code(UPDATED_CODE)
            .nameAr(UPDATED_NAME_AR)
            .nameEn(UPDATED_NAME_EN);
        return dlsVhlTrsExams;
    }

    @BeforeEach
    public void initTest() {
        dlsVhlTrsExams = createEntity(em);
    }

    @Test
    @Transactional
    public void createDlsVhlTrsExams() throws Exception {
        int databaseSizeBeforeCreate = dlsVhlTrsExamsRepository.findAll().size();

        // Create the DlsVhlTrsExams
        restDlsVhlTrsExamsMockMvc.perform(post("/api/dls-vhl-trs-exams")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(dlsVhlTrsExams)))
            .andExpect(status().isCreated());

        // Validate the DlsVhlTrsExams in the database
        List<DlsVhlTrsExams> dlsVhlTrsExamsList = dlsVhlTrsExamsRepository.findAll();
        assertThat(dlsVhlTrsExamsList).hasSize(databaseSizeBeforeCreate + 1);
        DlsVhlTrsExams testDlsVhlTrsExams = dlsVhlTrsExamsList.get(dlsVhlTrsExamsList.size() - 1);
        assertThat(testDlsVhlTrsExams.getCode()).isEqualTo(DEFAULT_CODE);
        assertThat(testDlsVhlTrsExams.getNameAr()).isEqualTo(DEFAULT_NAME_AR);
        assertThat(testDlsVhlTrsExams.getNameEn()).isEqualTo(DEFAULT_NAME_EN);
    }

    @Test
    @Transactional
    public void createDlsVhlTrsExamsWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = dlsVhlTrsExamsRepository.findAll().size();

        // Create the DlsVhlTrsExams with an existing ID
        dlsVhlTrsExams.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restDlsVhlTrsExamsMockMvc.perform(post("/api/dls-vhl-trs-exams")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(dlsVhlTrsExams)))
            .andExpect(status().isBadRequest());

        // Validate the DlsVhlTrsExams in the database
        List<DlsVhlTrsExams> dlsVhlTrsExamsList = dlsVhlTrsExamsRepository.findAll();
        assertThat(dlsVhlTrsExamsList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkCodeIsRequired() throws Exception {
        int databaseSizeBeforeTest = dlsVhlTrsExamsRepository.findAll().size();
        // set the field null
        dlsVhlTrsExams.setCode(null);

        // Create the DlsVhlTrsExams, which fails.

        restDlsVhlTrsExamsMockMvc.perform(post("/api/dls-vhl-trs-exams")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(dlsVhlTrsExams)))
            .andExpect(status().isBadRequest());

        List<DlsVhlTrsExams> dlsVhlTrsExamsList = dlsVhlTrsExamsRepository.findAll();
        assertThat(dlsVhlTrsExamsList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkNameArIsRequired() throws Exception {
        int databaseSizeBeforeTest = dlsVhlTrsExamsRepository.findAll().size();
        // set the field null
        dlsVhlTrsExams.setNameAr(null);

        // Create the DlsVhlTrsExams, which fails.

        restDlsVhlTrsExamsMockMvc.perform(post("/api/dls-vhl-trs-exams")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(dlsVhlTrsExams)))
            .andExpect(status().isBadRequest());

        List<DlsVhlTrsExams> dlsVhlTrsExamsList = dlsVhlTrsExamsRepository.findAll();
        assertThat(dlsVhlTrsExamsList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkNameEnIsRequired() throws Exception {
        int databaseSizeBeforeTest = dlsVhlTrsExamsRepository.findAll().size();
        // set the field null
        dlsVhlTrsExams.setNameEn(null);

        // Create the DlsVhlTrsExams, which fails.

        restDlsVhlTrsExamsMockMvc.perform(post("/api/dls-vhl-trs-exams")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(dlsVhlTrsExams)))
            .andExpect(status().isBadRequest());

        List<DlsVhlTrsExams> dlsVhlTrsExamsList = dlsVhlTrsExamsRepository.findAll();
        assertThat(dlsVhlTrsExamsList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllDlsVhlTrsExams() throws Exception {
        // Initialize the database
        dlsVhlTrsExamsRepository.saveAndFlush(dlsVhlTrsExams);

        // Get all the dlsVhlTrsExamsList
        restDlsVhlTrsExamsMockMvc.perform(get("/api/dls-vhl-trs-exams?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(dlsVhlTrsExams.getId().intValue())))
            .andExpect(jsonPath("$.[*].code").value(hasItem(DEFAULT_CODE)))
            .andExpect(jsonPath("$.[*].nameAr").value(hasItem(DEFAULT_NAME_AR)))
            .andExpect(jsonPath("$.[*].nameEn").value(hasItem(DEFAULT_NAME_EN)));
    }
    
    @Test
    @Transactional
    public void getDlsVhlTrsExams() throws Exception {
        // Initialize the database
        dlsVhlTrsExamsRepository.saveAndFlush(dlsVhlTrsExams);

        // Get the dlsVhlTrsExams
        restDlsVhlTrsExamsMockMvc.perform(get("/api/dls-vhl-trs-exams/{id}", dlsVhlTrsExams.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(dlsVhlTrsExams.getId().intValue()))
            .andExpect(jsonPath("$.code").value(DEFAULT_CODE))
            .andExpect(jsonPath("$.nameAr").value(DEFAULT_NAME_AR))
            .andExpect(jsonPath("$.nameEn").value(DEFAULT_NAME_EN));
    }

    @Test
    @Transactional
    public void getNonExistingDlsVhlTrsExams() throws Exception {
        // Get the dlsVhlTrsExams
        restDlsVhlTrsExamsMockMvc.perform(get("/api/dls-vhl-trs-exams/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateDlsVhlTrsExams() throws Exception {
        // Initialize the database
        dlsVhlTrsExamsService.save(dlsVhlTrsExams);

        int databaseSizeBeforeUpdate = dlsVhlTrsExamsRepository.findAll().size();

        // Update the dlsVhlTrsExams
        DlsVhlTrsExams updatedDlsVhlTrsExams = dlsVhlTrsExamsRepository.findById(dlsVhlTrsExams.getId()).get();
        // Disconnect from session so that the updates on updatedDlsVhlTrsExams are not directly saved in db
        em.detach(updatedDlsVhlTrsExams);
        updatedDlsVhlTrsExams
            .code(UPDATED_CODE)
            .nameAr(UPDATED_NAME_AR)
            .nameEn(UPDATED_NAME_EN);

        restDlsVhlTrsExamsMockMvc.perform(put("/api/dls-vhl-trs-exams")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedDlsVhlTrsExams)))
            .andExpect(status().isOk());

        // Validate the DlsVhlTrsExams in the database
        List<DlsVhlTrsExams> dlsVhlTrsExamsList = dlsVhlTrsExamsRepository.findAll();
        assertThat(dlsVhlTrsExamsList).hasSize(databaseSizeBeforeUpdate);
        DlsVhlTrsExams testDlsVhlTrsExams = dlsVhlTrsExamsList.get(dlsVhlTrsExamsList.size() - 1);
        assertThat(testDlsVhlTrsExams.getCode()).isEqualTo(UPDATED_CODE);
        assertThat(testDlsVhlTrsExams.getNameAr()).isEqualTo(UPDATED_NAME_AR);
        assertThat(testDlsVhlTrsExams.getNameEn()).isEqualTo(UPDATED_NAME_EN);
    }

    @Test
    @Transactional
    public void updateNonExistingDlsVhlTrsExams() throws Exception {
        int databaseSizeBeforeUpdate = dlsVhlTrsExamsRepository.findAll().size();

        // Create the DlsVhlTrsExams

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restDlsVhlTrsExamsMockMvc.perform(put("/api/dls-vhl-trs-exams")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(dlsVhlTrsExams)))
            .andExpect(status().isBadRequest());

        // Validate the DlsVhlTrsExams in the database
        List<DlsVhlTrsExams> dlsVhlTrsExamsList = dlsVhlTrsExamsRepository.findAll();
        assertThat(dlsVhlTrsExamsList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteDlsVhlTrsExams() throws Exception {
        // Initialize the database
        dlsVhlTrsExamsService.save(dlsVhlTrsExams);

        int databaseSizeBeforeDelete = dlsVhlTrsExamsRepository.findAll().size();

        // Delete the dlsVhlTrsExams
        restDlsVhlTrsExamsMockMvc.perform(delete("/api/dls-vhl-trs-exams/{id}", dlsVhlTrsExams.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<DlsVhlTrsExams> dlsVhlTrsExamsList = dlsVhlTrsExamsRepository.findAll();
        assertThat(dlsVhlTrsExamsList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
