package com.Xoot.CreditoParaTi.models.dao.services;

import java.util.List;

import com.Xoot.CreditoParaTi.entity.DocumentType;

public interface ITypeDocumentService {
	public List<DocumentType> findAll();

	public DocumentType findById(Integer id);

	public DocumentType save(DocumentType typeDocument);

	public void delete(Integer id);

	public List<DocumentType> findAllActive();
}
