package com.Xoot.CreditoParaTi.Definiciones.Services;

import java.util.List;

import com.Xoot.CreditoParaTi.entity.DocumentClass;

public interface IClassDocumentService {
	public List<DocumentClass> findAll();

	public DocumentClass findById(Integer id);

	public DocumentClass save(DocumentClass classDocument);

	public void delete(Integer id);

	public List<DocumentClass> findAllActive();
}
