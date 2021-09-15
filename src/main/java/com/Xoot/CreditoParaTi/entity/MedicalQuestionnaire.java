package com.Xoot.CreditoParaTi.entity;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "medical_questionnaire")
public class MedicalQuestionnaire {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id")
    private Integer IdMedicalQuestionnaire;

    @Column(name = "weight")
    private Double weight;

    @Column(name = "height")
    private Double height;

    @Column(name= "number_request")
    private Integer creaditApplication;

    @Column(name = "status_flag")
    private Integer status_flag;

    @Column(name = "crtd_on")
    @Temporal(TemporalType.TIMESTAMP)
    private java.util.Date crtd_on;

    @Column(name = "crtd_by", length = 50)
    private String crtd_by;

    @Column(name = "mdfd_on")
    @Temporal(TemporalType.TIMESTAMP)
    private java.util.Date mdfd_on;

    @Column(name = "mdfd_by", length = 50)
    private String mdfd_by;

    @PrePersist
    public void prePersist() {
        crtd_on = new java.util.Date();
        mdfd_on = new java.util.Date();
        status_flag = 1;
    }

    public Integer getIdMedicalQuestionnaire() {
        return IdMedicalQuestionnaire;
    }

    public void setIdMedicalQuestionnaire(Integer idMedicalQuestionnaire) {
        IdMedicalQuestionnaire = idMedicalQuestionnaire;
    }

    public Double getWeight() {
        return weight;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }

    public Double getHeight() {
        return height;
    }

    public void setHeight(Double height) {
        this.height = height;
    }

    public Integer getCreaditApplication() {
        return creaditApplication;
    }

    public void setCreaditApplication(Integer creaditApplication) {
        this.creaditApplication = creaditApplication;
    }

    public Integer getStatus_flag() {
        return status_flag;
    }

    public void setStatus_flag(Integer status_flag) {
        this.status_flag = status_flag;
    }

    public Date getCrtd_on() {
        return crtd_on;
    }

    public void setCrtd_on(Date crtd_on) {
        this.crtd_on = crtd_on;
    }

    public String getCrtd_by() {
        return crtd_by;
    }

    public void setCrtd_by(String crtd_by) {
        this.crtd_by = crtd_by;
    }

    public Date getMdfd_on() {
        return mdfd_on;
    }

    public void setMdfd_on(Date mdfd_on) {
        this.mdfd_on = mdfd_on;
    }

    public String getMdfd_by() {
        return mdfd_by;
    }

    public void setMdfd_by(String mdfd_by) {
        this.mdfd_by = mdfd_by;
    }
}
