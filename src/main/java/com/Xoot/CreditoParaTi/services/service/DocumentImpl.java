package com.Xoot.CreditoParaTi.services.service;

import java.util.Date;
import java.util.List;

import com.Xoot.CreditoParaTi.services.interfaces.ICreditApplicationService;
import com.Xoot.CreditoParaTi.entity.CreditApplication;
import com.Xoot.CreditoParaTi.dto.CreditApplicationDTO;
import com.Xoot.CreditoParaTi.repositories.interfaces.ICreditApplicationDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.Xoot.CreditoParaTi.services.interfaces.IDocumentService;
import com.Xoot.CreditoParaTi.services.interfaces.IDocumentTypeService;
import com.Xoot.CreditoParaTi.entity.Document;
import com.Xoot.CreditoParaTi.entity.DocumentType;
import com.Xoot.CreditoParaTi.dto.DocumentDTO;
import com.Xoot.CreditoParaTi.dto.ResponseDTO;
import com.Xoot.CreditoParaTi.repositories.interfaces.IDocumentDao;

@Service
public class DocumentImpl implements IDocumentService {
	@Autowired
	private IDocumentDao documentDao;

	@Autowired
	private ICreditApplicationDao creditApplicationDao;

	@Autowired
	private IDocumentService documentService;

	@Autowired
	public IDocumentTypeService typeDocumentService;
	@Autowired
	public ICreditApplicationService creditApplicationService;

	public Object data;
	public String message;
	public Boolean result;

	@Override
	@Transactional(readOnly = true)
	public Document findById(Integer id) {
		return documentDao.findById(id).orElse(null);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Document> findAllActive(Integer idCreditAplication) {
		return documentDao.findAllActive(idCreditAplication);
	}

	@Override
	@Transactional(readOnly = true)
	public Document findAllIds(Integer idCreditAplication,Integer idTypeDocument) {
		return documentDao.findAllIds(idCreditAplication, idTypeDocument);
	}

	@Override
	@Transactional(readOnly = true)
	public ResponseDTO getById(Integer id) {
		Document document = findById(id);

		if (document == null) {
			data = null;
			result = false;
			message = "No existe un documento con el id proporcionado.";
		} else {

			data = document;
			result = true;
			message = "Exito";
		}
		return new ResponseDTO(data, message, result);
	}
	
	@Override
	@Transactional
	public ResponseDTO save(DocumentDTO document) {
		Document documentExist = findAllIds(document.getCreditAplication(),document.getTypeDocumentId());
		DocumentType typeDocument = typeDocumentService.findById(document.getTypeDocumentId());
		//CreditApplication creditApplication = creditApplicationService.findById(document.getCreditAplication());
		CreditApplication creditApplication = creditApplicationDao.FindByCreditUser(document.getCreditAplication());

		if (creditApplication == null) {
			CreditApplicationDTO creditApplicationDTO = new CreditApplicationDTO();
			creditApplicationDTO.setUser(document.getUserId());
			creditApplicationDTO.setCreditId(document.getCreditAplication());
			creditApplicationService.save(creditApplicationDTO);
		}

		data = null;
		result = false;
		if (documentExist != null) {
			Document documentUpdate = documentService.findById(documentExist.getIdDocument());
			documentUpdate.setStatus_flag(0);
			documentDao.save(documentUpdate);
			message = "El documento ha sido registrado anteriormente.";
		} else if (typeDocument == null) {
			message = "El tipo de documento proporcionado no existe.";
			return new ResponseDTO(data, message, result);
		}

		Document newDocument = new Document();
		newDocument.setName(document.getName());
		newDocument.setStatus_flag(1);
		newDocument.setCreditAplication(document.getCreditAplication());
		newDocument.setTypeDocumentId(typeDocument.getIdTypeDocument());
		newDocument.setClass_document_id(document.getClassDocumentId());
		newDocument.setUserId(document.getUserId());
		data = documentDao.save(newDocument);
		result = true;
		message = "Registro creado.";
		return new ResponseDTO(data, message, result);
	}
	
	@Override
	@Transactional
	public ResponseDTO update(Integer id, DocumentDTO document) {
		
		Document documentActual = findById(id);
		Document documentExist = findAllIds(document.getCreditAplication(), document.getTypeDocumentId());
		DocumentType typeDocument = typeDocumentService.findById(document.getTypeDocumentId());

		data = null;
		result = false;
		if (documentActual == null) {
			message = "No existe un documento con el id proporcionado.";
		} else if (documentExist.getIdDocument() != id) {
			message = "Ya existe otro documento registrado con las mismas propiedades.";
		} else if (typeDocument == null) {
			message = "El tipo de documento proporcionado no existe.";
		} else {
			//newDocument.setCreditAplication(null);
			documentActual.setTypeDocumentId(typeDocument.getIdTypeDocument());

			data = documentDao.save(documentActual);
			result = true;
			message = "Registro Actualizado.";
		}
		return new ResponseDTO(data, message, result);
	}

	@Override
	@Transactional
	public ResponseDTO delete(Integer id) {
		Document document = findById(id);

		if (document == null) {
			data = null;
			result = false;
			message = "No existe una clase de documento con el id proporcionado.";
		} else {
			document.setStatus_flag(0);
			document.setMdfd_on(new Date());
			documentDao.save(document);

			data = null;
			result = true;
			message = "Registro eliminado.";
		}
		return new ResponseDTO(data, message, result);
	}
}
