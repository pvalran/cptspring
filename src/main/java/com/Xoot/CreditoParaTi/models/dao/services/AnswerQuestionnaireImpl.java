package com.Xoot.CreditoParaTi.models.dao.services;

import com.Xoot.CreditoParaTi.Definiciones.Services.IAnswerQuestionnaireService;
import com.Xoot.CreditoParaTi.entity.DTO.AnswerQuestionnaireDTO;
import com.Xoot.CreditoParaTi.entity.DTO.ResponseDTO;
import com.Xoot.CreditoParaTi.entity.AnswerQuestionnaire;
import com.Xoot.CreditoParaTi.models.dao.IAnswerQuestionnaireDao;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class AnswerQuestionnaireImpl implements IAnswerQuestionnaireService {
    @Autowired
    private IAnswerQuestionnaireDao AnswerQuestionnaireDao;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    @Transactional(readOnly = true)
    public AnswerQuestionnaire findById(Integer id) { return AnswerQuestionnaireDao.findById(id).orElse(null); }

    @Override
    @Transactional(readOnly = true)
    public List<AnswerQuestionnaire> findAllActive() { return AnswerQuestionnaireDao.findAllActive(); }

    @Override
    @Transactional
    public ResponseDTO save(AnswerQuestionnaireDTO AnswerQuestionnaireDTO) {
        AnswerQuestionnaire AnswerQuestionnaire = AnswerQuestionnaireDao.save(modelMapper.map(AnswerQuestionnaireDTO,AnswerQuestionnaire.class));
        if (AnswerQuestionnaire != null) {
            return  new ResponseDTO(AnswerQuestionnaire,"Registro creado",true);
        }
        return  new ResponseDTO(AnswerQuestionnaire,"Error en registro",false);
    }

    @Override
    public ResponseDTO update(Integer id, AnswerQuestionnaireDTO AnswerQuestionnaireDTO) {
        return null;
    }

    @Override
    public ResponseDTO active(Integer id) {
        return null;
    }

    @Override
    public ResponseDTO delete(Integer id) {
        return null;
    }
}
