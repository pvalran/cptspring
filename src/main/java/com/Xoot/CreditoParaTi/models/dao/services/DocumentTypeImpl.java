package com.Xoot.CreditoParaTi.models.dao.services;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.Xoot.CreditoParaTi.Definiciones.Services.IDocumentTypeService;
import com.Xoot.CreditoParaTi.entity.DocumentType;
import com.Xoot.CreditoParaTi.entity.DTO.CatalogoDTO;
import com.Xoot.CreditoParaTi.entity.DTO.ResponseDTO;
import com.Xoot.CreditoParaTi.models.dao.IDocumentTypeDao;

@Service
public class DocumentTypeImpl implements IDocumentTypeService{
	public Object data = null;
	public Boolean result = false;
	public String message;

	@Autowired
	private IDocumentTypeDao _typeDocumentDao;

	@Override
	@Transactional(readOnly = true)
	public List<DocumentType> findAllActive() {
		return _typeDocumentDao.findAllActive();
	}

	@Override
	@Transactional(readOnly = true)
	public DocumentType findById(Integer id) {
		return _typeDocumentDao.findById(id).orElse(null);
	}

	@Override
	@Transactional(readOnly = true)
	public DocumentType findByName(String name) {
		return _typeDocumentDao.findByNameActive(name);
	}

	@Override
	@Transactional(readOnly = true)
	public ResponseDTO getById(Integer id) {
		DocumentType documentType = findById(id);

		if(CheckDocumentTypeNotExist(documentType)) {
			return CreateResponseDocumentTypeNotExist();
		}
	
		data = documentType;
		result = true;
		message = "Exito";
		
		return new ResponseDTO(data, message, result);
	}

	@Override
	@Transactional
	public ResponseDTO save(CatalogoDTO catalogo) {
		DocumentType documentTypeByName = _typeDocumentDao.findByName(catalogo.getName());

		if (documentTypeByName != null) {
			return CreateResonseDuplicatedDocumentType(documentTypeByName);
		} 
		
		DocumentType documentType = new DocumentType();

		data = saveDocumentType(1,catalogo.getName(),documentType);

		result = true;

		message = "Registro creado.";
		
		return new ResponseDTO(data, message, result);
	}

	@Override
	@Transactional
	public ResponseDTO update(Integer id, CatalogoDTO catalogo) {

		DocumentType documentTypeById = findById(id);

		DocumentType documentTypeByName = _typeDocumentDao.findByName(catalogo.getName());

		if(CheckDocumentTypeNotExist(documentTypeById)) {
			return CreateResponseDocumentTypeNotExist();
		}

		if(CheckDuplicatedDocumentType(id, catalogo, documentTypeByName)) {
			return CreateResonseDuplicatedDocumentType(documentTypeByName);
		}

		data = saveDocumentType(1,catalogo.getName(),documentTypeById);

		result = true;

		message = "Registro actualizado.";

		return new ResponseDTO(data, message, result);
	}

	@Override
	@Transactional
	public ResponseDTO delete(Integer id) {
		DocumentType documentType = findById(id);
		
		if(CheckDocumentTypeNotExist(documentType)) {
			return CreateResponseDocumentTypeNotExist();
		}

		saveDocumentType(0,null,documentType);

		result = true;

		message = "Registro eliminado.";

		return new ResponseDTO(data, message, result);
	}

	@Override
	@Transactional
	public ResponseDTO active(Integer Id) {
		DocumentType documentTypeByName = findById(Id);
		
		if(CheckDocumentTypeNotExist(documentTypeByName)) {
			return CreateResponseDocumentTypeNotExist();
		}
		
		saveDocumentType(1,null,documentTypeByName);

		result = true;

		message = "Registro Activado.";

		return new ResponseDTO(data, message, result);
		
	}
	 
	private boolean CheckDocumentTypeNotExist(DocumentType DocumentType) {
		boolean response = false;
		
		if (DocumentType == null) {
			response = true;
		}
		
		return response;
	}
	
	private ResponseDTO CreateResponseDocumentTypeNotExist() {
		message = "No existe un tipo de documento con el id proporcionado.";
		
		return new ResponseDTO(data, message, result);
	}
	
	private DocumentType saveDocumentType(Integer documentType_flag, String documentTypeName, DocumentType documentType ) {

	    if( documentTypeName != null && !documentTypeName.isEmpty()) {
	    	documentType.setName(documentTypeName);
	    }
	    
	    documentType.setStatus_flag(documentType_flag);
		
	    documentType.setMdfd_on(new Date());
		
		return _typeDocumentDao.save(documentType);
	}

	private boolean CheckDuplicatedDocumentType(Integer id, CatalogoDTO catalogo, DocumentType documentTypeByName) {
		
		boolean response = false;

		if (documentTypeByName != null
				&& documentTypeByName.getIdTypeDocument() != id) {
			response = true;		
		}
		
		return response;
	}
	
	private ResponseDTO CreateResonseDuplicatedDocumentType(DocumentType DocumentTypeByName) {
		
		if(DocumentTypeByName.getStatus_flag() == 0) {
			message = "Ya existe un tipo de documento con el nombre: " + DocumentTypeByName.getName() + " y se encuentra inactivo ID " + DocumentTypeByName.getIdTypeDocument();
		}else {
			message = "Ya existe un tipo de documento con el nombre: " + DocumentTypeByName.getName();
		}
	
		return new ResponseDTO(data, message, result);
	}
}
