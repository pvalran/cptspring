package com.Xoot.CreditoParaTi.services.service;

import com.Xoot.CreditoParaTi.services.interfaces.IFreeQuestionnaireService;
import com.Xoot.CreditoParaTi.entity.FreeQuestionnaire;
import com.Xoot.CreditoParaTi.dto.FreeQuestionnaireDTO;
import com.Xoot.CreditoParaTi.dto.ResponseDTO;
import com.Xoot.CreditoParaTi.repositories.interfaces.IFreeQuestionnaireDao;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
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
    public ResponseDTO update(Integer id, FreeQuestionnaireDTO ObjDTO) {
        FreeQuestionnaire Entity = freeQuestionnaireDao.findById(id).orElse(null);
        if (Entity != null) {
            Entity.setMdfd_on(new Date());
            modelMapper.getConfiguration().setSkipNullEnabled(true)
                    .setCollectionsMergeEnabled(false)
                    .setMatchingStrategy(MatchingStrategies.STRICT);
            modelMapper.map(ObjDTO,Entity);
            freeQuestionnaireDao.save(Entity);
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
