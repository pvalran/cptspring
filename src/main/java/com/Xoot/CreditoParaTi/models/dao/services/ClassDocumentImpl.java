package com.Xoot.CreditoParaTi.models.dao.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.Xoot.CreditoParaTi.entity.DocumentClass;
import com.Xoot.CreditoParaTi.models.dao.IClassDocumentDao;

@Service
public class ClassDocumentImpl implements IClassDocumentService{
	
	@Autowired
	private IClassDocumentDao classDocumentDao;
	
	@Override
	@Transactional(readOnly = true)
	public List<DocumentClass> findAll() {
		return (List<DocumentClass>)classDocumentDao.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public DocumentClass findById(Integer id) {
		return classDocumentDao.findById(id).orElse(null);
	}

	@Override
	@Transactional
	public DocumentClass save(DocumentClass classDocument) {
		return classDocumentDao.save(classDocument);
	}

	@Override
	@Transactional
	public void delete(Integer id) {
		classDocumentDao.deleteById(id);
	}

	@Override
	@Transactional(readOnly = true)
	public List<DocumentClass> findAllActive() {
		return classDocumentDao.findAllActive();
	}

}
