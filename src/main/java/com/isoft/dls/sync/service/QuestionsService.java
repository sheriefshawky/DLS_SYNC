package com.isoft.dls.sync.service;

import com.isoft.dls.sync.domain.Questions;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link Questions}.
 */
public interface QuestionsService {

    /**
     * Save a questions.
     *
     * @param questions the entity to save.
     * @return the persisted entity.
     */
    Questions save(Questions questions);

    /**
     * Get all the questions.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<Questions> findAll(Pageable pageable);


    /**
     * Get the "id" questions.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<Questions> findOne(Long id);

    /**
     * Delete the "id" questions.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
