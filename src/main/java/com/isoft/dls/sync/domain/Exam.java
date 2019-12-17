package com.isoft.dls.sync.domain;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.time.Instant;
import java.util.HashSet;
import java.util.Set;

/**
 * A Exam.
 */
@Entity
@Table(name = "exam")
public class Exam implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @NotNull
    @Column(name = "validfrom", nullable = false)
    private Instant validfrom;

    @NotNull
    @Column(name = "validto", nullable = false)
    private Instant validto;

    @Column(name = "time_in_sec")
    private Long timeInSec;

    @Column(name = "score")
    private Double score;

    @NotNull
    @Column(name = "status", nullable = false)
    private Integer status;

    @Column(name = "pass_score")
    private Double passScore;

    @Column(name = "start_at")
    private Instant startAt;

    @Column(name = "submit_at")
    private Instant submitAt;

    @OneToOne
    @JoinColumn(unique = true)
    private Template template;

    @OneToMany(mappedBy = "exam")
    private Set<ExamQuestions> examQuestions = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Instant getValidfrom() {
        return validfrom;
    }

    public Exam validfrom(Instant validfrom) {
        this.validfrom = validfrom;
        return this;
    }

    public void setValidfrom(Instant validfrom) {
        this.validfrom = validfrom;
    }

    public Instant getValidto() {
        return validto;
    }

    public Exam validto(Instant validto) {
        this.validto = validto;
        return this;
    }

    public void setValidto(Instant validto) {
        this.validto = validto;
    }

    public Long getTimeInSec() {
        return timeInSec;
    }

    public Exam timeInSec(Long timeInSec) {
        this.timeInSec = timeInSec;
        return this;
    }

    public void setTimeInSec(Long timeInSec) {
        this.timeInSec = timeInSec;
    }

    public Double getScore() {
        return score;
    }

    public Exam score(Double score) {
        this.score = score;
        return this;
    }

    public void setScore(Double score) {
        this.score = score;
    }

    public Integer getStatus() {
        return status;
    }

    public Exam status(Integer status) {
        this.status = status;
        return this;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Double getPassScore() {
        return passScore;
    }

    public Exam passScore(Double passScore) {
        this.passScore = passScore;
        return this;
    }

    public void setPassScore(Double passScore) {
        this.passScore = passScore;
    }

    public Instant getStartAt() {
        return startAt;
    }

    public Exam startAt(Instant startAt) {
        this.startAt = startAt;
        return this;
    }

    public void setStartAt(Instant startAt) {
        this.startAt = startAt;
    }

    public Instant getSubmitAt() {
        return submitAt;
    }

    public Exam submitAt(Instant submitAt) {
        this.submitAt = submitAt;
        return this;
    }

    public void setSubmitAt(Instant submitAt) {
        this.submitAt = submitAt;
    }

    public Template getTemplate() {
        return template;
    }

    public Exam template(Template template) {
        this.template = template;
        return this;
    }

    public void setTemplate(Template template) {
        this.template = template;
    }

    public Set<ExamQuestions> getExamQuestions() {
        return examQuestions;
    }

    public Exam examQuestions(Set<ExamQuestions> examQuestions) {
        this.examQuestions = examQuestions;
        return this;
    }

    public Exam addExamQuestions(ExamQuestions examQuestions) {
        this.examQuestions.add(examQuestions);
        examQuestions.setExam(this);
        return this;
    }

    public Exam removeExamQuestions(ExamQuestions examQuestions) {
        this.examQuestions.remove(examQuestions);
        examQuestions.setExam(null);
        return this;
    }

    public void setExamQuestions(Set<ExamQuestions> examQuestions) {
        this.examQuestions = examQuestions;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Exam)) {
            return false;
        }
        return id != null && id.equals(((Exam) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "Exam{" +
            "id=" + getId() +
            ", validfrom='" + getValidfrom() + "'" +
            ", validto='" + getValidto() + "'" +
            ", timeInSec=" + getTimeInSec() +
            ", score=" + getScore() +
            ", status=" + getStatus() +
            ", passScore=" + getPassScore() +
            ", startAt='" + getStartAt() + "'" +
            ", submitAt='" + getSubmitAt() + "'" +
            "}";
    }
}
