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
	public Object data;
	public String message;
	public Boolean result;
	
	@Autowired
	private IDocumentTypeDao typeDocumentDao;

	@Override
	@Transactional(readOnly = true)
	public DocumentType findById(Integer id) {
		return typeDocumentDao.findById(id).orElse(null);
	}

	@Override
	@Transactional(readOnly = true)
	public List<DocumentType> findAllActive() {
		return typeDocumentDao.findAllActive();
	}

	@Override
	@Transactional(readOnly = true)
	public DocumentType findByName(String name) {
		return typeDocumentDao.findByName(name);
	}

	@Override
	@Transactional(readOnly = true)
	public ResponseDTO getById(Integer id) {
		DocumentType typeDocument = findById(id);

		if (typeDocument == null) {
			data = null;
			result = false;
			message = "No existe un tipo de documento con el id proporcionado.";
		} else {
			data = typeDocument;
			result = true;
			message = "Exito";
		}
		
		return new ResponseDTO(data, message, result);
	}
	
	@Override
	@Transactional
	public ResponseDTO save(CatalogoDTO catalogo) {
		DocumentType typeExist = findByName(catalogo.getName());
		
		if (typeExist != null) {
			data = null;
			result = false;
			message = "Ya se registro un tipo de documento con el nombre " + catalogo.getName();
		} else {
			DocumentType typeDocument = new DocumentType();
			typeDocument.setStatus_flag(1);
			typeDocument.setName(catalogo.getName());

			data = typeDocumentDao.save(typeDocument);
			result = true;
			message = "Registro creado.";
		}
		return new ResponseDTO(data, message, result);
	}

	@Override
	@Transactional
	public ResponseDTO update(Integer id, CatalogoDTO catalogo) {
		DocumentType typeActual = findById(id);
		DocumentType typeExist = findByName(catalogo.getName());
		
		data = null;
		result = false;
		
		if (typeActual == null) {	
			message = "No existe un tipo de documento con el id proporcionado.";
		} else if (typeExist != null && typeExist.getIdTypeDocument() != id) {
			message = "Ya se registro un tipo de documento con el nombre " + catalogo.getName();
		} else {
			typeActual.setName(catalogo.getName());
			typeActual.setMdfd_on(new Date());

			data = typeDocumentDao.save(typeActual);
			result = true;
			message = "Registro actualizado.";
		}
		return new ResponseDTO(data, message, result);
	}
	
	@Override
	@Transactional
	public ResponseDTO delete(Integer id) {
		DocumentType typeDocument = findById(id);

		if (typeDocument == null) {
			data = null;
			result = false;
			message = "No existe un tipo de documento con el id proporcionado.";
		} else {
			typeDocument.setStatus_flag(0);
			typeDocument.setMdfd_on(new Date());
			typeDocumentDao.save(typeDocument);

			data = null;
			result = true;
			message = "Registro eliminado.";
		}
		
		return new ResponseDTO(data, message, result);
	}
}
