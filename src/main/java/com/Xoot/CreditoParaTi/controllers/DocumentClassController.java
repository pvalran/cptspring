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

import com.Xoot.CreditoParaTi.Definiciones.Services.IDocumentClassService;
import com.Xoot.CreditoParaTi.entity.DocumentClass;
import com.Xoot.CreditoParaTi.entity.DTO.CatalogoDTO;
import com.Xoot.CreditoParaTi.entity.DTO.ResponseDTO;

@RestController
@RequestMapping("/documents/class")
public class DocumentClassController {
	@Autowired
	private IDocumentClassService classDocumentService;
	private Object data;
	private String message;
	private Boolean result;

	@GetMapping("/all")
	public ResponseDTO allActive() {
		try {
			data = classDocumentService.findAllActive();
			result = true;
			message = "Exito";
		} catch (Exception e) {
			data = null;
			result = false;
			message = "Ocurrió un error al mostrar todas las clases de documento.";
		}
		return new ResponseDTO(data, message, result);
	}

	@GetMapping("/show/{id}")
	public ResponseDTO show(@PathVariable Integer id) {
		try {
			DocumentClass classDocument = classDocumentService.findById(id);

			if (classDocument == null) {
				data = null;
				result = false;
				message = "No existe una clase de documento con el id proporcionado.";
			} else {

				data = classDocument;
				result = true;
				message = "Exito";
			}
		} catch (Exception e) {
			data = null;
			result = false;
			message = "Ocurrió un error al mostrar la clase de documento.";
		}
		return new ResponseDTO(data, message, result);
	}

	@PostMapping("/create")
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseDTO create(@RequestBody CatalogoDTO catalogo) {
		try {
			DocumentClass classExist = classDocumentService.findByName(catalogo.getName());
			DocumentClass classDocument = new DocumentClass();

			if (classExist != null) {
				data = null;
				result = false;
				message = "Ya se registro una clase de documento con el nombre " + catalogo.getName();
			} else {
				classDocument.setStatus_flag(1);
				classDocument.setName(catalogo.getName());

				data = classDocumentService.save(classDocument);
				result = true;
				message = "Registro creado.";
			}
		} catch (Exception e) {
			data = null;
			result = false;
			message = "Ocurrió un error al crear la clase de documento.";
		}
		return new ResponseDTO(data, message, result);
	}

	@PutMapping("/update/{id}")
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseDTO update(@PathVariable Integer id, @RequestBody CatalogoDTO catalogo) {
		try {
			DocumentClass classActual = classDocumentService.findById(id);
			DocumentClass classExist = classDocumentService.findByName(catalogo.getName());
			
			data = null;
			result = false;
			if (classActual == null) {	
				message = "No existe una clase de documento con el id proporcionado.";
			} else if (classExist != null && classExist.getIdClassDocument() != id) {
				message = "Ya se registro una clase de documento con el nombre " + catalogo.getName();
			} else {
				classActual.setName(catalogo.getName());
				classActual.setMdfd_on(new Date());

				data = classDocumentService.save(classActual);
				result = true;
				message = "Registro actualizado.";
			}
		} catch (Exception e) {
			data = null;
			result = false;
			message = "Ocurrió un error al actualizar la clase de documento.";
		}
		return new ResponseDTO(data, message, result);
	}

	@GetMapping("/delete/{id}")
	public ResponseDTO delete(@PathVariable Integer id) {
		try {
			DocumentClass classDocument = classDocumentService.findById(id);

			if (classDocument == null) {
				data = null;
				result = false;
				message = "No existe una clase de documento con el id proporcionado.";
			} else {
				classDocument.setStatus_flag(0);
				classDocument.setMdfd_on(new Date());
				classDocumentService.save(classDocument);

				data = null;
				result = true;
				message = "Registro eliminado.";
			}
		} catch (Exception e) {
			data = null;
			result = false;
			message = "Ocurrió un error al eliminar la clase de documento.";
		}
		return new ResponseDTO(data, message, result);
	}
}
