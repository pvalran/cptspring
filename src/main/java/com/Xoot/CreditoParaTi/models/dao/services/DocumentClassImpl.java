package com.Xoot.CreditoParaTi.models.dao.services;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.Xoot.CreditoParaTi.Definiciones.Services.IDocumentClassService;
import com.Xoot.CreditoParaTi.entity.DocumentClass;

import com.Xoot.CreditoParaTi.entity.DTO.CatalogoDTO;
import com.Xoot.CreditoParaTi.entity.DTO.ResponseDTO;
import com.Xoot.CreditoParaTi.models.dao.IDocumentClassDao;

@Service
public class DocumentClassImpl implements IDocumentClassService{
	public Object data = null;
	public Boolean result = false;
	public String message;

	@Autowired
	private IDocumentClassDao _classDocumentDao;

	@Override
	@Transactional(readOnly = true)
	public List<DocumentClass> findAllActive() {
		return _classDocumentDao.findAllActive();
	}

	@Override
	@Transactional(readOnly = true)
	public DocumentClass findById(Integer id) {
		return _classDocumentDao.findById(id).orElse(null);
	}

	@Override
	@Transactional(readOnly = true)
	public DocumentClass findByName(String name) {
		return _classDocumentDao.findByNameActive(name);
	}

	@Override
	@Transactional(readOnly = true)
	public ResponseDTO getById(Integer id) {
		DocumentClass documentClass = findById(id);

		if(CheckDocumentClassNotExist(documentClass)) {
			return CreateResponseDocumentClassNotExist();
		}
	
		data = documentClass;
		result = true;
		message = "Exito";
		
		return new ResponseDTO(data, message, result);
	}

	@Override
	@Transactional
	public ResponseDTO save(CatalogoDTO catalogo) {
		DocumentClass documentClassByName = _classDocumentDao.findByName(catalogo.getName());

		if (documentClassByName != null) {
			return CreateResonseDuplicatedDocumentClass(documentClassByName);
		} 
		
		DocumentClass documentClass = new DocumentClass();

		data = saveDocumentClass(1,catalogo.getName(),documentClass);

		result = true;

		message = "Registro creado.";
		
		return new ResponseDTO(data, message, result);
	}

	@Override
	@Transactional
	public ResponseDTO update(Integer id, CatalogoDTO catalogo) {

		DocumentClass documentClassById = findById(id);

		DocumentClass documentClassByName = _classDocumentDao.findByName(catalogo.getName());

		if(CheckDocumentClassNotExist(documentClassById)) {
			return CreateResponseDocumentClassNotExist();
		}

		if(CheckDuplicatedDocumentClass(id, catalogo, documentClassByName)) {
			return CreateResonseDuplicatedDocumentClass(documentClassByName);
		}

		data = saveDocumentClass(1,catalogo.getName(),documentClassById);

		result = true;

		message = "Registro actualizado.";

		return new ResponseDTO(data, message, result);
	}

	@Override
	@Transactional
	public ResponseDTO delete(Integer id) {
		DocumentClass documentClass = findById(id);
		
		if(CheckDocumentClassNotExist(documentClass)) {
			return CreateResponseDocumentClassNotExist();
		}

		saveDocumentClass(0,null,documentClass);

		result = true;

		message = "Registro eliminado.";

		return new ResponseDTO(data, message, result);
	}

	@Override
	@Transactional
	public ResponseDTO active(Integer Id) {
		DocumentClass documentClassByName = findById(Id);
		
		if(CheckDocumentClassNotExist(documentClassByName)) {
			return CreateResponseDocumentClassNotExist();
		}
		
		saveDocumentClass(1,null,documentClassByName);

		result = true;

		message = "Registro Activado.";

		return new ResponseDTO(data, message, result);
		
	}
	 
	private boolean CheckDocumentClassNotExist(DocumentClass DocumentClass) {
		boolean response = false;
		
		if (DocumentClass == null) {
			response = true;
		}
		
		return response;
	}
	
	private ResponseDTO CreateResponseDocumentClassNotExist() {
		message = "No existe una clase de documento con el id proporcionado.";
		
		return new ResponseDTO(data, message, result);
	}
	
	private DocumentClass saveDocumentClass(Integer documentClass_flag, String documentClassName, DocumentClass documentClass ) {

	    if( documentClassName != null && !documentClassName.isEmpty()) {
	    	documentClass.setName(documentClassName);
	    }
	    
	    documentClass.setStatus_flag(documentClass_flag);
		
	    documentClass.setMdfd_on(new Date());
		
		return _classDocumentDao.save(documentClass);
	}

	private boolean CheckDuplicatedDocumentClass(Integer id, CatalogoDTO catalogo, DocumentClass documentClassByName) {
		
		boolean response = false;

		if (documentClassByName != null
				&& documentClassByName.getIdClassDocument() != id) {
			response = true;		
		}
		
		return response;
	}
	
	private ResponseDTO CreateResonseDuplicatedDocumentClass(DocumentClass DocumentClassByName) {
		
		if(DocumentClassByName.getStatus_flag() == 0) {
			message = "Ya existe una clase de documento con el nombre: " + DocumentClassByName.getName() + " y se encuentra inactivo ID " + DocumentClassByName.getIdClassDocument();
		}else {
			message = "Ya existe una clase de documento con el nombre: " + DocumentClassByName.getName();
		}
	
		return new ResponseDTO(data, message, result);
	}
}
