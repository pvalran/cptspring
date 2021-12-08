package com.Xoot.CreditoParaTi.services.interfaces;

import java.util.List;

import com.Xoot.CreditoParaTi.dto.ResponseDTO;
import com.Xoot.CreditoParaTi.dto.UserBoardDTO;
import com.Xoot.CreditoParaTi.entity.app.Usuario;

public interface IUserService {

	public Usuario findById(Integer id);
	
	public Usuario save(Usuario user);

	public ResponseDTO update(Integer id, UserBoardDTO ObjDTO);
	
	public List<Usuario> findAllActive();

	public List<Usuario> findAllBoard();
	
	public Usuario findByUsername(String username);
	
	public Usuario findByemail(String email);

	public Usuario Login(String userName, String password);
}
