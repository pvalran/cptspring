package com.Xoot.CreditoParaTi.models.dao.services;

import com.Xoot.CreditoParaTi.Definiciones.Services.ITypeNationalityService;
import com.Xoot.CreditoParaTi.entity.DTO.ResponseDTO;
import com.Xoot.CreditoParaTi.entity.DTO.TypeNationalityDTO;
import com.Xoot.CreditoParaTi.entity.TypeNationality;
import com.Xoot.CreditoParaTi.models.dao.ITypeNationalityDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.modelmapper.ModelMapper;
import java.util.List;

@Service
public class TypeNationalityImpl implements ITypeNationalityService {
    @Autowired
    private ITypeNationalityDao _typeNationalityDao;

    @Autowired
    private ModelMapper modelMapper;


    @Override
    public TypeNationality findById(Integer id) {
        return _typeNationalityDao.findById(id).orElse(null);
    }

    @Override
    public List<TypeNationality> findAllActive() { return _typeNationalityDao.findAllActive(); }

    @Override
    public List<TypeNationality> findByDescription(String Description) { return _typeNationalityDao.findByDescription(Description); }

    @Override
    public ResponseDTO save(TypeNationalityDTO catalogo) {
        return null;
    }

    @Override
    public ResponseDTO update(Integer id, TypeNationalityDTO catalogo) {
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
