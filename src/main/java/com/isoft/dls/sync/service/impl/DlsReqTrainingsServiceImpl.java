package com.isoft.dls.sync.service.impl;

import com.isoft.dls.sync.service.DlsReqTrainingsService;
import com.isoft.dls.sync.domain.DlsReqTrainings;
import com.isoft.dls.sync.repository.DlsReqTrainingsRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link DlsReqTrainings}.
 */
@Service
@Transactional
public class DlsReqTrainingsServiceImpl implements DlsReqTrainingsService {

    private final Logger log = LoggerFactory.getLogger(DlsReqTrainingsServiceImpl.class);

    private final DlsReqTrainingsRepository dlsReqTrainingsRepository;

    public DlsReqTrainingsServiceImpl(DlsReqTrainingsRepository dlsReqTrainingsRepository) {
        this.dlsReqTrainingsRepository = dlsReqTrainingsRepository;
    }

    /**
     * Save a dlsReqTrainings.
     *
     * @param dlsReqTrainings the entity to save.
     * @return the persisted entity.
     */
    @Override
    public DlsReqTrainings save(DlsReqTrainings dlsReqTrainings) {
        log.debug("Request to save DlsReqTrainings : {}", dlsReqTrainings);
        return dlsReqTrainingsRepository.save(dlsReqTrainings);
    }

    /**
     * Get all the dlsReqTrainings.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public Page<DlsReqTrainings> findAll(Pageable pageable) {
        log.debug("Request to get all DlsReqTrainings");
        return dlsReqTrainingsRepository.findAll(pageable);
    }


    /**
     * Get one dlsReqTrainings by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<DlsReqTrainings> findOne(Long id) {
        log.debug("Request to get DlsReqTrainings : {}", id);
        return dlsReqTrainingsRepository.findById(id);
    }

    /**
     * Delete the dlsReqTrainings by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete DlsReqTrainings : {}", id);
        dlsReqTrainingsRepository.deleteById(id);
    }
}
