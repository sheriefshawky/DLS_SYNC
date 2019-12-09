package com.isoft.dls.sync.service.impl;

import com.isoft.dls.sync.service.DlsVehicleTypesService;
import com.isoft.dls.sync.domain.DlsVehicleTypes;
import com.isoft.dls.sync.repository.DlsVehicleTypesRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Service Implementation for managing {@link DlsVehicleTypes}.
 */
@Service
@Transactional
public class DlsVehicleTypesServiceImpl implements DlsVehicleTypesService {

    private final Logger log = LoggerFactory.getLogger(DlsVehicleTypesServiceImpl.class);

    private final DlsVehicleTypesRepository dlsVehicleTypesRepository;

    public DlsVehicleTypesServiceImpl(DlsVehicleTypesRepository dlsVehicleTypesRepository) {
        this.dlsVehicleTypesRepository = dlsVehicleTypesRepository;
    }

    /**
     * Save a dlsVehicleTypes.
     *
     * @param dlsVehicleTypes the entity to save.
     * @return the persisted entity.
     */
    @Override
    public DlsVehicleTypes save(DlsVehicleTypes dlsVehicleTypes) {
        log.debug("Request to save DlsVehicleTypes : {}", dlsVehicleTypes);
        return dlsVehicleTypesRepository.save(dlsVehicleTypes);
    }

    /**
     * Get all the dlsVehicleTypes.
     *
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public List<DlsVehicleTypes> findAll() {
        log.debug("Request to get all DlsVehicleTypes");
        return dlsVehicleTypesRepository.findAll();
    }


    /**
     * Get one dlsVehicleTypes by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<DlsVehicleTypes> findOne(Long id) {
        log.debug("Request to get DlsVehicleTypes : {}", id);
        return dlsVehicleTypesRepository.findById(id);
    }

    /**
     * Delete the dlsVehicleTypes by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete DlsVehicleTypes : {}", id);
        dlsVehicleTypesRepository.deleteById(id);
    }
}
