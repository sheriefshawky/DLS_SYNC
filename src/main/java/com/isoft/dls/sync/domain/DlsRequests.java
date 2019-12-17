package com.isoft.dls.sync.domain;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * A DlsRequests.
 */
@Entity
@Table(name = "dls_requests")
public class DlsRequests implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @NotNull
    @Column(name = "transaction_type", nullable = false)
    private Integer transactionType;

    @Column(name = "request_no")
    private Long requestNo;

    @Column(name = "exported")
    private Boolean exported;

    @OneToMany(mappedBy = "dlsRequests")
    private Set<DlsReqExams> exams = new HashSet<>();

    @OneToMany(mappedBy = "dlsRequests")
    private Set<DlsReqTrainings> trainings = new HashSet<>();

    @ManyToOne
    @JsonIgnoreProperties("requests")
    private DlsPersons dlsPersons;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getTransactionType() {
        return transactionType;
    }

    public DlsRequests transactionType(Integer transactionType) {
        this.transactionType = transactionType;
        return this;
    }

    public void setTransactionType(Integer transactionType) {
        this.transactionType = transactionType;
    }

    public Long getRequestNo() {
        return requestNo;
    }

    public DlsRequests requestNo(Long requestNo) {
        this.requestNo = requestNo;
        return this;
    }

    public void setRequestNo(Long requestNo) {
        this.requestNo = requestNo;
    }

    public Boolean isExported() {
        return exported;
    }

    public DlsRequests exported(Boolean exported) {
        this.exported = exported;
        return this;
    }

    public void setExported(Boolean exported) {
        this.exported = exported;
    }

    public Set<DlsReqExams> getExams() {
        return exams;
    }

    public DlsRequests exams(Set<DlsReqExams> dlsReqExams) {
        this.exams = dlsReqExams;
        return this;
    }

    public DlsRequests addExams(DlsReqExams dlsReqExams) {
        this.exams.add(dlsReqExams);
        dlsReqExams.setDlsRequests(this);
        return this;
    }

    public DlsRequests removeExams(DlsReqExams dlsReqExams) {
        this.exams.remove(dlsReqExams);
        dlsReqExams.setDlsRequests(null);
        return this;
    }

    public void setExams(Set<DlsReqExams> dlsReqExams) {
        this.exams = dlsReqExams;
    }

    public Set<DlsReqTrainings> getTrainings() {
        return trainings;
    }

    public DlsRequests trainings(Set<DlsReqTrainings> dlsReqTrainings) {
        this.trainings = dlsReqTrainings;
        return this;
    }

    public DlsRequests addTrainings(DlsReqTrainings dlsReqTrainings) {
        this.trainings.add(dlsReqTrainings);
        dlsReqTrainings.setDlsRequests(this);
        return this;
    }

    public DlsRequests removeTrainings(DlsReqTrainings dlsReqTrainings) {
        this.trainings.remove(dlsReqTrainings);
        dlsReqTrainings.setDlsRequests(null);
        return this;
    }

    public void setTrainings(Set<DlsReqTrainings> dlsReqTrainings) {
        this.trainings = dlsReqTrainings;
    }

    public DlsPersons getDlsPersons() {
        return dlsPersons;
    }

    public DlsRequests dlsPersons(DlsPersons dlsPersons) {
        this.dlsPersons = dlsPersons;
        return this;
    }

    public void setDlsPersons(DlsPersons dlsPersons) {
        this.dlsPersons = dlsPersons;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof DlsRequests)) {
            return false;
        }
        return id != null && id.equals(((DlsRequests) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "DlsRequests{" +
            "id=" + getId() +
            ", transactionType=" + getTransactionType() +
            ", requestNo=" + getRequestNo() +
            ", exported='" + isExported() + "'" +
            "}";
    }
}
