package com.isoft.dls.sync.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.isoft.dls.sync.web.rest.TestUtil;

public class DlsVhlTrsExamsTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(DlsVhlTrsExams.class);
        DlsVhlTrsExams dlsVhlTrsExams1 = new DlsVhlTrsExams();
        dlsVhlTrsExams1.setId(1L);
        DlsVhlTrsExams dlsVhlTrsExams2 = new DlsVhlTrsExams();
        dlsVhlTrsExams2.setId(dlsVhlTrsExams1.getId());
        assertThat(dlsVhlTrsExams1).isEqualTo(dlsVhlTrsExams2);
        dlsVhlTrsExams2.setId(2L);
        assertThat(dlsVhlTrsExams1).isNotEqualTo(dlsVhlTrsExams2);
        dlsVhlTrsExams1.setId(null);
        assertThat(dlsVhlTrsExams1).isNotEqualTo(dlsVhlTrsExams2);
    }
}
