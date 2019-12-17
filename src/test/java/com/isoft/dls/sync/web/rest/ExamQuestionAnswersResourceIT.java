package com.isoft.dls.sync.web.rest;

import com.isoft.dls.sync.DlssyncApp;
import com.isoft.dls.sync.domain.ExamQuestionAnswers;
import com.isoft.dls.sync.repository.ExamQuestionAnswersRepository;
import com.isoft.dls.sync.service.ExamQuestionAnswersService;
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
 * Integration tests for the {@link ExamQuestionAnswersResource} REST controller.
 */
@SpringBootTest(classes = DlssyncApp.class)
public class ExamQuestionAnswersResourceIT {

    private static final String DEFAULT_DESC_AR = "AAAAAAAAAA";
    private static final String UPDATED_DESC_AR = "BBBBBBBBBB";

    private static final String DEFAULT_DESC_EN = "AAAAAAAAAA";
    private static final String UPDATED_DESC_EN = "BBBBBBBBBB";

    private static final String DEFAULT_CODE = "AAAAAAAAAA";
    private static final String UPDATED_CODE = "BBBBBBBBBB";

    private static final String DEFAULT_IMG_PATH = "AAAAAAAAAA";
    private static final String UPDATED_IMG_PATH = "BBBBBBBBBB";

    private static final Boolean DEFAULT_IS_RIGHT_ANSWER = false;
    private static final Boolean UPDATED_IS_RIGHT_ANSWER = true;

    private static final Integer DEFAULT_STATUS = 1;
    private static final Integer UPDATED_STATUS = 2;

    private static final Integer DEFAULT_ANSWER_ID = 1;
    private static final Integer UPDATED_ANSWER_ID = 2;

    @Autowired
    private ExamQuestionAnswersRepository examQuestionAnswersRepository;

    @Autowired
    private ExamQuestionAnswersService examQuestionAnswersService;

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

    private MockMvc restExamQuestionAnswersMockMvc;

    private ExamQuestionAnswers examQuestionAnswers;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final ExamQuestionAnswersResource examQuestionAnswersResource = new ExamQuestionAnswersResource(examQuestionAnswersService);
        this.restExamQuestionAnswersMockMvc = MockMvcBuilders.standaloneSetup(examQuestionAnswersResource)
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
    public static ExamQuestionAnswers createEntity(EntityManager em) {
        ExamQuestionAnswers examQuestionAnswers = new ExamQuestionAnswers()
            .descAr(DEFAULT_DESC_AR)
            .descEn(DEFAULT_DESC_EN)
            .code(DEFAULT_CODE)
            .imgPath(DEFAULT_IMG_PATH)
            .isRightAnswer(DEFAULT_IS_RIGHT_ANSWER)
            .status(DEFAULT_STATUS)
            .answerId(DEFAULT_ANSWER_ID);
        return examQuestionAnswers;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ExamQuestionAnswers createUpdatedEntity(EntityManager em) {
        ExamQuestionAnswers examQuestionAnswers = new ExamQuestionAnswers()
            .descAr(UPDATED_DESC_AR)
            .descEn(UPDATED_DESC_EN)
            .code(UPDATED_CODE)
            .imgPath(UPDATED_IMG_PATH)
            .isRightAnswer(UPDATED_IS_RIGHT_ANSWER)
            .status(UPDATED_STATUS)
            .answerId(UPDATED_ANSWER_ID);
        return examQuestionAnswers;
    }

    @BeforeEach
    public void initTest() {
        examQuestionAnswers = createEntity(em);
    }

    @Test
    @Transactional
    public void createExamQuestionAnswers() throws Exception {
        int databaseSizeBeforeCreate = examQuestionAnswersRepository.findAll().size();

        // Create the ExamQuestionAnswers
        restExamQuestionAnswersMockMvc.perform(post("/api/exam-question-answers")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(examQuestionAnswers)))
            .andExpect(status().isCreated());

        // Validate the ExamQuestionAnswers in the database
        List<ExamQuestionAnswers> examQuestionAnswersList = examQuestionAnswersRepository.findAll();
        assertThat(examQuestionAnswersList).hasSize(databaseSizeBeforeCreate + 1);
        ExamQuestionAnswers testExamQuestionAnswers = examQuestionAnswersList.get(examQuestionAnswersList.size() - 1);
        assertThat(testExamQuestionAnswers.getDescAr()).isEqualTo(DEFAULT_DESC_AR);
        assertThat(testExamQuestionAnswers.getDescEn()).isEqualTo(DEFAULT_DESC_EN);
        assertThat(testExamQuestionAnswers.getCode()).isEqualTo(DEFAULT_CODE);
        assertThat(testExamQuestionAnswers.getImgPath()).isEqualTo(DEFAULT_IMG_PATH);
        assertThat(testExamQuestionAnswers.isIsRightAnswer()).isEqualTo(DEFAULT_IS_RIGHT_ANSWER);
        assertThat(testExamQuestionAnswers.getStatus()).isEqualTo(DEFAULT_STATUS);
        assertThat(testExamQuestionAnswers.getAnswerId()).isEqualTo(DEFAULT_ANSWER_ID);
    }

