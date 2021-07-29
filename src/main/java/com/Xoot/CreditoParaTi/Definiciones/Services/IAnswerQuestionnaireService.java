package com.Xoot.CreditoParaTi.Definiciones.Services;

import com.Xoot.CreditoParaTi.entity.AnswerQuestionnaire;
import com.Xoot.CreditoParaTi.entity.DTO.AnswerQuestionnaireDTO;
import com.Xoot.CreditoParaTi.entity.DTO.ResponseDTO;

import java.util.List;

public interface IAnswerQuestionnaireService {
    AnswerQuestionnaire findById(Integer id);

    List<AnswerQuestionnaire> findAllActive();

    ResponseDTO save(AnswerQuestionnaireDTO answerQuestionnaireDTO);

    ResponseDTO update(Integer id, AnswerQuestionnaireDTO answerQuestionnaireDTO);

    ResponseDTO active(Integer id);

    ResponseDTO delete(Integer id);
}
