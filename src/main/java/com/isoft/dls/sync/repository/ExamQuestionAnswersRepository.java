package com.isoft.dls.sync.repository;
import com.isoft.dls.sync.domain.ExamQuestionAnswers;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the ExamQuestionAnswers entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ExamQuestionAnswersRepository extends JpaRepository<ExamQuestionAnswers, Long> {

}
