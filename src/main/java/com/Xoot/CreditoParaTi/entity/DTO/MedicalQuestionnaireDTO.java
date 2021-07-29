package com.Xoot.CreditoParaTi.entity.DTO;

public class MedicalQuestionnaireDTO {
    private Integer IdMedicalQuestionnaire;
    private Double weight;
    private Double height;
    private Integer creaditApplication;

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
}
