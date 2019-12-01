package com.isoft.dls.sync.web.rest;

import com.isoft.dls.sync.DlssyncApp;
import com.isoft.dls.sync.domain.DlsReqExams;
import com.isoft.dls.sync.repository.DlsReqExamsRepository;
import com.isoft.dls.sync.service.DlsReqExamsService;
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
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.List;

import static com.isoft.dls.sync.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Integration tests for the {@link DlsReqExamsResource} REST controller.
 */
@SpringBootTest(classes = DlssyncApp.class)
public class DlsReqExamsResourceIT {

    private static final Integer DEFAULT_EXAM_CODE = 1;
    private static final Integer UPDATED_EXAM_CODE = 2;

    private static final Integer DEFAULT_EXAM_RESULT = 1;
    private static final Integer UPDATED_EXAM_RESULT = 2;

    private static final Instant DEFAULT_EXAM_DATE = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_EXAM_DATE = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final Integer DEFAULT_EXAM_GRADE = 1;
    private static final Integer UPDATED_EXAM_GRADE = 2;

    private static final Integer DEFAULT_IS_ELIGIBILE = 1;
    private static final Integer UPDATED_IS_ELIGIBILE = 2;

    private static final Boolean DEFAULT_EXPORTED = false;
    private static final Boolean UPDATED_EXPORTED = true;

    @Autowired
    private DlsReqExamsRepository dlsReqExamsRepository;

    @Autowired
    private DlsReqExamsService dlsReqExamsService;

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

    private MockMvc restDlsReqExamsMockMvc;

    private DlsReqExams dlsReqExams;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final DlsReqExamsResource dlsReqExamsResource = new DlsReqExamsResource(dlsReqExamsService);
        this.restDlsReqExamsMockMvc = MockMvcBuilders.standaloneSetup(dlsReqExamsResource)
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
    public static DlsReqExams createEntity(EntityManager em) {
        DlsReqExams dlsReqExams = new DlsReqExams()
            .examCode(DEFAULT_EXAM_CODE)
            .examResult(DEFAULT_EXAM_RESULT)
            .examDate(DEFAULT_EXAM_DATE)
            .examGrade(DEFAULT_EXAM_GRADE)
            .isEligibile(DEFAULT_IS_ELIGIBILE)
            .exported(DEFAULT_EXPORTED);
        return dlsReqExams;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static DlsReqExams createUpdatedEntity(EntityManager em) {
        DlsReqExams dlsReqExams = new DlsReqExams()
            .examCode(UPDATED_EXAM_CODE)
            .examResult(UPDATED_EXAM_RESULT)
            .examDate(UPDATED_EXAM_DATE)
            .examGrade(UPDATED_EXAM_GRADE)
            .isEligibile(UPDATED_IS_ELIGIBILE)
            .exported(UPDATED_EXPORTED);
        return dlsReqExams;
    }

    @BeforeEach
    public void initTest() {
        dlsReqExams = createEntity(em);
    }

    @Test
    @Transactional
    public void createDlsReqExams() throws Exception {
        int databaseSizeBeforeCreate = dlsReqExamsRepository.findAll().size();

        // Create the DlsReqExams
        restDlsReqExamsMockMvc.perform(post("/api/dls-req-exams")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(dlsReqExams)))
            .andExpect(status().isCreated());

        // Validate the DlsReqExams in the database
        List<DlsReqExams> dlsReqExamsList = dlsReqExamsRepository.findAll();
        assertThat(dlsReqExamsList).hasSize(databaseSizeBeforeCreate + 1);
        DlsReqExams testDlsReqExams = dlsReqExamsList.get(dlsReqExamsList.size() - 1);
        assertThat(testDlsReqExams.getExamCode()).isEqualTo(DEFAULT_EXAM_CODE);
        assertThat(testDlsReqExams.getExamResult()).isEqualTo(DEFAULT_EXAM_RESULT);
        assertThat(testDlsReqExams.getExamDate()).isEqualTo(DEFAULT_EXAM_DATE);
        assertThat(testDlsReqExams.getExamGrade()).isEqualTo(DEFAULT_EXAM_GRADE);
        assertThat(testDlsReqExams.getIsEligibile()).isEqualTo(DEFAULT_IS_ELIGIBILE);
        assertThat(testDlsReqExams.isExported()).isEqualTo(DEFAULT_EXPORTED);
    }

    @Test
    @Transactional
    public void createDlsReqExamsWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = dlsReqExamsRepository.findAll().size();

