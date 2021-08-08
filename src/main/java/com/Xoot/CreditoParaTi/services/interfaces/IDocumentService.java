package com.Xoot.CreditoParaTi.services.interfaces;

import java.util.List;

import com.Xoot.CreditoParaTi.entity.Document;
import com.Xoot.CreditoParaTi.dto.DocumentDTO;
import com.Xoot.CreditoParaTi.dto.ResponseDTO;

public interface IDocumentService {
	Document findById(Integer id);

	List<Document> findAllActive(Integer idCreditAplication);
	
	Document findAllIds(Integer idCreditAplication,Integer idTypeDocument);
	
	ResponseDTO save(DocumentDTO user);
	
	ResponseDTO update(Integer id, DocumentDTO user);
	
	ResponseDTO delete(Integer id);

	ResponseDTO getById(Integer id);	
}
