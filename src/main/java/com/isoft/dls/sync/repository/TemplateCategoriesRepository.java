package com.isoft.dls.sync.repository;
import com.isoft.dls.sync.domain.TemplateCategories;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the TemplateCategories entity.
 */
@SuppressWarnings("unused")
@Repository
public interface TemplateCategoriesRepository extends JpaRepository<TemplateCategories, Long> {

}
