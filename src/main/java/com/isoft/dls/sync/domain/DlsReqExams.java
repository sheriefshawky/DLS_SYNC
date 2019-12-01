package com.isoft.dls.sync.domain;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.time.Instant;

/**
 * A DlsReqExams.
 */
@Entity
@Table(name = "dls_req_exams")
public class DlsReqExams implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @NotNull
    @Column(name = "exam_code", nullable = false)
    private Integer examCode;

    @Column(name = "exam_result")
    private Integer examResult;

    @Column(name = "exam_date")
    private Instant examDate;

    @Column(name = "exam_grade")
    private Integer examGrade;

    @Column(name = "is_eligibile")
    private Integer isEligibile;

    @Column(name = "exported")
    private Boolean exported;

    @ManyToOne
    @JsonIgnoreProperties("exams")
    private DlsRequests dlsRequests;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getExamCode() {
        return examCode;
    }

    public DlsReqExams examCode(Integer examCode) {
        this.examCode = examCode;
        return this;
    }

    public void setExamCode(Integer examCode) {
        this.examCode = examCode;
    }

    public Integer getExamResult() {
        return examResult;
    }

    public DlsReqExams examResult(Integer examResult) {
        this.examResult = examResult;
        return this;
    }

    public void setExamResult(Integer examResult) {
        this.examResult = examResult;
    }

    public Instant getExamDate() {
        return examDate;
    }

    public DlsReqExams examDate(Instant examDate) {
        this.examDate = examDate;
        return this;
    }

    public void setExamDate(Instant examDate) {
        this.examDate = examDate;
    }

    public Integer getExamGrade() {
        return examGrade;
    }

    public DlsReqExams examGrade(Integer examGrade) {
        this.examGrade = examGrade;
        return this;
    }

    public void setExamGrade(Integer examGrade) {
        this.examGrade = examGrade;
    }

    public Integer getIsEligibile() {
        return isEligibile;
    }

    public DlsReqExams isEligibile(Integer isEligibile) {
        this.isEligibile = isEligibile;
        return this;
    }

    public void setIsEligibile(Integer isEligibile) {
        this.isEligibile = isEligibile;
    }

    public Boolean isExported() {
        return exported;
    }

    public DlsReqExams exported(Boolean exported) {
        this.exported = exported;
        return this;
    }

    public void setExported(Boolean exported) {
        this.exported = exported;
    }

    public DlsRequests getDlsRequests() {
        return dlsRequests;
    }

    public DlsReqExams dlsRequests(DlsRequests dlsRequests) {
        this.dlsRequests = dlsRequests;
        return this;
    }

    public void setDlsRequests(DlsRequests dlsRequests) {
        this.dlsRequests = dlsRequests;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof DlsReqExams)) {
            return false;
        }
        return id != null && id.equals(((DlsReqExams) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "DlsReqExams{" +
            "id=" + getId() +
            ", examCode=" + getExamCode() +
            ", examResult=" + getExamResult() +
            ", examDate='" + getExamDate() + "'" +
            ", examGrade=" + getExamGrade() +
            ", isEligibile=" + getIsEligibile() +
            ", exported='" + isExported() + "'" +
            "}";
    }
}
