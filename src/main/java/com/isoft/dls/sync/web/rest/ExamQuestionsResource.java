package com.isoft.dls.sync.web.rest;

import com.isoft.dls.sync.domain.ExamQuestions;
import com.isoft.dls.sync.service.ExamQuestionsService;
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
 * REST controller for managing {@link com.isoft.dls.sync.domain.ExamQuestions}.
 */
@RestController
@RequestMapping("/api")
public class ExamQuestionsResource {

    private final Logger log = LoggerFactory.getLogger(ExamQuestionsResource.class);

    private static final String ENTITY_NAME = "examQuestions";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final ExamQuestionsService examQuestionsService;

    public ExamQuestionsResource(ExamQuestionsService examQuestionsService) {
        this.examQuestionsService = examQuestionsService;
    }

    /**
     * {@code POST  /exam-questions} : Create a new examQuestions.
     *
     * @param examQuestions the examQuestions to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new examQuestions, or with status {@code 400 (Bad Request)} if the examQuestions has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/exam-questions")
    public ResponseEntity<ExamQuestions> createExamQuestions(@Valid @RequestBody ExamQuestions examQuestions) throws URISyntaxException {
        log.debug("REST request to save ExamQuestions : {}", examQuestions);
        if (examQuestions.getId() != null) {
            throw new BadRequestAlertException("A new examQuestions cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ExamQuestions result = examQuestionsService.save(examQuestions);
        return ResponseEntity.created(new URI("/api/exam-questions/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /exam-questions} : Updates an existing examQuestions.
     *
     * @param examQuestions the examQuestions to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated examQuestions,
     * or with status {@code 400 (Bad Request)} if the examQuestions is not valid,
     * or with status {@code 500 (Internal Server Error)} if the examQuestions couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/exam-questions")
    public ResponseEntity<ExamQuestions> updateExamQuestions(@Valid @RequestBody ExamQuestions examQuestions) throws URISyntaxException {
        log.debug("REST request to update ExamQuestions : {}", examQuestions);
        if (examQuestions.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        ExamQuestions result = examQuestionsService.save(examQuestions);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, examQuestions.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /exam-questions} : get all the examQuestions.
     *

     * @param pageable the pagination information.

     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of examQuestions in body.
     */
    @GetMapping("/exam-questions")
    public ResponseEntity<List<ExamQuestions>> getAllExamQuestions(Pageable pageable) {
        log.debug("REST request to get a page of ExamQuestions");
        Page<ExamQuestions> page = examQuestionsService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /exam-questions/:id} : get the "id" examQuestions.
     *
     * @param id the id of the examQuestions to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the examQuestions, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/exam-questions/{id}")
    public ResponseEntity<ExamQuestions> getExamQuestions(@PathVariable Long id) {
        log.debug("REST request to get ExamQuestions : {}", id);
        Optional<ExamQuestions> examQuestions = examQuestionsService.findOne(id);
        return ResponseUtil.wrapOrNotFound(examQuestions);
    }

    /**
     * {@code DELETE  /exam-questions/:id} : delete the "id" examQuestions.
     *
     * @param id the id of the examQuestions to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/exam-questions/{id}")
    public ResponseEntity<Void> deleteExamQuestions(@PathVariable Long id) {
        log.debug("REST request to delete ExamQuestions : {}", id);
        examQuestionsService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
