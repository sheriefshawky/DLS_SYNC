package com.isoft.dls.sync.repository;
import com.isoft.dls.sync.domain.DlsRequests;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the DlsRequests entity.
 */
@SuppressWarnings("unused")
@Repository
public interface DlsRequestsRepository extends JpaRepository<DlsRequests, Long> {

}
