package com.Xoot.CreditoParaTi.Definiciones.Services;

import java.util.List;

import com.Xoot.CreditoParaTi.entity.DocumentType;

public interface IDocumentTypeService {
	public DocumentType findById(Integer id);

	public DocumentType save(DocumentType typeDocument);

	public void delete(Integer id);

	public List<DocumentType> findAllActive();
}
