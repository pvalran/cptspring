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

import com.Xoot.CreditoParaTi.services.interfaces.ICustomerService;
import com.Xoot.CreditoParaTi.dto.CustomerDTO;
import com.Xoot.CreditoParaTi.dto.ResponseDTO;

@RestController
@RequestMapping("/customer/customer")
public class CustomerController {
	@Autowired
	private ICustomerService _customerService;
	
	@GetMapping("/all")
	public ResponseDTO allActive() {
		try {
			return new ResponseDTO(_customerService.findAllActive(), "Exito", true);
		} catch (Exception e) {
			return new ResponseDTO(null, "Ocurrió un error al mostrar todos los clientes activos.", false);
		}
	}

	@GetMapping("/show/{id}")
	public ResponseDTO show(@PathVariable Integer id) {
		try {
			return _customerService.getById(id);
		} catch (Exception e) {
			return new ResponseDTO(null, "Ocurrió un error al mostrar el cliente.", false);
		}
	}

	@PostMapping("/create")
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseDTO create(@RequestBody CustomerDTO customer) {
		try {
			return _customerService.save(customer);
		} catch (Exception e) {
			return new ResponseDTO(null, "Ocurrió un error al crear el cliente.", false);
		}
	}

	@PutMapping("/update/{id}")
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseDTO update(@PathVariable Integer id, @RequestBody CustomerDTO customer) {
		try {
			return _customerService.update(id, customer);
		} catch (Exception e) {
			return new ResponseDTO(null, "Ocurrió un error al actualizar el cliente.", false);
		}
	}
	
	@PutMapping("/Active/{id}")
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseDTO active(@PathVariable Integer id) {
		try {
			return _customerService.active(id);
			
		} catch (Exception e) {
			return new ResponseDTO(null, "Ocurrió un error al activar el cliente", false);
		}
	}

	@GetMapping("/delete/{id}")
	public ResponseDTO delete(@PathVariable Integer id) {
		try {
			return _customerService.delete(id);
		} catch (Exception e) {
			return new ResponseDTO(null, "Ocurrió un error al eliminar el cliente", false);
		}
	}
}
