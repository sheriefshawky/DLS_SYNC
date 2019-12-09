package com.isoft.dls.sync.repository;
import com.isoft.dls.sync.domain.DlsExams;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the DlsExams entity.
 */
@SuppressWarnings("unused")
@Repository
public interface DlsExamsRepository extends JpaRepository<DlsExams, Long> {

}
