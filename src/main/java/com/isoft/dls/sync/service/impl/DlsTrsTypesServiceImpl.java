package com.isoft.dls.sync.service.impl;

import com.isoft.dls.sync.service.DlsTrsTypesService;
import com.isoft.dls.sync.domain.DlsTrsTypes;
import com.isoft.dls.sync.repository.DlsTrsTypesRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Service Implementation for managing {@link DlsTrsTypes}.
 */
@Service
@Transactional
public class DlsTrsTypesServiceImpl implements DlsTrsTypesService {

    private final Logger log = LoggerFactory.getLogger(DlsTrsTypesServiceImpl.class);

    private final DlsTrsTypesRepository dlsTrsTypesRepository;

    public DlsTrsTypesServiceImpl(DlsTrsTypesRepository dlsTrsTypesRepository) {
        this.dlsTrsTypesRepository = dlsTrsTypesRepository;
    }

    /**
     * Save a dlsTrsTypes.
     *
     * @param dlsTrsTypes the entity to save.
     * @return the persisted entity.
     */
    @Override
    public DlsTrsTypes save(DlsTrsTypes dlsTrsTypes) {
        log.debug("Request to save DlsTrsTypes : {}", dlsTrsTypes);
        return dlsTrsTypesRepository.save(dlsTrsTypes);
    }

    /**
     * Get all the dlsTrsTypes.
     *
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public List<DlsTrsTypes> findAll() {
        log.debug("Request to get all DlsTrsTypes");
        return dlsTrsTypesRepository.findAll();
    }


    /**
     * Get one dlsTrsTypes by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<DlsTrsTypes> findOne(Long id) {
        log.debug("Request to get DlsTrsTypes : {}", id);
        return dlsTrsTypesRepository.findById(id);
    }

    /**
     * Delete the dlsTrsTypes by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete DlsTrsTypes : {}", id);
        dlsTrsTypesRepository.deleteById(id);
    }
}
