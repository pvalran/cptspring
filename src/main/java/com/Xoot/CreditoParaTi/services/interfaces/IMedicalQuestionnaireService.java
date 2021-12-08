package com.Xoot.CreditoParaTi.services.interfaces;

import com.Xoot.CreditoParaTi.entity.app.MedicalQuestionnaire;
import com.Xoot.CreditoParaTi.dto.MedicalQuestionnaireDTO;
import com.Xoot.CreditoParaTi.dto.ResponseDTO;

import java.util.List;

public interface IMedicalQuestionnaireService {
    MedicalQuestionnaire findById(Integer id);

    List<MedicalQuestionnaire> findAllActive();

    ResponseDTO save(MedicalQuestionnaireDTO medicalQuestionnaireDTO);

    ResponseDTO update(Integer id, MedicalQuestionnaireDTO medicalQuestionnaireDTO);

    ResponseDTO active(Integer id);

    ResponseDTO delete(Integer id);
}
