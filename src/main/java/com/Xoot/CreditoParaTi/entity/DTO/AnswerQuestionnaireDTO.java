package com.Xoot.CreditoParaTi.entity.DTO;

public class AnswerQuestionnaireDTO {
    private Integer IdAnswer;
    private Integer answerNumer;
    private Integer answer;
    private Integer IdMedicalQuestionnaire;
    private Integer creaditApplication;

    public Integer getIdAnswer() {
        return IdAnswer;
    }

    public void setIdAnswer(Integer idAnswer) {
        IdAnswer = idAnswer;
    }

    public Integer getAnswerNumer() {
        return answerNumer;
    }

    public void setAnswerNumer(Integer answerNumer) {
        this.answerNumer = answerNumer;
    }

    public Integer getAnswer() {
        return answer;
    }

    public void setAnswer(Integer answer) {
        this.answer = answer;
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
