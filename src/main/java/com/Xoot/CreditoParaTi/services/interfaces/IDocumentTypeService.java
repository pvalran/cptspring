package com.Xoot.CreditoParaTi.services.interfaces;

import java.util.List;

import com.Xoot.CreditoParaTi.entity.DocumentType;
import com.Xoot.CreditoParaTi.dto.DocumentTypeDTO;
import com.Xoot.CreditoParaTi.dto.ResponseDTO;

public interface IDocumentTypeService {
	DocumentType findById(Integer id);

	List<DocumentType> findAllActive();
	
	DocumentType findByName(String name);
	
	List<DocumentType> findByClass(Integer id);
	
	List<DocumentType> getlistDocumentType(List<Integer> lstIds);
	
	ResponseDTO save(DocumentTypeDTO catalogo);
	
	ResponseDTO update(Integer id, DocumentTypeDTO catalogo);
	
	ResponseDTO active(Integer id);
	
	ResponseDTO delete(Integer id);

	ResponseDTO getById(Integer id);
	
	ResponseDTO getByClass(Integer id);
}
