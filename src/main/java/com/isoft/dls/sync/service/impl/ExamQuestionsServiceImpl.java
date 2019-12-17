package com.isoft.dls.sync.service.impl;

import com.isoft.dls.sync.service.ExamQuestionsService;
import com.isoft.dls.sync.domain.ExamQuestions;
import com.isoft.dls.sync.repository.ExamQuestionsRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link ExamQuestions}.
 */
@Service
@Transactional
public class ExamQuestionsServiceImpl implements ExamQuestionsService {

    private final Logger log = LoggerFactory.getLogger(ExamQuestionsServiceImpl.class);

    private final ExamQuestionsRepository examQuestionsRepository;

    public ExamQuestionsServiceImpl(ExamQuestionsRepository examQuestionsRepository) {
        this.examQuestionsRepository = examQuestionsRepository;
    }

    /**
     * Save a examQuestions.
     *
     * @param examQuestions the entity to save.
     * @return the persisted entity.
     */
    @Override
    public ExamQuestions save(ExamQuestions examQuestions) {
        log.debug("Request to save ExamQuestions : {}", examQuestions);
        return examQuestionsRepository.save(examQuestions);
    }

    /**
     * Get all the examQuestions.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public Page<ExamQuestions> findAll(Pageable pageable) {
        log.debug("Request to get all ExamQuestions");
        return examQuestionsRepository.findAll(pageable);
    }


    /**
     * Get one examQuestions by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<ExamQuestions> findOne(Long id) {
        log.debug("Request to get ExamQuestions : {}", id);
        return examQuestionsRepository.findById(id);
    }

    /**
     * Delete the examQuestions by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete ExamQuestions : {}", id);
        examQuestionsRepository.deleteById(id);
    }
}
