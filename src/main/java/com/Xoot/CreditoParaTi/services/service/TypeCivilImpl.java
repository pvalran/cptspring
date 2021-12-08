package com.Xoot.CreditoParaTi.services.service;

import com.Xoot.CreditoParaTi.services.interfaces.ITypeCivilService;
import com.Xoot.CreditoParaTi.dto.ResponseDTO;
import com.Xoot.CreditoParaTi.dto.TypeCivilDTO;
import com.Xoot.CreditoParaTi.entity.app.TypeCivil;
import com.Xoot.CreditoParaTi.repositories.app.ITypeCivilDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.modelmapper.ModelMapper;
import java.util.List;

@Service
public class TypeCivilImpl implements ITypeCivilService {
    @Autowired
    private ITypeCivilDao _typeCivilDao;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public TypeCivil findById(Integer id) {
        return _typeCivilDao.findById(id).orElse(null);
    }

    @Override
    public List<TypeCivil> findAllActive() {
        return _typeCivilDao.findAllActive();
    }

    @Override
    public List<TypeCivil> findByDescription(String Description) { return _typeCivilDao.findByDescription(Description); }

    @Override
    public ResponseDTO save(TypeCivilDTO catalogo) {
        return null;
    }

    @Override
    public ResponseDTO update(Integer id, TypeCivilDTO catalogo) {
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
