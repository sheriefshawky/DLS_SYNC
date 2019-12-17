package com.isoft.dls.sync.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.isoft.dls.sync.web.rest.TestUtil;

public class ExamQuestionAnswersTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(ExamQuestionAnswers.class);
        ExamQuestionAnswers examQuestionAnswers1 = new ExamQuestionAnswers();
        examQuestionAnswers1.setId(1L);
        ExamQuestionAnswers examQuestionAnswers2 = new ExamQuestionAnswers();
        examQuestionAnswers2.setId(examQuestionAnswers1.getId());
        assertThat(examQuestionAnswers1).isEqualTo(examQuestionAnswers2);
        examQuestionAnswers2.setId(2L);
        assertThat(examQuestionAnswers1).isNotEqualTo(examQuestionAnswers2);
        examQuestionAnswers1.setId(null);
        assertThat(examQuestionAnswers1).isNotEqualTo(examQuestionAnswers2);
    }
}
