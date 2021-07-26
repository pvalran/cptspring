package com.Xoot.CreditoParaTi.models.dao.services;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.Xoot.CreditoParaTi.Definiciones.Services.ICustomerService;
import com.Xoot.CreditoParaTi.entity.Customer;
import com.Xoot.CreditoParaTi.entity.CustomerGender;
import com.Xoot.CreditoParaTi.entity.LocationState;
import com.Xoot.CreditoParaTi.entity.LocationSuburb;
import com.Xoot.CreditoParaTi.entity.DTO.CustomerDTO;
import com.Xoot.CreditoParaTi.entity.DTO.ResponseDTO;
import com.Xoot.CreditoParaTi.models.dao.ICustomerDao;
import com.Xoot.CreditoParaTi.models.dao.ICustomerGenderDao;
import com.Xoot.CreditoParaTi.models.dao.ILocationStateDao;
import com.Xoot.CreditoParaTi.models.dao.ILocationSuburbDao;

@Service
public class CustomerImpl implements ICustomerService {
	public Object data = null;
	public Boolean result = false;
	public String message;

	@Autowired
	private ICustomerDao _customerDao;
	@Autowired
	private ICustomerGenderDao _customerGenderDao;
	@Autowired
	private ILocationStateDao _locationStateDao;
	@Autowired
	private ILocationSuburbDao _locationSuburbDao;

	@Override
	@Transactional(readOnly = true)
	public List<Customer> findAllActive() {
		return _customerDao.findAllActive();
	}

	@Override
	@Transactional(readOnly = true)
	public Customer findById(Integer id) {
		return _customerDao.findById(id).orElse(null);
	}

	@Override
	@Transactional(readOnly = true)
	public Customer findByCurp(String curp) {
		return _customerDao.findByCurpActive(curp);
	}

	@Override
	@Transactional(readOnly = true)
	public ResponseDTO getById(Integer id) {
		Customer customer = findById(id);

		if (CheckCustomerNotExist(customer)) {
			return CreateResponseCustomerNotExist();
		}

		data = customer;
		result = true;
		message = "Exito";

		return new ResponseDTO(data, message, result);
	}

	@Override
	@Transactional
	public ResponseDTO save(CustomerDTO customerDTO) {
		Customer customerByCurp = _customerDao.findByCurp(customerDTO.getCurp());

		if (customerByCurp != null) {
			return CreateResonseDuplicatedCustomer(customerByCurp);
		}

		Customer customer = new Customer();

		data = saveCustomer(1, customerDTO, customer);

		result = true;

		message = "Registro creado.";

		return new ResponseDTO(data, message, result);
	}

	@Override
	@Transactional
	public ResponseDTO update(Integer id, CustomerDTO customerDTO) {

		Customer customerById = findById(id);

		//Customer customerByCurp = _customerDao.findByCurp(customerDTO.getCurp());

		if (CheckCustomerNotExist(customerById)) {
			return CreateResponseCustomerNotExist();
		}

		/*if (customerByCurp != null) {
			if (CheckDuplicatedCustomer(id, customerByCurp)) {
				return CreateResonseDuplicatedCustomer(customerByCurp);
			}
		}*/

		data = saveCustomer(1, customerDTO, customerById);

		result = true;

		message = "Registro actualizado.";

		return new ResponseDTO(data, message, result);
	}

	@Override
	@Transactional
	public ResponseDTO delete(Integer id) {
		Customer customer = findById(id);

		if (CheckCustomerNotExist(customer)) {
			return CreateResponseCustomerNotExist();
		}

		saveCustomer(0, null, customer);

		result = true;

		message = "Registro eliminado.";

		return new ResponseDTO(data, message, result);
	}

	@Override
	@Transactional
	public ResponseDTO active(Integer Id) {
		Customer customer = findById(Id);

		if (CheckCustomerNotExist(customer)) {
			return CreateResponseCustomerNotExist();
		}

		saveCustomer(1, null, customer);

		result = true;

		message = "Registro Activado.";

		return new ResponseDTO(data, message, result);

	}

	private boolean CheckCustomerNotExist(Customer customer) {
		boolean response = false;

		if (customer == null) {
			response = true;
		}

		return response;
	}

	private ResponseDTO CreateResponseCustomerNotExist() {
		message = "No existe un cliente con el id proporcionado.";

		return new ResponseDTO(data, message, result);
	}

	private Customer saveCustomer(Integer customer_flag, CustomerDTO customerDTO, Customer customer) {

		if (customerDTO != null) {
			if (customerDTO.getName() != null && !customerDTO.getName().isEmpty()) {
				customer.setName(customerDTO.getName());
			}
			if (customerDTO.getPaternalLastName() != null && !customerDTO.getPaternalLastName().isEmpty()) {
				customer.setPaternalLastName(customerDTO.getPaternalLastName());
			}
			if (customerDTO.getMotherLastName() != null && !customerDTO.getMotherLastName().isEmpty()) {
				customer.setMotherLastName(customerDTO.getMotherLastName());
			}
			if (customerDTO.getBirthday() != null) {
				customer.setBirthday(customerDTO.getBirthday());
			}
			if (customerDTO.getAge() != null) {
				customer.setAge(customerDTO.getAge());
			}
			if (customerDTO.getCurp() != null && !customerDTO.getCurp().isEmpty()) {
				customer.setCurp(customerDTO.getCurp());
			}
			if (customerDTO.getRfc() != null && !customerDTO.getRfc().isEmpty()) {
				customer.setRfc(customerDTO.getRfc());
			}
			if (customerDTO.getEmail() != null && !customerDTO.getEmail().isEmpty()) {
				customer.setEmail(customerDTO.getEmail());
			}
			if (customerDTO.getStreetAndNumber() != null && !customerDTO.getStreetAndNumber().isEmpty()) {
				customer.setStreetAndNumber(customerDTO.getStreetAndNumber());
			}
			if (customerDTO.getStateOfBirth_id() != null) {
				LocationState state = _locationStateDao.findById(customerDTO.getStateOfBirth_id()).orElse(null);
				customer.setStateOfBirth(state);
			}
			if (customerDTO.getGender_id() != null) {
				CustomerGender gender =  _customerGenderDao.findById(customerDTO.getGender_id()).orElse(null);
				customer.setGender(gender);
			}
			if (customerDTO.getColony_id() != null) {
				LocationSuburb suburb = _locationSuburbDao.findById(customerDTO.getColony_id()).orElse(null);
				customer.setSuburb(suburb);
			}
		}

		customer.setStatus_flag(customer_flag);

		customer.setMdfd_on(new Date());

		return _customerDao.save(customer);
	}

	private boolean CheckDuplicatedCustomer(Integer id, Customer CustomerByCurp) {

		boolean response = false;

		if (CustomerByCurp != null && CustomerByCurp.getIdCustomer() != id) {
			response = true;
		}

		return response;
	}

	private ResponseDTO CreateResonseDuplicatedCustomer(Customer customerByCurp) {

		if (customerByCurp.getStatus_flag() == 0) {
			message = "Ya existe un cliente con el curp: " + customerByCurp.getCurp() + " y se encuentra inactivo ID "
					+ customerByCurp.getIdCustomer();
		} else {
			message = "Ya existe un cliente con el curp: " + customerByCurp.getCurp();
		}

		return new ResponseDTO(data, message, result);
	}
}
