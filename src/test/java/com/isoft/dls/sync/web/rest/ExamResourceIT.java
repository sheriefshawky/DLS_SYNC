package com.isoft.dls.sync.web.rest;

import com.isoft.dls.sync.DlssyncApp;
import com.isoft.dls.sync.domain.Exam;
import com.isoft.dls.sync.repository.ExamRepository;
import com.isoft.dls.sync.service.ExamService;
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
 * Integration tests for the {@link ExamResource} REST controller.
 */
@SpringBootTest(classes = DlssyncApp.class)
public class ExamResourceIT {

    private static final Instant DEFAULT_VALIDFROM = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_VALIDFROM = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final Instant DEFAULT_VALIDTO = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_VALIDTO = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final Long DEFAULT_TIME_IN_SEC = 1L;
    private static final Long UPDATED_TIME_IN_SEC = 2L;

    private static final Double DEFAULT_SCORE = 1D;
    private static final Double UPDATED_SCORE = 2D;

    private static final Integer DEFAULT_STATUS = 1;
    private static final Integer UPDATED_STATUS = 2;

    private static final Double DEFAULT_PASS_SCORE = 1D;
    private static final Double UPDATED_PASS_SCORE = 2D;

    private static final Instant DEFAULT_START_AT = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_START_AT = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final Instant DEFAULT_SUBMIT_AT = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_SUBMIT_AT = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    @Autowired
    private ExamRepository examRepository;

    @Autowired
    private ExamService examService;

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

    private MockMvc restExamMockMvc;

    private Exam exam;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final ExamResource examResource = new ExamResource(examService);
        this.restExamMockMvc = MockMvcBuilders.standaloneSetup(examResource)
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
    public static Exam createEntity(EntityManager em) {
        Exam exam = new Exam()
            .validfrom(DEFAULT_VALIDFROM)
            .validto(DEFAULT_VALIDTO)
            .timeInSec(DEFAULT_TIME_IN_SEC)
            .score(DEFAULT_SCORE)
            .status(DEFAULT_STATUS)
            .passScore(DEFAULT_PASS_SCORE)
            .startAt(DEFAULT_START_AT)
            .submitAt(DEFAULT_SUBMIT_AT);
        return exam;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Exam createUpdatedEntity(EntityManager em) {
        Exam exam = new Exam()
            .validfrom(UPDATED_VALIDFROM)
            .validto(UPDATED_VALIDTO)
            .timeInSec(UPDATED_TIME_IN_SEC)
            .score(UPDATED_SCORE)
            .status(UPDATED_STATUS)
            .passScore(UPDATED_PASS_SCORE)
            .startAt(UPDATED_START_AT)
            .submitAt(UPDATED_SUBMIT_AT);
        return exam;
    }

    @BeforeEach
    public void initTest() {
        exam = createEntity(em);
    }

    @Test
    @Transactional
    public void createExam() throws Exception {
        int databaseSizeBeforeCreate = examRepository.findAll().size();

        // Create the Exam
        restExamMockMvc.perform(post("/api/exams")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(exam)))
            .andExpect(status().isCreated());

        // Validate the Exam in the database
        List<Exam> examList = examRepository.findAll();
        assertThat(examList).hasSize(databaseSizeBeforeCreate + 1);
        Exam testExam = examList.get(examList.size() - 1);
        assertThat(testExam.getValidfrom()).isEqualTo(DEFAULT_VALIDFROM);
        assertThat(testExam.getValidto()).isEqualTo(DEFAULT_VALIDTO);
        assertThat(testExam.getTimeInSec()).isEqualTo(DEFAULT_TIME_IN_SEC);
        assertThat(testExam.getScore()).isEqualTo(DEFAULT_SCORE);
        assertThat(testExam.getStatus()).isEqualTo(DEFAULT_STATUS);
        assertThat(testExam.getPassScore()).isEqualTo(DEFAULT_PASS_SCORE);
        assertThat(testExam.getStartAt()).isEqualTo(DEFAULT_START_AT);
        assertThat(testExam.getSubmitAt()).isEqualTo(DEFAULT_SUBMIT_AT);
    }

    @Test
    @Transactional
    public void createExamWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = examRepository.findAll().size();

