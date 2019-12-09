package com.isoft.dls.sync.web.rest;

import com.isoft.dls.sync.domain.DlsExams;
import com.isoft.dls.sync.service.DlsExamsService;
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
 * REST controller for managing {@link com.isoft.dls.sync.domain.DlsExams}.
 */
@RestController
@RequestMapping("/api")
public class DlsExamsResource {

    private final Logger log = LoggerFactory.getLogger(DlsExamsResource.class);

    private static final String ENTITY_NAME = "dlsExams";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final DlsExamsService dlsExamsService;

    public DlsExamsResource(DlsExamsService dlsExamsService) {
        this.dlsExamsService = dlsExamsService;
    }

    /**
     * {@code POST  /dls-exams} : Create a new dlsExams.
     *
     * @param dlsExams the dlsExams to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new dlsExams, or with status {@code 400 (Bad Request)} if the dlsExams has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/dls-exams")
    public ResponseEntity<DlsExams> createDlsExams(@Valid @RequestBody DlsExams dlsExams) throws URISyntaxException {
        log.debug("REST request to save DlsExams : {}", dlsExams);
        if (dlsExams.getId() != null) {
            throw new BadRequestAlertException("A new dlsExams cannot already have an ID", ENTITY_NAME, "idexists");
        }
        DlsExams result = dlsExamsService.save(dlsExams);
        return ResponseEntity.created(new URI("/api/dls-exams/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /dls-exams} : Updates an existing dlsExams.
     *
     * @param dlsExams the dlsExams to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated dlsExams,
     * or with status {@code 400 (Bad Request)} if the dlsExams is not valid,
     * or with status {@code 500 (Internal Server Error)} if the dlsExams couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/dls-exams")
    public ResponseEntity<DlsExams> updateDlsExams(@Valid @RequestBody DlsExams dlsExams) throws URISyntaxException {
        log.debug("REST request to update DlsExams : {}", dlsExams);
        if (dlsExams.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        DlsExams result = dlsExamsService.save(dlsExams);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, dlsExams.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /dls-exams} : get all the dlsExams.
     *

     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of dlsExams in body.
     */
    @GetMapping("/dls-exams")
    public List<DlsExams> getAllDlsExams() {
        log.debug("REST request to get all DlsExams");
        return dlsExamsService.findAll();
    }

    /**
     * {@code GET  /dls-exams/:id} : get the "id" dlsExams.
     *
     * @param id the id of the dlsExams to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the dlsExams, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/dls-exams/{id}")
    public ResponseEntity<DlsExams> getDlsExams(@PathVariable Long id) {
        log.debug("REST request to get DlsExams : {}", id);
        Optional<DlsExams> dlsExams = dlsExamsService.findOne(id);
        return ResponseUtil.wrapOrNotFound(dlsExams);
    }

    /**
     * {@code DELETE  /dls-exams/:id} : delete the "id" dlsExams.
     *
     * @param id the id of the dlsExams to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/dls-exams/{id}")
    public ResponseEntity<Void> deleteDlsExams(@PathVariable Long id) {
        log.debug("REST request to delete DlsExams : {}", id);
        dlsExamsService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
