package com.Xoot.CreditoParaTi.controllers;

import com.Xoot.CreditoParaTi.entity.LocationCity;
import com.Xoot.CreditoParaTi.entity.LocationCounty;
import com.Xoot.CreditoParaTi.entity.LocationState;
import com.Xoot.CreditoParaTi.mapper.RfcDTO;
import com.Xoot.CreditoParaTi.repositories.interfaces.ILocationCityDao;
import com.Xoot.CreditoParaTi.repositories.interfaces.ILocationStateDao;
import com.Xoot.CreditoParaTi.repositories.interfaces.ILocationsCountiesDao;
import com.josketres.rfcfacil.Rfc;
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
	private ILocationCountiesService locationCountiesService;
	@Autowired
	private ILocationCityService locationCityService;
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
	private ITypeDependentService TypeDependentService;
	@Autowired
	private IDetalleCredito detalleCreditoService;
	@Autowired
	private ICustomerService customerService;

	@Autowired
	private IUserService userService;
	@Autowired
	private ILocationStateDao stateDao;
	@Autowired
	private ILocationsCountiesDao countiesDao;
	@Autowired
	private ILocationCityDao cityDao;
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
					listType = new TypeToken<List<LocationsCountiesDTO>>() {}.getType();
					return new ResponseDTO(modelMapper.map(locationCountiesService.findAllActive(),listType), "Exito", true);
				case "city":
					listType = new TypeToken<List<LocationsCitiesDTO>>() {}.getType();
					return new ResponseDTO(modelMapper.map(locationCityService.findAllActive(),listType), "Exito", true);
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
				case "typedependent":
					listType = new TypeToken<List<TypeDependentDTO>>() {}.getType();
					return new ResponseDTO(modelMapper.map(TypeDependentService.findAllActive(),listType), "Exito", true);
			}
			return new ResponseDTO(null, "Catalogo no encontrado .", false);
		} catch (Exception e) {
			return new ResponseDTO(null, "Catalogo no encontrado .", false);
		}
	}

	@GetMapping("/getDependentByCivil/{civil}")
	public ResponseDTO getDependentByCivil(@PathVariable Integer civil) {
		try {
			Type listType;
			listType = new TypeToken<List<TypeDependentDTO>>() {}.getType();
			return new ResponseDTO(modelMapper.map(TypeDependentService.findByCivil(civil),listType), "Exito", true);

		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseDTO(null, e.getMessage(), false);
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

	@GetMapping("/getState")
	public ResponseDTO getState() {
		Type listType;
		try {
			listType = new TypeToken<List<LocationsStatesDTO>>() {}.getType();
			return new ResponseDTO(modelMapper.map(locationStateService.findAllActive(),listType), "Exito", true);
		} catch (Exception e) {
			return new ResponseDTO(null, "Catalogo no encontrado .", false);
		}
	}

	@GetMapping("/getMunByState/{id}")
	public ResponseDTO getMunByState(@PathVariable Integer id) {
		try {
			return new ResponseDTO(locationCountiesService.getMuntoState(id), "Exito", true);
		} catch (Exception e) {
			return new ResponseDTO(null, "Catalogo no encontrado .", false);
		}
	}

	@GetMapping("/getCityByMun/{id}")
	public ResponseDTO getCityByMun(@PathVariable Integer id) {
		try {
			Type listType = new TypeToken<List<LocationsCitiesDTO>>() {}.getType();
			return new ResponseDTO(modelMapper.map(locationCityService.getCityByMun(id),listType), "Exito", true);
		} catch (Exception e) {
			return new ResponseDTO(null, "Catalogo no encontrado .", false);
		}
	}

	@GetMapping("/getCityByMun/{state}/{counties}")
	public ResponseDTO getCityByMun(@PathVariable String state,@PathVariable String counties) {
		try {
			Type listType = new TypeToken<List<LocationsCitiesDTO>>() {}.getType();
			return new ResponseDTO(modelMapper.map(locationCityService.getCityToMun(state,counties),listType), "Exito", true);
		} catch (Exception e) {
			return new ResponseDTO(null, "Catalogo no encontrado .", false);
		}
	}

	@GetMapping("/getColonyByMun/{id}")
	public ResponseDTO getColonyToMun(@PathVariable Integer id) {
		try {
			Type listType = new TypeToken<List<LocationsSuburbDTO>>() {}.getType();
			return new ResponseDTO(modelMapper.map(locationSuburbService.getSuburbByMun(id),listType), "Exito", true);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseDTO(null, "Catalogo no encontrado .", false);
		}
	}

	@GetMapping("/getColonyToCity/{id}")
	public ResponseDTO getColonyToCity(@PathVariable Integer id) {
		try {
			Type listType = new TypeToken<List<LocationsSuburbDTO>>() {}.getType();
			return new ResponseDTO(modelMapper.map(locationSuburbService.getSuburbByCity(id),listType), "Exito", true);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseDTO(null, "Catalogo no encontrado .", false);
		}
	}

	@GetMapping("/getColonyToCity/{state}/{counties}/{cities}")
	public ResponseDTO getColonyToCity(@PathVariable String state,
									   @PathVariable String counties,
									   @PathVariable String cities) {
		try {
			Type listType = new TypeToken<List<LocationsSuburbDTO>>() {}.getType();
			return new ResponseDTO(modelMapper.map(locationSuburbService.getSuburbToCity(state,counties,cities),listType), "Exito", true);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseDTO(null, "Catalogo no encontrado .", false);
		}
	}

	@GetMapping("/getDirectionToCp/{zipcode}")
	public ResponseDTO getDirectionToCp(@PathVariable Integer zipcode) {
		try {
			List<LocationsSuburbDTO> suburbDTOS = locationSuburbService.getDirectionToCp(zipcode);

			return new ResponseDTO(suburbDTOS, "Exito", true);
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

	@PostMapping("/getRFC")
	public ResponseDTO getRFC(@RequestBody RfcDTO rfc) {
		try {
			Integer day = rfc.getDateBirthday().getDate();
			Integer month = rfc.getDateBirthday().getMonth() + 1;
			Integer year = rfc.getDateBirthday().getYear();
			Rfc rd = new Rfc.Builder().name(rfc.getName()).firstLastName(rfc.getLastName()).secondLastName(rfc.getLastSecondName()).birthday(day, month, year).build();
			return new ResponseDTO(rd.toString(), "Rfc generado", true);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseDTO(null, "Rfc no encontrado .", false);
		}
	}


}
