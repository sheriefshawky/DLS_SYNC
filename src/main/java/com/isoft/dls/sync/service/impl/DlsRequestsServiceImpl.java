package com.isoft.dls.sync.service.impl;

import com.isoft.dls.sync.service.DlsRequestsService;
import com.isoft.dls.sync.domain.DlsRequests;
import com.isoft.dls.sync.repository.DlsRequestsRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link DlsRequests}.
 */
@Service
@Transactional
public class DlsRequestsServiceImpl implements DlsRequestsService {

    private final Logger log = LoggerFactory.getLogger(DlsRequestsServiceImpl.class);

    private final DlsRequestsRepository dlsRequestsRepository;

    public DlsRequestsServiceImpl(DlsRequestsRepository dlsRequestsRepository) {
        this.dlsRequestsRepository = dlsRequestsRepository;
    }

    /**
     * Save a dlsRequests.
     *
     * @param dlsRequests the entity to save.
     * @return the persisted entity.
     */
    @Override
    public DlsRequests save(DlsRequests dlsRequests) {
        log.debug("Request to save DlsRequests : {}", dlsRequests);
        return dlsRequestsRepository.save(dlsRequests);
    }

    /**
     * Get all the dlsRequests.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public Page<DlsRequests> findAll(Pageable pageable) {
        log.debug("Request to get all DlsRequests");
        return dlsRequestsRepository.findAll(pageable);
    }


    /**
     * Get one dlsRequests by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<DlsRequests> findOne(Long id) {
        log.debug("Request to get DlsRequests : {}", id);
        return dlsRequestsRepository.findById(id);
    }

    /**
     * Delete the dlsRequests by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete DlsRequests : {}", id);
        dlsRequestsRepository.deleteById(id);
    }
}
