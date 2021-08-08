package com.Xoot.CreditoParaTi.services.service;

import com.Xoot.CreditoParaTi.services.interfaces.ITypeReferenceService;
import com.Xoot.CreditoParaTi.dto.ResponseDTO;
import com.Xoot.CreditoParaTi.dto.TypeReferenceDTO;
import com.Xoot.CreditoParaTi.entity.TypeReference;
import com.Xoot.CreditoParaTi.repositories.interfaces.ITypeReferenceDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.modelmapper.ModelMapper;

import java.util.List;

@Service
public class TypeReferenceImpl implements ITypeReferenceService {
    @Autowired
    private ITypeReferenceDao _typeReferenceDao;

    @Autowired
    private ModelMapper modelMapper;


    @Override
    @Transactional(readOnly = true)
    public TypeReference findById(Integer id) {
        return _typeReferenceDao.findById(id).orElse(null);
    }

    @Override
    @Transactional(readOnly = true)
    public List<TypeReference> findAllActive() {
        return _typeReferenceDao.findAllActive();
    }

    @Override
    @Transactional(readOnly = true)
    public List<TypeReference> findByDescription(String Description) { return _typeReferenceDao.findByDescription(Description);  }

    @Override
    public TypeReference save(TypeReferenceDTO catalogo) { return _typeReferenceDao.save(modelMapper.map(catalogo,TypeReference.class)); }

    @Override
    public ResponseDTO update(Integer id, TypeReferenceDTO catalogo) {
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

    @Override
    public ResponseDTO getById(Integer id) {
        return null;
    }
}
