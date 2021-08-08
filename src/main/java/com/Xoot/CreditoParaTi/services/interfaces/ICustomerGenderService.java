package com.Xoot.CreditoParaTi.services.interfaces;

import java.util.List;

import com.Xoot.CreditoParaTi.entity.CustomerGender;
import com.Xoot.CreditoParaTi.dto.CatalogoDTO;
import com.Xoot.CreditoParaTi.dto.ResponseDTO;

public interface ICustomerGenderService {
	CustomerGender findById(Integer id);

	List<CustomerGender> findAllActive();

	CustomerGender findByName(String name);

	ResponseDTO save(CatalogoDTO catalogo);

	ResponseDTO update(Integer id, CatalogoDTO catalogo);

	ResponseDTO active(Integer id);

	ResponseDTO delete(Integer id);

	ResponseDTO getById(Integer id);
}
