package com.Xoot.CreditoParaTi.controllers;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Xoot.CreditoParaTi.Definiciones.Services.IClassDocumentService;
import com.Xoot.CreditoParaTi.Definiciones.Services.ITypeDocumentService;
import com.Xoot.CreditoParaTi.entity.DocumentClass;
import com.Xoot.CreditoParaTi.entity.DocumentType;
import com.Xoot.CreditoParaTi.entity.DTO.CatalogueDTO;
import com.Xoot.CreditoParaTi.entity.DTO.ResponseDTO;

@RestController
@RequestMapping("/documents")
public class DocumentController {
	@Autowired
	private ITypeDocumentService typeDocumentService;
	@Autowired
	private IClassDocumentService classDocumentService;
	private Object data;
	private String message;
	private Boolean result;

	@GetMapping("/allType")
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

	@GetMapping("/allClass")
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

	@GetMapping("/showType/{id}")
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

	@GetMapping("/showClass/{id}")
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

	@PostMapping("/createType")
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

	@PostMapping("/createClass")
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
	
	@PostMapping("/updateType")
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
	
	@PostMapping("/updateClass")
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
	
	@GetMapping("/deleteType/{id}")
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
	
	@GetMapping("/deleteClass/{id}")
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
