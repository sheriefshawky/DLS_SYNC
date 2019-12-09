package com.isoft.dls.sync.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.isoft.dls.sync.web.rest.TestUtil;

public class DlsExamsTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(DlsExams.class);
        DlsExams dlsExams1 = new DlsExams();
        dlsExams1.setId(1L);
        DlsExams dlsExams2 = new DlsExams();
        dlsExams2.setId(dlsExams1.getId());
        assertThat(dlsExams1).isEqualTo(dlsExams2);
        dlsExams2.setId(2L);
        assertThat(dlsExams1).isNotEqualTo(dlsExams2);
        dlsExams1.setId(null);
        assertThat(dlsExams1).isNotEqualTo(dlsExams2);
    }
}
