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

import com.Xoot.CreditoParaTi.Definiciones.Services.IDocumentClassService;
import com.Xoot.CreditoParaTi.entity.DTO.CatalogoDTO;
import com.Xoot.CreditoParaTi.entity.DTO.ResponseDTO;

@RestController
@RequestMapping("/documents/class")
public class DocumentClassController {
	@Autowired
	private IDocumentClassService classDocumentService;

	@GetMapping("/all")
	public ResponseDTO allActive() {
		try {
			return new ResponseDTO(classDocumentService.findAllActive(), "Exito", true);
		} catch (Exception e) {
			return new ResponseDTO(null, "Ocurrió un error al mostrar todas las clases de documento.", false);
		}
	}

	@GetMapping("/show/{id}")
	public ResponseDTO show(@PathVariable Integer id) {
		try {
			return classDocumentService.getById(id);
		} catch (Exception e) {
			return new ResponseDTO(null, "Ocurrió un error al mostrar la clase de documento.", false);
		}
	}

	@PostMapping("/create")
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseDTO create(@RequestBody CatalogoDTO catalogo) {
		try {
			return classDocumentService.save(catalogo);
		} catch (Exception e) {
			return new ResponseDTO(null, "Ocurrió un error al crear la clase de documento.", false);
		}
	}

	@PutMapping("/update/{id}")
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseDTO update(@PathVariable Integer id, @RequestBody CatalogoDTO catalogo) {
		try {
			return classDocumentService.update(id, catalogo);
		} catch (Exception e) {
			return new ResponseDTO(null, "Ocurrió un error al actualizar la clase de documento.", false);
		}
	}

	@GetMapping("/delete/{id}")
	public ResponseDTO delete(@PathVariable Integer id) {
		try {
			return classDocumentService.delete(id);
		} catch (Exception e) {
			return new ResponseDTO(null, "Ocurrió un error al eliminar la clase de documento.", false);
		}
	}
}
