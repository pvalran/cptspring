package com.Xoot.CreditoParaTi.models.dao.services;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.Xoot.CreditoParaTi.Definiciones.Services.ICreditApplicationService;
import com.Xoot.CreditoParaTi.entity.CreditApplication;
import com.Xoot.CreditoParaTi.entity.CreditApplicationProduct;
import com.Xoot.CreditoParaTi.entity.CreditApplicationStatus;
import com.Xoot.CreditoParaTi.entity.Customer;
import com.Xoot.CreditoParaTi.entity.Usuario;
import com.Xoot.CreditoParaTi.entity.DTO.CreditApplicationDTO;
import com.Xoot.CreditoParaTi.entity.DTO.ResponseDTO;
import com.Xoot.CreditoParaTi.models.dao.ICreditApplicationDao;
import com.Xoot.CreditoParaTi.models.dao.ICreditApplicationProductDao;
import com.Xoot.CreditoParaTi.models.dao.ICreditApplicationStatusDao;
import com.Xoot.CreditoParaTi.models.dao.ICustomerDao;
import com.Xoot.CreditoParaTi.models.dao.IUserDao;

@Service
public class CreditApplicationImpl implements ICreditApplicationService{
	public Object data = null;
	public Boolean result = false;
	public String message;
	
	@Autowired
	private ICreditApplicationDao _creditApplicationDao;
	@Autowired
	private ICreditApplicationProductDao _creditApplicationProductDao;
	@Autowired
	private ICreditApplicationStatusDao _creditApplicationStatusDao;
	@Autowired
	private ICustomerDao _customerDao;
	@Autowired
	private IUserDao _userDao;
		
	@Override
	@Transactional(readOnly = true)
	public CreditApplication findById(Integer id) {
		return _creditApplicationDao.findById(id).orElse(null);
	}

	@Override
	@Transactional(readOnly = true)
	public List<CreditApplication> findAllActive() {
		return _creditApplicationDao.findAllActive();
	}

	@Override
	@Transactional(readOnly = true)
	public List<CreditApplication> getAllByUser(Integer idUser) {
		return _creditApplicationDao.getAllByUser(idUser);
	}
	
	@Override
	@Transactional(readOnly = true)
	public ResponseDTO getById(Integer id) {
		CreditApplication creditApplication = findById(id);

		if (CheckCreditApplicationNotExist(creditApplication)) {
			return CreateResponseCreditApplicationNotExist();
		}

		data = creditApplication;
		result = true;
		message = "Exito";

		return new ResponseDTO(data, message, result);
	}


	@Override
	@Transactional
	public ResponseDTO save(CreditApplicationDTO creditApplicationDTO) {

		CreditApplication creditApplication = new CreditApplication();

		data = saveCreditApplication(1, creditApplicationDTO, creditApplication);

		result = true;

		message = "Registro creado.";

		return new ResponseDTO(data, message, result);
	}

	@Override
	@Transactional
	public ResponseDTO update(Integer id, CreditApplicationDTO creditApplicationDTO) {
		CreditApplication creditApplicationById = findById(id);

		if (CheckCreditApplicationNotExist(creditApplicationById)) {
			return CreateResponseCreditApplicationNotExist();
		}

		data = saveCreditApplication(1, creditApplicationDTO, creditApplicationById);

		result = true;

		message = "Registro actualizado.";

		return new ResponseDTO(data, message, result);
	}

	@Override
	@Transactional
	public ResponseDTO active(Integer id) {
		CreditApplication creditApplication = findById(id);

		if (CheckCreditApplicationNotExist(creditApplication)) {
			return CreateResponseCreditApplicationNotExist();
		}

		saveCreditApplication(1, null, creditApplication);

		result = true;

		message = "Registro Activado.";

		return new ResponseDTO(data, message, result);
	}

	@Override
	@Transactional
	public ResponseDTO delete(Integer id) {
		CreditApplication creditApplication = findById(id);

		if (CheckCreditApplicationNotExist(creditApplication)) {
			return CreateResponseCreditApplicationNotExist();
		}

		saveCreditApplication(0, null, creditApplication);

		result = true;

		message = "Registro eliminado.";

		return new ResponseDTO(data, message, result);
	}
	
	@Override
	@Transactional
	public ResponseDTO addCustomer(Integer id, CreditApplicationDTO creditApplicationDTO) {
		CreditApplication creditApplicationById = findById(id);

		if (CheckCreditApplicationNotExist(creditApplicationById)) {
			return CreateResponseCreditApplicationNotExist();
		}
		
		
		Customer customer = _customerDao.findById(creditApplicationDTO.getCustomer()).orElse(null);
		
		if(customer != null) {
			creditApplicationById.setCustomer(customer);
			
			data = saveCreditApplication(1, creditApplicationDTO, creditApplicationById);
			result = true;
			message = "Usuario agregado a la solicitud de credito.";
		}else {
			data = null;
			result = false;
			message = "No existe el usuario.";
		}
		

		return new ResponseDTO(data, message, result);
	}
	
	private boolean CheckCreditApplicationNotExist(CreditApplication creditApplication) {
		boolean response = false;

		if (creditApplication == null) {
			response = true;
		}

		return response;
	}

	private ResponseDTO CreateResponseCreditApplicationNotExist() {
		message = "No existe una solicitud de credito con el id proporcionado.";

		return new ResponseDTO(data, message, result);
	}

	private CreditApplication saveCreditApplication(Integer creditApplication_flag, CreditApplicationDTO creditApplicationDTO, CreditApplication creditApplication) {

		if (creditApplicationDTO != null) {
			if (creditApplicationDTO.getProduct() != null) {
				CreditApplicationProduct product = _creditApplicationProductDao.findById(creditApplicationDTO.getCustomer()).orElse(null);
				creditApplication.setProduct(product);
			}
			if (creditApplicationDTO.getStatus() != null) {
				CreditApplicationStatus status = _creditApplicationStatusDao.findById(creditApplicationDTO.getCustomer()).orElse(null);
				creditApplication.setStatus(status);
			}
			if (creditApplicationDTO.getCustomer() != null) {
				Customer customer = _customerDao.findById(creditApplicationDTO.getCustomer()).orElse(null);
				creditApplication.setCustomer(customer);
			}
			if (creditApplicationDTO.getUser() != null) {
				Usuario user = _userDao.findById(creditApplicationDTO.getUser()).orElse(null);
				creditApplication.setUser(user);
			}
		}

		creditApplication.setStatus_flag(creditApplication_flag);

		creditApplication.setMdfd_on(new Date());

		return _creditApplicationDao.save(creditApplication);
	}

}
