package com.isoft.dls.sync.repository;
import com.isoft.dls.sync.domain.DlsVehicleTypes;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the DlsVehicleTypes entity.
 */
@SuppressWarnings("unused")
@Repository
public interface DlsVehicleTypesRepository extends JpaRepository<DlsVehicleTypes, Long> {

}
