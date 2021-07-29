package com.Xoot.CreditoParaTi.Definiciones.Services;

import com.Xoot.CreditoParaTi.entity.DTO.MedicalQuestionnaireAnswerDTO;
import com.Xoot.CreditoParaTi.entity.DTO.ResponseDTO;
import com.Xoot.CreditoParaTi.entity.MedicalQuestionnaire;

import java.util.List;

public interface IAnswerMedicalquestionnaireService {
    MedicalQuestionnaire findById(Integer id);

    List<MedicalQuestionnaire> findAllActive();

    ResponseDTO save(MedicalQuestionnaireAnswerDTO medicalQuestionnaireAnswerDTO);

    ResponseDTO update(Integer id, MedicalQuestionnaireAnswerDTO medicalQuestionnaireAnswerDTO);

    ResponseDTO active(Integer id);

    ResponseDTO delete(Integer id);
}

