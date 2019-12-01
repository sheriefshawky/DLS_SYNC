package com.isoft.dls.sync.service.impl;

import com.isoft.dls.sync.service.DlsReqExamsService;
import com.isoft.dls.sync.domain.DlsReqExams;
import com.isoft.dls.sync.repository.DlsReqExamsRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link DlsReqExams}.
 */
@Service
@Transactional
public class DlsReqExamsServiceImpl implements DlsReqExamsService {

    private final Logger log = LoggerFactory.getLogger(DlsReqExamsServiceImpl.class);

    private final DlsReqExamsRepository dlsReqExamsRepository;

    public DlsReqExamsServiceImpl(DlsReqExamsRepository dlsReqExamsRepository) {
        this.dlsReqExamsRepository = dlsReqExamsRepository;
    }

    /**
     * Save a dlsReqExams.
     *
     * @param dlsReqExams the entity to save.
     * @return the persisted entity.
     */
    @Override
    public DlsReqExams save(DlsReqExams dlsReqExams) {
        log.debug("Request to save DlsReqExams : {}", dlsReqExams);
        return dlsReqExamsRepository.save(dlsReqExams);
    }

    /**
     * Get all the dlsReqExams.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public Page<DlsReqExams> findAll(Pageable pageable) {
        log.debug("Request to get all DlsReqExams");
        return dlsReqExamsRepository.findAll(pageable);
    }


    /**
     * Get one dlsReqExams by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<DlsReqExams> findOne(Long id) {
        log.debug("Request to get DlsReqExams : {}", id);
        return dlsReqExamsRepository.findById(id);
    }

    /**
     * Delete the dlsReqExams by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete DlsReqExams : {}", id);
        dlsReqExamsRepository.deleteById(id);
    }
}
