package com.Xoot.CreditoParaTi.models.dao.services;

import com.Xoot.CreditoParaTi.Definiciones.Services.ITypeLaboralActivityService;
import com.Xoot.CreditoParaTi.entity.DTO.ResponseDTO;
import com.Xoot.CreditoParaTi.entity.DTO.TypeLaboralActivityDTO;
import com.Xoot.CreditoParaTi.entity.TypeLaboralActivity;
import com.Xoot.CreditoParaTi.models.dao.ITypeLaboralActivityDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.modelmapper.ModelMapper;
import java.util.List;

@Service
public class TypeLaboralActivityImpl implements ITypeLaboralActivityService {
    @Autowired
    private ITypeLaboralActivityDao _typeLaboralActivityDao;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public TypeLaboralActivity findById(Integer id) {
        return _typeLaboralActivityDao.findById(id).orElse(null);
    }

    @Override
    public List<TypeLaboralActivity> findAllActive() { return _typeLaboralActivityDao.findAllActive(); }

    @Override
    public List<TypeLaboralActivity> findByDescription(String Description) { return _typeLaboralActivityDao.findByDescription(Description); }

    @Override
    public ResponseDTO save(TypeLaboralActivityDTO catalogo) {
        return null;
    }

    @Override
    public ResponseDTO update(Integer id, TypeLaboralActivityDTO catalogo) {
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
