package com.isoft.dls.sync.web.rest;

import com.isoft.dls.sync.domain.DlsTrsTypes;
import com.isoft.dls.sync.service.DlsTrsTypesService;
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
 * REST controller for managing {@link com.isoft.dls.sync.domain.DlsTrsTypes}.
 */
@RestController
@RequestMapping("/api")
public class DlsTrsTypesResource {

    private final Logger log = LoggerFactory.getLogger(DlsTrsTypesResource.class);

    private static final String ENTITY_NAME = "dlsTrsTypes";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final DlsTrsTypesService dlsTrsTypesService;

    public DlsTrsTypesResource(DlsTrsTypesService dlsTrsTypesService) {
        this.dlsTrsTypesService = dlsTrsTypesService;
    }

    /**
     * {@code POST  /dls-trs-types} : Create a new dlsTrsTypes.
     *
     * @param dlsTrsTypes the dlsTrsTypes to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new dlsTrsTypes, or with status {@code 400 (Bad Request)} if the dlsTrsTypes has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/dls-trs-types")
    public ResponseEntity<DlsTrsTypes> createDlsTrsTypes(@Valid @RequestBody DlsTrsTypes dlsTrsTypes) throws URISyntaxException {
        log.debug("REST request to save DlsTrsTypes : {}", dlsTrsTypes);
        if (dlsTrsTypes.getId() != null) {
            throw new BadRequestAlertException("A new dlsTrsTypes cannot already have an ID", ENTITY_NAME, "idexists");
        }
        DlsTrsTypes result = dlsTrsTypesService.save(dlsTrsTypes);
        return ResponseEntity.created(new URI("/api/dls-trs-types/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /dls-trs-types} : Updates an existing dlsTrsTypes.
     *
     * @param dlsTrsTypes the dlsTrsTypes to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated dlsTrsTypes,
     * or with status {@code 400 (Bad Request)} if the dlsTrsTypes is not valid,
     * or with status {@code 500 (Internal Server Error)} if the dlsTrsTypes couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/dls-trs-types")
    public ResponseEntity<DlsTrsTypes> updateDlsTrsTypes(@Valid @RequestBody DlsTrsTypes dlsTrsTypes) throws URISyntaxException {
        log.debug("REST request to update DlsTrsTypes : {}", dlsTrsTypes);
        if (dlsTrsTypes.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        DlsTrsTypes result = dlsTrsTypesService.save(dlsTrsTypes);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, dlsTrsTypes.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /dls-trs-types} : get all the dlsTrsTypes.
     *

     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of dlsTrsTypes in body.
     */
    @GetMapping("/dls-trs-types")
    public List<DlsTrsTypes> getAllDlsTrsTypes() {
        log.debug("REST request to get all DlsTrsTypes");
        return dlsTrsTypesService.findAll();
    }

    /**
     * {@code GET  /dls-trs-types/:id} : get the "id" dlsTrsTypes.
     *
     * @param id the id of the dlsTrsTypes to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the dlsTrsTypes, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/dls-trs-types/{id}")
    public ResponseEntity<DlsTrsTypes> getDlsTrsTypes(@PathVariable Long id) {
        log.debug("REST request to get DlsTrsTypes : {}", id);
        Optional<DlsTrsTypes> dlsTrsTypes = dlsTrsTypesService.findOne(id);
        return ResponseUtil.wrapOrNotFound(dlsTrsTypes);
    }

    /**
     * {@code DELETE  /dls-trs-types/:id} : delete the "id" dlsTrsTypes.
     *
     * @param id the id of the dlsTrsTypes to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/dls-trs-types/{id}")
    public ResponseEntity<Void> deleteDlsTrsTypes(@PathVariable Long id) {
        log.debug("REST request to delete DlsTrsTypes : {}", id);
        dlsTrsTypesService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
