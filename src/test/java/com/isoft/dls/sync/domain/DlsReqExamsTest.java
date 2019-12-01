package com.isoft.dls.sync.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.isoft.dls.sync.web.rest.TestUtil;

public class DlsReqExamsTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(DlsReqExams.class);
        DlsReqExams dlsReqExams1 = new DlsReqExams();
        dlsReqExams1.setId(1L);
        DlsReqExams dlsReqExams2 = new DlsReqExams();
        dlsReqExams2.setId(dlsReqExams1.getId());
        assertThat(dlsReqExams1).isEqualTo(dlsReqExams2);
        dlsReqExams2.setId(2L);
        assertThat(dlsReqExams1).isNotEqualTo(dlsReqExams2);
        dlsReqExams1.setId(null);
        assertThat(dlsReqExams1).isNotEqualTo(dlsReqExams2);
    }
}
