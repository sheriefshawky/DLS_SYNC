package com.isoft.dls.sync.service.impl;

import com.isoft.dls.sync.service.TemplateCategoriesService;
import com.isoft.dls.sync.domain.TemplateCategories;
import com.isoft.dls.sync.repository.TemplateCategoriesRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link TemplateCategories}.
 */
@Service
@Transactional
public class TemplateCategoriesServiceImpl implements TemplateCategoriesService {

    private final Logger log = LoggerFactory.getLogger(TemplateCategoriesServiceImpl.class);

    private final TemplateCategoriesRepository templateCategoriesRepository;

    public TemplateCategoriesServiceImpl(TemplateCategoriesRepository templateCategoriesRepository) {
        this.templateCategoriesRepository = templateCategoriesRepository;
    }

    /**
     * Save a templateCategories.
     *
     * @param templateCategories the entity to save.
     * @return the persisted entity.
     */
    @Override
    public TemplateCategories save(TemplateCategories templateCategories) {
        log.debug("Request to save TemplateCategories : {}", templateCategories);
        return templateCategoriesRepository.save(templateCategories);
    }

    /**
     * Get all the templateCategories.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public Page<TemplateCategories> findAll(Pageable pageable) {
        log.debug("Request to get all TemplateCategories");
        return templateCategoriesRepository.findAll(pageable);
    }


    /**
     * Get one templateCategories by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<TemplateCategories> findOne(Long id) {
        log.debug("Request to get TemplateCategories : {}", id);
        return templateCategoriesRepository.findById(id);
    }

    /**
     * Delete the templateCategories by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete TemplateCategories : {}", id);
        templateCategoriesRepository.deleteById(id);
    }
}
