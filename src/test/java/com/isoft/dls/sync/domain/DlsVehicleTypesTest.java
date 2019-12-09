package com.isoft.dls.sync.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.isoft.dls.sync.web.rest.TestUtil;

public class DlsVehicleTypesTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(DlsVehicleTypes.class);
        DlsVehicleTypes dlsVehicleTypes1 = new DlsVehicleTypes();
        dlsVehicleTypes1.setId(1L);
        DlsVehicleTypes dlsVehicleTypes2 = new DlsVehicleTypes();
        dlsVehicleTypes2.setId(dlsVehicleTypes1.getId());
        assertThat(dlsVehicleTypes1).isEqualTo(dlsVehicleTypes2);
        dlsVehicleTypes2.setId(2L);
        assertThat(dlsVehicleTypes1).isNotEqualTo(dlsVehicleTypes2);
        dlsVehicleTypes1.setId(null);
        assertThat(dlsVehicleTypes1).isNotEqualTo(dlsVehicleTypes2);
    }
}
