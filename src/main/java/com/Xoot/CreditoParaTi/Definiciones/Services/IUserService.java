package com.Xoot.CreditoParaTi.Definiciones.Services;

import java.util.List;

import com.Xoot.CreditoParaTi.entity.Usuario;

public interface IUserService {
	public List<Usuario> findAll();

	public Usuario findById(Integer id);

	public Usuario save(Usuario user);

	public void delete(Integer id);

	public List<Usuario> findAllActive();

	public Usuario findByUsername(String username);

	public Usuario findByemail(String email);
}
