package com.Xoot.CreditoParaTi.models.dao.services;

import com.Xoot.CreditoParaTi.Definiciones.Services.ITypeDatosService;
import com.Xoot.CreditoParaTi.entity.DTO.ResponseDTO;
import com.Xoot.CreditoParaTi.entity.DTO.TypeDatosDTO;
import com.Xoot.CreditoParaTi.entity.TypeDatos;
import com.Xoot.CreditoParaTi.models.dao.ITypeDatosDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.modelmapper.ModelMapper;
import java.util.List;

@Service
public class TypeDatosImpl implements ITypeDatosService {
    @Autowired
    private ITypeDatosDao _typeDatosDao;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public TypeDatos findById(Integer id) { return _typeDatosDao.findById(id).orElse(null); }

    @Override
    public List<TypeDatos> findAllActive() {
        return _typeDatosDao.findAllActive();
    }

    @Override
    public List<TypeDatos> findByDescription(String Description) { return _typeDatosDao.findByDescription(Description);  }

    @Override
    public ResponseDTO save(TypeDatosDTO catalogo) {
        return null;
    }

    @Override
    public ResponseDTO update(Integer id, TypeDatosDTO catalogo) {
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
