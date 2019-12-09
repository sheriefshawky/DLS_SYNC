package com.isoft.dls.sync.repository;
import com.isoft.dls.sync.domain.DlsVhlTrsExams;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the DlsVhlTrsExams entity.
 */
@SuppressWarnings("unused")
@Repository
public interface DlsVhlTrsExamsRepository extends JpaRepository<DlsVhlTrsExams, Long> {

}
