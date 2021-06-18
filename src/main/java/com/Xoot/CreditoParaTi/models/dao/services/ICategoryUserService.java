package com.Xoot.CreditoParaTi.models.dao.services;

import java.util.List;

import com.Xoot.CreditoParaTi.entity.CategoryUser;

public interface ICategoryUserService {
	public List<CategoryUser> findAll();
	
	public CategoryUser findById(Integer id);
	
	public CategoryUser save(CategoryUser categoryUser);
	
	public void delete(Integer id);
	
	public List<CategoryUser> findAllActive();
}
