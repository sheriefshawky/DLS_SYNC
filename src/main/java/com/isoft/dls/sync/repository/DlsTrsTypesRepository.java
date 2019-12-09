package com.isoft.dls.sync.repository;
import com.isoft.dls.sync.domain.DlsTrsTypes;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the DlsTrsTypes entity.
 */
@SuppressWarnings("unused")
@Repository
public interface DlsTrsTypesRepository extends JpaRepository<DlsTrsTypes, Long> {

}
