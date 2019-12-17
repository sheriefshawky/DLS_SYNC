package com.isoft.dls.sync.web.rest;

import com.isoft.dls.sync.DlssyncApp;
import com.isoft.dls.sync.domain.ExamQuestions;
import com.isoft.dls.sync.repository.ExamQuestionsRepository;
import com.isoft.dls.sync.service.ExamQuestionsService;
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
 * Integration tests for the {@link ExamQuestionsResource} REST controller.
 */
@SpringBootTest(classes = DlssyncApp.class)
public class ExamQuestionsResourceIT {

    private static final String DEFAULT_DESC_AR = "AAAAAAAAAA";
    private static final String UPDATED_DESC_AR = "BBBBBBBBBB";

    private static final String DEFAULT_DESC_EN = "AAAAAAAAAA";
    private static final String UPDATED_DESC_EN = "BBBBBBBBBB";

    private static final String DEFAULT_CODE = "AAAAAAAAAA";
    private static final String UPDATED_CODE = "BBBBBBBBBB";

    private static final String DEFAULT_IMG_PATH = "AAAAAAAAAA";
    private static final String UPDATED_IMG_PATH = "BBBBBBBBBB";

    private static final Long DEFAULT_TIME_IN_SEC = 1L;
    private static final Long UPDATED_TIME_IN_SEC = 2L;

    private static final Integer DEFAULT_TYPE = 1;
    private static final Integer UPDATED_TYPE = 2;

    private static final Double DEFAULT_WEIGHT = 1D;
    private static final Double UPDATED_WEIGHT = 2D;

    private static final Double DEFAULT_SCORE = 1D;
    private static final Double UPDATED_SCORE = 2D;

    private static final Integer DEFAULT_STATUS = 1;
    private static final Integer UPDATED_STATUS = 2;

    private static final Integer DEFAULT_SEQ = 1;
    private static final Integer UPDATED_SEQ = 2;

    private static final Integer DEFAULT_CATEGORY_ID = 1;
    private static final Integer UPDATED_CATEGORY_ID = 2;

    private static final Integer DEFAULT_QUESTION_ID = 1;
    private static final Integer UPDATED_QUESTION_ID = 2;

    private static final Instant DEFAULT_START_AT = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_START_AT = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final Instant DEFAULT_SUBMIT_AT = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_SUBMIT_AT = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    @Autowired
    private ExamQuestionsRepository examQuestionsRepository;

    @Autowired
    private ExamQuestionsService examQuestionsService;

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

    private MockMvc restExamQuestionsMockMvc;

