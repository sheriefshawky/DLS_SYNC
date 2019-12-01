package com.isoft.dls.sync.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.isoft.dls.sync.web.rest.TestUtil;

public class DlsRequestsTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(DlsRequests.class);
        DlsRequests dlsRequests1 = new DlsRequests();
        dlsRequests1.setId(1L);
        DlsRequests dlsRequests2 = new DlsRequests();
        dlsRequests2.setId(dlsRequests1.getId());
        assertThat(dlsRequests1).isEqualTo(dlsRequests2);
        dlsRequests2.setId(2L);
        assertThat(dlsRequests1).isNotEqualTo(dlsRequests2);
        dlsRequests1.setId(null);
        assertThat(dlsRequests1).isNotEqualTo(dlsRequests2);
    }
}
