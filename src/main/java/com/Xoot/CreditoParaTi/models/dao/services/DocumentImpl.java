package com.Xoot.CreditoParaTi.models.dao.services;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.Xoot.CreditoParaTi.Definiciones.Services.IDocumentClassService;
import com.Xoot.CreditoParaTi.Definiciones.Services.IDocumentService;
import com.Xoot.CreditoParaTi.Definiciones.Services.IDocumentTypeService;
import com.Xoot.CreditoParaTi.entity.Document;
import com.Xoot.CreditoParaTi.entity.DocumentClass;
import com.Xoot.CreditoParaTi.entity.DocumentType;
import com.Xoot.CreditoParaTi.entity.DTO.DocumentDTO;
import com.Xoot.CreditoParaTi.entity.DTO.ResponseDTO;
import com.Xoot.CreditoParaTi.models.dao.IDocumentDao;

@Service
public class DocumentImpl implements IDocumentService {
	@Autowired
	private IDocumentDao documentDao;
	@Autowired
	public IDocumentClassService classDocumentService;
	@Autowired
	public IDocumentTypeService typeDocumentService;
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
	public Document findAllIds(Integer idCreditAplication, Integer idClassDocument, Integer idTypeDocument) {
		return documentDao.findAllIds(idCreditAplication, idClassDocument, idTypeDocument);
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
	
	/*
	 * Agregar Credit Aplication
	 */
	@Override
	@Transactional
	public ResponseDTO save(DocumentDTO document) {
		Document documentExist = findAllIds(document.getCreditAplicationId(), document.getClassDocumentId(),
				document.getTypeDocumentId());
		DocumentType typeDocument = typeDocumentService.findById(document.getTypeDocumentId());
		DocumentClass classDocument = classDocumentService.findById(document.getClassDocumentId());

		data = null;
		result = false;
		if (documentExist != null) {
			message = "El documento ha sido registrado anteriormente.";
		} else if (typeDocument == null) {
			message = "El tipo de documento proporcionado no existe.";
		} else if (classDocument == null) {
			message = "La clase de documento proporcionado no existe.";
		} else {
			Document newDocument = new Document();

			newDocument.setStatus_flag(1);
			//newDocument.setCreditAplication(null);
			newDocument.setTypeDocument(typeDocument);
			newDocument.setClassDocument(classDocument);

			data = documentDao.save(newDocument);
			result = true;
			message = "Registro creado.";
		}
		return new ResponseDTO(data, message, result);
	}
	
	/*
	 * Agregar Credit Aplication
	 */
	@Override
	@Transactional
	public ResponseDTO update(Integer id, DocumentDTO document) {
		
		Document documentActual = findById(id);
		Document documentExist = findAllIds(document.getCreditAplicationId(), document.getClassDocumentId(),
				document.getTypeDocumentId());
		DocumentType typeDocument = typeDocumentService.findById(document.getTypeDocumentId());
		DocumentClass classDocument = classDocumentService.findById(document.getClassDocumentId());

		data = null;
		result = false;
		if (documentActual == null) {
			message = "No existe un documento con el id proporcionado.";
		} else if (documentExist.getIdDocument() != id) {
			message = "Ya existe otro documento registrado con las mismas propiedades.";
		} else if (typeDocument == null) {
			message = "El tipo de documento proporcionado no existe.";
		} else if (classDocument == null) {
			message = "La clase de documento proporcionado no existe.";
		} else {
			//newDocument.setCreditAplication(null);
			documentActual.setTypeDocument(typeDocument);
			documentActual.setClassDocument(classDocument);

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
