package com.isoft.dls.sync.service.impl;

import com.isoft.dls.sync.service.QuestionsService;
import com.isoft.dls.sync.domain.Questions;
import com.isoft.dls.sync.repository.QuestionsRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link Questions}.
 */
@Service
@Transactional
public class QuestionsServiceImpl implements QuestionsService {

    private final Logger log = LoggerFactory.getLogger(QuestionsServiceImpl.class);

    private final QuestionsRepository questionsRepository;

    public QuestionsServiceImpl(QuestionsRepository questionsRepository) {
        this.questionsRepository = questionsRepository;
    }

    /**
     * Save a questions.
     *
     * @param questions the entity to save.
     * @return the persisted entity.
     */
    @Override
    public Questions save(Questions questions) {
        log.debug("Request to save Questions : {}", questions);
        return questionsRepository.save(questions);
    }

    /**
     * Get all the questions.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public Page<Questions> findAll(Pageable pageable) {
        log.debug("Request to get all Questions");
        return questionsRepository.findAll(pageable);
    }


    /**
     * Get one questions by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<Questions> findOne(Long id) {
        log.debug("Request to get Questions : {}", id);
        return questionsRepository.findById(id);
    }

    /**
     * Delete the questions by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Questions : {}", id);
        questionsRepository.deleteById(id);
    }
}
