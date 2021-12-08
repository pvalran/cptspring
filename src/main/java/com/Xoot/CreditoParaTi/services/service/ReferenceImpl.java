package com.Xoot.CreditoParaTi.services.service;

import com.Xoot.CreditoParaTi.services.interfaces.IReferenceService;
import com.Xoot.CreditoParaTi.entity.app.Reference;
import com.Xoot.CreditoParaTi.dto.ReferenceDTO;
import com.Xoot.CreditoParaTi.dto.ResponseDTO;
import com.Xoot.CreditoParaTi.repositories.app.IReferenceDao;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
public class ReferenceImpl implements IReferenceService {
    @Autowired
    private IReferenceDao referenceDao;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    @Transactional(readOnly = true)
    public Reference findById(Integer id) { return referenceDao.findById(id).orElse(null); }

    @Override
    @Transactional(readOnly = true)
    public List<Reference> findAllActive() { return referenceDao.findAllActive(); }

    @Override
    @Transactional
    public ResponseDTO save(ReferenceDTO referenceDTO) {
        Reference reference = referenceDao.save(modelMapper.map(referenceDTO,Reference.class));
        if (reference != null) {
            return  new ResponseDTO(reference,"Registro creado",true);
        }
        return  new ResponseDTO(reference,"Error en registro",false);
    }

    @Override
    public ResponseDTO update(Integer id, ReferenceDTO ObjDTO) {
        Reference Entity = referenceDao.findById(id).orElse(null);
        if (Entity != null) {
            Entity.setMdfd_on(new Date());
            modelMapper.getConfiguration().setSkipNullEnabled(true)
                    .setCollectionsMergeEnabled(false)
                    .setMatchingStrategy(MatchingStrategies.STRICT);
            modelMapper.map(ObjDTO,Entity);
            referenceDao.save(Entity);
            ObjDTO = modelMapper.map(Entity, ReferenceDTO.class);
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
        try {
            referenceDao.deleteById(id);
            return new ResponseDTO("","Eliminacion realizada con exito",true);
        } catch (Exception ex) {
            return new ResponseDTO("", "Error en la Eliminacion del registro", false);
        }
    }

    @Override
    public ResponseDTO remove(Integer creditId) {
        try {
            List<Reference> lstReference = referenceDao.findByCreditId(creditId);
            for (Reference reference:lstReference){
                referenceDao.delete(reference);
            }
            return new ResponseDTO("","Eliminacion realizada con exito",true);
        } catch (Exception ex) {
            return new ResponseDTO("", "Error en la Eliminacion del registro", false);
        }
    }
}
