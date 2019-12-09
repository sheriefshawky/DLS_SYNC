package com.isoft.dls.sync.domain;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

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

    @OneToMany(mappedBy = "dlsVhlTrsExams")
    private Set<DlsExams> exams = new HashSet<>();

    @OneToMany(mappedBy = "dlsVhlTrsExams")
    private Set<DlsVehicleTypes> vehicleTypes = new HashSet<>();

    @OneToMany(mappedBy = "dlsVhlTrsExams")
    private Set<DlsTrsTypes> trsTypes = new HashSet<>();

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

    public Set<DlsExams> getExams() {
        return exams;
    }

    public DlsVhlTrsExams exams(Set<DlsExams> dlsExams) {
        this.exams = dlsExams;
        return this;
    }

    public DlsVhlTrsExams addExams(DlsExams dlsExams) {
        this.exams.add(dlsExams);
        dlsExams.setDlsVhlTrsExams(this);
        return this;
    }

    public DlsVhlTrsExams removeExams(DlsExams dlsExams) {
        this.exams.remove(dlsExams);
        dlsExams.setDlsVhlTrsExams(null);
        return this;
    }

    public void setExams(Set<DlsExams> dlsExams) {
        this.exams = dlsExams;
    }

    public Set<DlsVehicleTypes> getVehicleTypes() {
        return vehicleTypes;
    }

    public DlsVhlTrsExams vehicleTypes(Set<DlsVehicleTypes> dlsVehicleTypes) {
        this.vehicleTypes = dlsVehicleTypes;
        return this;
    }

    public DlsVhlTrsExams addVehicleTypes(DlsVehicleTypes dlsVehicleTypes) {
        this.vehicleTypes.add(dlsVehicleTypes);
        dlsVehicleTypes.setDlsVhlTrsExams(this);
        return this;
    }

    public DlsVhlTrsExams removeVehicleTypes(DlsVehicleTypes dlsVehicleTypes) {
        this.vehicleTypes.remove(dlsVehicleTypes);
        dlsVehicleTypes.setDlsVhlTrsExams(null);
        return this;
    }

    public void setVehicleTypes(Set<DlsVehicleTypes> dlsVehicleTypes) {
        this.vehicleTypes = dlsVehicleTypes;
    }

    public Set<DlsTrsTypes> getTrsTypes() {
        return trsTypes;
    }

    public DlsVhlTrsExams trsTypes(Set<DlsTrsTypes> dlsTrsTypes) {
        this.trsTypes = dlsTrsTypes;
        return this;
    }

    public DlsVhlTrsExams addTrsTypes(DlsTrsTypes dlsTrsTypes) {
        this.trsTypes.add(dlsTrsTypes);
        dlsTrsTypes.setDlsVhlTrsExams(this);
        return this;
    }

    public DlsVhlTrsExams removeTrsTypes(DlsTrsTypes dlsTrsTypes) {
        this.trsTypes.remove(dlsTrsTypes);
        dlsTrsTypes.setDlsVhlTrsExams(null);
        return this;
    }

    public void setTrsTypes(Set<DlsTrsTypes> dlsTrsTypes) {
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
