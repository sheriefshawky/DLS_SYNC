package com.isoft.dls.sync.service;

import com.isoft.dls.sync.domain.DlsReqExams;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link DlsReqExams}.
 */
public interface DlsReqExamsService {

    /**
     * Save a dlsReqExams.
     *
     * @param dlsReqExams the entity to save.
     * @return the persisted entity.
     */
    DlsReqExams save(DlsReqExams dlsReqExams);

    /**
     * Get all the dlsReqExams.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<DlsReqExams> findAll(Pageable pageable);


    /**
     * Get the "id" dlsReqExams.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<DlsReqExams> findOne(Long id);

    /**
     * Delete the "id" dlsReqExams.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
