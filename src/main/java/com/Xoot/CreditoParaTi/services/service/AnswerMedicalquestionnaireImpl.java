package com.Xoot.CreditoParaTi.services.service;

import com.Xoot.CreditoParaTi.entity.EconomicDependents;
import com.Xoot.CreditoParaTi.services.interfaces.IAnswerMedicalquestionnaireService;
import com.Xoot.CreditoParaTi.services.interfaces.IAnswerQuestionnaireService;
import com.Xoot.CreditoParaTi.services.interfaces.IFreeQuestionnaireService;
import com.Xoot.CreditoParaTi.services.interfaces.IMedicalQuestionnaireService;
import com.Xoot.CreditoParaTi.dto.*;
import com.Xoot.CreditoParaTi.entity.AnswerQuestionnaire;
import com.Xoot.CreditoParaTi.entity.FreeQuestionnaire;
import com.Xoot.CreditoParaTi.entity.MedicalQuestionnaire;
import com.Xoot.CreditoParaTi.repositories.interfaces.IAnswerQuestionnaireDao;
import com.Xoot.CreditoParaTi.repositories.interfaces.IFreeQuestionnaireDao;
import com.Xoot.CreditoParaTi.repositories.interfaces.IMedicalQuestionnaireDao;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

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
    public MedicalQuestionnaireAnswerDTO findById(Integer id) {
        MedicalQuestionnaire medicalQuestionn = medicalQuestionnaireDao.findById(id).orElse(null);
        if (medicalQuestionn != null) {
            return findByCreditID(medicalQuestionn.getCreaditApplication());
        } else {
            return null;
        }
    }

    @Override
    public MedicalQuestionnaireAnswerDTO findByCreditID(Integer creditID) {
        Type lstTypeAnswer;
        Type lstTypeFree;
        EntityManager em= emf.createEntityManager();
        em.clear();


        MedicalQuestionnaireAnswerDTO medicalAnswerDTO = new MedicalQuestionnaireAnswerDTO();

        Query qryMedicalQuestionnaire = em.createNativeQuery("select * from medical_questionnaire where number_request = :creditID limit 1", MedicalQuestionnaire.class)
                .setParameter("creditID",creditID);
        MedicalQuestionnaire medicalQuestionn = (MedicalQuestionnaire) qryMedicalQuestionnaire.getResultStream()
                .findFirst().orElse(null);

        if (medicalQuestionn != null) {
            medicalAnswerDTO.setIdMedicalQuestionnaire(medicalQuestionn.getIdMedicalQuestionnaire());
            medicalAnswerDTO.setWeight(medicalQuestionn.getWeight());
            medicalAnswerDTO.setHeight(medicalQuestionn.getHeight());
            medicalAnswerDTO.setCreaditApplication(medicalQuestionn.getCreaditApplication());

            lstTypeAnswer = new TypeToken<List<AnswerQuestionnaireDTO>>() {
            }.getType();
            lstTypeFree = new TypeToken<List<FreeQuestionnaireDTO>>() {
            }.getType();


            Query qryAnswerQuestionnaire = em.createNativeQuery("select * from answer_questionnaire where medical_questionnaire_id = :medicalId order by answer_numer", AnswerQuestionnaire.class)
                    .setParameter("medicalId",medicalQuestionn.getIdMedicalQuestionnaire());
            List<AnswerQuestionnaire> lstAnswer = qryAnswerQuestionnaire.getResultList();

            Query qryFreeQuestionnaire = em.createNativeQuery("select * from free_questionnaire where medical_questionnaire_id = :medicalId order by answer_numer", FreeQuestionnaire.class)
                    .setParameter("medicalId",medicalQuestionn.getIdMedicalQuestionnaire());
            List<FreeQuestionnaire> lstFree = qryFreeQuestionnaire.getResultList();

            medicalAnswerDTO.setAnswerQuestionnairies(modelMapper.map(lstAnswer, lstTypeAnswer));
            medicalAnswerDTO.setFreeQuestionnairies(modelMapper.map(lstFree, lstTypeFree));

            return medicalAnswerDTO;
        } else {
            return null;
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

        MedicalQuestionnaire entity = medicalQuestionnaireDao.findById(id).orElse(null);

        if (entity != null) {
            entity.setMdfd_on(new Date());
            modelMapper.getConfiguration().setSkipNullEnabled(true)
                    .setCollectionsMergeEnabled(false)
                    .setMatchingStrategy(MatchingStrategies.STRICT);
            modelMapper.map(requestDTO,entity);
            medicalQuestionnaireDao.save(entity);
            medicalDTO = modelMapper.map(entity, MedicalQuestionnaireDTO.class);
            requestDTO.setCreaditApplication(medicalDTO.getCreaditApplication());
            requestDTO.setIdMedicalQuestionnaire(medicalDTO.getIdMedicalQuestionnaire());

            for (AnswerQuestionnaireDTO answerDTO : requestDTO.getanswerQuestionnairies()) {
                answer = answerQuestionnaireDao.findByMedicalQuestionnaireAnswer(
                        medicalDTO.getIdMedicalQuestionnaire(),
                        answerDTO.getAnswerNumer()
                );

                answer.setMdfd_on(new Date());
                modelMapper.getConfiguration().setSkipNullEnabled(true)
                        .setCollectionsMergeEnabled(false)
                        .setMatchingStrategy(MatchingStrategies.STRICT);
                modelMapper.map(answerDTO,answer);
                answerQuestionnaireDao.save(answer);
                AnswerDTO = modelMapper.map(answer, AnswerQuestionnaireDTO.class);
                AnswerDTO.setCreaditApplication(medicalDTO.getCreaditApplication());
                AnswerDTO.setIdMedicalQuestionnaire(medicalDTO.getIdMedicalQuestionnaire());
                LAnswerDTO.add(AnswerDTO);
            }

            requestDTO.setAnswerQuestionnairies(LAnswerDTO);
            for (FreeQuestionnaireDTO freeDTO : requestDTO.getFreeQuestionnairies()) {
                free = freeQuestionnaireDao.findByMedicalQuestionnaireAnswer(
                        medicalDTO.getIdMedicalQuestionnaire(),
                        freeDTO.getAnswerNumer()
                );
                free.setMdfd_on(new Date());
                modelMapper.getConfiguration().setSkipNullEnabled(true)
                        .setCollectionsMergeEnabled(false)
                        .setMatchingStrategy(MatchingStrategies.STRICT);
                modelMapper.map(freeDTO,free);
                freeQuestionnaireDao.save(free);
                freeDTO = modelMapper.map(free, FreeQuestionnaireDTO.class);
                freeDTO.setCreaditApplication(medicalDTO.getCreaditApplication());
                freeDTO.setIdMedicalQuestionnaire(medicalDTO.getIdMedicalQuestionnaire());
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

    @Override
    public ResponseDTO remove(Integer creditId) {
        try {
            List<AnswerQuestionnaire> listAnswer = answerQuestionnaireDao.findByCreditId(creditId);
            List<FreeQuestionnaire> listFree = freeQuestionnaireDao.findByCreditId(creditId);

            for (AnswerQuestionnaire answer : listAnswer) {
                answerQuestionnaireDao.delete(answer);
            }

            for (FreeQuestionnaire free : listFree) {
                freeQuestionnaireDao.delete(free);
            }

            MedicalQuestionnaire medicalQuestionnaire = medicalQuestionnaireDao.findByCreditID(creditId);
            if (medicalQuestionnaire != null) {
                medicalQuestionnaireDao.delete(medicalQuestionnaire);
            }
            return new ResponseDTO("","Eliminacion realizada con exito",true);
        } catch (Exception ex) {
            return new ResponseDTO("", "Error en la Eliminacion del registro", false);
        }
    }
}
