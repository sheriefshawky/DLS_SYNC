package com.isoft.dls.sync.service.impl;

import com.isoft.dls.sync.service.AnswersService;
import com.isoft.dls.sync.domain.Answers;
import com.isoft.dls.sync.repository.AnswersRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link Answers}.
 */
@Service
@Transactional
public class AnswersServiceImpl implements AnswersService {

    private final Logger log = LoggerFactory.getLogger(AnswersServiceImpl.class);

    private final AnswersRepository answersRepository;

    public AnswersServiceImpl(AnswersRepository answersRepository) {
        this.answersRepository = answersRepository;
    }

    /**
     * Save a answers.
     *
     * @param answers the entity to save.
     * @return the persisted entity.
     */
    @Override
    public Answers save(Answers answers) {
        log.debug("Request to save Answers : {}", answers);
        return answersRepository.save(answers);
    }

    /**
     * Get all the answers.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public Page<Answers> findAll(Pageable pageable) {
        log.debug("Request to get all Answers");
        return answersRepository.findAll(pageable);
    }


    /**
     * Get one answers by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<Answers> findOne(Long id) {
        log.debug("Request to get Answers : {}", id);
        return answersRepository.findById(id);
    }

    /**
     * Delete the answers by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Answers : {}", id);
        answersRepository.deleteById(id);
    }
}