        // Create the Exam with an existing ID
        exam.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restExamMockMvc.perform(post("/api/exams")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(exam)))
            .andExpect(status().isBadRequest());

        // Validate the Exam in the database
        List<Exam> examList = examRepository.findAll();
        assertThat(examList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkValidfromIsRequired() throws Exception {
        int databaseSizeBeforeTest = examRepository.findAll().size();
        // set the field null
        exam.setValidfrom(null);

        // Create the Exam, which fails.

        restExamMockMvc.perform(post("/api/exams")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(exam)))
            .andExpect(status().isBadRequest());

        List<Exam> examList = examRepository.findAll();
        assertThat(examList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkValidtoIsRequired() throws Exception {
        int databaseSizeBeforeTest = examRepository.findAll().size();
        // set the field null
        exam.setValidto(null);

        // Create the Exam, which fails.

        restExamMockMvc.perform(post("/api/exams")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(exam)))
            .andExpect(status().isBadRequest());

        List<Exam> examList = examRepository.findAll();
        assertThat(examList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkStatusIsRequired() throws Exception {
        int databaseSizeBeforeTest = examRepository.findAll().size();
        // set the field null
        exam.setStatus(null);

        // Create the Exam, which fails.

        restExamMockMvc.perform(post("/api/exams")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(exam)))
            .andExpect(status().isBadRequest());

        List<Exam> examList = examRepository.findAll();
        assertThat(examList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllExams() throws Exception {
        // Initialize the database
        examRepository.saveAndFlush(exam);

        // Get all the examList
        restExamMockMvc.perform(get("/api/exams?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(exam.getId().intValue())))
            .andExpect(jsonPath("$.[*].validfrom").value(hasItem(DEFAULT_VALIDFROM.toString())))
            .andExpect(jsonPath("$.[*].validto").value(hasItem(DEFAULT_VALIDTO.toString())))
            .andExpect(jsonPath("$.[*].timeInSec").value(hasItem(DEFAULT_TIME_IN_SEC.intValue())))
            .andExpect(jsonPath("$.[*].score").value(hasItem(DEFAULT_SCORE.doubleValue())))
            .andExpect(jsonPath("$.[*].status").value(hasItem(DEFAULT_STATUS)))
            .andExpect(jsonPath("$.[*].passScore").value(hasItem(DEFAULT_PASS_SCORE.doubleValue())))
            .andExpect(jsonPath("$.[*].startAt").value(hasItem(DEFAULT_START_AT.toString())))
            .andExpect(jsonPath("$.[*].submitAt").value(hasItem(DEFAULT_SUBMIT_AT.toString())));
    }
    
    @Test
    @Transactional
    public void getExam() throws Exception {
        // Initialize the database
        examRepository.saveAndFlush(exam);

        // Get the exam
        restExamMockMvc.perform(get("/api/exams/{id}", exam.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(exam.getId().intValue()))
            .andExpect(jsonPath("$.validfrom").value(DEFAULT_VALIDFROM.toString()))
            .andExpect(jsonPath("$.validto").value(DEFAULT_VALIDTO.toString()))
            .andExpect(jsonPath("$.timeInSec").value(DEFAULT_TIME_IN_SEC.intValue()))
            .andExpect(jsonPath("$.score").value(DEFAULT_SCORE.doubleValue()))
            .andExpect(jsonPath("$.status").value(DEFAULT_STATUS))
            .andExpect(jsonPath("$.passScore").value(DEFAULT_PASS_SCORE.doubleValue()))
            .andExpect(jsonPath("$.startAt").value(DEFAULT_START_AT.toString()))
            .andExpect(jsonPath("$.submitAt").value(DEFAULT_SUBMIT_AT.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingExam() throws Exception {
        // Get the exam
        restExamMockMvc.perform(get("/api/exams/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateExam() throws Exception {
        // Initialize the database
        examService.save(exam);

        int databaseSizeBeforeUpdate = examRepository.findAll().size();

        // Update the exam
        Exam updatedExam = examRepository.findById(exam.getId()).get();
        // Disconnect from session so that the updates on updatedExam are not directly saved in db
        em.detach(updatedExam);
        updatedExam
            .validfrom(UPDATED_VALIDFROM)
            .validto(UPDATED_VALIDTO)
            .timeInSec(UPDATED_TIME_IN_SEC)
            .score(UPDATED_SCORE)
            .status(UPDATED_STATUS)
            .passScore(UPDATED_PASS_SCORE)
            .startAt(UPDATED_START_AT)
            .submitAt(UPDATED_SUBMIT_AT);

        restExamMockMvc.perform(put("/api/exams")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedExam)))
            .andExpect(status().isOk());

        // Validate the Exam in the database
        List<Exam> examList = examRepository.findAll();
        assertThat(examList).hasSize(databaseSizeBeforeUpdate);
        Exam testExam = examList.get(examList.size() - 1);
        assertThat(testExam.getValidfrom()).isEqualTo(UPDATED_VALIDFROM);
        assertThat(testExam.getValidto()).isEqualTo(UPDATED_VALIDTO);
        assertThat(testExam.getTimeInSec()).isEqualTo(UPDATED_TIME_IN_SEC);
        assertThat(testExam.getScore()).isEqualTo(UPDATED_SCORE);
        assertThat(testExam.getStatus()).isEqualTo(UPDATED_STATUS);
        assertThat(testExam.getPassScore()).isEqualTo(UPDATED_PASS_SCORE);
        assertThat(testExam.getStartAt()).isEqualTo(UPDATED_START_AT);
        assertThat(testExam.getSubmitAt()).isEqualTo(UPDATED_SUBMIT_AT);
    }

    @Test
    @Transactional
    public void updateNonExistingExam() throws Exception {
        int databaseSizeBeforeUpdate = examRepository.findAll().size();

        // Create the Exam

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restExamMockMvc.perform(put("/api/exams")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(exam)))
            .andExpect(status().isBadRequest());

        // Validate the Exam in the database
        List<Exam> examList = examRepository.findAll();
        assertThat(examList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteExam() throws Exception {
        // Initialize the database
        examService.save(exam);

        int databaseSizeBeforeDelete = examRepository.findAll().size();

        // Delete the exam
        restExamMockMvc.perform(delete("/api/exams/{id}", exam.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Exam> examList = examRepository.findAll();
        assertThat(examList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