    @Test
    @Transactional
    public void createExamQuestionAnswersWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = examQuestionAnswersRepository.findAll().size();

        // Create the ExamQuestionAnswers with an existing ID
        examQuestionAnswers.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restExamQuestionAnswersMockMvc.perform(post("/api/exam-question-answers")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(examQuestionAnswers)))
            .andExpect(status().isBadRequest());

        // Validate the ExamQuestionAnswers in the database
        List<ExamQuestionAnswers> examQuestionAnswersList = examQuestionAnswersRepository.findAll();
        assertThat(examQuestionAnswersList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkDescArIsRequired() throws Exception {
        int databaseSizeBeforeTest = examQuestionAnswersRepository.findAll().size();
        // set the field null
        examQuestionAnswers.setDescAr(null);

        // Create the ExamQuestionAnswers, which fails.

        restExamQuestionAnswersMockMvc.perform(post("/api/exam-question-answers")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(examQuestionAnswers)))
            .andExpect(status().isBadRequest());

        List<ExamQuestionAnswers> examQuestionAnswersList = examQuestionAnswersRepository.findAll();
        assertThat(examQuestionAnswersList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkDescEnIsRequired() throws Exception {
        int databaseSizeBeforeTest = examQuestionAnswersRepository.findAll().size();
        // set the field null
        examQuestionAnswers.setDescEn(null);

        // Create the ExamQuestionAnswers, which fails.

        restExamQuestionAnswersMockMvc.perform(post("/api/exam-question-answers")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(examQuestionAnswers)))
            .andExpect(status().isBadRequest());

        List<ExamQuestionAnswers> examQuestionAnswersList = examQuestionAnswersRepository.findAll();
        assertThat(examQuestionAnswersList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkCodeIsRequired() throws Exception {
        int databaseSizeBeforeTest = examQuestionAnswersRepository.findAll().size();
        // set the field null
        examQuestionAnswers.setCode(null);

        // Create the ExamQuestionAnswers, which fails.

        restExamQuestionAnswersMockMvc.perform(post("/api/exam-question-answers")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(examQuestionAnswers)))
            .andExpect(status().isBadRequest());

        List<ExamQuestionAnswers> examQuestionAnswersList = examQuestionAnswersRepository.findAll();
        assertThat(examQuestionAnswersList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllExamQuestionAnswers() throws Exception {
        // Initialize the database
        examQuestionAnswersRepository.saveAndFlush(examQuestionAnswers);

        // Get all the examQuestionAnswersList
        restExamQuestionAnswersMockMvc.perform(get("/api/exam-question-answers?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(examQuestionAnswers.getId().intValue())))
            .andExpect(jsonPath("$.[*].descAr").value(hasItem(DEFAULT_DESC_AR)))
            .andExpect(jsonPath("$.[*].descEn").value(hasItem(DEFAULT_DESC_EN)))
            .andExpect(jsonPath("$.[*].code").value(hasItem(DEFAULT_CODE)))
            .andExpect(jsonPath("$.[*].imgPath").value(hasItem(DEFAULT_IMG_PATH)))
            .andExpect(jsonPath("$.[*].isRightAnswer").value(hasItem(DEFAULT_IS_RIGHT_ANSWER.booleanValue())))
            .andExpect(jsonPath("$.[*].status").value(hasItem(DEFAULT_STATUS)))
            .andExpect(jsonPath("$.[*].answerId").value(hasItem(DEFAULT_ANSWER_ID)));
    }
    
    @Test
    @Transactional
    public void getExamQuestionAnswers() throws Exception {
        // Initialize the database
        examQuestionAnswersRepository.saveAndFlush(examQuestionAnswers);

        // Get the examQuestionAnswers
        restExamQuestionAnswersMockMvc.perform(get("/api/exam-question-answers/{id}", examQuestionAnswers.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(examQuestionAnswers.getId().intValue()))
            .andExpect(jsonPath("$.descAr").value(DEFAULT_DESC_AR))
            .andExpect(jsonPath("$.descEn").value(DEFAULT_DESC_EN))
            .andExpect(jsonPath("$.code").value(DEFAULT_CODE))
            .andExpect(jsonPath("$.imgPath").value(DEFAULT_IMG_PATH))
            .andExpect(jsonPath("$.isRightAnswer").value(DEFAULT_IS_RIGHT_ANSWER.booleanValue()))
            .andExpect(jsonPath("$.status").value(DEFAULT_STATUS))
            .andExpect(jsonPath("$.answerId").value(DEFAULT_ANSWER_ID));
    }

    @Test
    @Transactional
    public void getNonExistingExamQuestionAnswers() throws Exception {
        // Get the examQuestionAnswers
        restExamQuestionAnswersMockMvc.perform(get("/api/exam-question-answers/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateExamQuestionAnswers() throws Exception {
        // Initialize the database
        examQuestionAnswersService.save(examQuestionAnswers);

        int databaseSizeBeforeUpdate = examQuestionAnswersRepository.findAll().size();

        // Update the examQuestionAnswers
        ExamQuestionAnswers updatedExamQuestionAnswers = examQuestionAnswersRepository.findById(examQuestionAnswers.getId()).get();
        // Disconnect from session so that the updates on updatedExamQuestionAnswers are not directly saved in db
        em.detach(updatedExamQuestionAnswers);
        updatedExamQuestionAnswers
            .descAr(UPDATED_DESC_AR)
            .descEn(UPDATED_DESC_EN)
            .code(UPDATED_CODE)
            .imgPath(UPDATED_IMG_PATH)
            .isRightAnswer(UPDATED_IS_RIGHT_ANSWER)
            .status(UPDATED_STATUS)
            .answerId(UPDATED_ANSWER_ID);

        restExamQuestionAnswersMockMvc.perform(put("/api/exam-question-answers")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedExamQuestionAnswers)))
            .andExpect(status().isOk());

        // Validate the ExamQuestionAnswers in the database
        List<ExamQuestionAnswers> examQuestionAnswersList = examQuestionAnswersRepository.findAll();
        assertThat(examQuestionAnswersList).hasSize(databaseSizeBeforeUpdate);
        ExamQuestionAnswers testExamQuestionAnswers = examQuestionAnswersList.get(examQuestionAnswersList.size() - 1);
        assertThat(testExamQuestionAnswers.getDescAr()).isEqualTo(UPDATED_DESC_AR);
        assertThat(testExamQuestionAnswers.getDescEn()).isEqualTo(UPDATED_DESC_EN);
        assertThat(testExamQuestionAnswers.getCode()).isEqualTo(UPDATED_CODE);
        assertThat(testExamQuestionAnswers.getImgPath()).isEqualTo(UPDATED_IMG_PATH);
        assertThat(testExamQuestionAnswers.isIsRightAnswer()).isEqualTo(UPDATED_IS_RIGHT_ANSWER);
        assertThat(testExamQuestionAnswers.getStatus()).isEqualTo(UPDATED_STATUS);
        assertThat(testExamQuestionAnswers.getAnswerId()).isEqualTo(UPDATED_ANSWER_ID);
    }

    @Test
    @Transactional
    public void updateNonExistingExamQuestionAnswers() throws Exception {
        int databaseSizeBeforeUpdate = examQuestionAnswersRepository.findAll().size();

        // Create the ExamQuestionAnswers

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restExamQuestionAnswersMockMvc.perform(put("/api/exam-question-answers")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(examQuestionAnswers)))
            .andExpect(status().isBadRequest());

        // Validate the ExamQuestionAnswers in the database
        List<ExamQuestionAnswers> examQuestionAnswersList = examQuestionAnswersRepository.findAll();
        assertThat(examQuestionAnswersList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteExamQuestionAnswers() throws Exception {
        // Initialize the database
        examQuestionAnswersService.save(examQuestionAnswers);

        int databaseSizeBeforeDelete = examQuestionAnswersRepository.findAll().size();

        // Delete the examQuestionAnswers
        restExamQuestionAnswersMockMvc.perform(delete("/api/exam-question-answers/{id}", examQuestionAnswers.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<ExamQuestionAnswers> examQuestionAnswersList = examQuestionAnswersRepository.findAll();
        assertThat(examQuestionAnswersList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
