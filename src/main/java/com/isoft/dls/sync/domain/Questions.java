package com.isoft.dls.sync.domain;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * A Questions.
 */
@Entity
@Table(name = "questions")
public class Questions implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @NotNull
    @Column(name = "desc_ar", nullable = false)
    private String descAr;

    @NotNull
    @Column(name = "desc_en", nullable = false)
    private String descEn;

    @NotNull
    @Column(name = "code", nullable = false)
    private String code;

    @Column(name = "img_path")
    private String imgPath;

    @Column(name = "time_in_sec")
    private Long timeInSec;

    @NotNull
    @Column(name = "type", nullable = false)
    private Integer type;

    @NotNull
    @Column(name = "weight", nullable = false)
    private Double weight;

    @Column(name = "status")
    private Integer status;

    @OneToMany(mappedBy = "questions")
    private Set<Answers> answers = new HashSet<>();

    @ManyToOne
    @JsonIgnoreProperties("questions")
    private Categories categories;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescAr() {
        return descAr;
    }

    public Questions descAr(String descAr) {
        this.descAr = descAr;
        return this;
    }

    public void setDescAr(String descAr) {
        this.descAr = descAr;
    }

    public String getDescEn() {
        return descEn;
    }

    public Questions descEn(String descEn) {
        this.descEn = descEn;
        return this;
    }

    public void setDescEn(String descEn) {
        this.descEn = descEn;
    }

    public String getCode() {
        return code;
    }

    public Questions code(String code) {
        this.code = code;
        return this;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getImgPath() {
        return imgPath;
    }

    public Questions imgPath(String imgPath) {
        this.imgPath = imgPath;
        return this;
    }

    public void setImgPath(String imgPath) {
        this.imgPath = imgPath;
    }

    public Long getTimeInSec() {
        return timeInSec;
    }

    public Questions timeInSec(Long timeInSec) {
        this.timeInSec = timeInSec;
        return this;
    }

    public void setTimeInSec(Long timeInSec) {
        this.timeInSec = timeInSec;
    }

    public Integer getType() {
        return type;
    }

    public Questions type(Integer type) {
        this.type = type;
        return this;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Double getWeight() {
        return weight;
    }

    public Questions weight(Double weight) {
        this.weight = weight;
        return this;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }

    public Integer getStatus() {
        return status;
    }

    public Questions status(Integer status) {
        this.status = status;
        return this;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Set<Answers> getAnswers() {
        return answers;
    }

    public Questions answers(Set<Answers> answers) {
        this.answers = answers;
        return this;
    }

    public Questions addAnswers(Answers answers) {
        this.answers.add(answers);
        answers.setQuestions(this);
        return this;
    }

    public Questions removeAnswers(Answers answers) {
        this.answers.remove(answers);
        answers.setQuestions(null);
        return this;
    }

    public void setAnswers(Set<Answers> answers) {
        this.answers = answers;
    }

    public Categories getCategories() {
        return categories;
    }

    public Questions categories(Categories categories) {
        this.categories = categories;
        return this;
    }

    public void setCategories(Categories categories) {
        this.categories = categories;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Questions)) {
            return false;
        }
        return id != null && id.equals(((Questions) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "Questions{" +
            "id=" + getId() +
            ", descAr='" + getDescAr() + "'" +
            ", descEn='" + getDescEn() + "'" +
            ", code='" + getCode() + "'" +
            ", imgPath='" + getImgPath() + "'" +
            ", timeInSec=" + getTimeInSec() +
            ", type=" + getType() +
            ", weight=" + getWeight() +
            ", status=" + getStatus() +
            "}";
    }
}
