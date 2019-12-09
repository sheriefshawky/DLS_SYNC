package com.isoft.dls.sync.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.isoft.dls.sync.web.rest.TestUtil;

public class DlsTrsTypesTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(DlsTrsTypes.class);
        DlsTrsTypes dlsTrsTypes1 = new DlsTrsTypes();
        dlsTrsTypes1.setId(1L);
        DlsTrsTypes dlsTrsTypes2 = new DlsTrsTypes();
        dlsTrsTypes2.setId(dlsTrsTypes1.getId());
        assertThat(dlsTrsTypes1).isEqualTo(dlsTrsTypes2);
        dlsTrsTypes2.setId(2L);
        assertThat(dlsTrsTypes1).isNotEqualTo(dlsTrsTypes2);
        dlsTrsTypes1.setId(null);
        assertThat(dlsTrsTypes1).isNotEqualTo(dlsTrsTypes2);
    }
}
