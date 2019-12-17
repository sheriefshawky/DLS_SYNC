package com.isoft.dls.sync.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.isoft.dls.sync.web.rest.TestUtil;

public class DlsPersonsTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(DlsPersons.class);
        DlsPersons dlsPersons1 = new DlsPersons();
        dlsPersons1.setId(1L);
        DlsPersons dlsPersons2 = new DlsPersons();
        dlsPersons2.setId(dlsPersons1.getId());
        assertThat(dlsPersons1).isEqualTo(dlsPersons2);
        dlsPersons2.setId(2L);
        assertThat(dlsPersons1).isNotEqualTo(dlsPersons2);
        dlsPersons1.setId(null);
        assertThat(dlsPersons1).isNotEqualTo(dlsPersons2);
    }
}
