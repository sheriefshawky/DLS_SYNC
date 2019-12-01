package com.isoft.dls.sync.domain;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * A DlsPersons.
 */
@Entity
@Table(name = "dls_persons")
public class DlsPersons implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @NotNull
    @Column(name = "full_name", nullable = false)
    private String fullName;

    @NotNull
    @Column(name = "mobile_no", nullable = false)
    private String mobileNo;

    @NotNull
    @Column(name = "license_category", nullable = false)
    private Integer licenseCategory;

    @Column(name = "national_id")
    private String nationalId;

    @Column(name = "passport_key")
    private String passportKey;

    @NotNull
    @Column(name = "transaction_type", nullable = false)
    private Integer transactionType;

    @Column(name = "exported")
    private Boolean exported;

    @OneToMany(mappedBy = "dlsPersons")
    private Set<DlsRequests> requests = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public DlsPersons fullName(String fullName) {
        this.fullName = fullName;
        return this;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getMobileNo() {
        return mobileNo;
    }

    public DlsPersons mobileNo(String mobileNo) {
        this.mobileNo = mobileNo;
        return this;
    }

    public void setMobileNo(String mobileNo) {
        this.mobileNo = mobileNo;
    }

    public Integer getLicenseCategory() {
        return licenseCategory;
    }

    public DlsPersons licenseCategory(Integer licenseCategory) {
        this.licenseCategory = licenseCategory;
        return this;
    }

    public void setLicenseCategory(Integer licenseCategory) {
        this.licenseCategory = licenseCategory;
    }

    public String getNationalId() {
        return nationalId;
    }

    public DlsPersons nationalId(String nationalId) {
        this.nationalId = nationalId;
        return this;
    }

    public void setNationalId(String nationalId) {
        this.nationalId = nationalId;
    }

    public String getPassportKey() {
        return passportKey;
    }

    public DlsPersons passportKey(String passportKey) {
        this.passportKey = passportKey;
        return this;
    }

    public void setPassportKey(String passportKey) {
        this.passportKey = passportKey;
    }

    public Integer getTransactionType() {
        return transactionType;
    }

    public DlsPersons transactionType(Integer transactionType) {
        this.transactionType = transactionType;
        return this;
    }

    public void setTransactionType(Integer transactionType) {
        this.transactionType = transactionType;
    }

    public Boolean isExported() {
        return exported;
    }

    public DlsPersons exported(Boolean exported) {
        this.exported = exported;
        return this;
    }

    public void setExported(Boolean exported) {
        this.exported = exported;
    }

    public Set<DlsRequests> getRequests() {
        return requests;
    }

    public DlsPersons requests(Set<DlsRequests> dlsRequests) {
        this.requests = dlsRequests;
        return this;
    }

    public DlsPersons addRequests(DlsRequests dlsRequests) {
        this.requests.add(dlsRequests);
        dlsRequests.setDlsPersons(this);
        return this;
    }

    public DlsPersons removeRequests(DlsRequests dlsRequests) {
        this.requests.remove(dlsRequests);
        dlsRequests.setDlsPersons(null);
        return this;
    }

    public void setRequests(Set<DlsRequests> dlsRequests) {
        this.requests = dlsRequests;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof DlsPersons)) {
            return false;
        }
        return id != null && id.equals(((DlsPersons) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "DlsPersons{" +
            "id=" + getId() +
            ", fullName='" + getFullName() + "'" +
            ", mobileNo='" + getMobileNo() + "'" +
            ", licenseCategory=" + getLicenseCategory() +
            ", nationalId='" + getNationalId() + "'" +
            ", passportKey='" + getPassportKey() + "'" +
            ", transactionType=" + getTransactionType() +
            ", exported='" + isExported() + "'" +
            "}";
    }
}
