package com.Xoot.CreditoParaTi.models.dao.services;

import com.Xoot.CreditoParaTi.Definiciones.Services.IFreeQuestionnaireService;
import com.Xoot.CreditoParaTi.entity.FreeQuestionnaire;
import com.Xoot.CreditoParaTi.entity.DTO.FreeQuestionnaireDTO;
import com.Xoot.CreditoParaTi.entity.DTO.ResponseDTO;
import com.Xoot.CreditoParaTi.models.dao.IFreeQuestionnaireDao;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class FreeQuestionnaireImpl implements IFreeQuestionnaireService {
    @Autowired
    private IFreeQuestionnaireDao freeQuestionnaireDao;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    @Transactional(readOnly = true)
    public FreeQuestionnaire findById(Integer id) { return freeQuestionnaireDao.findById(id).orElse(null); }

    @Override
    @Transactional(readOnly = true)
    public List<FreeQuestionnaire> findAllActive() { return freeQuestionnaireDao.findAllActive(); }

    @Override
    @Transactional
    public ResponseDTO save(FreeQuestionnaireDTO FreeQuestionnaireDTO) {
        FreeQuestionnaire FreeQuestionnaire = freeQuestionnaireDao.save(modelMapper.map(FreeQuestionnaireDTO,FreeQuestionnaire.class));
        if (FreeQuestionnaire != null) {
            return  new ResponseDTO(FreeQuestionnaire,"Registro creado",true);
        }
        return  new ResponseDTO(FreeQuestionnaire,"Error en registro",false);
    }

    @Override
    public ResponseDTO update(Integer id, FreeQuestionnaireDTO FreeQuestionnaireDTO) {
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