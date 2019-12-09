package com.isoft.dls.sync.domain;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;

/**
 * A DlsVhlTrsExams.
 */
@Entity
@Table(name = "dls_vhl_trs_exams")
public class DlsVhlTrsExams implements Serializable {

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

    @ManyToOne
    @JsonIgnoreProperties("dlsVhlTrsExams")
    private DlsExams exams;

    @ManyToOne
    @JsonIgnoreProperties("dlsVhlTrsExams")
    private DlsVehicleTypes vehicleTypes;

    @ManyToOne
    @JsonIgnoreProperties("dlsVhlTrsExams")
    private DlsTrsTypes trsTypes;

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

    public DlsVhlTrsExams code(String code) {
        this.code = code;
        return this;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getNameAr() {
        return nameAr;
    }

    public DlsVhlTrsExams nameAr(String nameAr) {
        this.nameAr = nameAr;
        return this;
    }

    public void setNameAr(String nameAr) {
        this.nameAr = nameAr;
    }

    public String getNameEn() {
        return nameEn;
    }

    public DlsVhlTrsExams nameEn(String nameEn) {
        this.nameEn = nameEn;
        return this;
    }

    public void setNameEn(String nameEn) {
        this.nameEn = nameEn;
    }

    public DlsExams getExams() {
        return exams;
    }

    public DlsVhlTrsExams exams(DlsExams dlsExams) {
        this.exams = dlsExams;
        return this;
    }

    public void setExams(DlsExams dlsExams) {
        this.exams = dlsExams;
    }

    public DlsVehicleTypes getVehicleTypes() {
        return vehicleTypes;
    }

    public DlsVhlTrsExams vehicleTypes(DlsVehicleTypes dlsVehicleTypes) {
        this.vehicleTypes = dlsVehicleTypes;
        return this;
    }

    public void setVehicleTypes(DlsVehicleTypes dlsVehicleTypes) {
        this.vehicleTypes = dlsVehicleTypes;
    }

    public DlsTrsTypes getTrsTypes() {
        return trsTypes;
    }

    public DlsVhlTrsExams trsTypes(DlsTrsTypes dlsTrsTypes) {
        this.trsTypes = dlsTrsTypes;
        return this;
    }

    public void setTrsTypes(DlsTrsTypes dlsTrsTypes) {
        this.trsTypes = dlsTrsTypes;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof DlsVhlTrsExams)) {
            return false;
        }
        return id != null && id.equals(((DlsVhlTrsExams) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "DlsVhlTrsExams{" +
            "id=" + getId() +
            ", code='" + getCode() + "'" +
            ", nameAr='" + getNameAr() + "'" +
            ", nameEn='" + getNameEn() + "'" +
            "}";
    }
}
