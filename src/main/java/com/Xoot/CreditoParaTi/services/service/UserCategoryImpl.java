package com.Xoot.CreditoParaTi.services.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.Xoot.CreditoParaTi.services.interfaces.IUserCategoryService;
import com.Xoot.CreditoParaTi.entity.app.UsuarioCategory;
import com.Xoot.CreditoParaTi.dto.CatalogoDTO;
import com.Xoot.CreditoParaTi.dto.ResponseDTO;
import com.Xoot.CreditoParaTi.repositories.app.IUserCategoryDao;


@Service
public class UserCategoryImpl implements IUserCategoryService {
	public Object data = null;
	public Boolean result = false;
	public String message;

	@Autowired
	private IUserCategoryDao _userCategoryDao;

	@Override
	@Transactional(readOnly = true)
	public List<UsuarioCategory> findAllActive() {
		return _userCategoryDao.findAllActive();
	}

	@Override
	@Transactional(readOnly = true)
	public UsuarioCategory findById(Integer id) {
		return _userCategoryDao.findById(id).orElse(null);
	}

	@Override
	@Transactional(readOnly = true)
	public UsuarioCategory findByName(String name) {
		return _userCategoryDao.findByNameActive(name);
	}

	@Override
	@Transactional(readOnly = true)
	public ResponseDTO getById(Integer id) {
		UsuarioCategory usuarioCategory = findById(id);

		if(CheckUserCategoryNotExist(usuarioCategory)) {
			return CreateResponseUserCategoryNotExist();
		}
	
		data = usuarioCategory;
		result = true;
		message = "Exito";
		
		return new ResponseDTO(data, message, result);
	}

	@Override
	@Transactional
	public ResponseDTO save(CatalogoDTO catalogo) {
		UsuarioCategory usuarioCategoryByName = _userCategoryDao.findByName(catalogo.getName());

		if (usuarioCategoryByName != null) {
			return CreateResonseDuplicatedUserCategory(usuarioCategoryByName);
		} 
		
		UsuarioCategory userCategory = new UsuarioCategory();

		data = saveUsuarioCategory(1,catalogo.getName(),userCategory);

		result = true;

		message = "Registro creado.";
		
		return new ResponseDTO(data, message, result);
	}

	@Override
	@Transactional
	public ResponseDTO update(Integer id, CatalogoDTO catalogo) {

		UsuarioCategory userCategoryById = findById(id);

		UsuarioCategory userCategoryByName = _userCategoryDao.findByName(catalogo.getName());

		if(CheckUserCategoryNotExist(userCategoryById)) {
			return CreateResponseUserCategoryNotExist();
		}

		if(CheckDuplicatedProducto(id, catalogo, userCategoryByName)) {
			return CreateResonseDuplicatedUserCategory(userCategoryByName);
		}

		data = saveUsuarioCategory(1,catalogo.getName(),userCategoryById);

		result = true;

		message = "Registro actualizado.";

		return new ResponseDTO(data, message, result);
	}

	@Override
	@Transactional
	public ResponseDTO delete(Integer id) {
		UsuarioCategory userCategory = findById(id);
		
		if(CheckUserCategoryNotExist(userCategory)) {
			return CreateResponseUserCategoryNotExist();
		}

		saveUsuarioCategory(0,null,userCategory);

		result = true;

		message = "Registro eliminado.";

		return new ResponseDTO(data, message, result);
	}

	@Override
	@Transactional
	public ResponseDTO active(Integer Id) {
		UsuarioCategory userCategoryByName = findById(Id);
		
		if(CheckUserCategoryNotExist(userCategoryByName)) {
			return CreateResponseUserCategoryNotExist();
		}
		
		saveUsuarioCategory(1,null,userCategoryByName);

		result = true;

		message = "Registro Activado.";

		return new ResponseDTO(data, message, result);
		
	}
	 
	@Override
	@Transactional(readOnly = true)
	public List<UsuarioCategory> getlistCategory(List<Integer> lstIds) {
		List<UsuarioCategory> lstRetorno = new ArrayList<>();
		UsuarioCategory cat;
		for (Integer p:lstIds) {
			cat = findById(p);
			lstRetorno.add(cat);
		}
		
		return lstRetorno;
	}
	
	private boolean CheckUserCategoryNotExist(UsuarioCategory usuarioCategory) {
		boolean response = false;
		
		if (usuarioCategory == null) {
			response = true;
		}
		
		return response;
	}
	
	private ResponseDTO CreateResponseUserCategoryNotExist() {
		message = "No existe una categoria de usuario con el id proporcionado.";
		
		return new ResponseDTO(data, message, result);
	}
	
	private UsuarioCategory saveUsuarioCategory(Integer userCategory_flag, String userCategoryName, UsuarioCategory userCategory ) {

	    if( userCategoryName != null && !userCategoryName.isEmpty()) {
	    	userCategory.setName(userCategoryName);
	    }
	    
	    userCategory.setStatus_flag(userCategory_flag);
		
	    userCategory.setMdfd_on(new Date());
		
		return _userCategoryDao.save(userCategory);
	}

	private boolean CheckDuplicatedProducto(Integer id, CatalogoDTO catalogo,
			UsuarioCategory userCategoryByName) {
		
		boolean response = false;

		if (userCategoryByName != null
				&& userCategoryByName.getCategory_id() != id) {
			response = true;		
		}
		
		return response;
	}
	
	private ResponseDTO CreateResonseDuplicatedUserCategory(UsuarioCategory userCategoryByName) {
		
		if(userCategoryByName.getStatus_flag() == 0) {
			message = "Ya existe una categoria de usuario con el nombre: " + userCategoryByName.getName() + " y se encuentra inactivo ID " + userCategoryByName.getCategory_id();
		}else {
			message = "Ya existe una categoria de usuario con el nombre: " + userCategoryByName.getName();
		}
	
		return new ResponseDTO(data, message, result);
	}

}

