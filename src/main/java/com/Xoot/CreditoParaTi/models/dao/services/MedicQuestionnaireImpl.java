package com.Xoot.CreditoParaTi.models.dao.services;

import com.Xoot.CreditoParaTi.Definiciones.Services.IMedicalQuestionnaireService;
import com.Xoot.CreditoParaTi.entity.MedicalQuestionnaire;
import com.Xoot.CreditoParaTi.entity.DTO.MedicalQuestionnaireDTO;
import com.Xoot.CreditoParaTi.entity.DTO.ResponseDTO;
import com.Xoot.CreditoParaTi.models.dao.IMedicalQuestionnaireDao;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class MedicQuestionnaireImpl implements IMedicalQuestionnaireService {
    @Autowired
    private IMedicalQuestionnaireDao MedicalQuestionnaireDao;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    @Transactional(readOnly = true)
    public MedicalQuestionnaire findById(Integer id) { return MedicalQuestionnaireDao.findById(id).orElse(null); }

    @Override
    @Transactional(readOnly = true)
    public List<MedicalQuestionnaire> findAllActive() { return MedicalQuestionnaireDao.findAllActive(); }

    @Override
    @Transactional
    public ResponseDTO save(MedicalQuestionnaireDTO MedicalQuestionnaireDTO) {
        MedicalQuestionnaire MedicalQuestionnaire = MedicalQuestionnaireDao.save(modelMapper.map(MedicalQuestionnaireDTO,MedicalQuestionnaire.class));
        if (MedicalQuestionnaire != null) {
            return  new ResponseDTO(MedicalQuestionnaire,"Registro creado",true);
        }
        return  new ResponseDTO(MedicalQuestionnaire,"Error en registro",false);
    }

    @Override
    public ResponseDTO update(Integer id, MedicalQuestionnaireDTO MedicalQuestionnaireDTO) {
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
