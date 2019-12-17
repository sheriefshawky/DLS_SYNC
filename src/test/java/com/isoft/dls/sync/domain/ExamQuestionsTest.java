package com.isoft.dls.sync.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.isoft.dls.sync.web.rest.TestUtil;

public class ExamQuestionsTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(ExamQuestions.class);
        ExamQuestions examQuestions1 = new ExamQuestions();
        examQuestions1.setId(1L);
        ExamQuestions examQuestions2 = new ExamQuestions();
        examQuestions2.setId(examQuestions1.getId());
        assertThat(examQuestions1).isEqualTo(examQuestions2);
        examQuestions2.setId(2L);
        assertThat(examQuestions1).isNotEqualTo(examQuestions2);
        examQuestions1.setId(null);
        assertThat(examQuestions1).isNotEqualTo(examQuestions2);
    }
}
