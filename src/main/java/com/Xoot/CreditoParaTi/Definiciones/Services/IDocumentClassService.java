package com.Xoot.CreditoParaTi.Definiciones.Services;

import java.util.List;

import com.Xoot.CreditoParaTi.entity.DocumentClass;
import com.Xoot.CreditoParaTi.entity.DTO.CatalogoDTO;
import com.Xoot.CreditoParaTi.entity.DTO.ResponseDTO;

public interface IDocumentClassService {
	DocumentClass findById(Integer id);

	List<DocumentClass> findAllActive();
	
	DocumentClass findByName(String name);
	
	ResponseDTO save(CatalogoDTO catalogo);
	
	ResponseDTO update(Integer id, CatalogoDTO catalogo);
	
	ResponseDTO delete(Integer id);

	ResponseDTO getById(Integer id);
	
}
