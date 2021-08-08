package com.Xoot.CreditoParaTi.entity;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "free_questionnaire")
public class FreeQuestionnaire {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "Id")
    private Integer IdFreeQuestionnaire;

    @Column(name = "answer_numer")
    private Integer answerNumer;

    @Column(name = "disease")
    private String disease;

    @Column(name = "date_disease")
    private Date date_disease;

    @Column(name = "duration")
    private String duration;

    @Column(name = "actual_conditions")
    private String actualConditions;

    @Column(name = "MedicalQuestionnaire_Id")
    private Integer IdMedicalQuestionnaire;

    @Column(name= "creadit_application_id")
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

    public Integer getIdFreeQuestionnaire() {
        return IdFreeQuestionnaire;
    }

    public void setIdFreeQuestionnaire(Integer IdFreeQuestionnaire) {
        this.IdFreeQuestionnaire = IdFreeQuestionnaire;
    }

    public Integer getAnswerNumer() {
        return answerNumer;
    }

    public void setAnswerNumer(Integer answerNumer) {
        this.answerNumer = answerNumer;
    }

    public String getDisease() {
        return disease;
    }

    public void setDisease(String disease) {
        this.disease = disease;
    }

    public Date getDate_disease() {
        return date_disease;
    }

    public void setDate_disease(Date date_disease) {
        this.date_disease = date_disease;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getActualConditions() {
        return actualConditions;
    }

    public void setActualConditions(String actualConditions) {
        this.actualConditions = actualConditions;
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

    public Integer getIdMedicalQuestionnaire() {
        return IdMedicalQuestionnaire;
    }

    public void setIdMedicalQuestionnaire(Integer idMedicalQuestionnaire) {
        this.IdMedicalQuestionnaire = idMedicalQuestionnaire;
    }
}
