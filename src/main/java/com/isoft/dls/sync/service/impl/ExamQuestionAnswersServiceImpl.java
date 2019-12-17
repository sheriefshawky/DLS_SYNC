package com.isoft.dls.sync.service.impl;

import com.isoft.dls.sync.service.ExamQuestionAnswersService;
import com.isoft.dls.sync.domain.ExamQuestionAnswers;
import com.isoft.dls.sync.repository.ExamQuestionAnswersRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link ExamQuestionAnswers}.
 */
@Service
@Transactional
public class ExamQuestionAnswersServiceImpl implements ExamQuestionAnswersService {

    private final Logger log = LoggerFactory.getLogger(ExamQuestionAnswersServiceImpl.class);

    private final ExamQuestionAnswersRepository examQuestionAnswersRepository;

    public ExamQuestionAnswersServiceImpl(ExamQuestionAnswersRepository examQuestionAnswersRepository) {
        this.examQuestionAnswersRepository = examQuestionAnswersRepository;
    }

    /**
     * Save a examQuestionAnswers.
     *
     * @param examQuestionAnswers the entity to save.
     * @return the persisted entity.
     */
    @Override
    public ExamQuestionAnswers save(ExamQuestionAnswers examQuestionAnswers) {
        log.debug("Request to save ExamQuestionAnswers : {}", examQuestionAnswers);
        return examQuestionAnswersRepository.save(examQuestionAnswers);
    }

    /**
     * Get all the examQuestionAnswers.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public Page<ExamQuestionAnswers> findAll(Pageable pageable) {
        log.debug("Request to get all ExamQuestionAnswers");
        return examQuestionAnswersRepository.findAll(pageable);
    }


    /**
     * Get one examQuestionAnswers by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<ExamQuestionAnswers> findOne(Long id) {
        log.debug("Request to get ExamQuestionAnswers : {}", id);
        return examQuestionAnswersRepository.findById(id);
    }

    /**
     * Delete the examQuestionAnswers by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete ExamQuestionAnswers : {}", id);
        examQuestionAnswersRepository.deleteById(id);
    }
}
