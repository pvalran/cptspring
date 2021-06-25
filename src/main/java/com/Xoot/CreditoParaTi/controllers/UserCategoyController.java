package com.Xoot.CreditoParaTi.controllers;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Xoot.CreditoParaTi.Definiciones.Services.IUserCategoryService;
import com.Xoot.CreditoParaTi.entity.UsuarioCategory;
import com.Xoot.CreditoParaTi.entity.DTO.CatalogueDTO;
import com.Xoot.CreditoParaTi.entity.DTO.ResponseDTO;

@RestController
@RequestMapping("/user/type")
public class UserCategoyController {
	@Autowired
	private IUserCategoryService CategoryUserService;
	private Object data;
    private String message;
    private Boolean result;
    
    /*
	 * Metodo para obtener todos las categorias de usuario activos
	 */
	@GetMapping("/all")
	public ResponseDTO allActive() {
		try {
			data = CategoryUserService.findAllActive();
			result = true;
			message = "Exito";
		} catch (Exception e) {
			data = null;
			result = false;
			message = e.getMessage();
		}
		return new ResponseDTO(data,message,result);
	}

	/*
	 * Metodo para obtener una categoria de usuario
	 */
	@GetMapping("/show/{id}")
	public ResponseDTO show(@PathVariable Integer id) {
		try {
			data = CategoryUserService.findById(id);
			result = true;
			message = "Exito";
		} catch (Exception e) {
			data = null;
			result = false;
			message = e.getMessage();
		}
		return new ResponseDTO(data,message,result);
	}

	/*
	 * Metodo para crear una categoria de usuario
	 */
	@PostMapping("/create")
	public ResponseDTO create(@RequestBody  CatalogueDTO catalogue) {		
		try {
			UsuarioCategory catUser = new UsuarioCategory();
			catUser.setStatus_flag(1);
			catUser.setName(catalogue.getName());
			
			data = CategoryUserService.save(catUser);
			result = true;
			message = "Exito";
		} catch (Exception e) {
			data = null;
			result = false;
			message = e.getMessage();
		}
		return new ResponseDTO(data,message,result);
	}

	/*
	 * Metodo para actualizar una categoria de usuario
	 */
	@PutMapping("/update")
	public ResponseDTO update(@RequestBody  CatalogueDTO catalogue) {
		try {
			UsuarioCategory CategoryUserActual = CategoryUserService.findById(catalogue.getIdCatalogue());
			CategoryUserActual.setName(catalogue.getName());
			CategoryUserActual.setStatus_flag(1);
			CategoryUserActual.setMdfd_on(new Date());
			
			data = CategoryUserService.save(CategoryUserActual);
			result = true;
			message = "Exito";
		} catch (Exception e) {
			data = null;
			result = false;
			message = e.getMessage();
		}
		return new ResponseDTO(data,message,result);
	}

	/*
	 * Metodo para borrado logico de un usuario por Id
	 */
	@GetMapping("/delete/{id}")
	public ResponseDTO delete(@PathVariable Integer id) {	
		try {
			UsuarioCategory catUser = CategoryUserService.findById(id);
			catUser.setStatus_flag(0);
			catUser.setMdfd_on(new Date());
			CategoryUserService.save(catUser);
			
			data = null;
			result = true;
			message = "Exito";
		} catch (Exception e) {
			data = null;
			result = false;
			message = e.getMessage();
		}
		return new ResponseDTO(data,message,result);
	}

}