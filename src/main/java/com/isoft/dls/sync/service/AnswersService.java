package com.isoft.dls.sync.service;

import com.isoft.dls.sync.domain.Answers;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link Answers}.
 */
public interface AnswersService {

    /**
     * Save a answers.
     *
     * @param answers the entity to save.
     * @return the persisted entity.
     */
    Answers save(Answers answers);

    /**
     * Get all the answers.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<Answers> findAll(Pageable pageable);


    /**
     * Get the "id" answers.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<Answers> findOne(Long id);

    /**
     * Delete the "id" answers.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
