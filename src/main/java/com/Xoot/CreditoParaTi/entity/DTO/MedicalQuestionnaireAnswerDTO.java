package com.Xoot.CreditoParaTi.entity.DTO;

import java.util.List;

public class MedicalQuestionnaireAnswerDTO {
    private Integer IdMedicalQuestionnaire;
    private Double weight;
    private Double height;
    private Integer creaditApplication;
    private List<AnswerQuestionnaireDTO> answerQuestionnairies;
    private List<FreeQuestionnaireDTO> freeQuestionnairies;

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

    public List<AnswerQuestionnaireDTO> getanswerQuestionnairies() {
        return answerQuestionnairies;
    }

    public void setAnswerQuestionnairies(List<AnswerQuestionnaireDTO> answerQuestionnairies) {
        this.answerQuestionnairies = answerQuestionnairies;
    }

    public List<FreeQuestionnaireDTO> getFreeQuestionnairies() {
        return freeQuestionnairies;
    }

    public void setFreeQuestionnairies(List<FreeQuestionnaireDTO> freeQuestionnairies) {
        this.freeQuestionnairies = freeQuestionnairies;
    }
}
