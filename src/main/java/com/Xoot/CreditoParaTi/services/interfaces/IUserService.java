package com.Xoot.CreditoParaTi.services.interfaces;

import java.util.List;

import com.Xoot.CreditoParaTi.entity.Usuario;
import org.springframework.data.repository.query.Param;

public interface IUserService {

	public Usuario findById(Integer id);
	
	public Usuario save(Usuario user);
	
	public List<Usuario> findAllActive();
	
	public Usuario findByUsername(String username);
	
	public Usuario findByemail(String email);

	public Usuario Login(String userName, String password);
}