        // Create the DlsReqExams with an existing ID
        dlsReqExams.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restDlsReqExamsMockMvc.perform(post("/api/dls-req-exams")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(dlsReqExams)))
            .andExpect(status().isBadRequest());

        // Validate the DlsReqExams in the database
        List<DlsReqExams> dlsReqExamsList = dlsReqExamsRepository.findAll();
        assertThat(dlsReqExamsList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkExamCodeIsRequired() throws Exception {
        int databaseSizeBeforeTest = dlsReqExamsRepository.findAll().size();
        // set the field null
        dlsReqExams.setExamCode(null);

        // Create the DlsReqExams, which fails.

        restDlsReqExamsMockMvc.perform(post("/api/dls-req-exams")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(dlsReqExams)))
            .andExpect(status().isBadRequest());

        List<DlsReqExams> dlsReqExamsList = dlsReqExamsRepository.findAll();
        assertThat(dlsReqExamsList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllDlsReqExams() throws Exception {
        // Initialize the database
        dlsReqExamsRepository.saveAndFlush(dlsReqExams);

        // Get all the dlsReqExamsList
        restDlsReqExamsMockMvc.perform(get("/api/dls-req-exams?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(dlsReqExams.getId().intValue())))
            .andExpect(jsonPath("$.[*].examCode").value(hasItem(DEFAULT_EXAM_CODE)))
            .andExpect(jsonPath("$.[*].examResult").value(hasItem(DEFAULT_EXAM_RESULT)))
            .andExpect(jsonPath("$.[*].examDate").value(hasItem(DEFAULT_EXAM_DATE.toString())))
            .andExpect(jsonPath("$.[*].examGrade").value(hasItem(DEFAULT_EXAM_GRADE)))
            .andExpect(jsonPath("$.[*].isEligibile").value(hasItem(DEFAULT_IS_ELIGIBILE)))
            .andExpect(jsonPath("$.[*].exported").value(hasItem(DEFAULT_EXPORTED.booleanValue())));
    }
    
    @Test
    @Transactional
    public void getDlsReqExams() throws Exception {
        // Initialize the database
        dlsReqExamsRepository.saveAndFlush(dlsReqExams);

        // Get the dlsReqExams
        restDlsReqExamsMockMvc.perform(get("/api/dls-req-exams/{id}", dlsReqExams.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(dlsReqExams.getId().intValue()))
            .andExpect(jsonPath("$.examCode").value(DEFAULT_EXAM_CODE))
            .andExpect(jsonPath("$.examResult").value(DEFAULT_EXAM_RESULT))
            .andExpect(jsonPath("$.examDate").value(DEFAULT_EXAM_DATE.toString()))
            .andExpect(jsonPath("$.examGrade").value(DEFAULT_EXAM_GRADE))
            .andExpect(jsonPath("$.isEligibile").value(DEFAULT_IS_ELIGIBILE))
            .andExpect(jsonPath("$.exported").value(DEFAULT_EXPORTED.booleanValue()));
    }

    @Test
    @Transactional
    public void getNonExistingDlsReqExams() throws Exception {
        // Get the dlsReqExams
        restDlsReqExamsMockMvc.perform(get("/api/dls-req-exams/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateDlsReqExams() throws Exception {
        // Initialize the database
        dlsReqExamsService.save(dlsReqExams);

        int databaseSizeBeforeUpdate = dlsReqExamsRepository.findAll().size();

        // Update the dlsReqExams
        DlsReqExams updatedDlsReqExams = dlsReqExamsRepository.findById(dlsReqExams.getId()).get();
        // Disconnect from session so that the updates on updatedDlsReqExams are not directly saved in db
        em.detach(updatedDlsReqExams);
        updatedDlsReqExams
            .examCode(UPDATED_EXAM_CODE)
            .examResult(UPDATED_EXAM_RESULT)
            .examDate(UPDATED_EXAM_DATE)
            .examGrade(UPDATED_EXAM_GRADE)
            .isEligibile(UPDATED_IS_ELIGIBILE)
            .exported(UPDATED_EXPORTED);

        restDlsReqExamsMockMvc.perform(put("/api/dls-req-exams")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedDlsReqExams)))
            .andExpect(status().isOk());

        // Validate the DlsReqExams in the database
        List<DlsReqExams> dlsReqExamsList = dlsReqExamsRepository.findAll();
        assertThat(dlsReqExamsList).hasSize(databaseSizeBeforeUpdate);
        DlsReqExams testDlsReqExams = dlsReqExamsList.get(dlsReqExamsList.size() - 1);
        assertThat(testDlsReqExams.getExamCode()).isEqualTo(UPDATED_EXAM_CODE);
        assertThat(testDlsReqExams.getExamResult()).isEqualTo(UPDATED_EXAM_RESULT);
        assertThat(testDlsReqExams.getExamDate()).isEqualTo(UPDATED_EXAM_DATE);
        assertThat(testDlsReqExams.getExamGrade()).isEqualTo(UPDATED_EXAM_GRADE);
        assertThat(testDlsReqExams.getIsEligibile()).isEqualTo(UPDATED_IS_ELIGIBILE);
        assertThat(testDlsReqExams.isExported()).isEqualTo(UPDATED_EXPORTED);
    }

    @Test
    @Transactional
    public void updateNonExistingDlsReqExams() throws Exception {
        int databaseSizeBeforeUpdate = dlsReqExamsRepository.findAll().size();

        // Create the DlsReqExams

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restDlsReqExamsMockMvc.perform(put("/api/dls-req-exams")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(dlsReqExams)))
            .andExpect(status().isBadRequest());

        // Validate the DlsReqExams in the database
        List<DlsReqExams> dlsReqExamsList = dlsReqExamsRepository.findAll();
        assertThat(dlsReqExamsList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteDlsReqExams() throws Exception {
        // Initialize the database
        dlsReqExamsService.save(dlsReqExams);

        int databaseSizeBeforeDelete = dlsReqExamsRepository.findAll().size();

        // Delete the dlsReqExams
        restDlsReqExamsMockMvc.perform(delete("/api/dls-req-exams/{id}", dlsReqExams.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<DlsReqExams> dlsReqExamsList = dlsReqExamsRepository.findAll();
        assertThat(dlsReqExamsList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
