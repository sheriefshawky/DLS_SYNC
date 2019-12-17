package com.isoft.dls.sync.service;

import com.isoft.dls.sync.domain.ExamQuestions;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link ExamQuestions}.
 */
public interface ExamQuestionsService {

    /**
     * Save a examQuestions.
     *
     * @param examQuestions the entity to save.
     * @return the persisted entity.
     */
    ExamQuestions save(ExamQuestions examQuestions);

    /**
     * Get all the examQuestions.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<ExamQuestions> findAll(Pageable pageable);


    /**
     * Get the "id" examQuestions.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<ExamQuestions> findOne(Long id);

    /**
     * Delete the "id" examQuestions.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