    private ExamQuestions examQuestions;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final ExamQuestionsResource examQuestionsResource = new ExamQuestionsResource(examQuestionsService);
        this.restExamQuestionsMockMvc = MockMvcBuilders.standaloneSetup(examQuestionsResource)
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
    public static ExamQuestions createEntity(EntityManager em) {
        ExamQuestions examQuestions = new ExamQuestions()
            .descAr(DEFAULT_DESC_AR)
            .descEn(DEFAULT_DESC_EN)
            .code(DEFAULT_CODE)
            .imgPath(DEFAULT_IMG_PATH)
            .timeInSec(DEFAULT_TIME_IN_SEC)
            .type(DEFAULT_TYPE)
            .weight(DEFAULT_WEIGHT)
            .score(DEFAULT_SCORE)
            .status(DEFAULT_STATUS)
            .seq(DEFAULT_SEQ)
            .categoryId(DEFAULT_CATEGORY_ID)
            .questionId(DEFAULT_QUESTION_ID)
            .startAt(DEFAULT_START_AT)
            .submitAt(DEFAULT_SUBMIT_AT);
        return examQuestions;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ExamQuestions createUpdatedEntity(EntityManager em) {
        ExamQuestions examQuestions = new ExamQuestions()
            .descAr(UPDATED_DESC_AR)
            .descEn(UPDATED_DESC_EN)
            .code(UPDATED_CODE)
            .imgPath(UPDATED_IMG_PATH)
            .timeInSec(UPDATED_TIME_IN_SEC)
            .type(UPDATED_TYPE)
            .weight(UPDATED_WEIGHT)
            .score(UPDATED_SCORE)
            .status(UPDATED_STATUS)
            .seq(UPDATED_SEQ)
            .categoryId(UPDATED_CATEGORY_ID)
            .questionId(UPDATED_QUESTION_ID)
            .startAt(UPDATED_START_AT)
            .submitAt(UPDATED_SUBMIT_AT);
        return examQuestions;
    }

    @BeforeEach
    public void initTest() {
        examQuestions = createEntity(em);
    }

    @Test
    @Transactional
    public void createExamQuestions() throws Exception {
        int databaseSizeBeforeCreate = examQuestionsRepository.findAll().size();

        // Create the ExamQuestions
        restExamQuestionsMockMvc.perform(post("/api/exam-questions")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(examQuestions)))
            .andExpect(status().isCreated());

        // Validate the ExamQuestions in the database
        List<ExamQuestions> examQuestionsList = examQuestionsRepository.findAll();
        assertThat(examQuestionsList).hasSize(databaseSizeBeforeCreate + 1);
        ExamQuestions testExamQuestions = examQuestionsList.get(examQuestionsList.size() - 1);
        assertThat(testExamQuestions.getDescAr()).isEqualTo(DEFAULT_DESC_AR);
        assertThat(testExamQuestions.getDescEn()).isEqualTo(DEFAULT_DESC_EN);
        assertThat(testExamQuestions.getCode()).isEqualTo(DEFAULT_CODE);
        assertThat(testExamQuestions.getImgPath()).isEqualTo(DEFAULT_IMG_PATH);
        assertThat(testExamQuestions.getTimeInSec()).isEqualTo(DEFAULT_TIME_IN_SEC);
        assertThat(testExamQuestions.getType()).isEqualTo(DEFAULT_TYPE);
        assertThat(testExamQuestions.getWeight()).isEqualTo(DEFAULT_WEIGHT);
        assertThat(testExamQuestions.getScore()).isEqualTo(DEFAULT_SCORE);
        assertThat(testExamQuestions.getStatus()).isEqualTo(DEFAULT_STATUS);
        assertThat(testExamQuestions.getSeq()).isEqualTo(DEFAULT_SEQ);
        assertThat(testExamQuestions.getCategoryId()).isEqualTo(DEFAULT_CATEGORY_ID);
        assertThat(testExamQuestions.getQuestionId()).isEqualTo(DEFAULT_QUESTION_ID);
        assertThat(testExamQuestions.getStartAt()).isEqualTo(DEFAULT_START_AT);
        assertThat(testExamQuestions.getSubmitAt()).isEqualTo(DEFAULT_SUBMIT_AT);
    }

    @Test
    @Transactional
    public void createExamQuestionsWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = examQuestionsRepository.findAll().size();

