package com.isoft.dls.sync.service;

import com.isoft.dls.sync.domain.DlsPersons;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link DlsPersons}.
 */
public interface DlsPersonsService {

    /**
     * Save a dlsPersons.
     *
     * @param dlsPersons the entity to save.
     * @return the persisted entity.
     */
    DlsPersons save(DlsPersons dlsPersons);

    /**
     * Get all the dlsPersons.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<DlsPersons> findAll(Pageable pageable);


    /**
     * Get the "id" dlsPersons.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<DlsPersons> findOne(Long id);

    /**
     * Delete the "id" dlsPersons.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
