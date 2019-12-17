package com.isoft.dls.sync.service;

import com.isoft.dls.sync.domain.ExamQuestionAnswers;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link ExamQuestionAnswers}.
 */
public interface ExamQuestionAnswersService {

    /**
     * Save a examQuestionAnswers.
     *
     * @param examQuestionAnswers the entity to save.
     * @return the persisted entity.
     */
    ExamQuestionAnswers save(ExamQuestionAnswers examQuestionAnswers);

    /**
     * Get all the examQuestionAnswers.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<ExamQuestionAnswers> findAll(Pageable pageable);


    /**
     * Get the "id" examQuestionAnswers.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<ExamQuestionAnswers> findOne(Long id);

    /**
     * Delete the "id" examQuestionAnswers.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
