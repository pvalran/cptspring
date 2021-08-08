package com.Xoot.CreditoParaTi.services.interfaces;

import com.Xoot.CreditoParaTi.entity.AnswerQuestionnaire;
import com.Xoot.CreditoParaTi.dto.AnswerQuestionnaireDTO;
import com.Xoot.CreditoParaTi.dto.ResponseDTO;

import java.util.List;

public interface IAnswerQuestionnaireService {
    AnswerQuestionnaire findById(Integer id);

    List<AnswerQuestionnaire> findAllActive();

    ResponseDTO save(AnswerQuestionnaireDTO answerQuestionnaireDTO);

    ResponseDTO update(Integer id, AnswerQuestionnaireDTO answerQuestionnaireDTO);

    ResponseDTO active(Integer id);

    ResponseDTO delete(Integer id);
}
