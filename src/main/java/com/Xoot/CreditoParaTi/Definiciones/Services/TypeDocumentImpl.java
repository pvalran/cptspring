package com.Xoot.CreditoParaTi.Definiciones.Services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.Xoot.CreditoParaTi.entity.DocumentType;
import com.Xoot.CreditoParaTi.models.dao.ITypeDocumentDao;

@Service
public class TypeDocumentImpl implements ITypeDocumentService{

	@Autowired
	private ITypeDocumentDao typeDocumentDao;

	@Override
	@Transactional(readOnly = true)
	public List<DocumentType> findAll() {
		return (List<DocumentType>)typeDocumentDao.findAll();
	}

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
	@Transactional
	public void delete(Integer id) {
		typeDocumentDao.deleteById(id);
	}

	@Override
	@Transactional(readOnly = true)
	public List<DocumentType> findAllActive() {
		return typeDocumentDao.findAllActive();
	}

}
