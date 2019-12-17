package com.isoft.dls.sync.web.rest;

import com.isoft.dls.sync.domain.DlsRequests;
import com.isoft.dls.sync.service.DlsRequestsService;
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
 * REST controller for managing {@link com.isoft.dls.sync.domain.DlsRequests}.
 */
@RestController
@RequestMapping("/api")
public class DlsRequestsResource {

    private final Logger log = LoggerFactory.getLogger(DlsRequestsResource.class);

    private static final String ENTITY_NAME = "dlsRequests";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final DlsRequestsService dlsRequestsService;

    public DlsRequestsResource(DlsRequestsService dlsRequestsService) {
        this.dlsRequestsService = dlsRequestsService;
    }

    /**
     * {@code POST  /dls-requests} : Create a new dlsRequests.
     *
     * @param dlsRequests the dlsRequests to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new dlsRequests, or with status {@code 400 (Bad Request)} if the dlsRequests has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/dls-requests")
    public ResponseEntity<DlsRequests> createDlsRequests(@Valid @RequestBody DlsRequests dlsRequests) throws URISyntaxException {
        log.debug("REST request to save DlsRequests : {}", dlsRequests);
        if (dlsRequests.getId() != null) {
            throw new BadRequestAlertException("A new dlsRequests cannot already have an ID", ENTITY_NAME, "idexists");
        }
        DlsRequests result = dlsRequestsService.save(dlsRequests);
        return ResponseEntity.created(new URI("/api/dls-requests/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /dls-requests} : Updates an existing dlsRequests.
     *
     * @param dlsRequests the dlsRequests to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated dlsRequests,
     * or with status {@code 400 (Bad Request)} if the dlsRequests is not valid,
     * or with status {@code 500 (Internal Server Error)} if the dlsRequests couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/dls-requests")
    public ResponseEntity<DlsRequests> updateDlsRequests(@Valid @RequestBody DlsRequests dlsRequests) throws URISyntaxException {
        log.debug("REST request to update DlsRequests : {}", dlsRequests);
        if (dlsRequests.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        DlsRequests result = dlsRequestsService.save(dlsRequests);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, dlsRequests.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /dls-requests} : get all the dlsRequests.
     *

     * @param pageable the pagination information.

     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of dlsRequests in body.
     */
    @GetMapping("/dls-requests")
    public ResponseEntity<List<DlsRequests>> getAllDlsRequests(Pageable pageable) {
        log.debug("REST request to get a page of DlsRequests");
        Page<DlsRequests> page = dlsRequestsService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /dls-requests/:id} : get the "id" dlsRequests.
     *
     * @param id the id of the dlsRequests to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the dlsRequests, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/dls-requests/{id}")
    public ResponseEntity<DlsRequests> getDlsRequests(@PathVariable Long id) {
        log.debug("REST request to get DlsRequests : {}", id);
        Optional<DlsRequests> dlsRequests = dlsRequestsService.findOne(id);
        return ResponseUtil.wrapOrNotFound(dlsRequests);
    }

    /**
     * {@code DELETE  /dls-requests/:id} : delete the "id" dlsRequests.
     *
     * @param id the id of the dlsRequests to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/dls-requests/{id}")
    public ResponseEntity<Void> deleteDlsRequests(@PathVariable Long id) {
        log.debug("REST request to delete DlsRequests : {}", id);
        dlsRequestsService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
