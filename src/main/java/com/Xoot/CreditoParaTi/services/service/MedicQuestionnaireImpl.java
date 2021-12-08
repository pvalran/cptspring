package com.Xoot.CreditoParaTi.services.service;

import com.Xoot.CreditoParaTi.services.interfaces.IMedicalQuestionnaireService;
import com.Xoot.CreditoParaTi.entity.app.MedicalQuestionnaire;
import com.Xoot.CreditoParaTi.dto.MedicalQuestionnaireDTO;
import com.Xoot.CreditoParaTi.dto.ResponseDTO;
import com.Xoot.CreditoParaTi.repositories.app.IMedicalQuestionnaireDao;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
public class MedicQuestionnaireImpl implements IMedicalQuestionnaireService {
    @Autowired
    private IMedicalQuestionnaireDao medicalQuestionnaireDao;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    @Transactional(readOnly = true)
    public MedicalQuestionnaire findById(Integer id) { return medicalQuestionnaireDao.findById(id).orElse(null); }

    @Override
    @Transactional(readOnly = true)
    public List<MedicalQuestionnaire> findAllActive() { return medicalQuestionnaireDao.findAllActive(); }

    @Override
    @Transactional
    public ResponseDTO save(MedicalQuestionnaireDTO MedicalQuestionnaireDTO) {
        MedicalQuestionnaire MedicalQuestionnaire = medicalQuestionnaireDao.save(modelMapper.map(MedicalQuestionnaireDTO,MedicalQuestionnaire.class));
        if (MedicalQuestionnaire != null) {
            return  new ResponseDTO(MedicalQuestionnaire,"Registro creado",true);
        }
        return  new ResponseDTO(MedicalQuestionnaire,"Error en registro",false);
    }

    @Override
    public ResponseDTO update(Integer id, MedicalQuestionnaireDTO ObjDTO) {
        MedicalQuestionnaire Entity = medicalQuestionnaireDao.findById(id).orElse(null);
        if (Entity != null) {
            Entity.setMdfd_on(new Date());
            modelMapper.getConfiguration().setSkipNullEnabled(true)
                    .setCollectionsMergeEnabled(false)
                    .setMatchingStrategy(MatchingStrategies.STRICT);
            modelMapper.map(ObjDTO,Entity);
            medicalQuestionnaireDao.save(Entity);
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
