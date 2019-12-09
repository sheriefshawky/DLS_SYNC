package com.isoft.dls.sync.service;

import com.isoft.dls.sync.domain.DlsVehicleTypes;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link DlsVehicleTypes}.
 */
public interface DlsVehicleTypesService {

    /**
     * Save a dlsVehicleTypes.
     *
     * @param dlsVehicleTypes the entity to save.
     * @return the persisted entity.
     */
    DlsVehicleTypes save(DlsVehicleTypes dlsVehicleTypes);

    /**
     * Get all the dlsVehicleTypes.
     *
     * @return the list of entities.
     */
    List<DlsVehicleTypes> findAll();


    /**
     * Get the "id" dlsVehicleTypes.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<DlsVehicleTypes> findOne(Long id);

    /**
     * Delete the "id" dlsVehicleTypes.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
