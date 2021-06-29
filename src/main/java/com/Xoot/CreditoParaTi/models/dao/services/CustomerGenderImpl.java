package com.Xoot.CreditoParaTi.models.dao.services;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.Xoot.CreditoParaTi.Definiciones.Services.ICustomerGenderService;
import com.Xoot.CreditoParaTi.entity.CustomerGender;
import com.Xoot.CreditoParaTi.entity.DTO.CatalogoDTO;
import com.Xoot.CreditoParaTi.entity.DTO.ResponseDTO;
import com.Xoot.CreditoParaTi.models.dao.ICustomerGenderDao;

@Service
public class CustomerGenderImpl implements ICustomerGenderService{
	public Object data = null;
	public Boolean result = false;
	public String message;

	@Autowired
	private ICustomerGenderDao _customerGenderDao;

	@Override
	@Transactional(readOnly = true)
	public List<CustomerGender> findAllActive() {
		return _customerGenderDao.findAllActive();
	}

	@Override
	@Transactional(readOnly = true)
	public CustomerGender findById(Integer id) {
		return _customerGenderDao.findById(id).orElse(null);
	}

	@Override
	@Transactional(readOnly = true)
	public CustomerGender findByName(String name) {
		return _customerGenderDao.findByNameActive(name);
	}

	@Override
	@Transactional(readOnly = true)
	public ResponseDTO getById(Integer id) {
		CustomerGender customerGender = findById(id);

		if(CheckGenderNotExist(customerGender)) {
			return CreateResponseGenderNotExist();
		}
	
		data = customerGender;
		result = true;
		message = "Exito";
		
		return new ResponseDTO(data, message, result);
	}

	@Override
	@Transactional
	public ResponseDTO save(CatalogoDTO catalogo) {
		CustomerGender customerGenderByName = _customerGenderDao.findByName(catalogo.getName());

		if (customerGenderByName != null) {
			return CreateResonseDuplicatedGender(customerGenderByName);
		} 
		
		CustomerGender customerGender = new CustomerGender();

		data = saveCustomerGender(1,catalogo.getName(),customerGender);

		result = true;

		message = "Registro creado.";
		
		return new ResponseDTO(data, message, result);
	}

	@Override
	@Transactional
	public ResponseDTO update(Integer id, CatalogoDTO catalogo) {

		CustomerGender customerGenderById = findById(id);

		CustomerGender customerGenderByName = _customerGenderDao.findByName(catalogo.getName());

		if(CheckGenderNotExist(customerGenderById)) {
			return CreateResponseGenderNotExist();
		}

		if(CheckDuplicatedGender(id, catalogo, customerGenderByName)) {
			return CreateResonseDuplicatedGender(customerGenderByName);
		}

		data = saveCustomerGender(1,catalogo.getName(),customerGenderById);

		result = true;

		message = "Registro actualizado.";

		return new ResponseDTO(data, message, result);
	}

	@Override
	@Transactional
	public ResponseDTO delete(Integer id) {
		CustomerGender customerGender = findById(id);
		
		if(CheckGenderNotExist(customerGender)) {
			return CreateResponseGenderNotExist();
		}

		saveCustomerGender(0,null,customerGender);

		result = true;

		message = "Registro eliminado.";

		return new ResponseDTO(data, message, result);
	}

	@Override
	@Transactional
	public ResponseDTO active(Integer Id) {
		CustomerGender customerGenderByName = findById(Id);
		
		if(CheckGenderNotExist(customerGenderByName)) {
			return CreateResponseGenderNotExist();
		}
		
		saveCustomerGender(1,null,customerGenderByName);

		result = true;

		message = "Registro Activado.";

		return new ResponseDTO(data, message, result);
		
	}
	 
	private boolean CheckGenderNotExist(CustomerGender customerGender) {
		boolean response = false;
		
		if (customerGender == null) {
			response = true;
		}
		
		return response;
	}
	
	private ResponseDTO CreateResponseGenderNotExist() {
		message = "No existe un genero con el id proporcionado.";
		
		return new ResponseDTO(data, message, result);
	}
	
	private CustomerGender saveCustomerGender(Integer gender_flag, String genderName, CustomerGender customerGender ) {

	    if( genderName != null && !genderName.isEmpty()) {
	    	customerGender.setName(genderName);
	    }
	    
	    customerGender.setStatus_flag(gender_flag);
		
	    customerGender.setMdfd_on(new Date());
		
		return _customerGenderDao.save(customerGender);
	}

	private boolean CheckDuplicatedGender(Integer id, CatalogoDTO catalogo, CustomerGender customerGenderByName) {
		
		boolean response = false;

		if (customerGenderByName != null
				&& customerGenderByName.getIdGender() != id) {
			response = true;		
		}
		
		return response;
	}
	
	private ResponseDTO CreateResonseDuplicatedGender(CustomerGender customerGenderByName) {
		
		if(customerGenderByName.getStatus_flag() == 0) {
			message = "Ya existe un genero con el nombre: " + customerGenderByName.getName() + " y se encuentra inactivo ID " + customerGenderByName.getIdGender();
		}else {
			message = "Ya existe un genero con el nombre: " + customerGenderByName.getName();
		}
	
		return new ResponseDTO(data, message, result);
	}
}
