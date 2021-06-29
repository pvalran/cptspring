package com.Xoot.CreditoParaTi.Definiciones.Services;
import java.util.List;

import com.Xoot.CreditoParaTi.entity.UsuarioCategory;
import com.Xoot.CreditoParaTi.entity.DTO.CatalogoDTO;
import com.Xoot.CreditoParaTi.entity.DTO.ResponseDTO;

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
