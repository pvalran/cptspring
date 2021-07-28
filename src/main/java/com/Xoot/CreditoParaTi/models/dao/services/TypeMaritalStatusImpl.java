package com.Xoot.CreditoParaTi.models.dao.services;

import com.Xoot.CreditoParaTi.Definiciones.Services.ITypeMaritalStatusService;
import com.Xoot.CreditoParaTi.entity.DTO.ResponseDTO;
import com.Xoot.CreditoParaTi.entity.DTO.TypeMaritalStatusDTO;
import com.Xoot.CreditoParaTi.entity.TypeMaritalStatus;
import com.Xoot.CreditoParaTi.models.dao.ITypeMaritalStatusDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

import org.modelmapper.ModelMapper;

@Service
public class TypeMaritalStatusImpl implements ITypeMaritalStatusService {
    @Autowired
    private ITypeMaritalStatusDao _typeMaritalStatusDao;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public TypeMaritalStatus findById(Integer id) {
        return _typeMaritalStatusDao.findById(id).orElse(null);
    }

    @Override
    public List<TypeMaritalStatus> findAllActive() {
        return _typeMaritalStatusDao.findAllActive();
    }

    @Override
    public List<TypeMaritalStatus> findByDescription(String Description) { return _typeMaritalStatusDao.findByDescription(Description); }

    @Override
    public ResponseDTO save(TypeMaritalStatusDTO catalogo) {
        return null;
    }

    @Override
    public ResponseDTO update(Integer id, TypeMaritalStatusDTO catalogo) {
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
