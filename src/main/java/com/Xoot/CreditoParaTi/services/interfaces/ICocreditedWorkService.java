package com.Xoot.CreditoParaTi.services.interfaces;

import com.Xoot.CreditoParaTi.dto.CocreditedWorkDTO;
import com.Xoot.CreditoParaTi.dto.ResponseDTO;
import com.Xoot.CreditoParaTi.entity.app.CocreditedWork;

import java.util.List;

public interface ICocreditedWorkService {
    CocreditedWork findById(Integer id);

    List<CocreditedWork> findAllActive();

    ResponseDTO save(CocreditedWorkDTO answerQuestionnaireDTO);

    ResponseDTO update(Integer id, CocreditedWorkDTO answerQuestionnaireDTO);

    ResponseDTO active(Integer id);

    ResponseDTO delete(Integer id);
}
