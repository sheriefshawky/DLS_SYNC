package com.isoft.dls.sync.domain;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;

/**
 * A TemplateCategories.
 */
@Entity
@Table(name = "template_categories")
public class TemplateCategories implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @NotNull
    @Column(name = "no_of_questions", nullable = false)
    private Integer noOfQuestions;

    @NotNull
    @Column(name = "seq", nullable = false)
    private Integer seq;

    @OneToOne
    @JoinColumn(unique = true)
    private Categories category;

    @ManyToOne
    @JsonIgnoreProperties("categories")
    private Template template;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getNoOfQuestions() {
        return noOfQuestions;
    }

    public TemplateCategories noOfQuestions(Integer noOfQuestions) {
        this.noOfQuestions = noOfQuestions;
        return this;
    }

    public void setNoOfQuestions(Integer noOfQuestions) {
        this.noOfQuestions = noOfQuestions;
    }

    public Integer getSeq() {
        return seq;
    }

    public TemplateCategories seq(Integer seq) {
        this.seq = seq;
        return this;
    }

    public void setSeq(Integer seq) {
        this.seq = seq;
    }

    public Categories getCategory() {
        return category;
    }

    public TemplateCategories category(Categories categories) {
        this.category = categories;
        return this;
    }

    public void setCategory(Categories categories) {
        this.category = categories;
    }

    public Template getTemplate() {
        return template;
    }

    public TemplateCategories template(Template template) {
        this.template = template;
        return this;
    }

    public void setTemplate(Template template) {
        this.template = template;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof TemplateCategories)) {
            return false;
        }
        return id != null && id.equals(((TemplateCategories) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "TemplateCategories{" +
            "id=" + getId() +
            ", noOfQuestions=" + getNoOfQuestions() +
            ", seq=" + getSeq() +
            "}";
    }
}
