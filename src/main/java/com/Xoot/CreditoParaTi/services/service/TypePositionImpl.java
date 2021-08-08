package com.Xoot.CreditoParaTi.services.service;

import com.Xoot.CreditoParaTi.services.interfaces.ITypePositionService;
import com.Xoot.CreditoParaTi.dto.ResponseDTO;
import com.Xoot.CreditoParaTi.dto.TypePositionDTO;
import com.Xoot.CreditoParaTi.entity.TypePosition;
import com.Xoot.CreditoParaTi.repositories.interfaces.ITypePositionDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.modelmapper.ModelMapper;


import java.util.List;

@Service
public class TypePositionImpl implements ITypePositionService {
    @Autowired
    private ITypePositionDao _typePositionDao;

    @Autowired
    private ModelMapper modelMapper;


    @Override
    public TypePosition findById(Integer id) { return _typePositionDao.findById(id).orElse(null); }

    @Override
    public List<TypePosition> findAllActive() { return _typePositionDao.findAllActive(); }

    @Override
    public List<TypePosition> findByDescription(String Description) { return _typePositionDao.findByDescription(Description); }

    @Override
    public ResponseDTO save(TypePositionDTO catalogo) {
        return null;
    }

    @Override
    public ResponseDTO update(Integer id, TypePositionDTO catalogo) {
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
