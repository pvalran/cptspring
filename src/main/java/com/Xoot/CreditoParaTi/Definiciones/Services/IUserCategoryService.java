package com.Xoot.CreditoParaTi.Definiciones.Services;
import java.util.List;

import com.Xoot.CreditoParaTi.entity.UsuarioCategory;

public interface IUserCategoryService {
	public UsuarioCategory findById(Integer id);
	
	public UsuarioCategory save(UsuarioCategory categoryUser);
	
	public List<UsuarioCategory> findAllActive();
	
	public List<UsuarioCategory> getlistCategory(List<Integer> lstIds);
	
	public UsuarioCategory findByName(String name);
}
