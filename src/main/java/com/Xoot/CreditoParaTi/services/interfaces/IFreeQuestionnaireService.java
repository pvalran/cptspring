package com.Xoot.CreditoParaTi.services.interfaces;

import com.Xoot.CreditoParaTi.entity.app.FreeQuestionnaire;
import com.Xoot.CreditoParaTi.dto.FreeQuestionnaireDTO;
import com.Xoot.CreditoParaTi.dto.ResponseDTO;

import java.util.List;

public interface IFreeQuestionnaireService {
    FreeQuestionnaire findById(Integer id);

    List<FreeQuestionnaire> findAllActive();

    ResponseDTO save(FreeQuestionnaireDTO freeQuestionnaireDTO);

    ResponseDTO update(Integer id, FreeQuestionnaireDTO freeQuestionnaireDTO);

    ResponseDTO active(Integer id);

    ResponseDTO delete(Integer id);
}
