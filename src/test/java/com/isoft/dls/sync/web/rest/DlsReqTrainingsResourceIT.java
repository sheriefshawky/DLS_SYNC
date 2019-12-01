package com.isoft.dls.sync.web.rest;

import com.isoft.dls.sync.DlssyncApp;
import com.isoft.dls.sync.domain.DlsReqTrainings;
import com.isoft.dls.sync.repository.DlsReqTrainingsRepository;
import com.isoft.dls.sync.service.DlsReqTrainingsService;
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
 * Integration tests for the {@link DlsReqTrainingsResource} REST controller.
 */
@SpringBootTest(classes = DlssyncApp.class)
public class DlsReqTrainingsResourceIT {

    private static final Integer DEFAULT_TRAINING_CODE = 1;
    private static final Integer UPDATED_TRAINING_CODE = 2;

    private static final Integer DEFAULT_TRAINING_LECTURES = 1;
    private static final Integer UPDATED_TRAINING_LECTURES = 2;

    private static final Integer DEFAULT_TRAININGFULFILLED = 1;
    private static final Integer UPDATED_TRAININGFULFILLED = 2;

    private static final Boolean DEFAULT_EXPORTED = false;
    private static final Boolean UPDATED_EXPORTED = true;

    @Autowired
    private DlsReqTrainingsRepository dlsReqTrainingsRepository;

    @Autowired
    private DlsReqTrainingsService dlsReqTrainingsService;

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

    private MockMvc restDlsReqTrainingsMockMvc;

    private DlsReqTrainings dlsReqTrainings;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final DlsReqTrainingsResource dlsReqTrainingsResource = new DlsReqTrainingsResource(dlsReqTrainingsService);
        this.restDlsReqTrainingsMockMvc = MockMvcBuilders.standaloneSetup(dlsReqTrainingsResource)
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
    public static DlsReqTrainings createEntity(EntityManager em) {
        DlsReqTrainings dlsReqTrainings = new DlsReqTrainings()
            .trainingCode(DEFAULT_TRAINING_CODE)
            .trainingLectures(DEFAULT_TRAINING_LECTURES)
            .trainingfulfilled(DEFAULT_TRAININGFULFILLED)
            .exported(DEFAULT_EXPORTED);
        return dlsReqTrainings;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static DlsReqTrainings createUpdatedEntity(EntityManager em) {
        DlsReqTrainings dlsReqTrainings = new DlsReqTrainings()
            .trainingCode(UPDATED_TRAINING_CODE)
            .trainingLectures(UPDATED_TRAINING_LECTURES)
            .trainingfulfilled(UPDATED_TRAININGFULFILLED)
            .exported(UPDATED_EXPORTED);
        return dlsReqTrainings;
    }

    @BeforeEach
    public void initTest() {
        dlsReqTrainings = createEntity(em);
    }

    @Test
    @Transactional
    public void createDlsReqTrainings() throws Exception {
        int databaseSizeBeforeCreate = dlsReqTrainingsRepository.findAll().size();

        // Create the DlsReqTrainings
        restDlsReqTrainingsMockMvc.perform(post("/api/dls-req-trainings")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(dlsReqTrainings)))
            .andExpect(status().isCreated());

        // Validate the DlsReqTrainings in the database
        List<DlsReqTrainings> dlsReqTrainingsList = dlsReqTrainingsRepository.findAll();
        assertThat(dlsReqTrainingsList).hasSize(databaseSizeBeforeCreate + 1);
        DlsReqTrainings testDlsReqTrainings = dlsReqTrainingsList.get(dlsReqTrainingsList.size() - 1);
        assertThat(testDlsReqTrainings.getTrainingCode()).isEqualTo(DEFAULT_TRAINING_CODE);
        assertThat(testDlsReqTrainings.getTrainingLectures()).isEqualTo(DEFAULT_TRAINING_LECTURES);
        assertThat(testDlsReqTrainings.getTrainingfulfilled()).isEqualTo(DEFAULT_TRAININGFULFILLED);
        assertThat(testDlsReqTrainings.isExported()).isEqualTo(DEFAULT_EXPORTED);
    }

    @Test
    @Transactional
    public void createDlsReqTrainingsWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = dlsReqTrainingsRepository.findAll().size();