        // Create the ExamQuestions with an existing ID
        examQuestions.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restExamQuestionsMockMvc.perform(post("/api/exam-questions")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(examQuestions)))
            .andExpect(status().isBadRequest());

        // Validate the ExamQuestions in the database
        List<ExamQuestions> examQuestionsList = examQuestionsRepository.findAll();
        assertThat(examQuestionsList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkDescArIsRequired() throws Exception {
        int databaseSizeBeforeTest = examQuestionsRepository.findAll().size();
        // set the field null
        examQuestions.setDescAr(null);

        // Create the ExamQuestions, which fails.

        restExamQuestionsMockMvc.perform(post("/api/exam-questions")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(examQuestions)))
            .andExpect(status().isBadRequest());

        List<ExamQuestions> examQuestionsList = examQuestionsRepository.findAll();
        assertThat(examQuestionsList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkDescEnIsRequired() throws Exception {
        int databaseSizeBeforeTest = examQuestionsRepository.findAll().size();
        // set the field null
        examQuestions.setDescEn(null);

        // Create the ExamQuestions, which fails.

        restExamQuestionsMockMvc.perform(post("/api/exam-questions")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(examQuestions)))
            .andExpect(status().isBadRequest());

        List<ExamQuestions> examQuestionsList = examQuestionsRepository.findAll();
        assertThat(examQuestionsList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkCodeIsRequired() throws Exception {
        int databaseSizeBeforeTest = examQuestionsRepository.findAll().size();
        // set the field null
        examQuestions.setCode(null);

        // Create the ExamQuestions, which fails.

        restExamQuestionsMockMvc.perform(post("/api/exam-questions")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(examQuestions)))
            .andExpect(status().isBadRequest());

        List<ExamQuestions> examQuestionsList = examQuestionsRepository.findAll();
        assertThat(examQuestionsList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkTypeIsRequired() throws Exception {
        int databaseSizeBeforeTest = examQuestionsRepository.findAll().size();
        // set the field null
        examQuestions.setType(null);

        // Create the ExamQuestions, which fails.

        restExamQuestionsMockMvc.perform(post("/api/exam-questions")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(examQuestions)))
            .andExpect(status().isBadRequest());

        List<ExamQuestions> examQuestionsList = examQuestionsRepository.findAll();
        assertThat(examQuestionsList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkWeightIsRequired() throws Exception {
        int databaseSizeBeforeTest = examQuestionsRepository.findAll().size();
        // set the field null
        examQuestions.setWeight(null);

        // Create the ExamQuestions, which fails.

        restExamQuestionsMockMvc.perform(post("/api/exam-questions")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(examQuestions)))
            .andExpect(status().isBadRequest());

        List<ExamQuestions> examQuestionsList = examQuestionsRepository.findAll();
        assertThat(examQuestionsList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllExamQuestions() throws Exception {
        // Initialize the database
        examQuestionsRepository.saveAndFlush(examQuestions);

        // Get all the examQuestionsList
        restExamQuestionsMockMvc.perform(get("/api/exam-questions?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(examQuestions.getId().intValue())))
            .andExpect(jsonPath("$.[*].descAr").value(hasItem(DEFAULT_DESC_AR)))
            .andExpect(jsonPath("$.[*].descEn").value(hasItem(DEFAULT_DESC_EN)))
            .andExpect(jsonPath("$.[*].code").value(hasItem(DEFAULT_CODE)))
            .andExpect(jsonPath("$.[*].imgPath").value(hasItem(DEFAULT_IMG_PATH)))
            .andExpect(jsonPath("$.[*].timeInSec").value(hasItem(DEFAULT_TIME_IN_SEC.intValue())))
            .andExpect(jsonPath("$.[*].type").value(hasItem(DEFAULT_TYPE)))
            .andExpect(jsonPath("$.[*].weight").value(hasItem(DEFAULT_WEIGHT.doubleValue())))
            .andExpect(jsonPath("$.[*].score").value(hasItem(DEFAULT_SCORE.doubleValue())))
            .andExpect(jsonPath("$.[*].status").value(hasItem(DEFAULT_STATUS)))
            .andExpect(jsonPath("$.[*].seq").value(hasItem(DEFAULT_SEQ)))
            .andExpect(jsonPath("$.[*].categoryId").value(hasItem(DEFAULT_CATEGORY_ID)))
            .andExpect(jsonPath("$.[*].questionId").value(hasItem(DEFAULT_QUESTION_ID)))
            .andExpect(jsonPath("$.[*].startAt").value(hasItem(DEFAULT_START_AT.toString())))
            .andExpect(jsonPath("$.[*].submitAt").value(hasItem(DEFAULT_SUBMIT_AT.toString())));
    }
    
    @Test
    @Transactional
    public void getExamQuestions() throws Exception {
        // Initialize the database
        examQuestionsRepository.saveAndFlush(examQuestions);

        // Get the examQuestions
        restExamQuestionsMockMvc.perform(get("/api/exam-questions/{id}", examQuestions.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(examQuestions.getId().intValue()))
            .andExpect(jsonPath("$.descAr").value(DEFAULT_DESC_AR))
            .andExpect(jsonPath("$.descEn").value(DEFAULT_DESC_EN))
            .andExpect(jsonPath("$.code").value(DEFAULT_CODE))
            .andExpect(jsonPath("$.imgPath").value(DEFAULT_IMG_PATH))
            .andExpect(jsonPath("$.timeInSec").value(DEFAULT_TIME_IN_SEC.intValue()))
            .andExpect(jsonPath("$.type").value(DEFAULT_TYPE))
            .andExpect(jsonPath("$.weight").value(DEFAULT_WEIGHT.doubleValue()))
            .andExpect(jsonPath("$.score").value(DEFAULT_SCORE.doubleValue()))
            .andExpect(jsonPath("$.status").value(DEFAULT_STATUS))
            .andExpect(jsonPath("$.seq").value(DEFAULT_SEQ))
            .andExpect(jsonPath("$.categoryId").value(DEFAULT_CATEGORY_ID))
            .andExpect(jsonPath("$.questionId").value(DEFAULT_QUESTION_ID))
            .andExpect(jsonPath("$.startAt").value(DEFAULT_START_AT.toString()))
            .andExpect(jsonPath("$.submitAt").value(DEFAULT_SUBMIT_AT.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingExamQuestions() throws Exception {
        // Get the examQuestions
        restExamQuestionsMockMvc.perform(get("/api/exam-questions/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateExamQuestions() throws Exception {
        // Initialize the database
        examQuestionsService.save(examQuestions);

        int databaseSizeBeforeUpdate = examQuestionsRepository.findAll().size();

        // Update the examQuestions
        ExamQuestions updatedExamQuestions = examQuestionsRepository.findById(examQuestions.getId()).get();
        // Disconnect from session so that the updates on updatedExamQuestions are not directly saved in db
        em.detach(updatedExamQuestions);
        updatedExamQuestions
            .descAr(UPDATED_DESC_AR)
            .descEn(UPDATED_DESC_EN)
            .code(UPDATED_CODE)
            .imgPath(UPDATED_IMG_PATH)
            .timeInSec(UPDATED_TIME_IN_SEC)
            .type(UPDATED_TYPE)
            .weight(UPDATED_WEIGHT)
            .score(UPDATED_SCORE)
            .status(UPDATED_STATUS)
            .seq(UPDATED_SEQ)
            .categoryId(UPDATED_CATEGORY_ID)
            .questionId(UPDATED_QUESTION_ID)
            .startAt(UPDATED_START_AT)
            .submitAt(UPDATED_SUBMIT_AT);

        restExamQuestionsMockMvc.perform(put("/api/exam-questions")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedExamQuestions)))
            .andExpect(status().isOk());

        // Validate the ExamQuestions in the database
        List<ExamQuestions> examQuestionsList = examQuestionsRepository.findAll();
        assertThat(examQuestionsList).hasSize(databaseSizeBeforeUpdate);
        ExamQuestions testExamQuestions = examQuestionsList.get(examQuestionsList.size() - 1);
        assertThat(testExamQuestions.getDescAr()).isEqualTo(UPDATED_DESC_AR);
        assertThat(testExamQuestions.getDescEn()).isEqualTo(UPDATED_DESC_EN);
        assertThat(testExamQuestions.getCode()).isEqualTo(UPDATED_CODE);
        assertThat(testExamQuestions.getImgPath()).isEqualTo(UPDATED_IMG_PATH);
        assertThat(testExamQuestions.getTimeInSec()).isEqualTo(UPDATED_TIME_IN_SEC);
        assertThat(testExamQuestions.getType()).isEqualTo(UPDATED_TYPE);
        assertThat(testExamQuestions.getWeight()).isEqualTo(UPDATED_WEIGHT);
        assertThat(testExamQuestions.getScore()).isEqualTo(UPDATED_SCORE);
        assertThat(testExamQuestions.getStatus()).isEqualTo(UPDATED_STATUS);
        assertThat(testExamQuestions.getSeq()).isEqualTo(UPDATED_SEQ);
        assertThat(testExamQuestions.getCategoryId()).isEqualTo(UPDATED_CATEGORY_ID);
        assertThat(testExamQuestions.getQuestionId()).isEqualTo(UPDATED_QUESTION_ID);
        assertThat(testExamQuestions.getStartAt()).isEqualTo(UPDATED_START_AT);
        assertThat(testExamQuestions.getSubmitAt()).isEqualTo(UPDATED_SUBMIT_AT);
    }

    @Test
    @Transactional
    public void updateNonExistingExamQuestions() throws Exception {
        int databaseSizeBeforeUpdate = examQuestionsRepository.findAll().size();

        // Create the ExamQuestions

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restExamQuestionsMockMvc.perform(put("/api/exam-questions")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(examQuestions)))
            .andExpect(status().isBadRequest());

        // Validate the ExamQuestions in the database
        List<ExamQuestions> examQuestionsList = examQuestionsRepository.findAll();
        assertThat(examQuestionsList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteExamQuestions() throws Exception {
        // Initialize the database
        examQuestionsService.save(examQuestions);

        int databaseSizeBeforeDelete = examQuestionsRepository.findAll().size();

        // Delete the examQuestions
        restExamQuestionsMockMvc.perform(delete("/api/exam-questions/{id}", examQuestions.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<ExamQuestions> examQuestionsList = examQuestionsRepository.findAll();
        assertThat(examQuestionsList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
