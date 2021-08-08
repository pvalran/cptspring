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

import com.Xoot.CreditoParaTi.services.interfaces.ICreditApplicationProductService;
import com.Xoot.CreditoParaTi.dto.CreditApplicationProductDTO;
import com.Xoot.CreditoParaTi.dto.ResponseDTO;

@RestController
@RequestMapping("/creditApplication/product")
public class CreditApplicationProductController {
	@Autowired
	private ICreditApplicationProductService _creditApplicationProductSerice;

	@GetMapping("/all")
	public ResponseDTO allActive() {
		try {
			return new ResponseDTO(_creditApplicationProductSerice.findAllActive(), "Exito", true);
		} catch (Exception e) {
			return new ResponseDTO(null, "Ocurrió un error al mostrar todos los Status activos.", false);
		}
	}

	@GetMapping("/show/{id}")
	public ResponseDTO show(@PathVariable Integer id) {
		try {
			return _creditApplicationProductSerice.getById(id);
		} catch (Exception e) {
			return new ResponseDTO(null, "Ocurrió un error al mostrar el Status.", false);
		}
	}

	@PostMapping("/create")
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseDTO create(@RequestBody CreditApplicationProductDTO creditApplicationProductDTO) {
		try {
			return _creditApplicationProductSerice.save(creditApplicationProductDTO);
		} catch (Exception e) {
			return new ResponseDTO(null, "Ocurrió un error al crear el Status.", false);
		}
	}

	@PutMapping("/update/{id}")
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseDTO update(@PathVariable Integer id, @RequestBody CreditApplicationProductDTO creditApplicationProductDTO) {
		try {
			return _creditApplicationProductSerice.update(id, creditApplicationProductDTO);
		} catch (Exception e) {
			return new ResponseDTO(null, "Ocurrió un error al actualizar el Status.", false);
		}
	}
	
	@PutMapping("/Active/{id}")
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseDTO active(@PathVariable Integer id) {
		try {
			return _creditApplicationProductSerice.active(id);
			
		} catch (Exception e) {
			return new ResponseDTO(null, "Ocurrió un error al activar el Status", false);
		}
	}

	@GetMapping("/delete/{id}")
	public ResponseDTO delete(@PathVariable Integer id) {
		try {
			return _creditApplicationProductSerice.delete(id);
		} catch (Exception e) {
			return new ResponseDTO(null, "Ocurrió un error al eliminar el Status", false);
		}
	}
}
