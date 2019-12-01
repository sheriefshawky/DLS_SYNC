package com.isoft.dls.sync.repository;
import com.isoft.dls.sync.domain.DlsReqExams;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the DlsReqExams entity.
 */
@SuppressWarnings("unused")
@Repository
public interface DlsReqExamsRepository extends JpaRepository<DlsReqExams, Long> {

}
