package com.Xoot.CreditoParaTi.controllers;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.Xoot.CreditoParaTi.entity.CategoryUser;
import com.Xoot.CreditoParaTi.entity.Usuario;
import com.Xoot.CreditoParaTi.entity.DTO.UserDTO;
import com.Xoot.CreditoParaTi.models.dao.services.ICategoryUserService;
import com.Xoot.CreditoParaTi.models.dao.services.IUserService;
import com.Xoot.CreditoParaTi.utilidades.ResponseDTO;
import com.Xoot.CreditoParaTi.utilidades.Security;

@CrossOrigin(origins = { "http://localhost:4200" }) // Cross para angular
@RestController
@RequestMapping("/user")
public class UserController {
	// Implementacion de interfaz
	@Autowired
	private IUserService userService;
	@Autowired
	private ICategoryUserService categoryUserService;
	private Object data;
	private String message;
	private Boolean result;

	/*
	 * Metodo para obtener todos los usuario
	 */
	@GetMapping("/all")
	public ResponseDTO all() {
		try {
			data = userService.findAll();
			result = true;
			message = "Exito";
		} catch (Exception e) {
			data = null;
			result = false;
			message = e.getMessage();
		}
		return new ResponseDTO(data, message, result);
	}

	/*
	 * Metodo para obtener un usuario por id
	 */
	@GetMapping("/show/{id}")
	public ResponseDTO show(@PathVariable Integer id) {
		try {
			// Se desencripta la contraseña para mostrarlo
			Security security = new Security();
			Usuario usuario = userService.findById(id);
			String Password = security.getAESDecrypt(usuario.getPassword());
			usuario.setPassword(Password);
			data = usuario;
			result = true;
			message = "Exito";
		} catch (Exception e) {
			data = null;
			result = false;
			message = e.getMessage();
		}
		return new ResponseDTO(data, message, result);
	}

	/*
	 * Metodo para crear un usuario
	 */
	@PostMapping("/create")
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseDTO create(@RequestBody UserDTO user) {
		try {
			Security security = new Security();

			Usuario newUser = new Usuario();
			newUser.setStatus_flag(1);
			newUser.setUsername(user.getUsername());
			newUser.setEmail(user.getEmail());
			newUser.setPassword(security.getAES(user.getPassword()));
			newUser.setDtLastLogin(null);

			//CategoryUser objCatUser = categoryUserService.findById(user.getIdCategory());

			//newUser.setCategoryUser(objCatUser);

			data = userService.save(newUser);
			result = true;
			message = "Exito";
		} catch (Exception e) {
			data = null;
			result = false;
			message = e.getMessage();
		}
		return new ResponseDTO(data, message, result);
	}

	/*
	 * Metodo para actualizar un usuario por Id
	 */
	@PutMapping("/update")
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseDTO update(@RequestBody UserDTO user) {
		try {
			Security security = new Security();
			//CategoryUser objCatUser = categoryUserService.findById(user.getIdCategory());

			Usuario userActual = userService.findById(user.getIdUser());
			userActual.setUsername(user.getUsername());
			userActual.setEmail(user.getEmail());
			userActual.setPassword(security.getAES(user.getPassword()));
			//userActual.setCategoryUser(objCatUser);
			userActual.setMdfd_on(new Date());

			data = userService.save(userActual);
			result = true;
			message = "Exito";
		} catch (Exception e) {
			data = null;
			result = false;
			message = e.getMessage();
		}
		return new ResponseDTO(data, message, result);
	}

	/*
	 * Metodo para borrado logico de un usuario por Id
	 */
	@GetMapping("/delete/{id}")
	public ResponseDTO delete(@PathVariable Integer id) {
		try {
			Usuario userActual = userService.findById(id);
			userActual.setStatus_flag(0);
			userActual.setMdfd_on(new Date());
			userService.save(userActual);

			data = null;
			result = true;
			message = "Exito";
		} catch (Exception e) {
			data = null;
			result = false;
			message = e.getMessage();
		}
		return new ResponseDTO(data, message, result);
	}

	/*
	 * Metodo para obtener todos los usuarios activos
	 */
	@GetMapping("/allActive")
	public ResponseDTO allActive() {
		try {
			data = userService.findAllActive();
			result = true;
			message = "Exito";
		} catch (Exception e) {
			data = null;
			result = false;
			message = e.getMessage();
		}
		return new ResponseDTO(data, message, result);
	}
	
	/*
	 * Metodo para obtener un usuario activo por Contraseña y UserName
	 */
	@PostMapping("/login")
	public ResponseDTO Login(@RequestBody UserDTO user) {
		try {
			Security security = new Security();
			data = userService.userLogin(user.getUsername(), security.getAES(user.getPassword()));
			result = true;
			message = "Exito";
		} catch (Exception e) {
			data = null;
			result = false;
			message = e.getMessage();
		}
		return new ResponseDTO(data, message, result);
	}
}
