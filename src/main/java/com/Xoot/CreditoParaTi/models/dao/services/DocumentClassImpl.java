package com.Xoot.CreditoParaTi.models.dao.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.Xoot.CreditoParaTi.Definiciones.Services.IDocumentClassService;
import com.Xoot.CreditoParaTi.entity.DocumentClass;
import com.Xoot.CreditoParaTi.models.dao.IDocumentClassDao;

@Service
public class DocumentClassImpl implements IDocumentClassService{
	
	@Autowired
	private IDocumentClassDao classDocumentDao;
	
	@Override
	@Transactional(readOnly = true)
	public List<DocumentClass> findAllActive() {
		return classDocumentDao.findAllActive();
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
	@Transactional(readOnly = true)
	public DocumentClass findByName(String name) {
		return classDocumentDao.findByName(name);
	}
}
