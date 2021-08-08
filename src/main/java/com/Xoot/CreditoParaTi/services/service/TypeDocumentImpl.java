package com.Xoot.CreditoParaTi.services.service;

import com.Xoot.CreditoParaTi.services.interfaces.ITypeDocumentService;
import com.Xoot.CreditoParaTi.dto.ResponseDTO;
import com.Xoot.CreditoParaTi.dto.TypeDocumentDTO;
import com.Xoot.CreditoParaTi.entity.TypeDocument;
import com.Xoot.CreditoParaTi.repositories.interfaces.ITypeDocumentDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.modelmapper.ModelMapper;
import java.util.List;

@Service
public class TypeDocumentImpl implements ITypeDocumentService {
    @Autowired
    private ITypeDocumentDao _typeDocumentDao;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public TypeDocument findById(Integer id) {
        return _typeDocumentDao.findById(id).orElse(null);
    }

    @Override
    public List <TypeDocument> findAllActive() {
        return _typeDocumentDao.findAllActive();
    }

    @Override
    public List <TypeDocument> findByDescription(String Description) { return _typeDocumentDao.findByDescription(Description); }

    @Override
    public ResponseDTO save(TypeDocumentDTO catalogo) {
        return null;
    }

    @Override
    public ResponseDTO update(Integer id, TypeDocumentDTO catalogo) {
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
