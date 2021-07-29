package com.Xoot.CreditoParaTi.Definiciones.Services;

import com.Xoot.CreditoParaTi.entity.FreeQuestionnaire;
import com.Xoot.CreditoParaTi.entity.DTO.FreeQuestionnaireDTO;
import com.Xoot.CreditoParaTi.entity.DTO.ResponseDTO;

import java.util.List;

public interface IFreeQuestionnaireService {
    FreeQuestionnaire findById(Integer id);

    List<FreeQuestionnaire> findAllActive();

    ResponseDTO save(FreeQuestionnaireDTO freeQuestionnaireDTO);

    ResponseDTO update(Integer id, FreeQuestionnaireDTO freeQuestionnaireDTO);

    ResponseDTO active(Integer id);

    ResponseDTO delete(Integer id);
}
