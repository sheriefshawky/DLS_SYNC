package com.isoft.dls.sync.service;

import com.isoft.dls.sync.domain.DlsRequests;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link DlsRequests}.
 */
public interface DlsRequestsService {

    /**
     * Save a dlsRequests.
     *
     * @param dlsRequests the entity to save.
     * @return the persisted entity.
     */
    DlsRequests save(DlsRequests dlsRequests);

    /**
     * Get all the dlsRequests.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<DlsRequests> findAll(Pageable pageable);


    /**
     * Get the "id" dlsRequests.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<DlsRequests> findOne(Long id);

    /**
     * Delete the "id" dlsRequests.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
