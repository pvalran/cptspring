package com.Xoot.CreditoParaTi.controllers;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.Xoot.CreditoParaTi.Definiciones.Services.IUserCategoryService;
import com.Xoot.CreditoParaTi.Definiciones.Services.IUserService;
import com.Xoot.CreditoParaTi.entity.Usuario;
import com.Xoot.CreditoParaTi.entity.DTO.ResponseDTO;
import com.Xoot.CreditoParaTi.entity.DTO.UserDTO;

@RestController
@RequestMapping("/user/user")
public class UserController {
	// Implementacion de interfaz
	@Autowired
	private IUserService userService;
	@Autowired
	private IUserCategoryService categoryUserService;
	@Autowired
	private BCryptPasswordEncoder encoder;
	private Object data;
	private String message;
	private Boolean result;

	/*
	 * Metodo para obtener todos los usuarios activos
	 */
	@GetMapping("/all")
	public ResponseDTO allActive() {
		try {
			data = userService.findAllActive();
			result = true;
			message = "Exito";
		} catch (Exception e) {
			data = null;
			result = false;
			message = "Ocurrió un error al mostrar todos los usuarios.";
		}
		return new ResponseDTO(data, message, result);
	}

	/*
	 * Metodo para obtener un usuario por id
	 */
	@GetMapping("/show/{id}")
	public ResponseDTO show(@PathVariable Integer id) {
		try {
			Usuario usuario = userService.findById(id);

			if (usuario == null) {
				data = null;
				result = false;
				message = "No existe un usuario con el id proporcionado.";
			} else {
				data = usuario;
				result = true;
				message = "Exito";
			}
		} catch (Exception e) {
			data = null;
			result = false;
			message = "Ocurrió un error al mostrar el usuario.";
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
			Usuario validaUsername = userService.findByUsername(user.getUsername());
			Usuario validaEmail = userService.findByemail(user.getEmail());
			
			data = null;
			result = false;
			if (validaUsername != null) {
				message = "El nombre de usuario " + user.getUsername() + ". Se encuentra en uso";
			} else if (validaEmail != null) {
				message = "El email " + user.getEmail() + ". Ya se encuentra registrado";
			} else {
				Usuario newUser = new Usuario();

				newUser.setStatus_flag(1);
				newUser.setUsername(user.getUsername());
				newUser.setEmail(user.getEmail());
				newUser.setPassword(encoder.encode(user.getPassword()));
				newUser.setDtLastLogin(null);
				newUser.setCategoryUser(categoryUserService.getlistCategory(user.getIdCategory()));

				data = userService.save(newUser);
				result = true;
				message = "Usuario creado";
			}
		} catch (Exception ex) {
			data = null;
			result = false;
			message = "Ocurrió un error al crear el usuario.";
		}
		return new ResponseDTO(data, message, result);
	}

	/*
	 * Metodo para actualizar un usuario por Id
	 */
	@PutMapping("/update/{id}")
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseDTO update(@PathVariable Integer id,@RequestBody UserDTO user) {
		try {
			Usuario validaUsername = userService.findByUsername(user.getUsername());
			Usuario validaEmail = userService.findByemail(user.getEmail());
			Usuario userActual = userService.findById(id);
			
			data = null;
			result = false;
			if (userActual == null) {
				message = "No existe un usuario con el id proporcionado.";
			} else if (validaUsername != null && validaUsername.getIdUser() != id) {
				message = "El nombre de usuario " + user.getUsername() + ". Se encuentra en uso";
			} else if (validaEmail != null && validaUsername.getIdUser() != id ) {
				message = "El email " + user.getEmail() + ". Ya se encuentra registrado";
			}else {
				userActual.setUsername(user.getUsername());
				userActual.setEmail(user.getEmail());
				userActual.setPassword(encoder.encode(user.getPassword()));
				userActual.setCategoryUser(categoryUserService.getlistCategory(user.getIdCategory()));
				userActual.setMdfd_on(new Date());

				data = userService.save(userActual);
				result = true;
				message = "Exito";
			}
		} catch (Exception e) {
			data = null;
			result = false;
			message = "Ocurrió un error al actualizar el usuario.";
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
			
			if(userActual == null) {
				data = null;
				result = false;
				message = "No existe un usuario con el id proporcionado.";
			}else {
				userActual.setStatus_flag(0);
				userActual.setMdfd_on(new Date());
				userService.save(userActual);

				data = null;
				result = true;
				message = "Exito";
			}
		} catch (Exception e) {
			data = null;
			result = false;
			message = "Ocurrió un error al eliminar el usuario.";
		}
		return new ResponseDTO(data, message, result);
	}
}
