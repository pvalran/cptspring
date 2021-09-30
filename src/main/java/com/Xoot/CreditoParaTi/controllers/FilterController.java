package com.Xoot.CreditoParaTi.controllers;

import com.Xoot.CreditoParaTi.dto.*;
import com.Xoot.CreditoParaTi.entity.StatisticsTransaction;
import com.Xoot.CreditoParaTi.mapper.FilterTransacionDTO;
import com.Xoot.CreditoParaTi.services.interfaces.*;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.lang.reflect.Type;
import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/filter")
public class FilterController {
	@PersistenceContext
	private EntityManager entityManager;
	@Autowired
	private IDocumentService documentService;
	@Autowired
	private IDocumentTypeService documentTypeService;
	@Autowired
	private ILocationStateService locationStateService;
	@Autowired
	private ILocationCityService locationCityService;
	@Autowired
	private ILocationCountiesService locationColoniesService;
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
	private ITransactionService transactionService;


	@Autowired
	private ModelMapper modelMapper;

	@PostMapping("/customerstransacion")
	public ResponseDTO Customerstransacion(@RequestBody FilterTransacionDTO filterTransacionDto) {
		Type listType;
		try {
			return transactionService.filterDate(filterTransacionDto);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseDTO(null, "Filtro no realizado", false);
		}
	}

	@PostMapping("/customerstransacion/search")
	public ResponseDTO CustomersSearctransacion(@RequestBody FilterTransacionDTO filterTransacionDto) {
		Type listType;
		try {
			return transactionService.filterSearch(filterTransacionDto);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseDTO(null, "Filtro no realizado", false);
		}
	}

	@PostMapping("/statisticstransacion")
	public ResponseDTO statisticstransacion(@RequestBody FilterTransacionDTO filterTransacionDto) {
		Type listType;
		String status;
		try {
			if ((filterTransacionDto.getStatus().isEmpty()) ||
					(filterTransacionDto.getStatus() == null)) {
				status = "A,R,P";
			} else {
				status = filterTransacionDto.getStatus();
			}


			List<StatisticsTransaction> statisticsTransaction = entityManager.createNamedStoredProcedureQuery("DataRequest")
					.setParameter("dateInit", filterTransacionDto.getStartdate())
					.setParameter("dateEnd", filterTransacionDto.getEnddate())
					.setParameter("filters", status)
					.getResultList();
			return new ResponseDTO(statisticsTransaction, "Filtro  realizado", true);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseDTO(null, "Filtro no realizado", false);
		}
	}




}
