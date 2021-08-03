package com.Xoot.CreditoParaTi.models.dao.services;

import com.Xoot.CreditoParaTi.Definiciones.Services.IAnswerMedicalquestionnaireService;
import com.Xoot.CreditoParaTi.Definiciones.Services.IAnswerQuestionnaireService;
import com.Xoot.CreditoParaTi.Definiciones.Services.IFreeQuestionnaireService;
import com.Xoot.CreditoParaTi.Definiciones.Services.IMedicalQuestionnaireService;
import com.Xoot.CreditoParaTi.entity.AnswerQuestionnaire;
import com.Xoot.CreditoParaTi.entity.DTO.*;
import com.Xoot.CreditoParaTi.entity.FreeQuestionnaire;
import com.Xoot.CreditoParaTi.entity.MedicalQuestionnaire;
import com.Xoot.CreditoParaTi.models.dao.IAnswerQuestionnaireDao;
import com.Xoot.CreditoParaTi.models.dao.IFreeQuestionnaireDao;
import com.Xoot.CreditoParaTi.models.dao.IMedicalQuestionnaireDao;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.lang.reflect.Type;
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
    private IMedicalQuestionnaireService medicalQuestionnaireService;
    @Autowired
    private IAnswerQuestionnaireService answerQuestionnaireService;
    @Autowired
    private IFreeQuestionnaireService freeQuestionnaireService;




    @Autowired
    private ModelMapper modelMapper;

    @Override
    public ResponseDTO findById(Integer id) {
        MedicalQuestionnaire medicalQuestionn = medicalQuestionnaireDao.findById(id).orElse(null);
        if (medicalQuestionn != null) {
            return findByCreditID(medicalQuestionn.getCreditApplication());
        } else {
            return new ResponseDTO("", "Error en el sistema", false);
        }
    }

    @Override
    public ResponseDTO findByCreditID(Integer creditID) {
        Type lstTypeAnswer;
        Type lstTypeFree;

        MedicalQuestionnaireAnswerDTO medicalAnswerDTO = new MedicalQuestionnaireAnswerDTO();
        MedicalQuestionnaire medicalQuestionn = medicalQuestionnaireDao.findByCreditID(creditID);

        if (medicalQuestionn != null) {
            medicalAnswerDTO.setIdMedicalQuestionnaire(medicalQuestionn.getIdMedicalQuestionnaire());
            medicalAnswerDTO.setWeight(medicalQuestionn.getWeight());
            medicalAnswerDTO.setHeight(medicalQuestionn.getHeight());
            medicalAnswerDTO.setCreditApplication(medicalQuestionn.getCreditApplication());

            lstTypeAnswer = new TypeToken<List<AnswerQuestionnaireDTO>>() {
            }.getType();
            lstTypeFree = new TypeToken<List<FreeQuestionnaireDTO>>() {
            }.getType();

            List<AnswerQuestionnaire> lstAnswer = answerQuestionnaireDao.findByMedicalQuestionnaire(medicalQuestionn.getIdMedicalQuestionnaire());
            List<FreeQuestionnaire> lstFree = freeQuestionnaireDao.findByMedicalQuestionnaire(medicalQuestionn.getIdMedicalQuestionnaire());

            medicalAnswerDTO.setAnswerQuestionnairies(modelMapper.map(lstAnswer, lstTypeAnswer));
            medicalAnswerDTO.setFreeQuestionnairies(modelMapper.map(lstFree, lstTypeFree));

            return new ResponseDTO(medicalAnswerDTO, "Información del cuestionario medico", true);
        } else {
            return new ResponseDTO("", "Error en el sistema", false);
        }
    }



    @Override
    public List<MedicalQuestionnaire> findAllActive() { return null; }

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
                answerDTO.setCreaditApplication(MedicalresponseDTO.getCreditApplication());
                answerDTO.setIdMedicalQuestionnaire(MedicalresponseDTO.getIdMedicalQuestionnaire());

                answer = modelMapper.map(answerDTO, AnswerQuestionnaire.class);
                answer = answerQuestionnaireDao.save(answer);
                AnswerDTO = modelMapper.map(answer, AnswerQuestionnaireDTO.class);
                LAnswerDTO.add(AnswerDTO);
            }
            MedicalresponseDTO.setAnswerQuestionnairies(LAnswerDTO);

            for (FreeQuestionnaireDTO freeDTO : requestDTO.getFreeQuestionnairies()) {
                freeDTO.setCreaditApplication(MedicalresponseDTO.getCreditApplication());
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
    public ResponseDTO update(Integer id, MedicalQuestionnaireAnswerDTO requestDTO) {
        AnswerQuestionnaireDTO AnswerDTO;
        List<AnswerQuestionnaireDTO> LAnswerDTO;
        FreeQuestionnaireDTO FreeDTO;
        AnswerQuestionnaire answer;
        FreeQuestionnaire free;
        List<FreeQuestionnaireDTO> LFreeDTO;

        LAnswerDTO = new ArrayList<AnswerQuestionnaireDTO>(Arrays.asList());
        LFreeDTO = new ArrayList<FreeQuestionnaireDTO>(Arrays.asList());

        ResponseDTO responseDTO;

        MedicalQuestionnaireDTO medicalDTO;

        medicalDTO = modelMapper.map(requestDTO,MedicalQuestionnaireDTO.class);
        responseDTO = medicalQuestionnaireService.update(id,medicalDTO);

        if (responseDTO.getResult() == true) {
            medicalDTO = modelMapper.map(responseDTO.getData(),MedicalQuestionnaireDTO.class);
            for (AnswerQuestionnaireDTO answerDTO : requestDTO.getanswerQuestionnairies()) {
                answerDTO.setCreaditApplication(medicalDTO.getCreaditApplication());
                answerDTO.setIdMedicalQuestionnaire(medicalDTO.getIdMedicalQuestionnaire());
                answer = modelMapper.map(answerDTO, AnswerQuestionnaire.class);
                answer = answerQuestionnaireDao.save(answer);
                AnswerDTO = modelMapper.map(answer, AnswerQuestionnaireDTO.class);
                LAnswerDTO.add(AnswerDTO);
            }
            requestDTO.setAnswerQuestionnairies(LAnswerDTO);

            for (FreeQuestionnaireDTO freeDTO : requestDTO.getFreeQuestionnairies()) {
                freeDTO.setCreaditApplication(medicalDTO.getCreaditApplication());
                freeDTO.setIdMedicalQuestionnaire(medicalDTO.getIdMedicalQuestionnaire());

                free = modelMapper.map(freeDTO, FreeQuestionnaire.class);
                free = freeQuestionnaireDao.save(free);
                freeDTO = modelMapper.map(free, FreeQuestionnaireDTO.class);

                LFreeDTO.add(freeDTO);
            }
            requestDTO.setFreeQuestionnairies(LFreeDTO);
            return new ResponseDTO(requestDTO, "Actualización realizada", true);
        }
        return new ResponseDTO(requestDTO, "Error en el Actualizaciòn del registro", true);
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
