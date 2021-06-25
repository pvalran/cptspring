package com.Xoot.CreditoParaTi.controllers;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.Xoot.CreditoParaTi.Definiciones.Services.IUserCategoryService;
import com.Xoot.CreditoParaTi.entity.UsuarioCategory;
import com.Xoot.CreditoParaTi.entity.DTO.CatalogoDTO;
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
			message = "Ocurrió un error al mostrar todas las categorías de usuario.";
		}
		return new ResponseDTO(data, message, result);
	}

	/*
	 * Metodo para obtener una categoria de usuario
	 */
	@GetMapping("/show/{id}")
	public ResponseDTO show(@PathVariable Integer id) {
		try {
			UsuarioCategory catUser = CategoryUserService.findById(id);

			if (catUser == null) {
				data = null;
				result = false;
				message = "No existe una categoría de usuario con el id proporcionado.";
			} else {
				data = catUser;
				result = true;
				message = "Exito";
			}
		} catch (Exception e) {
			data = null;
			result = false;
			message = "Ocurrió un error al mostrar la categoría de usuario.";
		}
		return new ResponseDTO(data, message, result);
	}

	/*
	 * Metodo para crear una categoria de usuario
	 */
	@PostMapping("/create")
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseDTO create(@RequestBody CatalogoDTO catalogo) {
		try {

			UsuarioCategory CategoryUserExits = CategoryUserService.findByName(catalogo.getName());

			if (CategoryUserExits != null) {
				data = null;
				result = false;
				message = "Ya se registro una categoria de usuario con el nombre " + catalogo.getName();
			} else {
				UsuarioCategory catUser = new UsuarioCategory();

				catUser.setStatus_flag(1);
				catUser.setName(catalogo.getName());

				data = CategoryUserService.save(catUser);
				result = true;
				message = "Registro creado.";
			}
		} catch (Exception e) {
			data = null;
			result = false;
			message = "Ocurrió un error al crear la categoría de usuario.";
		}
		return new ResponseDTO(data, message, result);
	}

	/*
	 * Metodo para actualizar una categoria de usuario
	 */
	@PutMapping("/update/{id}")
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseDTO update(@PathVariable Integer id, @RequestBody CatalogoDTO catalogo) {
		try {
			UsuarioCategory CategoryUserActual = CategoryUserService.findById(id);
			UsuarioCategory CategoryUserExits = CategoryUserService.findByName(catalogo.getName());
			
			data = null;
			result = false;
			if (CategoryUserActual == null) {
				message = "No existe una categoría de usuario con el id proporcionado.";
			} else if(CategoryUserExits != null && CategoryUserExits.getCategory_id() != id) {
				message = "Ya se registro una categoria de usuario con el nombre " + catalogo.getName();
			} else {
				CategoryUserActual.setName(catalogo.getName());
				CategoryUserActual.setMdfd_on(new Date());

				data = CategoryUserService.save(CategoryUserActual);
				result = true;
				message = "Registro actualizado.";
			}
		} catch (Exception e) {
			data = null;
			result = false;
			message = "Ocurrió un error al actualizar la categoría de usuario.";
		}
		return new ResponseDTO(data, message, result);
	}

	/*
	 * Metodo para borrado logico de un usuario por Id
	 */
	@GetMapping("/delete/{id}")
	public ResponseDTO delete(@PathVariable Integer id) {
		try {
			UsuarioCategory catUser = CategoryUserService.findById(id);

			if (catUser == null) {
				data = null;
				result = false;
				message = "No existe una categoría de usuario con el id proporcionado.";
			} else {
				catUser.setStatus_flag(0);
				catUser.setMdfd_on(new Date());
				CategoryUserService.save(catUser);

				data = null;
				result = true;
				message = "Registro eliminado.";
			}
		} catch (Exception e) {
			data = null;
			result = false;
			message = "Ocurrió un error al eliminar la categoría de usuario.";
		}
		return new ResponseDTO(data, message, result);
	}
}
