package com.isoft.dls.sync.web.rest;

import com.isoft.dls.sync.domain.DlsReqTrainings;
import com.isoft.dls.sync.service.DlsReqTrainingsService;
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
 * REST controller for managing {@link com.isoft.dls.sync.domain.DlsReqTrainings}.
 */
@RestController
@RequestMapping("/api")
public class DlsReqTrainingsResource {

    private final Logger log = LoggerFactory.getLogger(DlsReqTrainingsResource.class);

    private static final String ENTITY_NAME = "dlsReqTrainings";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final DlsReqTrainingsService dlsReqTrainingsService;

    public DlsReqTrainingsResource(DlsReqTrainingsService dlsReqTrainingsService) {
        this.dlsReqTrainingsService = dlsReqTrainingsService;
    }

    /**
     * {@code POST  /dls-req-trainings} : Create a new dlsReqTrainings.
     *
     * @param dlsReqTrainings the dlsReqTrainings to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new dlsReqTrainings, or with status {@code 400 (Bad Request)} if the dlsReqTrainings has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/dls-req-trainings")
    public ResponseEntity<DlsReqTrainings> createDlsReqTrainings(@Valid @RequestBody DlsReqTrainings dlsReqTrainings) throws URISyntaxException {
        log.debug("REST request to save DlsReqTrainings : {}", dlsReqTrainings);
        if (dlsReqTrainings.getId() != null) {
            throw new BadRequestAlertException("A new dlsReqTrainings cannot already have an ID", ENTITY_NAME, "idexists");
        }
        DlsReqTrainings result = dlsReqTrainingsService.save(dlsReqTrainings);
        return ResponseEntity.created(new URI("/api/dls-req-trainings/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /dls-req-trainings} : Updates an existing dlsReqTrainings.
     *
     * @param dlsReqTrainings the dlsReqTrainings to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated dlsReqTrainings,
     * or with status {@code 400 (Bad Request)} if the dlsReqTrainings is not valid,
     * or with status {@code 500 (Internal Server Error)} if the dlsReqTrainings couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/dls-req-trainings")
    public ResponseEntity<DlsReqTrainings> updateDlsReqTrainings(@Valid @RequestBody DlsReqTrainings dlsReqTrainings) throws URISyntaxException {
        log.debug("REST request to update DlsReqTrainings : {}", dlsReqTrainings);
        if (dlsReqTrainings.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        DlsReqTrainings result = dlsReqTrainingsService.save(dlsReqTrainings);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, dlsReqTrainings.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /dls-req-trainings} : get all the dlsReqTrainings.
     *

     * @param pageable the pagination information.

     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of dlsReqTrainings in body.
     */
    @GetMapping("/dls-req-trainings")
    public ResponseEntity<List<DlsReqTrainings>> getAllDlsReqTrainings(Pageable pageable) {
        log.debug("REST request to get a page of DlsReqTrainings");
        Page<DlsReqTrainings> page = dlsReqTrainingsService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /dls-req-trainings/:id} : get the "id" dlsReqTrainings.
     *
     * @param id the id of the dlsReqTrainings to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the dlsReqTrainings, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/dls-req-trainings/{id}")
    public ResponseEntity<DlsReqTrainings> getDlsReqTrainings(@PathVariable Long id) {
        log.debug("REST request to get DlsReqTrainings : {}", id);
        Optional<DlsReqTrainings> dlsReqTrainings = dlsReqTrainingsService.findOne(id);
        return ResponseUtil.wrapOrNotFound(dlsReqTrainings);
    }

    /**
     * {@code DELETE  /dls-req-trainings/:id} : delete the "id" dlsReqTrainings.
     *
     * @param id the id of the dlsReqTrainings to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/dls-req-trainings/{id}")
    public ResponseEntity<Void> deleteDlsReqTrainings(@PathVariable Long id) {
        log.debug("REST request to delete DlsReqTrainings : {}", id);
        dlsReqTrainingsService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
