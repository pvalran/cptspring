package com.Xoot.CreditoParaTi.Definiciones.Services;

import com.Xoot.CreditoParaTi.entity.MedicalQuestionnaire;
import com.Xoot.CreditoParaTi.entity.DTO.MedicalQuestionnaireDTO;
import com.Xoot.CreditoParaTi.entity.DTO.ResponseDTO;

import java.util.List;

public interface IMedicalQuestionnaireService {
    MedicalQuestionnaire findById(Integer id);

    List<MedicalQuestionnaire> findAllActive();

    ResponseDTO save(MedicalQuestionnaireDTO medicalQuestionnaireDTO);

    ResponseDTO update(Integer id, MedicalQuestionnaireDTO medicalQuestionnaireDTO);

    ResponseDTO active(Integer id);

    ResponseDTO delete(Integer id);
}
