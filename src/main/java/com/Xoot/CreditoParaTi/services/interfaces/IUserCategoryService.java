package com.Xoot.CreditoParaTi.services.interfaces;
import java.util.List;

import com.Xoot.CreditoParaTi.entity.app.UsuarioCategory;
import com.Xoot.CreditoParaTi.dto.CatalogoDTO;
import com.Xoot.CreditoParaTi.dto.ResponseDTO;

public interface IUserCategoryService {
	UsuarioCategory findById(Integer id);
	
	List<UsuarioCategory> findAllActive();
	
	List<UsuarioCategory> getlistCategory(List<Integer> lstIds);
	
	UsuarioCategory findByName(String name);
	
	ResponseDTO save(CatalogoDTO catalogo);
	
	ResponseDTO update(Integer id, CatalogoDTO catalogo);
	
	ResponseDTO active(Integer id);
	
	ResponseDTO delete(Integer id);

	ResponseDTO getById(Integer id);
}
