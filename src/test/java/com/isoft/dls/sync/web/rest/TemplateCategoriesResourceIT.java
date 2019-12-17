package com.isoft.dls.sync.web.rest;

import com.isoft.dls.sync.DlssyncApp;
import com.isoft.dls.sync.domain.TemplateCategories;
import com.isoft.dls.sync.repository.TemplateCategoriesRepository;
import com.isoft.dls.sync.service.TemplateCategoriesService;
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
 * Integration tests for the {@link TemplateCategoriesResource} REST controller.
 */
@SpringBootTest(classes = DlssyncApp.class)
public class TemplateCategoriesResourceIT {

    private static final String DEFAULT_NAME_AR = "AAAAAAAAAA";
    private static final String UPDATED_NAME_AR = "BBBBBBBBBB";

    private static final String DEFAULT_NAME_EN = "AAAAAAAAAA";
    private static final String UPDATED_NAME_EN = "BBBBBBBBBB";

    private static final String DEFAULT_CODE = "AAAAAAAAAA";
    private static final String UPDATED_CODE = "BBBBBBBBBB";

    private static final Integer DEFAULT_NO_OF_QUESTIONS = 1;
    private static final Integer UPDATED_NO_OF_QUESTIONS = 2;

    private static final Integer DEFAULT_SEQ = 1;
    private static final Integer UPDATED_SEQ = 2;

    @Autowired
    private TemplateCategoriesRepository templateCategoriesRepository;

    @Autowired
    private TemplateCategoriesService templateCategoriesService;

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

    private MockMvc restTemplateCategoriesMockMvc;

    private TemplateCategories templateCategories;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final TemplateCategoriesResource templateCategoriesResource = new TemplateCategoriesResource(templateCategoriesService);
        this.restTemplateCategoriesMockMvc = MockMvcBuilders.standaloneSetup(templateCategoriesResource)
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
    public static TemplateCategories createEntity(EntityManager em) {
        TemplateCategories templateCategories = new TemplateCategories()
            .nameAr(DEFAULT_NAME_AR)
            .nameEn(DEFAULT_NAME_EN)
            .code(DEFAULT_CODE)
            .noOfQuestions(DEFAULT_NO_OF_QUESTIONS)
            .seq(DEFAULT_SEQ);
        return templateCategories;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static TemplateCategories createUpdatedEntity(EntityManager em) {
        TemplateCategories templateCategories = new TemplateCategories()
            .nameAr(UPDATED_NAME_AR)
            .nameEn(UPDATED_NAME_EN)
            .code(UPDATED_CODE)
            .noOfQuestions(UPDATED_NO_OF_QUESTIONS)
            .seq(UPDATED_SEQ);
        return templateCategories;
    }

    @BeforeEach
    public void initTest() {
        templateCategories = createEntity(em);
    }

    @Test
    @Transactional
    public void createTemplateCategories() throws Exception {
        int databaseSizeBeforeCreate = templateCategoriesRepository.findAll().size();

        // Create the TemplateCategories
        restTemplateCategoriesMockMvc.perform(post("/api/template-categories")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(templateCategories)))
            .andExpect(status().isCreated());

        // Validate the TemplateCategories in the database
        List<TemplateCategories> templateCategoriesList = templateCategoriesRepository.findAll();
        assertThat(templateCategoriesList).hasSize(databaseSizeBeforeCreate + 1);
        TemplateCategories testTemplateCategories = templateCategoriesList.get(templateCategoriesList.size() - 1);
        assertThat(testTemplateCategories.getNameAr()).isEqualTo(DEFAULT_NAME_AR);
        assertThat(testTemplateCategories.getNameEn()).isEqualTo(DEFAULT_NAME_EN);
        assertThat(testTemplateCategories.getCode()).isEqualTo(DEFAULT_CODE);
        assertThat(testTemplateCategories.getNoOfQuestions()).isEqualTo(DEFAULT_NO_OF_QUESTIONS);
        assertThat(testTemplateCategories.getSeq()).isEqualTo(DEFAULT_SEQ);
    }

