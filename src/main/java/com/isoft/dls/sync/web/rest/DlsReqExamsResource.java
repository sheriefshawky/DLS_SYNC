package com.isoft.dls.sync.web.rest;

import com.isoft.dls.sync.domain.DlsReqExams;
import com.isoft.dls.sync.service.DlsReqExamsService;
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
 * REST controller for managing {@link com.isoft.dls.sync.domain.DlsReqExams}.
 */
@RestController
@RequestMapping("/api")
public class DlsReqExamsResource {

    private final Logger log = LoggerFactory.getLogger(DlsReqExamsResource.class);

    private static final String ENTITY_NAME = "dlsReqExams";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final DlsReqExamsService dlsReqExamsService;

    public DlsReqExamsResource(DlsReqExamsService dlsReqExamsService) {
        this.dlsReqExamsService = dlsReqExamsService;
    }

    /**
     * {@code POST  /dls-req-exams} : Create a new dlsReqExams.
     *
     * @param dlsReqExams the dlsReqExams to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new dlsReqExams, or with status {@code 400 (Bad Request)} if the dlsReqExams has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/dls-req-exams")
    public ResponseEntity<DlsReqExams> createDlsReqExams(@Valid @RequestBody DlsReqExams dlsReqExams) throws URISyntaxException {
        log.debug("REST request to save DlsReqExams : {}", dlsReqExams);
        if (dlsReqExams.getId() != null) {
            throw new BadRequestAlertException("A new dlsReqExams cannot already have an ID", ENTITY_NAME, "idexists");
        }
        DlsReqExams result = dlsReqExamsService.save(dlsReqExams);
        return ResponseEntity.created(new URI("/api/dls-req-exams/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /dls-req-exams} : Updates an existing dlsReqExams.
     *
     * @param dlsReqExams the dlsReqExams to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated dlsReqExams,
     * or with status {@code 400 (Bad Request)} if the dlsReqExams is not valid,
     * or with status {@code 500 (Internal Server Error)} if the dlsReqExams couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/dls-req-exams")
    public ResponseEntity<DlsReqExams> updateDlsReqExams(@Valid @RequestBody DlsReqExams dlsReqExams) throws URISyntaxException {
        log.debug("REST request to update DlsReqExams : {}", dlsReqExams);
        if (dlsReqExams.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        DlsReqExams result = dlsReqExamsService.save(dlsReqExams);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, dlsReqExams.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /dls-req-exams} : get all the dlsReqExams.
     *

     * @param pageable the pagination information.

     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of dlsReqExams in body.
     */
    @GetMapping("/dls-req-exams")
    public ResponseEntity<List<DlsReqExams>> getAllDlsReqExams(Pageable pageable) {
        log.debug("REST request to get a page of DlsReqExams");
        Page<DlsReqExams> page = dlsReqExamsService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /dls-req-exams/:id} : get the "id" dlsReqExams.
     *
     * @param id the id of the dlsReqExams to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the dlsReqExams, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/dls-req-exams/{id}")
    public ResponseEntity<DlsReqExams> getDlsReqExams(@PathVariable Long id) {
        log.debug("REST request to get DlsReqExams : {}", id);
        Optional<DlsReqExams> dlsReqExams = dlsReqExamsService.findOne(id);
        return ResponseUtil.wrapOrNotFound(dlsReqExams);
    }

    /**
     * {@code DELETE  /dls-req-exams/:id} : delete the "id" dlsReqExams.
     *
     * @param id the id of the dlsReqExams to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/dls-req-exams/{id}")
    public ResponseEntity<Void> deleteDlsReqExams(@PathVariable Long id) {
        log.debug("REST request to delete DlsReqExams : {}", id);
        dlsReqExamsService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
