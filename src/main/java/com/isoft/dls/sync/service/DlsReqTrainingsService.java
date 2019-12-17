package com.isoft.dls.sync.service;

import com.isoft.dls.sync.domain.DlsReqTrainings;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link DlsReqTrainings}.
 */
public interface DlsReqTrainingsService {

    /**
     * Save a dlsReqTrainings.
     *
     * @param dlsReqTrainings the entity to save.
     * @return the persisted entity.
     */
    DlsReqTrainings save(DlsReqTrainings dlsReqTrainings);

    /**
     * Get all the dlsReqTrainings.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<DlsReqTrainings> findAll(Pageable pageable);


    /**
     * Get the "id" dlsReqTrainings.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<DlsReqTrainings> findOne(Long id);

    /**
     * Delete the "id" dlsReqTrainings.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
