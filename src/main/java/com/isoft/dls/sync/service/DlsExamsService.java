package com.isoft.dls.sync.service;

import com.isoft.dls.sync.domain.DlsExams;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link DlsExams}.
 */
public interface DlsExamsService {

    /**
     * Save a dlsExams.
     *
     * @param dlsExams the entity to save.
     * @return the persisted entity.
     */
    DlsExams save(DlsExams dlsExams);

    /**
     * Get all the dlsExams.
     *
     * @return the list of entities.
     */
    List<DlsExams> findAll();


    /**
     * Get the "id" dlsExams.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<DlsExams> findOne(Long id);

    /**
     * Delete the "id" dlsExams.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
