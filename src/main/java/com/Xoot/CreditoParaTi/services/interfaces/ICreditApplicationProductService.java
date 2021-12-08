package com.Xoot.CreditoParaTi.services.interfaces;

import java.util.List;

import com.Xoot.CreditoParaTi.entity.app.CreditApplicationProduct;
import com.Xoot.CreditoParaTi.dto.CreditApplicationProductDTO;
import com.Xoot.CreditoParaTi.dto.ResponseDTO;

public interface ICreditApplicationProductService {
	CreditApplicationProduct findById(Integer id);

	List<CreditApplicationProduct> findAllActive();
	
	CreditApplicationProduct findByName(String name);
	
	ResponseDTO save(CreditApplicationProductDTO creditApplicationProductDTO);
	
	ResponseDTO update(Integer id, CreditApplicationProductDTO creditApplicationProductDTO);
	
	ResponseDTO active(Integer id);
	
	ResponseDTO delete(Integer id);

	ResponseDTO getById(Integer id);



}
