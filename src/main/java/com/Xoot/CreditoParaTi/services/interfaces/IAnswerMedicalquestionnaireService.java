package com.Xoot.CreditoParaTi.services.interfaces;

import com.Xoot.CreditoParaTi.dto.MedicalQuestionnaireAnswerDTO;
import com.Xoot.CreditoParaTi.dto.ResponseDTO;
import com.Xoot.CreditoParaTi.entity.app.MedicalQuestionnaire;

import java.util.List;

public interface IAnswerMedicalquestionnaireService {
    MedicalQuestionnaireAnswerDTO findById(Integer id);

    MedicalQuestionnaireAnswerDTO findByCreditID(Integer CreditID);

    List<MedicalQuestionnaire> findAllActive();

    ResponseDTO save(MedicalQuestionnaireAnswerDTO medicalQuestionnaireAnswerDTO);

    ResponseDTO update(Integer id, MedicalQuestionnaireAnswerDTO medicalQuestionnaireAnswerDTO);

    ResponseDTO active(Integer id);

    ResponseDTO delete(Integer id);

    ResponseDTO remove(Integer creditId);
}

