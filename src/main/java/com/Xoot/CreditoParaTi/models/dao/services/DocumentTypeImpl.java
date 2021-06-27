package com.Xoot.CreditoParaTi.models.dao.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.Xoot.CreditoParaTi.Definiciones.Services.IDocumentTypeService;
import com.Xoot.CreditoParaTi.entity.DocumentType;
import com.Xoot.CreditoParaTi.models.dao.IDocumentTypeDao;

@Service
public class DocumentTypeImpl implements IDocumentTypeService{
	
	@Autowired
	private IDocumentTypeDao typeDocumentDao;

	@Override
	@Transactional(readOnly = true)
	public DocumentType findById(Integer id) {
		return typeDocumentDao.findById(id).orElse(null);
	}

	@Override
	@Transactional
	public DocumentType save(DocumentType typeDocument) {
		return typeDocumentDao.save(typeDocument);
	}

	@Override
	@Transactional(readOnly = true)
	public List<DocumentType> findAllActive() {
		return typeDocumentDao.findAllActive();
	}

	@Override
	@Transactional(readOnly = true)
	public DocumentType findByName(String name) {
		return typeDocumentDao.findByName(name);
	}

}
