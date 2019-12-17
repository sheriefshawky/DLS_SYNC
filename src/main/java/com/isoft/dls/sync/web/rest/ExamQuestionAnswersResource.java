package com.isoft.dls.sync.web.rest;

import com.isoft.dls.sync.domain.ExamQuestionAnswers;
import com.isoft.dls.sync.service.ExamQuestionAnswersService;
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
 * REST controller for managing {@link com.isoft.dls.sync.domain.ExamQuestionAnswers}.
 */
@RestController
@RequestMapping("/api")
public class ExamQuestionAnswersResource {

    private final Logger log = LoggerFactory.getLogger(ExamQuestionAnswersResource.class);

    private static final String ENTITY_NAME = "examQuestionAnswers";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final ExamQuestionAnswersService examQuestionAnswersService;

    public ExamQuestionAnswersResource(ExamQuestionAnswersService examQuestionAnswersService) {
        this.examQuestionAnswersService = examQuestionAnswersService;
    }

    /**
     * {@code POST  /exam-question-answers} : Create a new examQuestionAnswers.
     *
     * @param examQuestionAnswers the examQuestionAnswers to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new examQuestionAnswers, or with status {@code 400 (Bad Request)} if the examQuestionAnswers has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/exam-question-answers")
    public ResponseEntity<ExamQuestionAnswers> createExamQuestionAnswers(@Valid @RequestBody ExamQuestionAnswers examQuestionAnswers) throws URISyntaxException {
        log.debug("REST request to save ExamQuestionAnswers : {}", examQuestionAnswers);
        if (examQuestionAnswers.getId() != null) {
            throw new BadRequestAlertException("A new examQuestionAnswers cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ExamQuestionAnswers result = examQuestionAnswersService.save(examQuestionAnswers);
        return ResponseEntity.created(new URI("/api/exam-question-answers/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /exam-question-answers} : Updates an existing examQuestionAnswers.
     *
     * @param examQuestionAnswers the examQuestionAnswers to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated examQuestionAnswers,
     * or with status {@code 400 (Bad Request)} if the examQuestionAnswers is not valid,
     * or with status {@code 500 (Internal Server Error)} if the examQuestionAnswers couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/exam-question-answers")
    public ResponseEntity<ExamQuestionAnswers> updateExamQuestionAnswers(@Valid @RequestBody ExamQuestionAnswers examQuestionAnswers) throws URISyntaxException {
        log.debug("REST request to update ExamQuestionAnswers : {}", examQuestionAnswers);
        if (examQuestionAnswers.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        ExamQuestionAnswers result = examQuestionAnswersService.save(examQuestionAnswers);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, examQuestionAnswers.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /exam-question-answers} : get all the examQuestionAnswers.
     *

     * @param pageable the pagination information.

     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of examQuestionAnswers in body.
     */
    @GetMapping("/exam-question-answers")
    public ResponseEntity<List<ExamQuestionAnswers>> getAllExamQuestionAnswers(Pageable pageable) {
        log.debug("REST request to get a page of ExamQuestionAnswers");
        Page<ExamQuestionAnswers> page = examQuestionAnswersService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /exam-question-answers/:id} : get the "id" examQuestionAnswers.
     *
     * @param id the id of the examQuestionAnswers to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the examQuestionAnswers, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/exam-question-answers/{id}")
    public ResponseEntity<ExamQuestionAnswers> getExamQuestionAnswers(@PathVariable Long id) {
        log.debug("REST request to get ExamQuestionAnswers : {}", id);
        Optional<ExamQuestionAnswers> examQuestionAnswers = examQuestionAnswersService.findOne(id);
        return ResponseUtil.wrapOrNotFound(examQuestionAnswers);
    }

    /**
     * {@code DELETE  /exam-question-answers/:id} : delete the "id" examQuestionAnswers.
     *
     * @param id the id of the examQuestionAnswers to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/exam-question-answers/{id}")
    public ResponseEntity<Void> deleteExamQuestionAnswers(@PathVariable Long id) {
        log.debug("REST request to delete ExamQuestionAnswers : {}", id);
        examQuestionAnswersService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
