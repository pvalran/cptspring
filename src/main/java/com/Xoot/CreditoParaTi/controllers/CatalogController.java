package com.Xoot.CreditoParaTi.controllers;

import com.Xoot.CreditoParaTi.Definiciones.Services.*;
import com.Xoot.CreditoParaTi.entity.DTO.*;
import com.Xoot.CreditoParaTi.entity.Document;
import com.Xoot.CreditoParaTi.entity.LocationState;
import org.modelmapper.ModelMapper;
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

import java.util.List;
import java.lang.reflect.Type;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;

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
	private ICreditApplicationProductService CreditApplicationProductService;
	@Autowired
	private ICreditApplicationService CreditApplicationService;
	@Autowired
	private ModelMapper modelMapper;
	@GetMapping("/catalogy/{catalog}")
	public ResponseDTO allActive(@PathVariable String catalog) {
		Type listType;
		try {
			switch (catalog) {
				case "document":
					listType = new TypeToken<List<DocumentDTO>>() {}.getType();
					return new ResponseDTO(modelMapper.map(documentTypeService.findAllActive(),listType), "Exito", true);
				case "state":
					listType = new TypeToken<List<LocationsStatesDTO>>() {}.getType();
					return new ResponseDTO(modelMapper.map(locationStateService.findAllActive(),listType), "Exito", true);
				case "municipality":
					listType = new TypeToken<List<LocationsCitiesDTO>>() {}.getType();
					return new ResponseDTO(modelMapper.map(locationCityService.findAllActive(),listType), "Exito", true);
				case "city":
					listType = new TypeToken<List<LocationsColoniesDTO>>() {}.getType();
					return new ResponseDTO(modelMapper.map(locationColoniesService.findAllActive(),listType), "Exito", true);
				case "colony":
					listType = new TypeToken<List<LocationsSuburbDTO>>() {}.getType();
					return new ResponseDTO(modelMapper.map(locationSuburbService.findAllActive(),listType), "Exito", true);
				case "gender":
					listType = new TypeToken<List<CustomerGenderDTO>>() {}.getType();
					return new ResponseDTO(modelMapper.map(CustomerGenderService.findAllActive(),listType), "Exito", true);
				case "statuscredit":
					listType = new TypeToken<List<CreditApplicationStatusDTO>>() {}.getType();
					return new ResponseDTO(modelMapper.map(CreditStatusService.findAllActive(),listType), "Exito", true);
				case "typecredit":
					listType = new TypeToken<List<CreditApplicationProductDTO>>() {}.getType();
					return new ResponseDTO(modelMapper.map(CreditApplicationProductService.findAllActive(),listType), "Exito", true);
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
			Type listType = new TypeToken<List<LocationsColoniesDTO>>() {}.getType();
			return new ResponseDTO(modelMapper.map(locationColoniesService.getCityToMun(id),listType), "Exito", true);
		} catch (Exception e) {
			return new ResponseDTO(null, "Catalogo no encontrado .", false);
		}
	}

	@GetMapping("/getColonyToMun/{id}")
	public ResponseDTO getColonyToMun(@PathVariable Integer id) {
		try {
			Type listType = new TypeToken<List<LocationsSuburbDTO>>() {}.getType();
			return new ResponseDTO(modelMapper.map(locationSuburbService.getColonyToMun(id),listType), "Exito", true);
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
			Type listType = new TypeToken<List<DocumentDTO>>() {}.getType();
			return new ResponseDTO(modelMapper.map(documentService.findAllActive(id),listType), "Exito", true);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseDTO(null, "Catalogo no encontrado .", false);
		}
	}

	@GetMapping("/getCreditForUser/{id}")
	public ResponseDTO getCreditForUser(@PathVariable Integer id) {
		try {
			Type listType = new TypeToken<List<CreditApplicationDTO>>() {}.getType();
			return new ResponseDTO(modelMapper.map(CreditApplicationService.getAllByUser(id),listType), "Exito", true);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseDTO(null, "Catalogo no encontrado .", false);
		}
	}
}
