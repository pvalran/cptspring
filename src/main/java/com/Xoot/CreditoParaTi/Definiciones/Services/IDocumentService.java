package com.Xoot.CreditoParaTi.Definiciones.Services;

import java.util.List;

import com.Xoot.CreditoParaTi.entity.Document;
import com.Xoot.CreditoParaTi.entity.DTO.DocumentDTO;
import com.Xoot.CreditoParaTi.entity.DTO.ResponseDTO;

public interface IDocumentService {
	Document findById(Integer id);

	List<Document> findAllActive(Integer idCreditAplication);
	
	Document findAllIds(Integer idCreditAplication,Integer idTypeDocument);
	
	ResponseDTO save(DocumentDTO user);
	
	ResponseDTO update(Integer id, DocumentDTO user);
	
	ResponseDTO delete(Integer id);

	ResponseDTO getById(Integer id);	
}
