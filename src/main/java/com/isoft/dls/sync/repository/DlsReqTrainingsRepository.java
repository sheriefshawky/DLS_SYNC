package com.isoft.dls.sync.repository;
import com.isoft.dls.sync.domain.DlsReqTrainings;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the DlsReqTrainings entity.
 */
@SuppressWarnings("unused")
@Repository
public interface DlsReqTrainingsRepository extends JpaRepository<DlsReqTrainings, Long> {

}
