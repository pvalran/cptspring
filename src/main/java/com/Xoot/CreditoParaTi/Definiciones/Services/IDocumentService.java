package com.Xoot.CreditoParaTi.Definiciones.Services;

import java.util.List;

import com.Xoot.CreditoParaTi.entity.Document;

public interface IDocumentService {
	public Document findById(Integer id);

	public Document save(Document document);

	public void delete(Integer id);

	public List<Document> findAllActive();
}
