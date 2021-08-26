package com.Xoot.CreditoParaTi.controllers;

import com.Xoot.CreditoParaTi.dto.*;
import com.Xoot.CreditoParaTi.services.interfaces.*;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.lang.reflect.Type;

import org.modelmapper.TypeToken;

@RestController
@CrossOrigin(origins = "*")
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
	private ITypeCivilService TypeCivilService;
	@Autowired
	private ITypeContractService TypeContractService;
	@Autowired
	private ITypeDatosService TypeDatosService;
	@Autowired
	private ITypeDocumentService TypeDocumentService;
	@Autowired
	private ITypeLaboralActivityService TypeLaboralActivityService;
	@Autowired
	private ITypeMaritalStatusService TypeMaritalStatusService;
	@Autowired
	private ITypeNationalityService TypeNationalityService;
	@Autowired
	private ITypePositionService TypePositionService;
	@Autowired
	private ITypeReferenceService TypeReferenceService;
	@Autowired
	private IDetalleCredito detalleCreditoService;
	@Autowired
	private ICustomerService customerService;

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
				case "typecivil":
					listType = new TypeToken<List<TypeCivilDTO>>() {}.getType();
					return new ResponseDTO(modelMapper.map(TypeCivilService.findAllActive(),listType), "Exito", true);
				case "typecontract":
					listType = new TypeToken<List<TypeContractDTO>>() {}.getType();
					return new ResponseDTO(modelMapper.map(TypeContractService.findAllActive(),listType), "Exito", true);
				case "typedatos":
					listType = new TypeToken<List<TypeDatosDTO>>() {}.getType();
					return new ResponseDTO(modelMapper.map(TypeDatosService.findAllActive(),listType), "Exito", true);
				case "typedocument":
					listType = new TypeToken<List<TypeDocumentDTO>>() {}.getType();
					return new ResponseDTO(modelMapper.map(TypeDocumentService.findAllActive(),listType), "Exito", true);
				case "typelaboralactivity":
					listType = new TypeToken<List<TypeLaboralActivityDTO>>() {}.getType();
					return new ResponseDTO(modelMapper.map(TypeLaboralActivityService.findAllActive(),listType), "Exito", true);
				case "typemaritalstatus":
					listType = new TypeToken<List<TypeMaritalStatusDTO>>() {}.getType();
					return new ResponseDTO(modelMapper.map(TypeMaritalStatusService.findAllActive(),listType), "Exito", true);
				case "typenationality":
					listType = new TypeToken<List<TypeNationalityDTO>>() {}.getType();
					return new ResponseDTO(modelMapper.map(TypeNationalityService.findAllActive(),listType), "Exito", true);
				case "typeposition":
					listType = new TypeToken<List<TypePositionDTO>>() {}.getType();
					return new ResponseDTO(modelMapper.map(TypePositionService.findAllActive(),listType), "Exito", true);
				case "typereference":
					listType = new TypeToken<List<TypeReferenceDTO>>() {}.getType();
					return new ResponseDTO(modelMapper.map(TypeReferenceService.findAllActive(),listType), "Exito", true);
				case "customerstransacion":
					return customerService.getByCustomerTransaction();

			}
			return new ResponseDTO(null, "Catalogo no encontrado .", false);
		} catch (Exception e) {
			return new ResponseDTO(null, "Catalogo no encontrado .", false);
		}
	}

	@GetMapping("/getDetalleCredito/{id}")
	public ResponseDTO getDetalleCredito(@PathVariable Integer id) {
		try {
			return new ResponseDTO(detalleCreditoService.findByCreditID(id), "Exito", true);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseDTO(null, e.getMessage(), false);
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
