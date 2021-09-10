package com.Xoot.CreditoParaTi.services.interfaces;

import java.util.List;

import com.Xoot.CreditoParaTi.entity.Customer;
import com.Xoot.CreditoParaTi.dto.CustomerDTO;
import com.Xoot.CreditoParaTi.dto.ResponseDTO;

public interface ICustomerService {
	Customer findById(Integer id);

	List<Customer> findAllActive();

	Customer findByCurp(String curp);

	ResponseDTO save(CustomerDTO customer);

	ResponseDTO update(Integer id, CustomerDTO customer);

	ResponseDTO active(Integer id);

	ResponseDTO delete(Integer id);

	ResponseDTO getById(Integer id);

	ResponseDTO getByCustomerTransaction();

	ResponseDTO getByCustomerTransaction(Integer UserID);
}
