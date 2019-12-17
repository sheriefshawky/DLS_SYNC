package com.isoft.dls.sync.web.rest;

import com.isoft.dls.sync.domain.TemplateCategories;
import com.isoft.dls.sync.service.TemplateCategoriesService;
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
 * REST controller for managing {@link com.isoft.dls.sync.domain.TemplateCategories}.
 */
@RestController
@RequestMapping("/api")
public class TemplateCategoriesResource {

    private final Logger log = LoggerFactory.getLogger(TemplateCategoriesResource.class);

    private static final String ENTITY_NAME = "templateCategories";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final TemplateCategoriesService templateCategoriesService;

    public TemplateCategoriesResource(TemplateCategoriesService templateCategoriesService) {
        this.templateCategoriesService = templateCategoriesService;
    }

    /**
     * {@code POST  /template-categories} : Create a new templateCategories.
     *
     * @param templateCategories the templateCategories to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new templateCategories, or with status {@code 400 (Bad Request)} if the templateCategories has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/template-categories")
    public ResponseEntity<TemplateCategories> createTemplateCategories(@Valid @RequestBody TemplateCategories templateCategories) throws URISyntaxException {
        log.debug("REST request to save TemplateCategories : {}", templateCategories);
        if (templateCategories.getId() != null) {
            throw new BadRequestAlertException("A new templateCategories cannot already have an ID", ENTITY_NAME, "idexists");
        }
        TemplateCategories result = templateCategoriesService.save(templateCategories);
        return ResponseEntity.created(new URI("/api/template-categories/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /template-categories} : Updates an existing templateCategories.
     *
     * @param templateCategories the templateCategories to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated templateCategories,
     * or with status {@code 400 (Bad Request)} if the templateCategories is not valid,
     * or with status {@code 500 (Internal Server Error)} if the templateCategories couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/template-categories")
    public ResponseEntity<TemplateCategories> updateTemplateCategories(@Valid @RequestBody TemplateCategories templateCategories) throws URISyntaxException {
        log.debug("REST request to update TemplateCategories : {}", templateCategories);
        if (templateCategories.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        TemplateCategories result = templateCategoriesService.save(templateCategories);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, templateCategories.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /template-categories} : get all the templateCategories.
     *

     * @param pageable the pagination information.

     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of templateCategories in body.
     */
    @GetMapping("/template-categories")
    public ResponseEntity<List<TemplateCategories>> getAllTemplateCategories(Pageable pageable) {
        log.debug("REST request to get a page of TemplateCategories");
        Page<TemplateCategories> page = templateCategoriesService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /template-categories/:id} : get the "id" templateCategories.
     *
     * @param id the id of the templateCategories to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the templateCategories, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/template-categories/{id}")
    public ResponseEntity<TemplateCategories> getTemplateCategories(@PathVariable Long id) {
        log.debug("REST request to get TemplateCategories : {}", id);
        Optional<TemplateCategories> templateCategories = templateCategoriesService.findOne(id);
        return ResponseUtil.wrapOrNotFound(templateCategories);
    }

    /**
     * {@code DELETE  /template-categories/:id} : delete the "id" templateCategories.
     *
     * @param id the id of the templateCategories to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/template-categories/{id}")
    public ResponseEntity<Void> deleteTemplateCategories(@PathVariable Long id) {
        log.debug("REST request to delete TemplateCategories : {}", id);
        templateCategoriesService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
