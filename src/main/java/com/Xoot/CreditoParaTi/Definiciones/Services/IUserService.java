package com.Xoot.CreditoParaTi.Definiciones.Services;

import java.util.List;

import com.Xoot.CreditoParaTi.entity.Usuario;

public interface IUserService {

	public Usuario findById(Integer id);
	
	public Usuario save(Usuario user);
	
	public List<Usuario> findAllActive();
	
	public Usuario findByUsername(String username);
	
	public Usuario findByemail(String email);
}
