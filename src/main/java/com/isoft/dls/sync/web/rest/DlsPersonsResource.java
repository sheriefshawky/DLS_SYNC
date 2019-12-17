package com.isoft.dls.sync.web.rest;

import com.isoft.dls.sync.domain.DlsPersons;
import com.isoft.dls.sync.service.DlsPersonsService;
import com.isoft.dls.sync.web.rest.errors.BadRequestAlertException;

import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.PaginationUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;

import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing {@link com.isoft.dls.sync.domain.DlsPersons}.
 */
@RestController
@RequestMapping("/api")
public class DlsPersonsResource {

    private final Logger log = LoggerFactory.getLogger(DlsPersonsResource.class);

    private static final String ENTITY_NAME = "dlsPersons";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final DlsPersonsService dlsPersonsService;

    public DlsPersonsResource(DlsPersonsService dlsPersonsService) {
        this.dlsPersonsService = dlsPersonsService;
    }

    /**
     * {@code POST  /dls-persons} : Create a new dlsPersons.
     *
     * @param dlsPersons the dlsPersons to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new dlsPersons, or with status {@code 400 (Bad Request)} if the dlsPersons has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/dls-persons")
    public ResponseEntity<DlsPersons> createDlsPersons(@Valid @RequestBody DlsPersons dlsPersons) throws URISyntaxException {
        log.debug("REST request to save DlsPersons : {}", dlsPersons);
        if (dlsPersons.getId() != null) {
            throw new BadRequestAlertException("A new dlsPersons cannot already have an ID", ENTITY_NAME, "idexists");
        }
        DlsPersons result = dlsPersonsService.save(dlsPersons);
        return ResponseEntity.created(new URI("/api/dls-persons/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /dls-persons} : Updates an existing dlsPersons.
     *
     * @param dlsPersons the dlsPersons to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated dlsPersons,
     * or with status {@code 400 (Bad Request)} if the dlsPersons is not valid,
     * or with status {@code 500 (Internal Server Error)} if the dlsPersons couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/dls-persons")
    public ResponseEntity<DlsPersons> updateDlsPersons(@Valid @RequestBody DlsPersons dlsPersons) throws URISyntaxException {
        log.debug("REST request to update DlsPersons : {}", dlsPersons);
        if (dlsPersons.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        DlsPersons result = dlsPersonsService.save(dlsPersons);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, dlsPersons.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /dls-persons} : get all the dlsPersons.
     *

     * @param pageable the pagination information.

     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of dlsPersons in body.
     */
    @GetMapping("/dls-persons")
    public ResponseEntity<List<DlsPersons>> getAllDlsPersons(Pageable pageable) {
        log.debug("REST request to get a page of DlsPersons");
        Page<DlsPersons> page = dlsPersonsService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /dls-persons/:id} : get the "id" dlsPersons.
     *
     * @param id the id of the dlsPersons to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the dlsPersons, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/dls-persons/{id}")
    public ResponseEntity<DlsPersons> getDlsPersons(@PathVariable Long id) {
        log.debug("REST request to get DlsPersons : {}", id);
        Optional<DlsPersons> dlsPersons = dlsPersonsService.findOne(id);
        return ResponseUtil.wrapOrNotFound(dlsPersons);
    }

    /**
     * {@code DELETE  /dls-persons/:id} : delete the "id" dlsPersons.
     *
     * @param id the id of the dlsPersons to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/dls-persons/{id}")
    public ResponseEntity<Void> deleteDlsPersons(@PathVariable Long id) {
        log.debug("REST request to delete DlsPersons : {}", id);
        dlsPersonsService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
