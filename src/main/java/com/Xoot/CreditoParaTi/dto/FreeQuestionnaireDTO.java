package com.Xoot.CreditoParaTi.dto;

import java.util.Date;

public class FreeQuestionnaireDTO {
    private Integer IdFreeQuestionnaire;
    private Integer answerNumer;
    private String disease;
    private Date date_disease;
    private String duration;
    private String actualConditions;
    private Integer IdMedicalQuestionnaire;
    private Integer creaditApplication;

    public Integer getIdFreeQuestionnaire() {
        return IdFreeQuestionnaire;
    }

    public void setIdFreeQuestionnaire(Integer idFreeQuestionnaire) {
        IdFreeQuestionnaire = idFreeQuestionnaire;
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

    public Integer getIdMedicalQuestionnaire() {
        return IdMedicalQuestionnaire;
    }

    public void setIdMedicalQuestionnaire(Integer idMedicalQuestionnaire) {
        this.IdMedicalQuestionnaire = idMedicalQuestionnaire;
    }

    public Integer getCreaditApplication() {
        return creaditApplication;
    }

    public void setCreaditApplication(Integer creaditApplication) {
        this.creaditApplication = creaditApplication;
    }
}
