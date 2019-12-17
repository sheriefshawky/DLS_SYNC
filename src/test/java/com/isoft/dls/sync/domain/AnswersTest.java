package com.isoft.dls.sync.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.isoft.dls.sync.web.rest.TestUtil;

public class AnswersTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Answers.class);
        Answers answers1 = new Answers();
        answers1.setId(1L);
        Answers answers2 = new Answers();
        answers2.setId(answers1.getId());
        assertThat(answers1).isEqualTo(answers2);
        answers2.setId(2L);
        assertThat(answers1).isNotEqualTo(answers2);
        answers1.setId(null);
        assertThat(answers1).isNotEqualTo(answers2);
    }
}
