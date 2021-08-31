package com.Xoot.CreditoParaTi.services.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import com.Xoot.CreditoParaTi.dto.CustomerTransactionDTO;
import com.Xoot.CreditoParaTi.dto.DocumentDTO;
import com.Xoot.CreditoParaTi.entity.AdditionalInformation;
import com.Xoot.CreditoParaTi.entity.CreditApplication;
import com.Xoot.CreditoParaTi.entity.transaction;
import com.Xoot.CreditoParaTi.repositories.interfaces.*;
import com.Xoot.CreditoParaTi.utils.DocumentUtil;
import com.Xoot.CreditoParaTi.utils.TransactionUtil;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.Xoot.CreditoParaTi.services.interfaces.ICustomerService;
import com.Xoot.CreditoParaTi.entity.Customer;
import com.Xoot.CreditoParaTi.dto.CustomerDTO;
import com.Xoot.CreditoParaTi.dto.ResponseDTO;

@Service
public class CustomerImpl implements ICustomerService {
	public Object data = null;
	public Boolean result = false;
	public String message;
	public TransactionUtil transactionUtil;

	@Autowired
	private ICustomerDao _customerDao;
	@Autowired
	private ICustomerGenderDao _customerGenderDao;
	@Autowired
	private ILocationStateDao _locationStateDao;
	@Autowired
	private ILocationSuburbDao _locationSuburbDao;
	@Autowired
	private ITransactionDao transactionDao;
	@Autowired
	private ICreditApplicationDao creditApplicationDao;
	@Autowired
	private IAdditionalInformationDao additionalInformationDao;




	@Autowired
	private ModelMapper modelMapper;

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

	@Transactional(readOnly = true)
	public ResponseDTO getByCustomerTransaction() {
		TransactionUtil ObjTransUtil = new TransactionUtil();
		List<CreditApplication> creditApplications = creditApplicationDao.findAllActive();

		List<CustomerTransactionDTO> customerTransactions;
		HashMap<Integer,String> map = new HashMap<>();
		map.put (1, "A");
		map.put (2, "R");

		customerTransactions = new ArrayList<CustomerTransactionDTO>();
		for(CreditApplication creditApplication:creditApplications){
			if (creditApplication.getCustomer() != null ){
				CustomerTransactionDTO customerTransactionDTO = new CustomerTransactionDTO();
				Customer customer = _customerDao.findById(creditApplication.getCustomer()).orElse(null);
				customerTransactionDTO.setCustomer(
						modelMapper.map(customer, CustomerDTO.class)
				);
				AdditionalInformation additional =  additionalInformationDao.findByCreditId(creditApplication.getCreditId());
				List<transaction> transactions = transactionDao.findByCreditID(creditApplication.getCreditId());
				customerTransactionDTO.setLayerDocument("");
				customerTransactionDTO.setLayerBiometric("");
				customerTransactionDTO.setLayerGobernment("");
				for (transaction Transaction : transactions) {
					switch (Transaction.getTransactionType()) {
						case 1:
							customerTransactionDTO.setLayerDocument(map.get(Transaction.getTransactionStatus()));
							break;
						case 2:
							customerTransactionDTO.setLayerBiometric(map.get(Transaction.getTransactionStatus()));
							break;
						case 3:
							customerTransactionDTO.setLayerGobernment(map.get(Transaction.getTransactionStatus()));
							break;
					}
				}

				if (customerTransactionDTO.getLayerDocument() == "R" || customerTransactionDTO.getLayerBiometric() == "R") {
					customerTransactionDTO.setStatus("R");
				} else if (customerTransactionDTO.getLayerDocument() == "A" && customerTransactionDTO.getLayerBiometric() == "A") {
					if (customerTransactionDTO.getLayerGobernment() == "A") {
						customerTransactionDTO.setStatus("A");
					} else {
						customerTransactionDTO.setStatus("P");
					}
				} else {
					customerTransactionDTO.setStatus("R");
				}

				if (additional != null) {
					customerTransactionDTO.setMobile(additional.getMobile());
				} else {
					customerTransactionDTO.setMobile("");
				}
				customerTransactionDTO.setCrtd_on(creditApplication.getCrtd_on());
				customerTransactions.add(customerTransactionDTO);
			}
		}

		data = customerTransactions;
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

		data =  modelMapper.map(saveCustomer(1, customerDTO, customerById), CustomerDTO.class);

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
				customer.setStateOfBirth_id(customerDTO.getStateOfBirth_id());
			}
			if (customerDTO.getGender_id() != null) {
				customer.setGender_id(customerDTO.getGender_id());
			}
			if (customerDTO.getColony_id() != null) {
				customer.setColony_id(customerDTO.getColony_id());
			}

			if (customerDTO.getState_id() != null) {
				customer.setState_id(customerDTO.getState_id());
			}

			if (customerDTO.getCreditId() != null) {
				customer.setCreditId(customerDTO.getCreditId());
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

		return new ResponseDTO(customerByCurp, message, result);
	}
}
