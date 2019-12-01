package com.isoft.dls.sync.service.impl;

import com.isoft.dls.sync.service.DlsPersonsService;
import com.isoft.dls.sync.domain.DlsPersons;
import com.isoft.dls.sync.repository.DlsPersonsRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link DlsPersons}.
 */
@Service
@Transactional
public class DlsPersonsServiceImpl implements DlsPersonsService {

    private final Logger log = LoggerFactory.getLogger(DlsPersonsServiceImpl.class);

    private final DlsPersonsRepository dlsPersonsRepository;

    public DlsPersonsServiceImpl(DlsPersonsRepository dlsPersonsRepository) {
        this.dlsPersonsRepository = dlsPersonsRepository;
    }

    /**
     * Save a dlsPersons.
     *
     * @param dlsPersons the entity to save.
     * @return the persisted entity.
     */
    @Override
    public DlsPersons save(DlsPersons dlsPersons) {
        log.debug("Request to save DlsPersons : {}", dlsPersons);
        return dlsPersonsRepository.save(dlsPersons);
    }

    /**
     * Get all the dlsPersons.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public Page<DlsPersons> findAll(Pageable pageable) {
        log.debug("Request to get all DlsPersons");
        return dlsPersonsRepository.findAll(pageable);
    }


    /**
     * Get one dlsPersons by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<DlsPersons> findOne(Long id) {
        log.debug("Request to get DlsPersons : {}", id);
        return dlsPersonsRepository.findById(id);
    }

    /**
     * Delete the dlsPersons by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete DlsPersons : {}", id);
        dlsPersonsRepository.deleteById(id);
    }
}
