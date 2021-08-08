package com.Xoot.CreditoParaTi.services.service;

import com.Xoot.CreditoParaTi.services.interfaces.IAnswerQuestionnaireService;
import com.Xoot.CreditoParaTi.dto.AnswerQuestionnaireDTO;
import com.Xoot.CreditoParaTi.dto.ResponseDTO;
import com.Xoot.CreditoParaTi.entity.AnswerQuestionnaire;
import com.Xoot.CreditoParaTi.repositories.interfaces.IAnswerQuestionnaireDao;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
public class AnswerQuestionnaireImpl implements IAnswerQuestionnaireService {
    @Autowired
    private IAnswerQuestionnaireDao answerQuestionnaireDao;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    @Transactional(readOnly = true)
    public AnswerQuestionnaire findById(Integer id) { return answerQuestionnaireDao.findById(id).orElse(null); }

    @Override
    @Transactional(readOnly = true)
    public List<AnswerQuestionnaire> findAllActive() { return answerQuestionnaireDao.findAllActive(); }

    @Override
    @Transactional
    public ResponseDTO save(AnswerQuestionnaireDTO AnswerQuestionnaireDTO) {
        AnswerQuestionnaire AnswerQuestionnaire = answerQuestionnaireDao.save(modelMapper.map(AnswerQuestionnaireDTO,AnswerQuestionnaire.class));
        if (AnswerQuestionnaire != null) {
            return  new ResponseDTO(AnswerQuestionnaire,"Registro creado",true);
        }
        return  new ResponseDTO(AnswerQuestionnaire,"Error en registro",false);
    }

    @Override
    public ResponseDTO update(Integer id, AnswerQuestionnaireDTO ObjDTO) {
        AnswerQuestionnaire Entity = answerQuestionnaireDao.findById(id).orElse(null);
        if (Entity != null) {
            Entity.setMdfd_on(new Date());
            modelMapper.getConfiguration().setSkipNullEnabled(true)
                    .setCollectionsMergeEnabled(false)
                    .setMatchingStrategy(MatchingStrategies.STRICT);
            modelMapper.map(ObjDTO,Entity);
            answerQuestionnaireDao.save(Entity);
            return new ResponseDTO(ObjDTO,"Modificaciòn realizada con exito",true);
        }
        return new ResponseDTO(ObjDTO,"Error en la modificación del registro",false);
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
