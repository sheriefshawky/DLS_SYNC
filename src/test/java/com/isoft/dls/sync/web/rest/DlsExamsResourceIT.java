package com.isoft.dls.sync.web.rest;

import com.isoft.dls.sync.DlssyncApp;
import com.isoft.dls.sync.domain.DlsExams;
import com.isoft.dls.sync.repository.DlsExamsRepository;
import com.isoft.dls.sync.service.DlsExamsService;
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
 * Integration tests for the {@link DlsExamsResource} REST controller.
 */
@SpringBootTest(classes = DlssyncApp.class)
public class DlsExamsResourceIT {

    private static final String DEFAULT_CODE = "AAAAAAAAAA";
    private static final String UPDATED_CODE = "BBBBBBBBBB";

    private static final String DEFAULT_NAME_AR = "AAAAAAAAAA";
    private static final String UPDATED_NAME_AR = "BBBBBBBBBB";

    private static final String DEFAULT_NAME_EN = "AAAAAAAAAA";
    private static final String UPDATED_NAME_EN = "BBBBBBBBBB";

    private static final String DEFAULT_TEST_ID = "AAAAAAAAAA";
    private static final String UPDATED_TEST_ID = "BBBBBBBBBB";

    private static final String DEFAULT_QROUP_ID = "AAAAAAAAAA";
    private static final String UPDATED_QROUP_ID = "BBBBBBBBBB";

    private static final Double DEFAULT_PASS_PERCENTAGE = 1D;
    private static final Double UPDATED_PASS_PERCENTAGE = 2D;

    @Autowired
    private DlsExamsRepository dlsExamsRepository;

    @Autowired
    private DlsExamsService dlsExamsService;

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

    private MockMvc restDlsExamsMockMvc;

    private DlsExams dlsExams;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final DlsExamsResource dlsExamsResource = new DlsExamsResource(dlsExamsService);
        this.restDlsExamsMockMvc = MockMvcBuilders.standaloneSetup(dlsExamsResource)
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
    public static DlsExams createEntity(EntityManager em) {
        DlsExams dlsExams = new DlsExams()
            .code(DEFAULT_CODE)
            .nameAr(DEFAULT_NAME_AR)
            .nameEn(DEFAULT_NAME_EN)
            .testId(DEFAULT_TEST_ID)
            .qroupId(DEFAULT_QROUP_ID)
            .passPercentage(DEFAULT_PASS_PERCENTAGE);
        return dlsExams;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static DlsExams createUpdatedEntity(EntityManager em) {
        DlsExams dlsExams = new DlsExams()
            .code(UPDATED_CODE)
            .nameAr(UPDATED_NAME_AR)
            .nameEn(UPDATED_NAME_EN)
            .testId(UPDATED_TEST_ID)
            .qroupId(UPDATED_QROUP_ID)
            .passPercentage(UPDATED_PASS_PERCENTAGE);
        return dlsExams;
    }

    @BeforeEach
    public void initTest() {
        dlsExams = createEntity(em);
    }

    @Test
    @Transactional
    public void createDlsExams() throws Exception {
        int databaseSizeBeforeCreate = dlsExamsRepository.findAll().size();

        // Create the DlsExams
        restDlsExamsMockMvc.perform(post("/api/dls-exams")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(dlsExams)))
            .andExpect(status().isCreated());

        // Validate the DlsExams in the database
        List<DlsExams> dlsExamsList = dlsExamsRepository.findAll();
        assertThat(dlsExamsList).hasSize(databaseSizeBeforeCreate + 1);
        DlsExams testDlsExams = dlsExamsList.get(dlsExamsList.size() - 1);
        assertThat(testDlsExams.getCode()).isEqualTo(DEFAULT_CODE);
        assertThat(testDlsExams.getNameAr()).isEqualTo(DEFAULT_NAME_AR);
        assertThat(testDlsExams.getNameEn()).isEqualTo(DEFAULT_NAME_EN);
        assertThat(testDlsExams.getTestId()).isEqualTo(DEFAULT_TEST_ID);
        assertThat(testDlsExams.getQroupId()).isEqualTo(DEFAULT_QROUP_ID);
        assertThat(testDlsExams.getPassPercentage()).isEqualTo(DEFAULT_PASS_PERCENTAGE);
    }

