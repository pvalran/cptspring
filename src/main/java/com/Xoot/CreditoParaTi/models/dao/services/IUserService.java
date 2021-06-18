package com.Xoot.CreditoParaTi.models.dao.services;

import java.util.List;

import com.Xoot.CreditoParaTi.entity.Usuario;

public interface IUserService {
	public List<Usuario> findAll();
	
	public Usuario findById(Integer id);
	
	public Usuario save(Usuario user);
	
	public void delete(Integer id);
	
	public Usuario userLogin(String userName, String pass);
	
	public List<Usuario> findAllActive();
}