    @Test
    @Transactional
    public void createTemplateCategoriesWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = templateCategoriesRepository.findAll().size();

        // Create the TemplateCategories with an existing ID
        templateCategories.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restTemplateCategoriesMockMvc.perform(post("/api/template-categories")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(templateCategories)))
            .andExpect(status().isBadRequest());

        // Validate the TemplateCategories in the database
        List<TemplateCategories> templateCategoriesList = templateCategoriesRepository.findAll();
        assertThat(templateCategoriesList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkNameArIsRequired() throws Exception {
        int databaseSizeBeforeTest = templateCategoriesRepository.findAll().size();
        // set the field null
        templateCategories.setNameAr(null);

        // Create the TemplateCategories, which fails.

        restTemplateCategoriesMockMvc.perform(post("/api/template-categories")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(templateCategories)))
            .andExpect(status().isBadRequest());

        List<TemplateCategories> templateCategoriesList = templateCategoriesRepository.findAll();
        assertThat(templateCategoriesList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkNameEnIsRequired() throws Exception {
        int databaseSizeBeforeTest = templateCategoriesRepository.findAll().size();
        // set the field null
        templateCategories.setNameEn(null);

        // Create the TemplateCategories, which fails.

        restTemplateCategoriesMockMvc.perform(post("/api/template-categories")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(templateCategories)))
            .andExpect(status().isBadRequest());

        List<TemplateCategories> templateCategoriesList = templateCategoriesRepository.findAll();
        assertThat(templateCategoriesList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkCodeIsRequired() throws Exception {
        int databaseSizeBeforeTest = templateCategoriesRepository.findAll().size();
        // set the field null
        templateCategories.setCode(null);

        // Create the TemplateCategories, which fails.

        restTemplateCategoriesMockMvc.perform(post("/api/template-categories")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(templateCategories)))
            .andExpect(status().isBadRequest());

        List<TemplateCategories> templateCategoriesList = templateCategoriesRepository.findAll();
        assertThat(templateCategoriesList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkNoOfQuestionsIsRequired() throws Exception {
        int databaseSizeBeforeTest = templateCategoriesRepository.findAll().size();
        // set the field null
        templateCategories.setNoOfQuestions(null);

        // Create the TemplateCategories, which fails.

        restTemplateCategoriesMockMvc.perform(post("/api/template-categories")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(templateCategories)))
            .andExpect(status().isBadRequest());

        List<TemplateCategories> templateCategoriesList = templateCategoriesRepository.findAll();
        assertThat(templateCategoriesList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkSeqIsRequired() throws Exception {
        int databaseSizeBeforeTest = templateCategoriesRepository.findAll().size();
        // set the field null
        templateCategories.setSeq(null);

        // Create the TemplateCategories, which fails.

        restTemplateCategoriesMockMvc.perform(post("/api/template-categories")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(templateCategories)))
            .andExpect(status().isBadRequest());

        List<TemplateCategories> templateCategoriesList = templateCategoriesRepository.findAll();
        assertThat(templateCategoriesList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllTemplateCategories() throws Exception {
        // Initialize the database
        templateCategoriesRepository.saveAndFlush(templateCategories);

        // Get all the templateCategoriesList
        restTemplateCategoriesMockMvc.perform(get("/api/template-categories?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(templateCategories.getId().intValue())))
            .andExpect(jsonPath("$.[*].nameAr").value(hasItem(DEFAULT_NAME_AR)))
            .andExpect(jsonPath("$.[*].nameEn").value(hasItem(DEFAULT_NAME_EN)))
            .andExpect(jsonPath("$.[*].code").value(hasItem(DEFAULT_CODE)))
            .andExpect(jsonPath("$.[*].noOfQuestions").value(hasItem(DEFAULT_NO_OF_QUESTIONS)))
            .andExpect(jsonPath("$.[*].seq").value(hasItem(DEFAULT_SEQ)));
    }
    
    @Test
    @Transactional
    public void getTemplateCategories() throws Exception {
        // Initialize the database
        templateCategoriesRepository.saveAndFlush(templateCategories);

        // Get the templateCategories
        restTemplateCategoriesMockMvc.perform(get("/api/template-categories/{id}", templateCategories.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(templateCategories.getId().intValue()))
            .andExpect(jsonPath("$.nameAr").value(DEFAULT_NAME_AR))
            .andExpect(jsonPath("$.nameEn").value(DEFAULT_NAME_EN))
            .andExpect(jsonPath("$.code").value(DEFAULT_CODE))
            .andExpect(jsonPath("$.noOfQuestions").value(DEFAULT_NO_OF_QUESTIONS))
            .andExpect(jsonPath("$.seq").value(DEFAULT_SEQ));
    }

    @Test
    @Transactional
    public void getNonExistingTemplateCategories() throws Exception {
        // Get the templateCategories
        restTemplateCategoriesMockMvc.perform(get("/api/template-categories/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateTemplateCategories() throws Exception {
        // Initialize the database
        templateCategoriesService.save(templateCategories);

        int databaseSizeBeforeUpdate = templateCategoriesRepository.findAll().size();

        // Update the templateCategories
        TemplateCategories updatedTemplateCategories = templateCategoriesRepository.findById(templateCategories.getId()).get();
        // Disconnect from session so that the updates on updatedTemplateCategories are not directly saved in db
        em.detach(updatedTemplateCategories);
        updatedTemplateCategories
            .nameAr(UPDATED_NAME_AR)
            .nameEn(UPDATED_NAME_EN)
            .code(UPDATED_CODE)
            .noOfQuestions(UPDATED_NO_OF_QUESTIONS)
            .seq(UPDATED_SEQ);

        restTemplateCategoriesMockMvc.perform(put("/api/template-categories")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedTemplateCategories)))
            .andExpect(status().isOk());

        // Validate the TemplateCategories in the database
        List<TemplateCategories> templateCategoriesList = templateCategoriesRepository.findAll();
        assertThat(templateCategoriesList).hasSize(databaseSizeBeforeUpdate);
        TemplateCategories testTemplateCategories = templateCategoriesList.get(templateCategoriesList.size() - 1);
        assertThat(testTemplateCategories.getNameAr()).isEqualTo(UPDATED_NAME_AR);
        assertThat(testTemplateCategories.getNameEn()).isEqualTo(UPDATED_NAME_EN);
        assertThat(testTemplateCategories.getCode()).isEqualTo(UPDATED_CODE);
        assertThat(testTemplateCategories.getNoOfQuestions()).isEqualTo(UPDATED_NO_OF_QUESTIONS);
        assertThat(testTemplateCategories.getSeq()).isEqualTo(UPDATED_SEQ);
    }

    @Test
    @Transactional
    public void updateNonExistingTemplateCategories() throws Exception {
        int databaseSizeBeforeUpdate = templateCategoriesRepository.findAll().size();

        // Create the TemplateCategories

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restTemplateCategoriesMockMvc.perform(put("/api/template-categories")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(templateCategories)))
            .andExpect(status().isBadRequest());

        // Validate the TemplateCategories in the database
        List<TemplateCategories> templateCategoriesList = templateCategoriesRepository.findAll();
        assertThat(templateCategoriesList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteTemplateCategories() throws Exception {
        // Initialize the database
        templateCategoriesService.save(templateCategories);

        int databaseSizeBeforeDelete = templateCategoriesRepository.findAll().size();

        // Delete the templateCategories
        restTemplateCategoriesMockMvc.perform(delete("/api/template-categories/{id}", templateCategories.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<TemplateCategories> templateCategoriesList = templateCategoriesRepository.findAll();
        assertThat(templateCategoriesList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
