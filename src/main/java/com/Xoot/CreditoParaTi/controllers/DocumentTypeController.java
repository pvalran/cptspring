package com.Xoot.CreditoParaTi.controllers;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Xoot.CreditoParaTi.Definiciones.Services.IDocumentTypeService;
import com.Xoot.CreditoParaTi.entity.DocumentType;
import com.Xoot.CreditoParaTi.entity.DTO.CatalogueDTO;
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
	public ResponseDTO allType() {
		try {
			data = typeDocumentService.findAllActive();
			result = true;
			message = "Exito";
		} catch (Exception e) {
			data = null;
			result = false;
			message = e.getMessage();
		}
		return new ResponseDTO(data, message, result);
	}

	@GetMapping("/show/{id}")
	public ResponseDTO showType(@PathVariable Integer id) {
		try {
			data = typeDocumentService.findById(id);
			result = true;
			message = "Exito";
		} catch (Exception e) {
			data = null;
			result = false;
			message = e.getMessage();
		}
		return new ResponseDTO(data, message, result);
	}

	@PostMapping("/create")
	public ResponseDTO createType(@RequestBody CatalogueDTO catalogue) {
		try {
			DocumentType typeDocument = new DocumentType();
			typeDocument.setStatus_flag(1);
			typeDocument.setName(catalogue.getName());

			data = typeDocumentService.save(typeDocument);
			result = true;
			message = "Exito";
		} catch (Exception e) {
			data = null;
			result = false;
			message = e.getMessage();
		}
		return new ResponseDTO(data, message, result);
	}

	@PostMapping("/update")
	public ResponseDTO updateType(@RequestBody CatalogueDTO catalogue) {
		try {
			DocumentType typeActual = typeDocumentService.findById(catalogue.getIdCatalogue());
			typeActual.setName(catalogue.getName());
			typeActual.setStatus_flag(1);
			typeActual.setMdfd_on(new Date());
			
			data = typeDocumentService.save(typeActual);
			result = true;
			message = "Exito";
		} catch (Exception e) {
			data = null;
			result = false;
			message = e.getMessage();
		}
		return new ResponseDTO(data,message,result);
	}

	@GetMapping("/delete/{id}")
	public ResponseDTO deleteType(@PathVariable Integer id) {	
		try {
			DocumentType typeDocument = typeDocumentService.findById(id);
			typeDocument.setStatus_flag(0);
			typeDocument.setMdfd_on(new Date());
			typeDocumentService.save(typeDocument);
			
			data = null;
			result = true;
			message = "Exito";
		} catch (Exception e) {
			data = null;
			result = false;
			message = e.getMessage();
		}
		return new ResponseDTO(data,message,result);
	}
}