        // Create the DlsReqTrainings with an existing ID
        dlsReqTrainings.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restDlsReqTrainingsMockMvc.perform(post("/api/dls-req-trainings")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(dlsReqTrainings)))
            .andExpect(status().isBadRequest());

        // Validate the DlsReqTrainings in the database
        List<DlsReqTrainings> dlsReqTrainingsList = dlsReqTrainingsRepository.findAll();
        assertThat(dlsReqTrainingsList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkTrainingCodeIsRequired() throws Exception {
        int databaseSizeBeforeTest = dlsReqTrainingsRepository.findAll().size();
        // set the field null
        dlsReqTrainings.setTrainingCode(null);

        // Create the DlsReqTrainings, which fails.

        restDlsReqTrainingsMockMvc.perform(post("/api/dls-req-trainings")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(dlsReqTrainings)))
            .andExpect(status().isBadRequest());

        List<DlsReqTrainings> dlsReqTrainingsList = dlsReqTrainingsRepository.findAll();
        assertThat(dlsReqTrainingsList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllDlsReqTrainings() throws Exception {
        // Initialize the database
        dlsReqTrainingsRepository.saveAndFlush(dlsReqTrainings);

        // Get all the dlsReqTrainingsList
        restDlsReqTrainingsMockMvc.perform(get("/api/dls-req-trainings?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(dlsReqTrainings.getId().intValue())))
            .andExpect(jsonPath("$.[*].trainingCode").value(hasItem(DEFAULT_TRAINING_CODE)))
            .andExpect(jsonPath("$.[*].trainingLectures").value(hasItem(DEFAULT_TRAINING_LECTURES)))
            .andExpect(jsonPath("$.[*].trainingfulfilled").value(hasItem(DEFAULT_TRAININGFULFILLED)))
            .andExpect(jsonPath("$.[*].exported").value(hasItem(DEFAULT_EXPORTED.booleanValue())));
    }
    
    @Test
    @Transactional
    public void getDlsReqTrainings() throws Exception {
        // Initialize the database
        dlsReqTrainingsRepository.saveAndFlush(dlsReqTrainings);

        // Get the dlsReqTrainings
        restDlsReqTrainingsMockMvc.perform(get("/api/dls-req-trainings/{id}", dlsReqTrainings.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(dlsReqTrainings.getId().intValue()))
            .andExpect(jsonPath("$.trainingCode").value(DEFAULT_TRAINING_CODE))
            .andExpect(jsonPath("$.trainingLectures").value(DEFAULT_TRAINING_LECTURES))
            .andExpect(jsonPath("$.trainingfulfilled").value(DEFAULT_TRAININGFULFILLED))
            .andExpect(jsonPath("$.exported").value(DEFAULT_EXPORTED.booleanValue()));
    }

    @Test
    @Transactional
    public void getNonExistingDlsReqTrainings() throws Exception {
        // Get the dlsReqTrainings
        restDlsReqTrainingsMockMvc.perform(get("/api/dls-req-trainings/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateDlsReqTrainings() throws Exception {
        // Initialize the database
        dlsReqTrainingsService.save(dlsReqTrainings);

        int databaseSizeBeforeUpdate = dlsReqTrainingsRepository.findAll().size();

        // Update the dlsReqTrainings
        DlsReqTrainings updatedDlsReqTrainings = dlsReqTrainingsRepository.findById(dlsReqTrainings.getId()).get();
        // Disconnect from session so that the updates on updatedDlsReqTrainings are not directly saved in db
        em.detach(updatedDlsReqTrainings);
        updatedDlsReqTrainings
            .trainingCode(UPDATED_TRAINING_CODE)
            .trainingLectures(UPDATED_TRAINING_LECTURES)
            .trainingfulfilled(UPDATED_TRAININGFULFILLED)
            .exported(UPDATED_EXPORTED);

        restDlsReqTrainingsMockMvc.perform(put("/api/dls-req-trainings")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedDlsReqTrainings)))
            .andExpect(status().isOk());

        // Validate the DlsReqTrainings in the database
        List<DlsReqTrainings> dlsReqTrainingsList = dlsReqTrainingsRepository.findAll();
        assertThat(dlsReqTrainingsList).hasSize(databaseSizeBeforeUpdate);
        DlsReqTrainings testDlsReqTrainings = dlsReqTrainingsList.get(dlsReqTrainingsList.size() - 1);
        assertThat(testDlsReqTrainings.getTrainingCode()).isEqualTo(UPDATED_TRAINING_CODE);
        assertThat(testDlsReqTrainings.getTrainingLectures()).isEqualTo(UPDATED_TRAINING_LECTURES);
        assertThat(testDlsReqTrainings.getTrainingfulfilled()).isEqualTo(UPDATED_TRAININGFULFILLED);
        assertThat(testDlsReqTrainings.isExported()).isEqualTo(UPDATED_EXPORTED);
    }

    @Test
    @Transactional
    public void updateNonExistingDlsReqTrainings() throws Exception {
        int databaseSizeBeforeUpdate = dlsReqTrainingsRepository.findAll().size();

        // Create the DlsReqTrainings

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restDlsReqTrainingsMockMvc.perform(put("/api/dls-req-trainings")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(dlsReqTrainings)))
            .andExpect(status().isBadRequest());

        // Validate the DlsReqTrainings in the database
        List<DlsReqTrainings> dlsReqTrainingsList = dlsReqTrainingsRepository.findAll();
        assertThat(dlsReqTrainingsList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteDlsReqTrainings() throws Exception {
        // Initialize the database
        dlsReqTrainingsService.save(dlsReqTrainings);

        int databaseSizeBeforeDelete = dlsReqTrainingsRepository.findAll().size();

        // Delete the dlsReqTrainings
        restDlsReqTrainingsMockMvc.perform(delete("/api/dls-req-trainings/{id}", dlsReqTrainings.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<DlsReqTrainings> dlsReqTrainingsList = dlsReqTrainingsRepository.findAll();
        assertThat(dlsReqTrainingsList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
