package com.Xoot.CreditoParaTi.Definiciones.Services;

import java.util.List;

import com.Xoot.CreditoParaTi.entity.CustomerGender;
import com.Xoot.CreditoParaTi.entity.DTO.CatalogoDTO;
import com.Xoot.CreditoParaTi.entity.DTO.ResponseDTO;

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
