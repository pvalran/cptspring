package com.Xoot.CreditoParaTi.models.dao.services;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.Xoot.CreditoParaTi.Definiciones.Services.ICreditApplicationProductService;
import com.Xoot.CreditoParaTi.entity.CreditApplicationProduct;
import com.Xoot.CreditoParaTi.entity.DTO.CatalogoDTO;
import com.Xoot.CreditoParaTi.entity.DTO.ResponseDTO;
import com.Xoot.CreditoParaTi.models.dao.ICreditApplicationProductDao;

@Service
public class CreditApplicationProductImpl implements ICreditApplicationProductService {
	public Object data = null;
	public Boolean result = false;
	public String message;

	@Autowired
	private ICreditApplicationProductDao _CreditAplicationProductDao;

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
	public ResponseDTO save(CatalogoDTO catalogo) {
		CreditApplicationProduct CreditaApplicationProductoByName = _CreditAplicationProductDao.findByName(catalogo.getName());

		if (CreditaApplicationProductoByName != null) {
			return CreateResonseDuplicatedProducto(CreditaApplicationProductoByName);
		} 
		
		CreditApplicationProduct CreditAplicationProduct = new CreditApplicationProduct();

		data = saveCreditAplicationProduct(1,catalogo.getName(),CreditAplicationProduct);

		result = true;

		message = "Registro creado.";
		
		return new ResponseDTO(data, message, result);
	}

	@Override
	@Transactional
	public ResponseDTO update(Integer id, CatalogoDTO catalogo) {

		CreditApplicationProduct CreditaApplicationProductoById = findById(id);

		CreditApplicationProduct CreditaApplicationProductoByName = _CreditAplicationProductDao.findByName(catalogo.getName());

		if(CheckProductoNotExist(CreditaApplicationProductoById)) {
			return CreateResponseProductoNotExist();
		}

		if(CheckDuplicatedProducto(id, catalogo, CreditaApplicationProductoByName)) {
			return CreateResonseDuplicatedProducto(CreditaApplicationProductoByName);
		}

		data = saveCreditAplicationProduct(1,catalogo.getName(),CreditaApplicationProductoById);

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
	
	private CreditApplicationProduct saveCreditAplicationProduct(Integer Producto_flag, String ProductoName, CreditApplicationProduct CreditAplicationProduct ) {

	    if( ProductoName != null && !ProductoName.isEmpty()) {
	    	CreditAplicationProduct.setName(ProductoName);
	    }
	    
	    CreditAplicationProduct.setStatus_flag(Producto_flag);
		
	    CreditAplicationProduct.setMdfd_on(new Date());
		
		return _CreditAplicationProductDao.save(CreditAplicationProduct);
	}

	private boolean CheckDuplicatedProducto(Integer id, CatalogoDTO catalogo,
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
