package com.Xoot.CreditoParaTi.models.dao.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.Xoot.CreditoParaTi.Definiciones.Services.IUserCategoryService;
import com.Xoot.CreditoParaTi.entity.UsuarioCategory;
import com.Xoot.CreditoParaTi.models.dao.IUserCategoryDao;


@Service
public class UserCategoryImpl implements IUserCategoryService {
	
	@Autowired
	private IUserCategoryDao categoryUserDao;
	
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
	@Transactional
	public UsuarioCategory save(UsuarioCategory CategoryUser) {
		return categoryUserDao.save(CategoryUser);
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
}
