package com.isoft.dls.sync.service;

import com.isoft.dls.sync.domain.DlsTrsTypes;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link DlsTrsTypes}.
 */
public interface DlsTrsTypesService {

    /**
     * Save a dlsTrsTypes.
     *
     * @param dlsTrsTypes the entity to save.
     * @return the persisted entity.
     */
    DlsTrsTypes save(DlsTrsTypes dlsTrsTypes);

    /**
     * Get all the dlsTrsTypes.
     *
     * @return the list of entities.
     */
    List<DlsTrsTypes> findAll();


    /**
     * Get the "id" dlsTrsTypes.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<DlsTrsTypes> findOne(Long id);

    /**
     * Delete the "id" dlsTrsTypes.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
