package com.Xoot.CreditoParaTi.controllers;

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

import com.Xoot.CreditoParaTi.services.interfaces.IUserCategoryService;
import com.Xoot.CreditoParaTi.dto.CatalogoDTO;
import com.Xoot.CreditoParaTi.dto.ResponseDTO;

@RestController
@RequestMapping("/user/type")
public class UserCategoyController {
	@Autowired
	private IUserCategoryService _categoryUserService;

	/*
	 * Metodo para obtener todos las categorias de usuario activos
	 */
	@GetMapping("/all")
	public ResponseDTO allActive() {
		try {
			return new ResponseDTO(_categoryUserService.findAllActive(), "Exito", true);
		} catch (Exception e) {
			return new ResponseDTO(null, "Ocurrió un error al mostrar todas las categorías de usuario.", false);
		}
	}

	/*
	 * Metodo para obtener una categoria de usuario
	 */
	@GetMapping("/show/{id}")
	public ResponseDTO show(@PathVariable Integer id) {
		try {
			return _categoryUserService.getById(id);
		} catch (Exception e) {
			return new ResponseDTO(null, "Ocurrió un error al mostrar la categoría de usuario.", false);
		}
	}

	/*
	 * Metodo para crear una categoria de usuario
	 */
	@PostMapping("/create")
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseDTO create(@RequestBody CatalogoDTO catalogo) {
		try {
			return _categoryUserService.save(catalogo);
		} catch (Exception e) {
			return new ResponseDTO(null, "Ocurrió un error al crear la categoría de usuario.", false);
		}
	}

	/*
	 * Metodo para actualizar una categoria de usuario
	 */
	@PutMapping("/update/{id}")
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseDTO update(@PathVariable Integer id, @RequestBody CatalogoDTO catalogo) {
		try {
			return _categoryUserService.update(id, catalogo);
		} catch (Exception e) {
			return new ResponseDTO(true, "Ocurrió un error al actualizar la categoría de usuario.", false);
		}
	}
	
	@PutMapping("/Active/{id}")
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseDTO active(@PathVariable Integer id) {
		try {
			return _categoryUserService.active(id);
			
		} catch (Exception e) {
			return new ResponseDTO(null, "Ocurrió un error al activar el Status", false);
		}
	}

	/*
	 * Metodo para borrado logico de un usuario por Id
	 */
	@GetMapping("/delete/{id}")
	public ResponseDTO delete(@PathVariable Integer id) {
		try {
			return _categoryUserService.delete(id);
		} catch (Exception e) {
			return new ResponseDTO(null, "Ocurrió un error al eliminar la categoría de usuario.", false);
		}
	}
}
