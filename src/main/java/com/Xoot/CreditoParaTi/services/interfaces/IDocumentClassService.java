package com.Xoot.CreditoParaTi.services.interfaces;

import java.util.List;

import com.Xoot.CreditoParaTi.entity.app.DocumentClass;
import com.Xoot.CreditoParaTi.dto.CatalogoDTO;
import com.Xoot.CreditoParaTi.dto.ResponseDTO;

public interface IDocumentClassService {
	DocumentClass findById(Integer id);

	List<DocumentClass> findAllActive();
	
	DocumentClass findByName(String name);
	
	ResponseDTO save(CatalogoDTO catalogo);
	
	ResponseDTO update(Integer id, CatalogoDTO catalogo);
	
	ResponseDTO active(Integer id);
	
	ResponseDTO delete(Integer id);

	ResponseDTO getById(Integer id);
	
}
