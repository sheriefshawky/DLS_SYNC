package com.isoft.dls.sync.service.impl;

import com.isoft.dls.sync.service.DlsExamsService;
import com.isoft.dls.sync.domain.DlsExams;
import com.isoft.dls.sync.repository.DlsExamsRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Service Implementation for managing {@link DlsExams}.
 */
@Service
@Transactional
public class DlsExamsServiceImpl implements DlsExamsService {

    private final Logger log = LoggerFactory.getLogger(DlsExamsServiceImpl.class);

    private final DlsExamsRepository dlsExamsRepository;

    public DlsExamsServiceImpl(DlsExamsRepository dlsExamsRepository) {
        this.dlsExamsRepository = dlsExamsRepository;
    }

    /**
     * Save a dlsExams.
     *
     * @param dlsExams the entity to save.
     * @return the persisted entity.
     */
    @Override
    public DlsExams save(DlsExams dlsExams) {
        log.debug("Request to save DlsExams : {}", dlsExams);
        return dlsExamsRepository.save(dlsExams);
    }

    /**
     * Get all the dlsExams.
     *
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public List<DlsExams> findAll() {
        log.debug("Request to get all DlsExams");
        return dlsExamsRepository.findAll();
    }


    /**
     * Get one dlsExams by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<DlsExams> findOne(Long id) {
        log.debug("Request to get DlsExams : {}", id);
        return dlsExamsRepository.findById(id);
    }

    /**
     * Delete the dlsExams by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete DlsExams : {}", id);
        dlsExamsRepository.deleteById(id);
    }
}
