package com.isoft.dls.sync.domain;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * A Template.
 */
@Entity
@Table(name = "template")
public class Template implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @NotNull
    @Column(name = "name_ar", nullable = false)
    private String nameAr;

    @NotNull
    @Column(name = "name_en", nullable = false)
    private String nameEn;

    @NotNull
    @Column(name = "code", nullable = false)
    private String code;

    @Column(name = "time_in_sec")
    private Long timeInSec;

    @Column(name = "pass_score")
    private Double passScore;

    @Column(name = "status")
    private Integer status;

    @OneToMany(mappedBy = "template")
    private Set<TemplateCategories> categories = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNameAr() {
        return nameAr;
    }

    public Template nameAr(String nameAr) {
        this.nameAr = nameAr;
        return this;
    }

    public void setNameAr(String nameAr) {
        this.nameAr = nameAr;
    }

    public String getNameEn() {
        return nameEn;
    }

    public Template nameEn(String nameEn) {
        this.nameEn = nameEn;
        return this;
    }

    public void setNameEn(String nameEn) {
        this.nameEn = nameEn;
    }

    public String getCode() {
        return code;
    }

    public Template code(String code) {
        this.code = code;
        return this;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Long getTimeInSec() {
        return timeInSec;
    }

    public Template timeInSec(Long timeInSec) {
        this.timeInSec = timeInSec;
        return this;
    }

    public void setTimeInSec(Long timeInSec) {
        this.timeInSec = timeInSec;
    }

    public Double getPassScore() {
        return passScore;
    }

    public Template passScore(Double passScore) {
        this.passScore = passScore;
        return this;
    }

    public void setPassScore(Double passScore) {
        this.passScore = passScore;
    }

    public Integer getStatus() {
        return status;
    }

    public Template status(Integer status) {
        this.status = status;
        return this;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Set<TemplateCategories> getCategories() {
        return categories;
    }

    public Template categories(Set<TemplateCategories> templateCategories) {
        this.categories = templateCategories;
        return this;
    }

    public Template addCategories(TemplateCategories templateCategories) {
        this.categories.add(templateCategories);
        templateCategories.setTemplate(this);
        return this;
    }

    public Template removeCategories(TemplateCategories templateCategories) {
        this.categories.remove(templateCategories);
        templateCategories.setTemplate(null);
        return this;
    }

    public void setCategories(Set<TemplateCategories> templateCategories) {
        this.categories = templateCategories;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Template)) {
            return false;
        }
        return id != null && id.equals(((Template) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "Template{" +
            "id=" + getId() +
            ", nameAr='" + getNameAr() + "'" +
            ", nameEn='" + getNameEn() + "'" +
            ", code='" + getCode() + "'" +
            ", timeInSec=" + getTimeInSec() +
            ", passScore=" + getPassScore() +
            ", status=" + getStatus() +
            "}";
    }
}
