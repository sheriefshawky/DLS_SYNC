package com.isoft.dls.sync.service.impl;

import com.isoft.dls.sync.service.DlsVhlTrsExamsService;
import com.isoft.dls.sync.domain.DlsVhlTrsExams;
import com.isoft.dls.sync.repository.DlsVhlTrsExamsRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Service Implementation for managing {@link DlsVhlTrsExams}.
 */
@Service
@Transactional
public class DlsVhlTrsExamsServiceImpl implements DlsVhlTrsExamsService {

    private final Logger log = LoggerFactory.getLogger(DlsVhlTrsExamsServiceImpl.class);

    private final DlsVhlTrsExamsRepository dlsVhlTrsExamsRepository;

    public DlsVhlTrsExamsServiceImpl(DlsVhlTrsExamsRepository dlsVhlTrsExamsRepository) {
        this.dlsVhlTrsExamsRepository = dlsVhlTrsExamsRepository;
    }

    /**
     * Save a dlsVhlTrsExams.
     *
     * @param dlsVhlTrsExams the entity to save.
     * @return the persisted entity.
     */
    @Override
    public DlsVhlTrsExams save(DlsVhlTrsExams dlsVhlTrsExams) {
        log.debug("Request to save DlsVhlTrsExams : {}", dlsVhlTrsExams);
        return dlsVhlTrsExamsRepository.save(dlsVhlTrsExams);
    }

    /**
     * Get all the dlsVhlTrsExams.
     *
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public List<DlsVhlTrsExams> findAll() {
        log.debug("Request to get all DlsVhlTrsExams");
        return dlsVhlTrsExamsRepository.findAll();
    }


    /**
     * Get one dlsVhlTrsExams by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<DlsVhlTrsExams> findOne(Long id) {
        log.debug("Request to get DlsVhlTrsExams : {}", id);
        return dlsVhlTrsExamsRepository.findById(id);
    }

    /**
     * Delete the dlsVhlTrsExams by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete DlsVhlTrsExams : {}", id);
        dlsVhlTrsExamsRepository.deleteById(id);
    }
}