    @Test
    @Transactional
    public void createDlsExamsWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = dlsExamsRepository.findAll().size();

        // Create the DlsExams with an existing ID
        dlsExams.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restDlsExamsMockMvc.perform(post("/api/dls-exams")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(dlsExams)))
            .andExpect(status().isBadRequest());

        // Validate the DlsExams in the database
        List<DlsExams> dlsExamsList = dlsExamsRepository.findAll();
        assertThat(dlsExamsList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkCodeIsRequired() throws Exception {
        int databaseSizeBeforeTest = dlsExamsRepository.findAll().size();
        // set the field null
        dlsExams.setCode(null);

        // Create the DlsExams, which fails.

        restDlsExamsMockMvc.perform(post("/api/dls-exams")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(dlsExams)))
            .andExpect(status().isBadRequest());

        List<DlsExams> dlsExamsList = dlsExamsRepository.findAll();
        assertThat(dlsExamsList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkNameArIsRequired() throws Exception {
        int databaseSizeBeforeTest = dlsExamsRepository.findAll().size();
        // set the field null
        dlsExams.setNameAr(null);

        // Create the DlsExams, which fails.

        restDlsExamsMockMvc.perform(post("/api/dls-exams")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(dlsExams)))
            .andExpect(status().isBadRequest());

        List<DlsExams> dlsExamsList = dlsExamsRepository.findAll();
        assertThat(dlsExamsList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkNameEnIsRequired() throws Exception {
        int databaseSizeBeforeTest = dlsExamsRepository.findAll().size();
        // set the field null
        dlsExams.setNameEn(null);

        // Create the DlsExams, which fails.

        restDlsExamsMockMvc.perform(post("/api/dls-exams")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(dlsExams)))
            .andExpect(status().isBadRequest());

        List<DlsExams> dlsExamsList = dlsExamsRepository.findAll();
        assertThat(dlsExamsList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkTestIdIsRequired() throws Exception {
        int databaseSizeBeforeTest = dlsExamsRepository.findAll().size();
        // set the field null
        dlsExams.setTestId(null);

        // Create the DlsExams, which fails.

        restDlsExamsMockMvc.perform(post("/api/dls-exams")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(dlsExams)))
            .andExpect(status().isBadRequest());

        List<DlsExams> dlsExamsList = dlsExamsRepository.findAll();
        assertThat(dlsExamsList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkQroupIdIsRequired() throws Exception {
        int databaseSizeBeforeTest = dlsExamsRepository.findAll().size();
        // set the field null
        dlsExams.setQroupId(null);

        // Create the DlsExams, which fails.

        restDlsExamsMockMvc.perform(post("/api/dls-exams")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(dlsExams)))
            .andExpect(status().isBadRequest());

        List<DlsExams> dlsExamsList = dlsExamsRepository.findAll();
        assertThat(dlsExamsList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkPassPercentageIsRequired() throws Exception {
        int databaseSizeBeforeTest = dlsExamsRepository.findAll().size();
        // set the field null
        dlsExams.setPassPercentage(null);

        // Create the DlsExams, which fails.

        restDlsExamsMockMvc.perform(post("/api/dls-exams")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(dlsExams)))
            .andExpect(status().isBadRequest());

        List<DlsExams> dlsExamsList = dlsExamsRepository.findAll();
        assertThat(dlsExamsList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllDlsExams() throws Exception {
        // Initialize the database
        dlsExamsRepository.saveAndFlush(dlsExams);

        // Get all the dlsExamsList
        restDlsExamsMockMvc.perform(get("/api/dls-exams?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(dlsExams.getId().intValue())))
            .andExpect(jsonPath("$.[*].code").value(hasItem(DEFAULT_CODE)))
            .andExpect(jsonPath("$.[*].nameAr").value(hasItem(DEFAULT_NAME_AR)))
            .andExpect(jsonPath("$.[*].nameEn").value(hasItem(DEFAULT_NAME_EN)))
            .andExpect(jsonPath("$.[*].testId").value(hasItem(DEFAULT_TEST_ID)))
            .andExpect(jsonPath("$.[*].qroupId").value(hasItem(DEFAULT_QROUP_ID)))
            .andExpect(jsonPath("$.[*].passPercentage").value(hasItem(DEFAULT_PASS_PERCENTAGE.doubleValue())));
    }
    
    @Test
    @Transactional
    public void getDlsExams() throws Exception {
        // Initialize the database
        dlsExamsRepository.saveAndFlush(dlsExams);

        // Get the dlsExams
        restDlsExamsMockMvc.perform(get("/api/dls-exams/{id}", dlsExams.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(dlsExams.getId().intValue()))
            .andExpect(jsonPath("$.code").value(DEFAULT_CODE))
            .andExpect(jsonPath("$.nameAr").value(DEFAULT_NAME_AR))
            .andExpect(jsonPath("$.nameEn").value(DEFAULT_NAME_EN))
            .andExpect(jsonPath("$.testId").value(DEFAULT_TEST_ID))
            .andExpect(jsonPath("$.qroupId").value(DEFAULT_QROUP_ID))
            .andExpect(jsonPath("$.passPercentage").value(DEFAULT_PASS_PERCENTAGE.doubleValue()));
    }

    @Test
    @Transactional
    public void getNonExistingDlsExams() throws Exception {
        // Get the dlsExams
        restDlsExamsMockMvc.perform(get("/api/dls-exams/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateDlsExams() throws Exception {
        // Initialize the database
        dlsExamsService.save(dlsExams);

        int databaseSizeBeforeUpdate = dlsExamsRepository.findAll().size();

        // Update the dlsExams
        DlsExams updatedDlsExams = dlsExamsRepository.findById(dlsExams.getId()).get();
        // Disconnect from session so that the updates on updatedDlsExams are not directly saved in db
        em.detach(updatedDlsExams);
        updatedDlsExams
            .code(UPDATED_CODE)
            .nameAr(UPDATED_NAME_AR)
            .nameEn(UPDATED_NAME_EN)
            .testId(UPDATED_TEST_ID)
            .qroupId(UPDATED_QROUP_ID)
            .passPercentage(UPDATED_PASS_PERCENTAGE);

        restDlsExamsMockMvc.perform(put("/api/dls-exams")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedDlsExams)))
            .andExpect(status().isOk());

        // Validate the DlsExams in the database
        List<DlsExams> dlsExamsList = dlsExamsRepository.findAll();
        assertThat(dlsExamsList).hasSize(databaseSizeBeforeUpdate);
        DlsExams testDlsExams = dlsExamsList.get(dlsExamsList.size() - 1);
        assertThat(testDlsExams.getCode()).isEqualTo(UPDATED_CODE);
        assertThat(testDlsExams.getNameAr()).isEqualTo(UPDATED_NAME_AR);
        assertThat(testDlsExams.getNameEn()).isEqualTo(UPDATED_NAME_EN);
        assertThat(testDlsExams.getTestId()).isEqualTo(UPDATED_TEST_ID);
        assertThat(testDlsExams.getQroupId()).isEqualTo(UPDATED_QROUP_ID);
        assertThat(testDlsExams.getPassPercentage()).isEqualTo(UPDATED_PASS_PERCENTAGE);
    }

    @Test
    @Transactional
    public void updateNonExistingDlsExams() throws Exception {
        int databaseSizeBeforeUpdate = dlsExamsRepository.findAll().size();

        // Create the DlsExams

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restDlsExamsMockMvc.perform(put("/api/dls-exams")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(dlsExams)))
            .andExpect(status().isBadRequest());

        // Validate the DlsExams in the database
        List<DlsExams> dlsExamsList = dlsExamsRepository.findAll();
        assertThat(dlsExamsList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteDlsExams() throws Exception {
        // Initialize the database
        dlsExamsService.save(dlsExams);

        int databaseSizeBeforeDelete = dlsExamsRepository.findAll().size();

        // Delete the dlsExams
        restDlsExamsMockMvc.perform(delete("/api/dls-exams/{id}", dlsExams.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<DlsExams> dlsExamsList = dlsExamsRepository.findAll();
        assertThat(dlsExamsList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
