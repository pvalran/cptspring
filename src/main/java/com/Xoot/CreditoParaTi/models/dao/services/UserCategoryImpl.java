package com.Xoot.CreditoParaTi.models.dao.services;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.Xoot.CreditoParaTi.Definiciones.Services.IUserCategoryService;
import com.Xoot.CreditoParaTi.entity.UsuarioCategory;
import com.Xoot.CreditoParaTi.entity.DTO.CatalogoDTO;
import com.Xoot.CreditoParaTi.entity.DTO.ResponseDTO;
import com.Xoot.CreditoParaTi.models.dao.IUserCategoryDao;


@Service
public class UserCategoryImpl implements IUserCategoryService {
	
	@Autowired
	private IUserCategoryDao categoryUserDao;
	public Object data;
	public String message;
	public Boolean result;
	
	@Override
	@Transactional(readOnly = true)
	public List<UsuarioCategory> findAllActive() {
		return categoryUserDao.findAllActive();
	}

	@Override
	@Transactional(readOnly = true)
	public UsuarioCategory findById(Integer id) {
		return categoryUserDao.findById(id).orElse(null);
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

	@Override
	public UsuarioCategory findByName(String name) {
		return categoryUserDao.findByName(name);
	}
	
	@Override
	@Transactional(readOnly = true)
	public ResponseDTO getById(Integer id) {
		UsuarioCategory category = findById(id);

		if (category == null) {
			data = null;
			result = false;
			message = "No existe una categoria de usuario con el id proporcionado.";
		} else {
			data = category;
			result = true;
			message = "Exito";
		}
		
		return new ResponseDTO(data, message, result);
	}
	
	@Override
	@Transactional
	public ResponseDTO save(CatalogoDTO catalogo) {
		UsuarioCategory categoryExist = findByName(catalogo.getName());
		
		if (categoryExist != null) {
			data = null;
			result = false;
			message = "Ya se registro una categoria de usuario con el nombre " + catalogo.getName();
		} else {
			UsuarioCategory category = new UsuarioCategory();
			category.setStatus_flag(1);
			category.setName(catalogo.getName());

			data = categoryUserDao.save(category);
			result = true;
			message = "Registro creado.";
		}
		return new ResponseDTO(data, message, result);
	}

	@Override
	@Transactional
	public ResponseDTO update(Integer id, CatalogoDTO catalogo) {
		UsuarioCategory categoryActual = findById(id);
		UsuarioCategory categoryExist = findByName(catalogo.getName());
		
		data = null;
		result = false;
		
		if (categoryActual == null) {	
			message = "No existe una categoria de usuario con el id proporcionado.";
		} else if (categoryExist != null && categoryExist.getCategory_id() != id) {
			message = "Ya se registro una categoria de usuario con el nombre " + catalogo.getName();
		} else {
			categoryActual.setName(catalogo.getName());
			categoryActual.setMdfd_on(new Date());

			data = categoryUserDao.save(categoryActual);
			result = true;
			message = "Registro actualizado.";
		}
		return new ResponseDTO(data, message, result);
	}
	
	@Override
	@Transactional
	public ResponseDTO delete(Integer id) {
		UsuarioCategory category = findById(id);

		if (category == null) {
			data = null;
			result = false;
			message = "No existe una categoria de usuario con el id proporcionado.";
		} else {
			category.setStatus_flag(0);
			category.setMdfd_on(new Date());
			categoryUserDao.save(category);

			data = null;
			result = true;
			message = "Registro eliminado.";
		}
		
		return new ResponseDTO(data, message, result);
	}
	
	
}
