package com.isoft.dls.sync.repository;
import com.isoft.dls.sync.domain.Answers;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the Answers entity.
 */
@SuppressWarnings("unused")
@Repository
public interface AnswersRepository extends JpaRepository<Answers, Long> {

}
