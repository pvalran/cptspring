package com.Xoot.CreditoParaTi.models.dao.services;

import com.Xoot.CreditoParaTi.Definiciones.Services.IAnswerMedicalquestionnaireService;
import com.Xoot.CreditoParaTi.entity.AnswerQuestionnaire;
import com.Xoot.CreditoParaTi.entity.DTO.AnswerQuestionnaireDTO;
import com.Xoot.CreditoParaTi.entity.DTO.FreeQuestionnaireDTO;
import com.Xoot.CreditoParaTi.entity.DTO.MedicalQuestionnaireAnswerDTO;
import com.Xoot.CreditoParaTi.entity.DTO.ResponseDTO;
import com.Xoot.CreditoParaTi.entity.FreeQuestionnaire;
import com.Xoot.CreditoParaTi.entity.MedicalQuestionnaire;
import com.Xoot.CreditoParaTi.models.dao.IAnswerQuestionnaireDao;
import com.Xoot.CreditoParaTi.models.dao.IFreeQuestionnaireDao;
import com.Xoot.CreditoParaTi.models.dao.IMedicalQuestionnaireDao;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Collection;
import java.util.Iterator;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ListIterator;

@Service
public class AnswerMedicalquestionnaireImpl implements IAnswerMedicalquestionnaireService {
    @Autowired
    private IMedicalQuestionnaireDao medicalQuestionnaireDao;
    @Autowired
    private IAnswerQuestionnaireDao answerQuestionnaireDao;
    @Autowired
    private IFreeQuestionnaireDao freeQuestionnaireDao;



    @Autowired
    private ModelMapper modelMapper;

    @Override
    public MedicalQuestionnaire findById(Integer id) {
        return null;
    }

    @Override
    public List<MedicalQuestionnaire> findAllActive() {
        return null;
    }

    @Override
    @Transactional
    public ResponseDTO save(MedicalQuestionnaireAnswerDTO requestDTO) {
        MedicalQuestionnaireAnswerDTO MedicalresponseDTO;
        AnswerQuestionnaireDTO AnswerDTO;
        List<AnswerQuestionnaireDTO> LAnswerDTO;
        FreeQuestionnaireDTO FreeDTO;
        AnswerQuestionnaire answer;
        FreeQuestionnaire free;
        List<FreeQuestionnaireDTO> LFreeDTO;

        LAnswerDTO = new ArrayList<AnswerQuestionnaireDTO>(Arrays.asList());
        LFreeDTO = new ArrayList<FreeQuestionnaireDTO>(Arrays.asList());
        MedicalQuestionnaire medicalQuestionnaire = modelMapper.map(requestDTO, MedicalQuestionnaire.class);
        medicalQuestionnaire = medicalQuestionnaireDao.save(medicalQuestionnaire);
        if (medicalQuestionnaire != null) {
            MedicalresponseDTO = modelMapper.map(medicalQuestionnaire, MedicalQuestionnaireAnswerDTO.class);

            for (AnswerQuestionnaireDTO answerDTO : requestDTO.getanswerQuestionnairies()) {
                answerDTO.setCreaditApplication(MedicalresponseDTO.getCreaditApplication());
                answerDTO.setIdMedicalQuestionnaire(MedicalresponseDTO.getIdMedicalQuestionnaire());

                answer = modelMapper.map(answerDTO, AnswerQuestionnaire.class);
                answer = answerQuestionnaireDao.save(answer);
                AnswerDTO = modelMapper.map(answer, AnswerQuestionnaireDTO.class);
                LAnswerDTO.add(AnswerDTO);
            }
            MedicalresponseDTO.setAnswerQuestionnairies(LAnswerDTO);

            for (FreeQuestionnaireDTO freeDTO : requestDTO.getFreeQuestionnairies()) {
                freeDTO.setCreaditApplication(MedicalresponseDTO.getCreaditApplication());
                freeDTO.setIdMedicalQuestionnaire(MedicalresponseDTO.getIdMedicalQuestionnaire());

                free = modelMapper.map(freeDTO, FreeQuestionnaire.class);
                free = freeQuestionnaireDao.save(free);
                freeDTO = modelMapper.map(free, FreeQuestionnaireDTO.class);

                LFreeDTO.add(freeDTO);
            }
            MedicalresponseDTO.setFreeQuestionnairies(LFreeDTO);

            return new ResponseDTO(MedicalresponseDTO, "Registro creado", true);
        }
        return new ResponseDTO("", "Error en el guardado", true);
    }

    @Override
    public ResponseDTO update(Integer id, MedicalQuestionnaireAnswerDTO medicalQuestionnaireAnswerDTO) {
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
