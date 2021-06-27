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

import com.Xoot.CreditoParaTi.Definiciones.Services.IDocumentService;
import com.Xoot.CreditoParaTi.entity.DTO.DocumentDTO;
import com.Xoot.CreditoParaTi.entity.DTO.ResponseDTO;

@RestController
@RequestMapping("/documents/document")
public class DocumentController {
	@Autowired
	private IDocumentService documentService;

	@GetMapping("/all/{id}")
	public ResponseDTO allActive(@PathVariable Integer id) {
		try {
			return new ResponseDTO(documentService.findAllActive(id), "Exito", true);
		} catch (Exception e) {
			return new ResponseDTO(null,
					"Ocurrió un error al mostrar todos los documentos de solicitud de credito: " + id + ".", false);
		}
	}

	@GetMapping("/show/{id}")
	public ResponseDTO show(@PathVariable Integer id) {
		try {
			return documentService.getById(id);
		} catch (Exception e) {
			return new ResponseDTO(null, "Ocurrió un error al mostrar el documento.", false);
		}
	}

	@PostMapping("/create")
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseDTO create(@RequestBody DocumentDTO document) {
		try {
			return documentService.save(document);
		} catch (Exception e) {
			return new ResponseDTO(null, "Ocurrió un error al crear el documento.", false);
		}
	}

	@PutMapping("/update/{id}")
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseDTO update(@PathVariable Integer id, @RequestBody DocumentDTO document) {
		try {
			return documentService.update(id, document);
		} catch (Exception e) {
			return new ResponseDTO(null, "Ocurrió un error al actualizar el documento.", false);
		}
	}

	@GetMapping("/delete/{id}")
	public ResponseDTO delete(@PathVariable Integer id) {
		try {
			return documentService.delete(id);
		} catch (Exception e) {
			return new ResponseDTO(null, "Ocurrió un error al eliminar el documento.", false);
		}
	}
}
