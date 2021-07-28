package com.Xoot.CreditoParaTi.models.dao.services;

import com.Xoot.CreditoParaTi.Definiciones.Services.IReferenceService;
import com.Xoot.CreditoParaTi.entity.Reference;
import com.Xoot.CreditoParaTi.entity.DTO.ReferenceDTO;
import com.Xoot.CreditoParaTi.entity.DTO.ResponseDTO;
import com.Xoot.CreditoParaTi.models.dao.IReferenceDao;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    public ResponseDTO update(Integer id, ReferenceDTO referenceDTO) {
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
