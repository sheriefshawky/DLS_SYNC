package com.isoft.dls.sync.repository;
import com.isoft.dls.sync.domain.ExamQuestions;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the ExamQuestions entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ExamQuestionsRepository extends JpaRepository<ExamQuestions, Long> {

}
