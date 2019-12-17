package com.isoft.dls.sync.service;

import com.isoft.dls.sync.domain.TemplateCategories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link TemplateCategories}.
 */
public interface TemplateCategoriesService {

    /**
     * Save a templateCategories.
     *
     * @param templateCategories the entity to save.
     * @return the persisted entity.
     */
    TemplateCategories save(TemplateCategories templateCategories);

    /**
     * Get all the templateCategories.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<TemplateCategories> findAll(Pageable pageable);


    /**
     * Get the "id" templateCategories.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<TemplateCategories> findOne(Long id);

    /**
     * Delete the "id" templateCategories.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
