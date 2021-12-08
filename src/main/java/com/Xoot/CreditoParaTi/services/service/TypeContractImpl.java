package com.Xoot.CreditoParaTi.services.service;

import com.Xoot.CreditoParaTi.services.interfaces.ITypeContractService;
import com.Xoot.CreditoParaTi.dto.ResponseDTO;
import com.Xoot.CreditoParaTi.dto.TypeContractDTO;
import com.Xoot.CreditoParaTi.entity.app.TypeContract;
import com.Xoot.CreditoParaTi.repositories.app.ITypeContractDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.modelmapper.ModelMapper;


import java.util.List;

@Service
public class TypeContractImpl implements ITypeContractService {
    @Autowired
    private ITypeContractDao _typeContractDao;

    @Autowired
    private ModelMapper modelMapper;


    @Override
    public TypeContract findById(Integer id) {
        return _typeContractDao.findById(id).orElse(null);
    }

    @Override
    public List<TypeContract> findAllActive() { return _typeContractDao.findAllActive(); }

    @Override
    public List<TypeContract> findByDescription(String Description) { return _typeContractDao.findByDescription(Description); }

    @Override
    public ResponseDTO save(TypeContractDTO catalogo) {
        return null;
    }

    @Override
    public ResponseDTO update(Integer id, TypeContractDTO catalogo) {
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
