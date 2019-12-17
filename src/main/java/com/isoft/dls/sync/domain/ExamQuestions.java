package com.isoft.dls.sync.domain;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * A ExamQuestions.
 */
@Entity
@Table(name = "exam_questions")
public class ExamQuestions implements Serializable {

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

    @Column(name = "score")
    private Double score;

    @Column(name = "status")
    private Integer status;

    @Column(name = "seq")
    private Integer seq;

    @Column(name = "category_id")
    private Integer categoryId;

    @Column(name = "question_id")
    private Integer questionId;

    @OneToMany(mappedBy = "examQuestions")
    private Set<ExamQuestionAnswers> examQuestionAnswers = new HashSet<>();

    @ManyToOne
    @JsonIgnoreProperties("examQuestions")
    private Exam exam;

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

    public ExamQuestions descAr(String descAr) {
        this.descAr = descAr;
        return this;
    }

    public void setDescAr(String descAr) {
        this.descAr = descAr;
    }

    public String getDescEn() {
        return descEn;
    }

    public ExamQuestions descEn(String descEn) {
        this.descEn = descEn;
        return this;
    }

    public void setDescEn(String descEn) {
        this.descEn = descEn;
    }

    public String getCode() {
        return code;
    }

    public ExamQuestions code(String code) {
        this.code = code;
        return this;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getImgPath() {
        return imgPath;
    }

    public ExamQuestions imgPath(String imgPath) {
        this.imgPath = imgPath;
        return this;
    }

    public void setImgPath(String imgPath) {
        this.imgPath = imgPath;
    }

    public Long getTimeInSec() {
        return timeInSec;
    }

    public ExamQuestions timeInSec(Long timeInSec) {
        this.timeInSec = timeInSec;
        return this;
    }

    public void setTimeInSec(Long timeInSec) {
        this.timeInSec = timeInSec;
    }

    public Integer getType() {
        return type;
    }

    public ExamQuestions type(Integer type) {
        this.type = type;
        return this;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Double getWeight() {
        return weight;
    }

    public ExamQuestions weight(Double weight) {
        this.weight = weight;
        return this;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }

    public Double getScore() {
        return score;
    }

    public ExamQuestions score(Double score) {
        this.score = score;
        return this;
    }

    public void setScore(Double score) {
        this.score = score;
    }

    public Integer getStatus() {
        return status;
    }

    public ExamQuestions status(Integer status) {
        this.status = status;
        return this;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getSeq() {
        return seq;
    }

    public ExamQuestions seq(Integer seq) {
        this.seq = seq;
        return this;
    }

    public void setSeq(Integer seq) {
        this.seq = seq;
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public ExamQuestions categoryId(Integer categoryId) {
        this.categoryId = categoryId;
        return this;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public Integer getQuestionId() {
        return questionId;
    }

    public ExamQuestions questionId(Integer questionId) {
        this.questionId = questionId;
        return this;
    }

    public void setQuestionId(Integer questionId) {
        this.questionId = questionId;
    }

    public Set<ExamQuestionAnswers> getExamQuestionAnswers() {
        return examQuestionAnswers;
    }

    public ExamQuestions examQuestionAnswers(Set<ExamQuestionAnswers> examQuestionAnswers) {
        this.examQuestionAnswers = examQuestionAnswers;
        return this;
    }

    public ExamQuestions addExamQuestionAnswers(ExamQuestionAnswers examQuestionAnswers) {
        this.examQuestionAnswers.add(examQuestionAnswers);
        examQuestionAnswers.setExamQuestions(this);
        return this;
    }

    public ExamQuestions removeExamQuestionAnswers(ExamQuestionAnswers examQuestionAnswers) {
        this.examQuestionAnswers.remove(examQuestionAnswers);
        examQuestionAnswers.setExamQuestions(null);
        return this;
    }

    public void setExamQuestionAnswers(Set<ExamQuestionAnswers> examQuestionAnswers) {
        this.examQuestionAnswers = examQuestionAnswers;
    }

    public Exam getExam() {
        return exam;
    }

    public ExamQuestions exam(Exam exam) {
        this.exam = exam;
        return this;
    }

    public void setExam(Exam exam) {
        this.exam = exam;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ExamQuestions)) {
            return false;
        }
        return id != null && id.equals(((ExamQuestions) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "ExamQuestions{" +
            "id=" + getId() +
            ", descAr='" + getDescAr() + "'" +
            ", descEn='" + getDescEn() + "'" +
            ", code='" + getCode() + "'" +
            ", imgPath='" + getImgPath() + "'" +
            ", timeInSec=" + getTimeInSec() +
            ", type=" + getType() +
            ", weight=" + getWeight() +
            ", score=" + getScore() +
            ", status=" + getStatus() +
            ", seq=" + getSeq() +
            ", categoryId=" + getCategoryId() +
            ", questionId=" + getQuestionId() +
            "}";
    }
}
