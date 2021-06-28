package com.Xoot.CreditoParaTi.Definiciones.Services;

import java.util.List;

import com.Xoot.CreditoParaTi.entity.CreditApplicationStatus;
import com.Xoot.CreditoParaTi.entity.DTO.CatalogoDTO;
import com.Xoot.CreditoParaTi.entity.DTO.ResponseDTO;

public interface ICreditApplicationStatusService {
	
	CreditApplicationStatus findById(Integer id);

	List<CreditApplicationStatus> findAllActive();
	
	CreditApplicationStatus findByName(String name);
	
	ResponseDTO save(CatalogoDTO catalogo);
	
	ResponseDTO update(Integer id, CatalogoDTO catalogo);
	
	 ResponseDTO active(Integer id);
	
	ResponseDTO delete(Integer id);

	ResponseDTO getById(Integer id);
}
