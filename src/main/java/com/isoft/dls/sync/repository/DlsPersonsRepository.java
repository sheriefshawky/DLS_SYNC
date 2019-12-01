package com.isoft.dls.sync.repository;
import com.isoft.dls.sync.domain.DlsPersons;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the DlsPersons entity.
 */
@SuppressWarnings("unused")
@Repository
public interface DlsPersonsRepository extends JpaRepository<DlsPersons, Long> {

}
