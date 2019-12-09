package com.isoft.dls.sync.web.rest;

import com.isoft.dls.sync.domain.DlsVehicleTypes;
import com.isoft.dls.sync.service.DlsVehicleTypesService;
import com.isoft.dls.sync.web.rest.errors.BadRequestAlertException;

import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;

import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing {@link com.isoft.dls.sync.domain.DlsVehicleTypes}.
 */
@RestController
@RequestMapping("/api")
public class DlsVehicleTypesResource {

    private final Logger log = LoggerFactory.getLogger(DlsVehicleTypesResource.class);

    private static final String ENTITY_NAME = "dlsVehicleTypes";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final DlsVehicleTypesService dlsVehicleTypesService;

    public DlsVehicleTypesResource(DlsVehicleTypesService dlsVehicleTypesService) {
        this.dlsVehicleTypesService = dlsVehicleTypesService;
    }

    /**
     * {@code POST  /dls-vehicle-types} : Create a new dlsVehicleTypes.
     *
     * @param dlsVehicleTypes the dlsVehicleTypes to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new dlsVehicleTypes, or with status {@code 400 (Bad Request)} if the dlsVehicleTypes has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/dls-vehicle-types")
    public ResponseEntity<DlsVehicleTypes> createDlsVehicleTypes(@Valid @RequestBody DlsVehicleTypes dlsVehicleTypes) throws URISyntaxException {
        log.debug("REST request to save DlsVehicleTypes : {}", dlsVehicleTypes);
        if (dlsVehicleTypes.getId() != null) {
            throw new BadRequestAlertException("A new dlsVehicleTypes cannot already have an ID", ENTITY_NAME, "idexists");
        }
        DlsVehicleTypes result = dlsVehicleTypesService.save(dlsVehicleTypes);
        return ResponseEntity.created(new URI("/api/dls-vehicle-types/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /dls-vehicle-types} : Updates an existing dlsVehicleTypes.
     *
     * @param dlsVehicleTypes the dlsVehicleTypes to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated dlsVehicleTypes,
     * or with status {@code 400 (Bad Request)} if the dlsVehicleTypes is not valid,
     * or with status {@code 500 (Internal Server Error)} if the dlsVehicleTypes couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/dls-vehicle-types")
    public ResponseEntity<DlsVehicleTypes> updateDlsVehicleTypes(@Valid @RequestBody DlsVehicleTypes dlsVehicleTypes) throws URISyntaxException {
        log.debug("REST request to update DlsVehicleTypes : {}", dlsVehicleTypes);
        if (dlsVehicleTypes.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        DlsVehicleTypes result = dlsVehicleTypesService.save(dlsVehicleTypes);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, dlsVehicleTypes.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /dls-vehicle-types} : get all the dlsVehicleTypes.
     *

     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of dlsVehicleTypes in body.
     */
    @GetMapping("/dls-vehicle-types")
    public List<DlsVehicleTypes> getAllDlsVehicleTypes() {
        log.debug("REST request to get all DlsVehicleTypes");
        return dlsVehicleTypesService.findAll();
    }

    /**
     * {@code GET  /dls-vehicle-types/:id} : get the "id" dlsVehicleTypes.
     *
     * @param id the id of the dlsVehicleTypes to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the dlsVehicleTypes, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/dls-vehicle-types/{id}")
    public ResponseEntity<DlsVehicleTypes> getDlsVehicleTypes(@PathVariable Long id) {
        log.debug("REST request to get DlsVehicleTypes : {}", id);
        Optional<DlsVehicleTypes> dlsVehicleTypes = dlsVehicleTypesService.findOne(id);
        return ResponseUtil.wrapOrNotFound(dlsVehicleTypes);
    }

    /**
     * {@code DELETE  /dls-vehicle-types/:id} : delete the "id" dlsVehicleTypes.
     *
     * @param id the id of the dlsVehicleTypes to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/dls-vehicle-types/{id}")
    public ResponseEntity<Void> deleteDlsVehicleTypes(@PathVariable Long id) {
        log.debug("REST request to delete DlsVehicleTypes : {}", id);
        dlsVehicleTypesService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
