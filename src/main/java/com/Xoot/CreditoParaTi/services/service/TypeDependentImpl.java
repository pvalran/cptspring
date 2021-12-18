package com.Xoot.CreditoParaTi.services.service;

import com.Xoot.CreditoParaTi.dto.ResponseDTO;
import com.Xoot.CreditoParaTi.dto.TypeDependentDTO;
import com.Xoot.CreditoParaTi.dto.TypeReferenceDTO;
import com.Xoot.CreditoParaTi.entity.TypeDependent;
import com.Xoot.CreditoParaTi.entity.TypeReference;
import com.Xoot.CreditoParaTi.repositories.interfaces.ITypeDependentDao;
import com.Xoot.CreditoParaTi.repositories.interfaces.ITypeReferenceDao;
import com.Xoot.CreditoParaTi.services.interfaces.ITypeDependentService;
import com.Xoot.CreditoParaTi.services.interfaces.ITypeReferenceService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class TypeDependentImpl implements ITypeDependentService {
    @Autowired
    private ITypeDependentDao _typeDependentDao;

    @Autowired
    private ModelMapper modelMapper;


    @Override
    @Transactional(readOnly = true)
    public TypeDependent findById(Integer id) {
        return _typeDependentDao.findById(id).orElse(null);
    }

    @Override
    @Transactional(readOnly = true)
    public List<TypeDependent> findAllActive() {
        return _typeDependentDao.findAllActive();
    }

    @Override
    @Transactional(readOnly = true)
    public List<TypeDependent> findByDescription(String Description) { return _typeDependentDao.findByDescription(Description);  }

    @Override
    public TypeDependent save(TypeDependentDTO catalogo) { return _typeDependentDao.save(modelMapper.map(catalogo,TypeDependent.class)); }

    @Override
    public ResponseDTO update(Integer id, TypeDependentDTO catalogo) {
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
