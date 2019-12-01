package com.isoft.dls.sync.domain;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;

/**
 * A DlsReqTrainings.
 */
@Entity
@Table(name = "dls_req_trainings")
public class DlsReqTrainings implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @NotNull
    @Column(name = "training_code", nullable = false)
    private Integer trainingCode;

    @Column(name = "training_lectures")
    private Integer trainingLectures;

    @Column(name = "trainingfulfilled")
    private Integer trainingfulfilled;

    @Column(name = "exported")
    private Boolean exported;

    @ManyToOne
    @JsonIgnoreProperties("trainings")
    private DlsRequests dlsRequests;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getTrainingCode() {
        return trainingCode;
    }

    public DlsReqTrainings trainingCode(Integer trainingCode) {
        this.trainingCode = trainingCode;
        return this;
    }

    public void setTrainingCode(Integer trainingCode) {
        this.trainingCode = trainingCode;
    }

    public Integer getTrainingLectures() {
        return trainingLectures;
    }

    public DlsReqTrainings trainingLectures(Integer trainingLectures) {
        this.trainingLectures = trainingLectures;
        return this;
    }

    public void setTrainingLectures(Integer trainingLectures) {
        this.trainingLectures = trainingLectures;
    }

    public Integer getTrainingfulfilled() {
        return trainingfulfilled;
    }

    public DlsReqTrainings trainingfulfilled(Integer trainingfulfilled) {
        this.trainingfulfilled = trainingfulfilled;
        return this;
    }

    public void setTrainingfulfilled(Integer trainingfulfilled) {
        this.trainingfulfilled = trainingfulfilled;
    }

    public Boolean isExported() {
        return exported;
    }

    public DlsReqTrainings exported(Boolean exported) {
        this.exported = exported;
        return this;
    }

    public void setExported(Boolean exported) {
        this.exported = exported;
    }

    public DlsRequests getDlsRequests() {
        return dlsRequests;
    }

    public DlsReqTrainings dlsRequests(DlsRequests dlsRequests) {
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
        if (!(o instanceof DlsReqTrainings)) {
            return false;
        }
        return id != null && id.equals(((DlsReqTrainings) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "DlsReqTrainings{" +
            "id=" + getId() +
            ", trainingCode=" + getTrainingCode() +
            ", trainingLectures=" + getTrainingLectures() +
            ", trainingfulfilled=" + getTrainingfulfilled() +
            ", exported='" + isExported() + "'" +
            "}";
    }
}
