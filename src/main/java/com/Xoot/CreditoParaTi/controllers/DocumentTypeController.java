package com.Xoot.CreditoParaTi.controllers;

import java.util.Date;

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
import com.Xoot.CreditoParaTi.entity.DocumentType;
import com.Xoot.CreditoParaTi.entity.DTO.CatalogoDTO;
import com.Xoot.CreditoParaTi.entity.DTO.ResponseDTO;

@RestController
@RequestMapping("/documents/type")
public class DocumentTypeController {
	@Autowired
	private IDocumentTypeService typeDocumentService;
	private Object data;
	private String message;
	private Boolean result;

	@GetMapping("/all")
	public ResponseDTO allActive() {
		try {
			data = typeDocumentService.findAllActive();
			result = true;
			message = "Exito";
		} catch (Exception e) {
			data = null;
			result = false;
			message = "Ocurrió un error al mostrar todos los tipos de documento.";
		}
		return new ResponseDTO(data, message, result);
	}

	@GetMapping("/show/{id}")
	public ResponseDTO show(@PathVariable Integer id) {
		try {
			DocumentType typeDocument = typeDocumentService.findById(id);

			if (typeDocument == null) {
				data = null;
				result = false;
				message = "No existe un tipo de documento con el id proporcionado.";
			} else {
				data = typeDocument;
				result = true;
				message = "Exito";
			}
		} catch (Exception e) {
			data = null;
			result = false;
			message = "Ocurrió al mostrar el tipo de documento.";
		}
		return new ResponseDTO(data, message, result);
	}

	@PostMapping("/create")
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseDTO create(@RequestBody CatalogoDTO catalogo) {
		try {
			DocumentType typeDocumentExits = typeDocumentService.findByName(catalogo.getName());

			if (typeDocumentExits != null) {
				data = null;
				result = false;
				message = "Ya se registro un tipo de documento con el nombre " + catalogo.getName();
			} else {
				DocumentType typeDocument = new DocumentType();

				typeDocument.setStatus_flag(1);
				typeDocument.setName(catalogo.getName());

				data = typeDocumentService.save(typeDocument);
				result = true;
				message = "Registro creado.";
			}
		} catch (Exception e) {
			data = null;
			result = false;
			message = "Ocurrió al crear el tipo de documento.";
		}
		return new ResponseDTO(data, message, result);
	}

	@PutMapping("/update/{id}")
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseDTO update(@PathVariable Integer id, @RequestBody CatalogoDTO catalogo) {
		try {
			DocumentType typeActual = typeDocumentService.findById(id);
			DocumentType typeDocumentExits = typeDocumentService.findByName(catalogo.getName());

			data = null;
			result = false;
			if (typeActual == null) {
				message = "No existe un tipo de documento con el id proporcionado.";
			} else if (typeDocumentExits != null && typeDocumentExits.getIdTypeDocument() != id) {
				message = "Ya se registro un tipo de documento con el nombre " + catalogo.getName();
			} else {
				typeActual.setName(catalogo.getName());
				typeActual.setMdfd_on(new Date());

				data = typeDocumentService.save(typeActual);
				result = true;
				message = "Registro actualizado.";
			}
		} catch (Exception e) {
			data = null;
			result = false;
			message = "Ocurrió al actualizar el tipo de documento.";
		}
		return new ResponseDTO(data, message, result);
	}

	@GetMapping("/delete/{id}")
	public ResponseDTO delete(@PathVariable Integer id) {
		try {
			DocumentType typeDocument = typeDocumentService.findById(id);

			if (typeDocument == null) {
				data = null;
				result = false;
				message = "No existe un tipo de documento con el id proporcionado.";
			} else {
				typeDocument.setStatus_flag(0);
				typeDocument.setMdfd_on(new Date());
				typeDocumentService.save(typeDocument);

				data = null;
				result = true;
				message = "Registro eliminado.";
			}
		} catch (Exception e) {
			data = null;
			result = false;
			message = "Ocurrió al eliminar el tipo de documento.";
		}
		return new ResponseDTO(data, message, result);
	}
}