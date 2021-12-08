package com.Xoot.CreditoParaTi.services.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.Xoot.CreditoParaTi.services.interfaces.IDocumentTypeService;
import com.Xoot.CreditoParaTi.entity.app.DocumentClass;
import com.Xoot.CreditoParaTi.entity.app.DocumentType;
import com.Xoot.CreditoParaTi.dto.DocumentTypeDTO;
import com.Xoot.CreditoParaTi.dto.ResponseDTO;
import com.Xoot.CreditoParaTi.repositories.app.IDocumentClassDao;
import com.Xoot.CreditoParaTi.repositories.app.IDocumentTypeDao;

@Service
public class DocumentTypeImpl implements IDocumentTypeService{
	public Object data = null;
	public Boolean result = false;
	public String message;

	@Autowired
	private IDocumentTypeDao _typeDocumentDao;
	@Autowired
	private IDocumentClassDao _classDocumentDao;
	
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
	public List<DocumentType> findByClass(Integer id) {
		return _typeDocumentDao.findByClass(id);
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
	@Transactional(readOnly = true)
	public ResponseDTO getByClass(Integer id) {
		List<DocumentType> lstDocumentType = findByClass(id);

		if(lstDocumentType.size() == 0) {
			return new ResponseDTO(null, "No existen tipos de documentos de la la clase: " + id, false);
		}
		
		return new ResponseDTO(lstDocumentType, "Exito", true);
	}

	@Override
	@Transactional
	public ResponseDTO save(DocumentTypeDTO catalogo) {
		DocumentType documentTypeByName = _typeDocumentDao.findByName(catalogo.getName());

		if (documentTypeByName != null) {
			return CreateResonseDuplicatedDocumentType(documentTypeByName);
		} 
		
		DocumentType documentType = new DocumentType();

		DocumentType documentTypeReturn = saveDocumentType(1,catalogo.getName(),catalogo.getIdClass(),documentType);
		
		data = documentTypeReturn;

		result = documentTypeReturn == null ? false : true;
		if(documentTypeReturn == null) {
			message = "No existe la clase de documento enviado.";
		}else {
			message = "Registro creado.";
		}
		
		return new ResponseDTO(data, message, result);
	}

	@Override
	@Transactional
	public ResponseDTO update(Integer id, DocumentTypeDTO catalogo) {

		DocumentType documentTypeById = findById(id);

		DocumentType documentTypeByName = _typeDocumentDao.findByName(catalogo.getName());

		if(CheckDocumentTypeNotExist(documentTypeById)) {
			return CreateResponseDocumentTypeNotExist();
		}

		if(CheckDuplicatedDocumentType(id, catalogo, documentTypeByName)) {
			return CreateResonseDuplicatedDocumentType(documentTypeByName);
		}

		data = saveDocumentType(1,catalogo.getName(),catalogo.getIdClass(),documentTypeById);

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

		saveDocumentType(0,null,null,documentType);

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
		
		saveDocumentType(1,null,null,documentTypeByName);

		result = true;

		message = "Registro Activado.";

		return new ResponseDTO(data, message, result);
		
	}
	
	@Override
	@Transactional(readOnly = true)
	public List<DocumentType> getlistDocumentType(List<Integer> lstIds) {
		List<DocumentType> lstRetorno = new ArrayList<>();
		DocumentType documentType;
		for (Integer p:lstIds) {
			documentType = findById(p);
			if(documentType != null) {
				lstRetorno.add(documentType);
			}
		}
		
		return lstRetorno;
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
	
	private DocumentType saveDocumentType(Integer documentType_flag, String documentTypeName, Integer idClass,DocumentType documentType ) {

	    if( documentTypeName != null && !documentTypeName.isEmpty()) {
	    	documentType.setName(documentTypeName);
	    }
	    
	    DocumentType documentTypeReturn = null;
	    
	    if( idClass != null && idClass > 0) {
	    	DocumentClass documentClass= _classDocumentDao.findById(idClass).orElse(null);
	    	
	    	if(documentClass != null) {
	    		documentType.setClassDocument(documentClass);
	    		
	    		documentType.setStatus_flag(documentType_flag);
	    		
	    	    documentType.setMdfd_on(new Date());
	    	    
	    	    documentTypeReturn = _typeDocumentDao.save(documentType);
	    	}
	    }
	    		
		return documentTypeReturn;
	}

	private boolean CheckDuplicatedDocumentType(Integer id, DocumentTypeDTO catalogo, DocumentType documentTypeByName) {
		
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
