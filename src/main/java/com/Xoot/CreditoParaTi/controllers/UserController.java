package com.Xoot.CreditoParaTi.controllers;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.Xoot.CreditoParaTi.entity.Employee;
import com.Xoot.CreditoParaTi.mapper.Mail;
import com.Xoot.CreditoParaTi.services.interfaces.IEmployeeService;
import com.Xoot.CreditoParaTi.services.interfaces.IMailService;
import com.Xoot.CreditoParaTi.utils.PasswordGeneratorUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import com.Xoot.CreditoParaTi.services.interfaces.IUserCategoryService;
import com.Xoot.CreditoParaTi.services.interfaces.IUserService;
import com.Xoot.CreditoParaTi.entity.Usuario;
import com.Xoot.CreditoParaTi.dto.ResponseDTO;
import com.Xoot.CreditoParaTi.dto.UserDTO;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/user/user")
public class UserController {
	// Implementacion de interfaz
	@Autowired
	private IUserService userService;

	@Autowired
	private IEmployeeService employeeService;


	@Autowired
	private IUserCategoryService categoryUserService;
	@Autowired
	private BCryptPasswordEncoder encoder;
	@Autowired
	private IMailService mailService;

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
				//newUser.setPassword(encoder.encode(user.getPassword()));
				newUser.setPassword(user.getPassword());
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

	@PostMapping("/login")
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseDTO Login(@RequestBody UserDTO user) {
		try {
			Employee validaUsername = employeeService.findByUsername(user.getUsername());
			Employee validaEmail = employeeService.findByemail(user.getEmail());
			data = null;
			result = false;
			Employee employee = employeeService.Login(user.getUsername(),user.getPassword());
			if (employee == null) {
				message = "No autorizado";
				result = false;
			} else {
				if (employee.getDtLastLogin() == null){
					message = "ChangePassword";
				} else {
					message = "Autorizado";
				}
				data = employee;
				result = true;
			}
		} catch (Exception ex) {
			data = null;
			result = false;
			message = "Ocurrió un error en el login  usuario.";
		}
		return new ResponseDTO(data, message, result);
	}

	@PostMapping("/restorepassword")
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseDTO Restorepassword(@RequestBody UserDTO user) {
		try {
			data = null;
			result = false;

			Usuario userchange = userService.findByemail(user.getEmail());
			if (userchange == null) {
				message = "Su correo no ha sido registrado";
				result = false;
			} else {
				String username = userchange.getName()+" "+userchange.getPaternalLastName()+" "+userchange.getMotherLastName();
				String password = PasswordGeneratorUtil.getPassword(8);
				userchange.setPassword(password);
				userService.save(userchange);
				Mail mail = new Mail();
				mail.setMailFrom("envios@creditoparati.com.mx");
				mail.setMailTo(user.getEmail());
				mail.setMailSubject("Recuperación de contraseña");
				mail.setMailContent("Estimado(a): "+ username+"\n Su nueva contraseña es:"+ password);
				mailService.sendEmail(mail);
				message = "Su correo ha sido enviado";
				result = true;
			}
		} catch (Exception ex) {
			data = null;
			result = false;
			message = "Ocurrió un error en la recuperación de contraseña.";
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
