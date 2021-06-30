package com.Xoot.CreditoParaTi.Definiciones.Services;

import java.util.List;

import com.Xoot.CreditoParaTi.entity.DocumentType;
import com.Xoot.CreditoParaTi.entity.DTO.DocumentTypeDTO;
import com.Xoot.CreditoParaTi.entity.DTO.ResponseDTO;

public interface IDocumentTypeService {
	DocumentType findById(Integer id);

	List<DocumentType> findAllActive();
	
	DocumentType findByName(String name);
	
	List<DocumentType> findByClass(Integer id);
	
	ResponseDTO save(DocumentTypeDTO catalogo);
	
	ResponseDTO update(Integer id, DocumentTypeDTO catalogo);
	
	ResponseDTO active(Integer id);
	
	ResponseDTO delete(Integer id);

	ResponseDTO getById(Integer id);
	
	ResponseDTO getByClass(Integer id);
}
