package com.isoft.dls.sync.domain;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;

/**
 * A DlsExams.
 */
@Entity
@Table(name = "dls_exams")
public class DlsExams implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @NotNull
    @Column(name = "code", nullable = false)
    private String code;

    @NotNull
    @Column(name = "name_ar", nullable = false)
    private String nameAr;

    @NotNull
    @Column(name = "name_en", nullable = false)
    private String nameEn;

    @NotNull
    @Column(name = "test_id", nullable = false)
    private String testId;

    @NotNull
    @Column(name = "qroup_id", nullable = false)
    private String qroupId;

    @NotNull
    @Column(name = "pass_percentage", nullable = false)
    private Double passPercentage;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public DlsExams code(String code) {
        this.code = code;
        return this;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getNameAr() {
        return nameAr;
    }

    public DlsExams nameAr(String nameAr) {
        this.nameAr = nameAr;
        return this;
    }

    public void setNameAr(String nameAr) {
        this.nameAr = nameAr;
    }

    public String getNameEn() {
        return nameEn;
    }

    public DlsExams nameEn(String nameEn) {
        this.nameEn = nameEn;
        return this;
    }

    public void setNameEn(String nameEn) {
        this.nameEn = nameEn;
    }

    public String getTestId() {
        return testId;
    }

    public DlsExams testId(String testId) {
        this.testId = testId;
        return this;
    }

    public void setTestId(String testId) {
        this.testId = testId;
    }

    public String getQroupId() {
        return qroupId;
    }

    public DlsExams qroupId(String qroupId) {
        this.qroupId = qroupId;
        return this;
    }

    public void setQroupId(String qroupId) {
        this.qroupId = qroupId;
    }

    public Double getPassPercentage() {
        return passPercentage;
    }

    public DlsExams passPercentage(Double passPercentage) {
        this.passPercentage = passPercentage;
        return this;
    }

    public void setPassPercentage(Double passPercentage) {
        this.passPercentage = passPercentage;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof DlsExams)) {
            return false;
        }
        return id != null && id.equals(((DlsExams) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "DlsExams{" +
            "id=" + getId() +
            ", code='" + getCode() + "'" +
            ", nameAr='" + getNameAr() + "'" +
            ", nameEn='" + getNameEn() + "'" +
            ", testId='" + getTestId() + "'" +
            ", qroupId='" + getQroupId() + "'" +
            ", passPercentage=" + getPassPercentage() +
            "}";
    }
}
