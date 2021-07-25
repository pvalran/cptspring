package com.Xoot.CreditoParaTi.controllers;

import com.Xoot.CreditoParaTi.Definiciones.Services.*;
import com.Xoot.CreditoParaTi.entity.Document;
import com.Xoot.CreditoParaTi.entity.LocationState;
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

import com.Xoot.CreditoParaTi.entity.DTO.DocumentDTO;
import com.Xoot.CreditoParaTi.entity.DTO.ResponseDTO;

import java.util.List;

@RestController
@RequestMapping("/catalogies")
public class CatalogController {
	@Autowired
	private IDocumentService documentService;
	@Autowired
	private IDocumentTypeService documentTypeService;
	@Autowired
	private ILocationStateService locationStateService;
	@Autowired
	private ILocationCityService locationCityService;
	@Autowired
	private ILocationColoniesService locationColoniesService;
	@Autowired
	private ILocationSuburbService locationSuburbService;
	@Autowired
	private ICustomerGenderService CustomerGenderService;
	@Autowired
	private ICreditApplicationStatusService CreditStatusService;
	@Autowired
	private ICreditApplicationService CreditApplicationService;

	@GetMapping("/catalogy/{catalog}")
	public ResponseDTO allActive(@PathVariable String catalog) {
		try {
			switch (catalog) {
				case "document":
					return new ResponseDTO(documentTypeService.findAllActive(), "Exito", true);
				case "state":
					return new ResponseDTO(locationStateService.findAllActive(), "Exito", true);
				case "municipality":
					return new ResponseDTO(locationCityService.findAllActive(), "Exito", true);
				case "city":
					return new ResponseDTO(locationColoniesService.findAllActive(), "Exito", true);
				case "colony":
					return new ResponseDTO(locationSuburbService.findAllActive(), "Exito", true);
				case "gender":
					return new ResponseDTO(CustomerGenderService.findAllActive(), "Exito", true);
				case "statuscredit":
					return new ResponseDTO(CreditStatusService.findAllActive(), "Exito", true);
				case "typecredit":
					return new ResponseDTO(CreditApplicationService.findAllActive(), "Exito", true);
			}
			return new ResponseDTO(null, "Catalogo no encontrado .", false);
		} catch (Exception e) {
			return new ResponseDTO(null, "Catalogo no encontrado .", false);
		}
	}

	@GetMapping("/getMunToState/{id}")
	public ResponseDTO getMuntoState(@PathVariable Integer id) {
		try {
			return new ResponseDTO(locationCityService.getMuntoState(id), "Exito", true);
		} catch (Exception e) {
			return new ResponseDTO(null, "Catalogo no encontrado .", false);
		}
	}

	@GetMapping("/getCityToMun/{id}")
	public ResponseDTO getCityToMun(@PathVariable Integer id) {
		try {
			return new ResponseDTO(locationColoniesService.getCityToMun(id), "Exito", true);
		} catch (Exception e) {
			return new ResponseDTO(null, "Catalogo no encontrado .", false);
		}
	}

	@GetMapping("/getColonyToMun/{id}")
	public ResponseDTO getColonyToMun(@PathVariable Integer id) {
		try {
			return new ResponseDTO(locationSuburbService.getColonyToMun(id), "Exito", true);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseDTO(null, "Catalogo no encontrado .", false);
		}
	}

	@GetMapping("/getDirectionToCp/{zipcode}")
	public ResponseDTO getDirectionToCp(@PathVariable Integer zipcode) {
		try {
			return new ResponseDTO(locationSuburbService.getDirectionToCp(zipcode), "Exito", true);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseDTO(null, "Catalogo no encontrado .", false);
		}
	}

	@GetMapping("/getDocumentByCredit/{id}")
	public ResponseDTO getDocumentByCredit(@PathVariable Integer id) {
		try {

			List<Document> documents = documentService.findAllActive(id);
			return new ResponseDTO(documents, "Exito", true);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseDTO(null, "Catalogo no encontrado .", false);
		}
	}

	@GetMapping("/getCreditForUser/{id}")
	public ResponseDTO getCreditForUser(@PathVariable Integer id) {
		try {
			return new ResponseDTO(CreditApplicationService.getAllByUser(id), "Exito", true);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseDTO(null, "Catalogo no encontrado .", false);
		}
	}
}
