package com.Xoot.CreditoParaTi.services.interfaces;

import com.Xoot.CreditoParaTi.dto.MedicalQuestionnaireAnswerDTO;
import com.Xoot.CreditoParaTi.dto.ResponseDTO;
import com.Xoot.CreditoParaTi.entity.MedicalQuestionnaire;

import java.util.List;

public interface IAnswerMedicalquestionnaireService {
    ResponseDTO findById(Integer id);

    ResponseDTO findByCreditID(Integer CreditID);

    List<MedicalQuestionnaire> findAllActive();

    ResponseDTO save(MedicalQuestionnaireAnswerDTO medicalQuestionnaireAnswerDTO);

    ResponseDTO update(Integer id, MedicalQuestionnaireAnswerDTO medicalQuestionnaireAnswerDTO);

    ResponseDTO active(Integer id);

    ResponseDTO delete(Integer id);
}

