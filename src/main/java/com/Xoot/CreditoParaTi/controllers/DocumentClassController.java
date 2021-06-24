package com.Xoot.CreditoParaTi.controllers;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Xoot.CreditoParaTi.Definiciones.Services.IDocumentClassService;
import com.Xoot.CreditoParaTi.entity.DocumentClass;
import com.Xoot.CreditoParaTi.entity.DTO.CatalogueDTO;
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
	public ResponseDTO allClass() {
		try {
			data = classDocumentService.findAllActive();
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
	public ResponseDTO showClass(@PathVariable Integer id) {
		try {
			data = classDocumentService.findById(id);
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
	public ResponseDTO createClass(@RequestBody CatalogueDTO catalogue) {
		try {
			DocumentClass classDocument = new DocumentClass();
			classDocument.setStatus_flag(1);
			classDocument.setName(catalogue.getName());

			data = classDocumentService.save(classDocument);
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
	public ResponseDTO updateClass(@RequestBody CatalogueDTO catalogue) {
		try {
			DocumentClass classActual = classDocumentService.findById(catalogue.getIdCatalogue());
			classActual.setName(catalogue.getName());
			classActual.setStatus_flag(1);
			classActual.setMdfd_on(new Date());
			
			data = classDocumentService.save(classActual);
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
	public ResponseDTO deleteClass(@PathVariable Integer id) {	
		try {
			DocumentClass classDocument = classDocumentService.findById(id);
			classDocument.setStatus_flag(0);
			classDocument.setMdfd_on(new Date());
			classDocumentService.save(classDocument);
			
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
