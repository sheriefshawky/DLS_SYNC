package com.isoft.dls.sync.service;

import com.isoft.dls.sync.domain.DlsVhlTrsExams;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link DlsVhlTrsExams}.
 */
public interface DlsVhlTrsExamsService {

    /**
     * Save a dlsVhlTrsExams.
     *
     * @param dlsVhlTrsExams the entity to save.
     * @return the persisted entity.
     */
    DlsVhlTrsExams save(DlsVhlTrsExams dlsVhlTrsExams);

    /**
     * Get all the dlsVhlTrsExams.
     *
     * @return the list of entities.
     */
    List<DlsVhlTrsExams> findAll();


    /**
     * Get the "id" dlsVhlTrsExams.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<DlsVhlTrsExams> findOne(Long id);

    /**
     * Delete the "id" dlsVhlTrsExams.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
