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

import com.Xoot.CreditoParaTi.Definiciones.Services.ICreditApplicationService;
import com.Xoot.CreditoParaTi.entity.DTO.CreditApplicationDTO;
import com.Xoot.CreditoParaTi.entity.DTO.ResponseDTO;

@RestController
@RequestMapping("/creditApplication/creditApplication")
public class CreditApplicationController {
	@Autowired
	private ICreditApplicationService _creditApplicationService;
	
	@GetMapping("/all")
	public ResponseDTO allActive() {
		try {
			return new ResponseDTO(_creditApplicationService.findAllActive(), "Exito", true);
		} catch (Exception e) {
			return new ResponseDTO(null, "Ocurrió un error al mostrar todas las solicitudes de credito activos.", false);
		}
	}
	
	@GetMapping("/allbyuser/{id}")
	public ResponseDTO allByUser(@PathVariable Integer id) {
		try {
			return new ResponseDTO(_creditApplicationService.getAllByUser(id), "Exito", true);
		} catch (Exception e) {
			return new ResponseDTO(null, "Ocurrió un error al mostrar todas las solicitudes de credito activos.", false);
		}
	}

	@GetMapping("/show/{id}")
	public ResponseDTO show(@PathVariable Integer id) {
		try {
			return _creditApplicationService.getById(id);
		} catch (Exception e) {
			return new ResponseDTO(null, "Ocurrió un error al mostrar la solicitude de credito.", false);
		}
	}

	@PostMapping("/create")
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseDTO create(@RequestBody CreditApplicationDTO creditApplicationDTO) {
		try {
			return _creditApplicationService.save(creditApplicationDTO);
		} catch (Exception e) {
			return new ResponseDTO(null, "Ocurrió un error al crear la solicitude de credito.", false);
		}
	}

	@PutMapping("/update/{id}")
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseDTO update(@PathVariable Integer id, @RequestBody CreditApplicationDTO creditApplicationDTO) {
		try {
			return _creditApplicationService.update(id, creditApplicationDTO);
		} catch (Exception e) {
			return new ResponseDTO(null, "Ocurrió un error al actualizar la solicitud de credito.", false);
		}
	}
	
	@PutMapping("/Active/{id}")
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseDTO active(@PathVariable Integer id) {
		try {
			return _creditApplicationService.active(id);
			
		} catch (Exception e) {
			return new ResponseDTO(null, "Ocurrió un error al activar la solicitude de credito", false);
		}
	}

	@GetMapping("/delete/{id}")
	public ResponseDTO delete(@PathVariable Integer id) {
		try {
			return _creditApplicationService.delete(id);
		} catch (Exception e) {
			return new ResponseDTO(null, "Ocurrió un error al eliminar la solicitude de credito", false);
		}
	}

	@PutMapping("/addcustomer/{id}")
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseDTO addCustomer(@PathVariable Integer id, @RequestBody CreditApplicationDTO creditApplicationDTO) {
		try {
			return _creditApplicationService.addCustomer(id, creditApplicationDTO);
		} catch (Exception e) {
			return new ResponseDTO(null, "Ocurrió un error al agregar el cliente a la solicitud de credito.", false);
		}
	}
}
