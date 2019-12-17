package com.isoft.dls.sync.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.isoft.dls.sync.web.rest.TestUtil;

public class DlsReqTrainingsTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(DlsReqTrainings.class);
        DlsReqTrainings dlsReqTrainings1 = new DlsReqTrainings();
        dlsReqTrainings1.setId(1L);
        DlsReqTrainings dlsReqTrainings2 = new DlsReqTrainings();
        dlsReqTrainings2.setId(dlsReqTrainings1.getId());
        assertThat(dlsReqTrainings1).isEqualTo(dlsReqTrainings2);
        dlsReqTrainings2.setId(2L);
        assertThat(dlsReqTrainings1).isNotEqualTo(dlsReqTrainings2);
        dlsReqTrainings1.setId(null);
        assertThat(dlsReqTrainings1).isNotEqualTo(dlsReqTrainings2);
    }
}
