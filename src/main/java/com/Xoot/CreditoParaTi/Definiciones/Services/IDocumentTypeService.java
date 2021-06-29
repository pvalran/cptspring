package com.Xoot.CreditoParaTi.Definiciones.Services;

import java.util.List;

import com.Xoot.CreditoParaTi.entity.DocumentType;
import com.Xoot.CreditoParaTi.entity.DTO.CatalogoDTO;
import com.Xoot.CreditoParaTi.entity.DTO.ResponseDTO;

public interface IDocumentTypeService {
	DocumentType findById(Integer id);

	List<DocumentType> findAllActive();
	
	DocumentType findByName(String name);
	
	ResponseDTO save(CatalogoDTO catalogo);
	
	ResponseDTO update(Integer id, CatalogoDTO catalogo);
	
	ResponseDTO active(Integer id);
	
	ResponseDTO delete(Integer id);

	ResponseDTO getById(Integer id);
}
