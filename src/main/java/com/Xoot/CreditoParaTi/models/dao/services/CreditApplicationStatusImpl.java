package com.Xoot.CreditoParaTi.models.dao.services;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.Xoot.CreditoParaTi.Definiciones.Services.ICreditApplicationStatusService;
import com.Xoot.CreditoParaTi.entity.CreditApplicationStatus;
import com.Xoot.CreditoParaTi.entity.DTO.CatalogoDTO;
import com.Xoot.CreditoParaTi.entity.DTO.ResponseDTO;
import com.Xoot.CreditoParaTi.models.dao.ICreditApplicationStatusDao;

@Service
public class CreditApplicationStatusImpl implements ICreditApplicationStatusService {

	public Object data = null;
	public Boolean result = false;
	public String message;

	@Autowired
	private ICreditApplicationStatusDao _creditApplicationStatusDao;

	@Override
	@Transactional(readOnly = true)
	public List<CreditApplicationStatus> findAllActive() {
		return _creditApplicationStatusDao.findAllActive();
	}

	@Override
	@Transactional(readOnly = true)
	public CreditApplicationStatus findById(Integer id) {
		return _creditApplicationStatusDao.findById(id).orElse(null);
	}

	@Override
	@Transactional(readOnly = true)
	public CreditApplicationStatus findByName(String name) {
		return _creditApplicationStatusDao.findByNameActive(name);
	}

	@Override
	@Transactional(readOnly = true)
	public ResponseDTO getById(Integer id) {
		CreditApplicationStatus creditApplicationStatus = findById(id);

		if(CheckStatusNotExist(creditApplicationStatus)) {
			return CreateResponseStatusNotExist();
		}
	
		data = creditApplicationStatus;
		result = true;
		message = "Exito";
		
		return new ResponseDTO(data, message, result);
	}

	@Override
	@Transactional
	public ResponseDTO save(CatalogoDTO catalogo) {
		CreditApplicationStatus CreditaApplicationStatusByName = _creditApplicationStatusDao.findByName(catalogo.getName());

		if (CreditaApplicationStatusByName != null) {
			return CreateResonseDuplicatedStatus(CreditaApplicationStatusByName);
		} 
		
		CreditApplicationStatus creditApplicationStatus = new CreditApplicationStatus();

		data = saveCreditApplicationStatus(1,catalogo.getName(),creditApplicationStatus);

		result = true;

		message = "Registro creado.";
		
		return new ResponseDTO(data, message, result);
	}

	@Override
	@Transactional
	public ResponseDTO update(Integer id, CatalogoDTO catalogo) {

		CreditApplicationStatus CreditaApplicationStatusById = findById(id);

		CreditApplicationStatus CreditaApplicationStatusByName = _creditApplicationStatusDao.findByName(catalogo.getName());

		if(CheckStatusNotExist(CreditaApplicationStatusById)) {
			return CreateResponseStatusNotExist();
		}

		if(CheckDuplicatedStatus(id, catalogo, CreditaApplicationStatusByName)) {
			return CreateResonseDuplicatedStatus(CreditaApplicationStatusByName);
		}

		data = saveCreditApplicationStatus(1,catalogo.getName(),CreditaApplicationStatusById);

		result = true;

		message = "Registro actualizado.";

		return new ResponseDTO(data, message, result);
	}

	@Override
	@Transactional
	public ResponseDTO delete(Integer id) {
		CreditApplicationStatus creditApplicationStatus = findById(id);
		
		if(CheckStatusNotExist(creditApplicationStatus)) {
			return CreateResponseStatusNotExist();
		}

		saveCreditApplicationStatus(0,null,creditApplicationStatus);

		result = true;

		message = "Registro eliminado.";

		return new ResponseDTO(data, message, result);
	}

	@Override
	@Transactional
	public ResponseDTO active(Integer Id) {
		CreditApplicationStatus CreditaApplicationStatusByName = findById(Id);
		
		if(CheckStatusNotExist(CreditaApplicationStatusByName)) {
			return CreateResponseStatusNotExist();
		}
		
		saveCreditApplicationStatus(1,null,CreditaApplicationStatusByName);

		result = true;

		message = "Registro Activado.";

		return new ResponseDTO(data, message, result);
		
	}
	 
	private boolean CheckStatusNotExist(CreditApplicationStatus creditApplicationStatus) {
		boolean response = false;
		
		if (creditApplicationStatus == null) {
			response = true;
		}
		
		return response;
	}
	
	private ResponseDTO CreateResponseStatusNotExist() {
		message = "No existe un Status con el id proporcionado.";
		
		return new ResponseDTO(data, message, result);
	}
	
	private CreditApplicationStatus saveCreditApplicationStatus(Integer Status_flag, String StatusName, CreditApplicationStatus creditApplicationStatus ) {

	    if( StatusName != null && !StatusName.isEmpty()) {
	    	creditApplicationStatus.setName(StatusName);
	    }
	    
	    creditApplicationStatus.setStatus_flag(Status_flag);
		
	    creditApplicationStatus.setMdfd_on(new Date());
		
		return _creditApplicationStatusDao.save(creditApplicationStatus);
	}

	private boolean CheckDuplicatedStatus(Integer id, CatalogoDTO catalogo,
			CreditApplicationStatus CreditaApplicationStatusByName) {
		
		boolean response = false;

		if (CreditaApplicationStatusByName != null
				&& CreditaApplicationStatusByName.getIdCreditAplicationStatus() != id) {
			response = true;		
		}
		
		return response;
	}
	
	private ResponseDTO CreateResonseDuplicatedStatus(CreditApplicationStatus CreditaApplicationStatusByName) {
		
		if(CreditaApplicationStatusByName.getStatus_flag() == 0) {
			message = "Ya existe un registro Status con el nombre: " + CreditaApplicationStatusByName.getName() + " y se encuentra inactivo ID " + CreditaApplicationStatusByName.getIdCreditAplicationStatus();
		}else {
			message = "Ya existe un registro Status con el nombre: " + CreditaApplicationStatusByName.getName();
		}
	
		return new ResponseDTO(data, message, result);
	}
}
