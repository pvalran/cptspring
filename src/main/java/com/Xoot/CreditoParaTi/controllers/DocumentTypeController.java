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

import com.Xoot.CreditoParaTi.Definiciones.Services.IDocumentTypeService;
import com.Xoot.CreditoParaTi.entity.DTO.DocumentTypeDTO;
import com.Xoot.CreditoParaTi.entity.DTO.ResponseDTO;

@RestController
@RequestMapping("/documents/types")
public class DocumentTypeController {
	@Autowired
	private IDocumentTypeService typeDocumentService;

	@GetMapping("/all")
	public ResponseDTO allActive() {
		try {
			return new ResponseDTO(typeDocumentService.findAllActive(), "Exito", true);
		} catch (Exception e) {
			return new ResponseDTO(e, "Ocurrió un error al mostrar todos los tipos de documento.", false);
		}
	}

	@GetMapping("/show/{id}")
	public ResponseDTO show(@PathVariable Integer id) {
		try {
			return typeDocumentService.getById(id);
		} catch (Exception e) {
			return new ResponseDTO(e, "Ocurrió al mostrar el tipo de documento.", false);
		}
	}
	
	@GetMapping("/getByClass/{id}")
	public ResponseDTO getByClass(@PathVariable Integer id) {
		try {
			return typeDocumentService.getByClass(id);
		} catch (Exception e) {
			return new ResponseDTO(e, "Ocurrió al mostrar los tipos de documento.", false);
		}
	}

	@PostMapping("/create")
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseDTO create(@RequestBody DocumentTypeDTO catalogo) {
		try {
			return typeDocumentService.save(catalogo);
		} catch (Exception e) {
			return new ResponseDTO(e, "Ocurrió al crear el tipo de documento.", false);
		}
	}

	@PutMapping("/update/{id}")
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseDTO update(@PathVariable Integer id, @RequestBody DocumentTypeDTO catalogo) {
		try {
			return typeDocumentService.update(id, catalogo);
		} catch (Exception e) {
			return new ResponseDTO(e, "Ocurrió al actualizar el tipo de documento.", false);
		}
	}

	@PutMapping("/Active/{id}")
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseDTO active(@PathVariable Integer id) {
		try {
			return typeDocumentService.active(id);
			
		} catch (Exception e) {
			return new ResponseDTO(null, "Ocurrió un error al activar el Status", false);
		}
	}
	
	@GetMapping("/delete/{id}")
	public ResponseDTO delete(@PathVariable Integer id) {
		try {
			return typeDocumentService.delete(id);
		} catch (Exception e) {
			return new ResponseDTO(e, "Ocurrió al eliminar el tipo de documento.", false);
		}
	}
}
