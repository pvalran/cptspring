package com.Xoot.CreditoParaTi.services.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.Xoot.CreditoParaTi.services.interfaces.ICreditApplicationProductService;
import com.Xoot.CreditoParaTi.services.interfaces.IDocumentTypeService;
import com.Xoot.CreditoParaTi.entity.app.CreditApplicationProduct;
import com.Xoot.CreditoParaTi.dto.CreditApplicationProductDTO;
import com.Xoot.CreditoParaTi.dto.ResponseDTO;
import com.Xoot.CreditoParaTi.repositories.app.ICreditApplicationProductDao;

@Service
public class CreditApplicationProductImpl implements ICreditApplicationProductService {
	public Object data = null;
	public Boolean result = false;
	public String message;

	@Autowired
	private ICreditApplicationProductDao _CreditAplicationProductDao;
	@Autowired
	private IDocumentTypeService _documentTypeService;

	@Override
	@Transactional(readOnly = true)
	public List<CreditApplicationProduct> findAllActive() {
		return _CreditAplicationProductDao.findAllActive();
	}

	@Override
	@Transactional(readOnly = true)
	public CreditApplicationProduct findById(Integer id) {
		return _CreditAplicationProductDao.findById(id).orElse(null);
	}

	@Override
	@Transactional(readOnly = true)
	public CreditApplicationProduct findByName(String name) {
		return _CreditAplicationProductDao.findByNameActive(name);
	}

	@Override
	@Transactional(readOnly = true)
	public ResponseDTO getById(Integer id) {
		CreditApplicationProduct CreditAplicationProduct = findById(id);

		if(CheckProductoNotExist(CreditAplicationProduct)) {
			return CreateResponseProductoNotExist();
		}
	
		data = CreditAplicationProduct;
		result = true;
		message = "Exito";
		
		return new ResponseDTO(data, message, result);
	}

	@Override
	@Transactional
	public ResponseDTO save(CreditApplicationProductDTO creditApplicationProductDTO) {
		CreditApplicationProduct CreditaApplicationProductoByName = _CreditAplicationProductDao.findByName(creditApplicationProductDTO.getName());

		if (CreditaApplicationProductoByName != null) {
			return CreateResonseDuplicatedProducto(CreditaApplicationProductoByName);
		} 
		
		CreditApplicationProduct CreditAplicationProduct = new CreditApplicationProduct();

		data = saveCreditAplicationProduct(1,creditApplicationProductDTO,CreditAplicationProduct);

		result = true;

		message = "Registro creado.";
		
		return new ResponseDTO(data, message, result);
	}

	@Override
	@Transactional
	public ResponseDTO update(Integer id, CreditApplicationProductDTO creditApplicationProductDTO) {

		CreditApplicationProduct CreditaApplicationProductoById = findById(id);

		CreditApplicationProduct CreditaApplicationProductoByName = _CreditAplicationProductDao.findByName(creditApplicationProductDTO.getName());

		if(CheckProductoNotExist(CreditaApplicationProductoById)) {
			return CreateResponseProductoNotExist();
		}

		if(CheckDuplicatedProducto(id, creditApplicationProductDTO, CreditaApplicationProductoByName)) {
			return CreateResonseDuplicatedProducto(CreditaApplicationProductoByName);
		}

		data = saveCreditAplicationProduct(1,creditApplicationProductDTO,CreditaApplicationProductoById);

		result = true;

		message = "Registro actualizado.";

		return new ResponseDTO(data, message, result);
	}

	@Override
	@Transactional
	public ResponseDTO delete(Integer id) {
		CreditApplicationProduct CreditAplicationProduct = findById(id);
		
		if(CheckProductoNotExist(CreditAplicationProduct)) {
			return CreateResponseProductoNotExist();
		}

		saveCreditAplicationProduct(0,null,CreditAplicationProduct);

		result = true;

		message = "Registro eliminado.";

		return new ResponseDTO(data, message, result);
	}

	@Override
	@Transactional
	public ResponseDTO active(Integer Id) {
		CreditApplicationProduct CreditaApplicationProductoByName = findById(Id);
		
		if(CheckProductoNotExist(CreditaApplicationProductoByName)) {
			return CreateResponseProductoNotExist();
		}
		
		saveCreditAplicationProduct(1,null,CreditaApplicationProductoByName);

		result = true;

		message = "Registro Activado.";

		return new ResponseDTO(data, message, result);
		
	}
	 
	private boolean CheckProductoNotExist(CreditApplicationProduct CreditAplicationProduct) {
		boolean response = false;
		
		if (CreditAplicationProduct == null) {
			response = true;
		}
		
		return response;
	}
	
	private ResponseDTO CreateResponseProductoNotExist() {
		message = "No existe un Producto con el id proporcionado.";
		
		return new ResponseDTO(data, message, result);
	}
	
	private CreditApplicationProduct saveCreditAplicationProduct(Integer Producto_flag, 
			CreditApplicationProductDTO creditApplicationProductDTO, 
			CreditApplicationProduct creditAplicationProduct ) {

	    if( creditApplicationProductDTO.getName() != null && !creditApplicationProductDTO.getName().isEmpty()) {
	    	creditAplicationProduct.setName(creditApplicationProductDTO.getName());
	    }
	    

	    
	    creditAplicationProduct.setStatus_flag(Producto_flag);
		
	    creditAplicationProduct.setMdfd_on(new Date());
		
		return _CreditAplicationProductDao.save(creditAplicationProduct);
	}

	private boolean CheckDuplicatedProducto(Integer id, CreditApplicationProductDTO creditApplicationProductDTO,
			CreditApplicationProduct CreditaApplicationProductoByName) {
		
		boolean response = false;

		if (CreditaApplicationProductoByName != null
				&& CreditaApplicationProductoByName.getIdProduct() != id) {
			response = true;		
		}
		
		return response;
	}
	
	private ResponseDTO CreateResonseDuplicatedProducto(CreditApplicationProduct CreditaApplicationProductoByName) {
		
		if(CreditaApplicationProductoByName.getStatus_flag() == 0) {
			message = "Ya existe un registro Producto con el nombre: " + CreditaApplicationProductoByName.getName() + " y se encuentra inactivo ID " + CreditaApplicationProductoByName.getIdProduct();
		}else {
			message = "Ya existe un registro Producto con el nombre: " + CreditaApplicationProductoByName.getName();
		}
	
		return new ResponseDTO(data, message, result);
	}

}
