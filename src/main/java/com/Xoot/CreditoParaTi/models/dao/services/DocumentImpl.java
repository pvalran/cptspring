package com.Xoot.CreditoParaTi.models.dao.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.Xoot.CreditoParaTi.Definiciones.Services.IDocumentService;
import com.Xoot.CreditoParaTi.entity.Document;
import com.Xoot.CreditoParaTi.models.dao.IDocumentDao;

@Service
public class DocumentImpl implements IDocumentService{
	@Autowired
	private IDocumentDao documentDao;
	
	@Override
	@Transactional(readOnly = true)
	public Document findById(Integer id) {
		return documentDao.findById(id).orElse(null);
	}

	@Override
	public Document save(Document document) {
		return documentDao.save(null);
	}

	@Override
	public void delete(Integer id) {
		documentDao.deleteById(id);		
	}

	@Override
	@Transactional(readOnly = true)
	public List<Document> findAllActive() {
		return documentDao.findAllActive();
	}

}
