package com.Xoot.CreditoParaTi.services.interfaces;

import java.util.List;

import com.Xoot.CreditoParaTi.entity.app.CreditApplication;
import com.Xoot.CreditoParaTi.dto.CreditApplicationDTO;
import com.Xoot.CreditoParaTi.dto.ResponseDTO;

public interface ICreditApplicationService {

	CreditApplication findById(Integer id);

	List<CreditApplication> findAllActive();

	List<CreditApplication> getAllByUser(Integer idUser);

	ResponseDTO save(CreditApplicationDTO creditApplicationDTO);

	ResponseDTO update(Integer id, CreditApplicationDTO creditApplicationDTO);

	ResponseDTO active(Integer id);

	ResponseDTO delete(Integer id);

	ResponseDTO getById(Integer id);


	
	ResponseDTO addCustomer(Integer id, CreditApplicationDTO creditApplicationDTO);
}
