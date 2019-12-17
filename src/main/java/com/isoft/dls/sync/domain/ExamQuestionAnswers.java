package com.isoft.dls.sync.domain;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;

/**
 * A ExamQuestionAnswers.
 */
@Entity
@Table(name = "exam_question_answers")
public class ExamQuestionAnswers implements Serializable {

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

    @Column(name = "is_right_answer")
    private Boolean isRightAnswer;

    @Column(name = "status")
    private Integer status;

    @Column(name = "answer_id")
    private Integer answerId;

    @ManyToOne
    @JsonIgnoreProperties("examQuestionAnswers")
    private ExamQuestions examQuestions;

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

    public ExamQuestionAnswers descAr(String descAr) {
        this.descAr = descAr;
        return this;
    }

    public void setDescAr(String descAr) {
        this.descAr = descAr;
    }

    public String getDescEn() {
        return descEn;
    }

    public ExamQuestionAnswers descEn(String descEn) {
        this.descEn = descEn;
        return this;
    }

    public void setDescEn(String descEn) {
        this.descEn = descEn;
    }

    public String getCode() {
        return code;
    }

    public ExamQuestionAnswers code(String code) {
        this.code = code;
        return this;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getImgPath() {
        return imgPath;
    }

    public ExamQuestionAnswers imgPath(String imgPath) {
        this.imgPath = imgPath;
        return this;
    }

    public void setImgPath(String imgPath) {
        this.imgPath = imgPath;
    }

    public Boolean isIsRightAnswer() {
        return isRightAnswer;
    }

    public ExamQuestionAnswers isRightAnswer(Boolean isRightAnswer) {
        this.isRightAnswer = isRightAnswer;
        return this;
    }

    public void setIsRightAnswer(Boolean isRightAnswer) {
        this.isRightAnswer = isRightAnswer;
    }

    public Integer getStatus() {
        return status;
    }

    public ExamQuestionAnswers status(Integer status) {
        this.status = status;
        return this;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getAnswerId() {
        return answerId;
    }

    public ExamQuestionAnswers answerId(Integer answerId) {
        this.answerId = answerId;
        return this;
    }

    public void setAnswerId(Integer answerId) {
        this.answerId = answerId;
    }

    public ExamQuestions getExamQuestions() {
        return examQuestions;
    }

    public ExamQuestionAnswers examQuestions(ExamQuestions examQuestions) {
        this.examQuestions = examQuestions;
        return this;
    }

    public void setExamQuestions(ExamQuestions examQuestions) {
        this.examQuestions = examQuestions;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ExamQuestionAnswers)) {
            return false;
        }
        return id != null && id.equals(((ExamQuestionAnswers) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "ExamQuestionAnswers{" +
            "id=" + getId() +
            ", descAr='" + getDescAr() + "'" +
            ", descEn='" + getDescEn() + "'" +
            ", code='" + getCode() + "'" +
            ", imgPath='" + getImgPath() + "'" +
            ", isRightAnswer='" + isIsRightAnswer() + "'" +
            ", status=" + getStatus() +
            ", answerId=" + getAnswerId() +
            "}";
    }
}
