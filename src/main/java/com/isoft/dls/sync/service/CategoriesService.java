package com.isoft.dls.sync.service;

import com.isoft.dls.sync.domain.Categories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link Categories}.
 */
public interface CategoriesService {

    /**
     * Save a categories.
     *
     * @param categories the entity to save.
     * @return the persisted entity.
     */
    Categories save(Categories categories);

    /**
     * Get all the categories.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<Categories> findAll(Pageable pageable);


    /**
     * Get the "id" categories.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<Categories> findOne(Long id);

    /**
     * Delete the "id" categories.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
