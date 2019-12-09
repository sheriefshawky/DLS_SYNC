package com.isoft.dls.sync.web.rest;

import com.isoft.dls.sync.domain.DlsVhlTrsExams;
import com.isoft.dls.sync.service.DlsVhlTrsExamsService;
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
 * REST controller for managing {@link com.isoft.dls.sync.domain.DlsVhlTrsExams}.
 */
@RestController
@RequestMapping("/api")
public class DlsVhlTrsExamsResource {

    private final Logger log = LoggerFactory.getLogger(DlsVhlTrsExamsResource.class);

    private static final String ENTITY_NAME = "dlsVhlTrsExams";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final DlsVhlTrsExamsService dlsVhlTrsExamsService;

    public DlsVhlTrsExamsResource(DlsVhlTrsExamsService dlsVhlTrsExamsService) {
        this.dlsVhlTrsExamsService = dlsVhlTrsExamsService;
    }

    /**
     * {@code POST  /dls-vhl-trs-exams} : Create a new dlsVhlTrsExams.
     *
     * @param dlsVhlTrsExams the dlsVhlTrsExams to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new dlsVhlTrsExams, or with status {@code 400 (Bad Request)} if the dlsVhlTrsExams has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/dls-vhl-trs-exams")
    public ResponseEntity<DlsVhlTrsExams> createDlsVhlTrsExams(@Valid @RequestBody DlsVhlTrsExams dlsVhlTrsExams) throws URISyntaxException {
        log.debug("REST request to save DlsVhlTrsExams : {}", dlsVhlTrsExams);
        if (dlsVhlTrsExams.getId() != null) {
            throw new BadRequestAlertException("A new dlsVhlTrsExams cannot already have an ID", ENTITY_NAME, "idexists");
        }
        DlsVhlTrsExams result = dlsVhlTrsExamsService.save(dlsVhlTrsExams);
        return ResponseEntity.created(new URI("/api/dls-vhl-trs-exams/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /dls-vhl-trs-exams} : Updates an existing dlsVhlTrsExams.
     *
     * @param dlsVhlTrsExams the dlsVhlTrsExams to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated dlsVhlTrsExams,
     * or with status {@code 400 (Bad Request)} if the dlsVhlTrsExams is not valid,
     * or with status {@code 500 (Internal Server Error)} if the dlsVhlTrsExams couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/dls-vhl-trs-exams")
    public ResponseEntity<DlsVhlTrsExams> updateDlsVhlTrsExams(@Valid @RequestBody DlsVhlTrsExams dlsVhlTrsExams) throws URISyntaxException {
        log.debug("REST request to update DlsVhlTrsExams : {}", dlsVhlTrsExams);
        if (dlsVhlTrsExams.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        DlsVhlTrsExams result = dlsVhlTrsExamsService.save(dlsVhlTrsExams);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, dlsVhlTrsExams.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /dls-vhl-trs-exams} : get all the dlsVhlTrsExams.
     *

     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of dlsVhlTrsExams in body.
     */
    @GetMapping("/dls-vhl-trs-exams")
    public List<DlsVhlTrsExams> getAllDlsVhlTrsExams() {
        log.debug("REST request to get all DlsVhlTrsExams");
        return dlsVhlTrsExamsService.findAll();
    }

    /**
     * {@code GET  /dls-vhl-trs-exams/:id} : get the "id" dlsVhlTrsExams.
     *
     * @param id the id of the dlsVhlTrsExams to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the dlsVhlTrsExams, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/dls-vhl-trs-exams/{id}")
    public ResponseEntity<DlsVhlTrsExams> getDlsVhlTrsExams(@PathVariable Long id) {
        log.debug("REST request to get DlsVhlTrsExams : {}", id);
        Optional<DlsVhlTrsExams> dlsVhlTrsExams = dlsVhlTrsExamsService.findOne(id);
        return ResponseUtil.wrapOrNotFound(dlsVhlTrsExams);
    }

    /**
     * {@code DELETE  /dls-vhl-trs-exams/:id} : delete the "id" dlsVhlTrsExams.
     *
     * @param id the id of the dlsVhlTrsExams to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/dls-vhl-trs-exams/{id}")
    public ResponseEntity<Void> deleteDlsVhlTrsExams(@PathVariable Long id) {
        log.debug("REST request to delete DlsVhlTrsExams : {}", id);
        dlsVhlTrsExamsService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
