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
	public Object data;
	public String message;
	public Boolean result;
	
	@Autowired
	private IDocumentClassDao classDocumentDao;
	
	@Override
	@Transactional(readOnly = true)
	public List<DocumentClass> findAllActive() {
		return classDocumentDao.findAllActive();
	}

	@Override
	@Transactional(readOnly = true)
	public DocumentClass findById(Integer id) {
		return classDocumentDao.findById(id).orElse(null);
	}

	@Override
	@Transactional(readOnly = true)
	public DocumentClass findByName(String name) {
		return classDocumentDao.findByName(name);
	}
	
	@Override
	@Transactional(readOnly = true)
	public ResponseDTO getById(Integer id) {
		DocumentClass classDocument = findById(id);

		if (classDocument == null) {
			data = null;
			result = false;
			message = "No existe una clase de documento con el id proporcionado.";
		} else {

			data = classDocument;
			result = true;
			message = "Exito";
		}
		
		return new ResponseDTO(data, message, result);
	}
	
	@Override
	@Transactional
	public ResponseDTO save(CatalogoDTO catalogo) {
		DocumentClass classExist = findByName(catalogo.getName());
		
		if (classExist != null) {
			data = null;
			result = false;
			message = "Ya se registro una clase de documento con el nombre " + catalogo.getName();
		} else {
			DocumentClass classDocument = new DocumentClass();
			classDocument.setStatus_flag(1);
			classDocument.setName(catalogo.getName());

			data = classDocumentDao.save(classDocument);
			result = true;
			message = "Registro creado.";
		}
		return new ResponseDTO(data, message, result);
	}

	@Override
	@Transactional
	public ResponseDTO update(Integer id, CatalogoDTO catalogo) {
		DocumentClass classActual = findById(id);
		DocumentClass classExist = findByName(catalogo.getName());
		
		data = null;
		result = false;
		
		if (classActual == null) {	
			message = "No existe una clase de documento con el id proporcionado.";
		} else if (classExist != null && classExist.getIdClassDocument() != id) {
			message = "Ya se registro una clase de documento con el nombre " + catalogo.getName();
		} else {
			classActual.setName(catalogo.getName());
			classActual.setMdfd_on(new Date());

			data = classDocumentDao.save(classActual);
			result = true;
			message = "Registro actualizado.";
		}
		return new ResponseDTO(data, message, result);
	}
	
	@Override
	@Transactional
	public ResponseDTO delete(Integer id) {
		DocumentClass classDocument = findById(id);

		if (classDocument == null) {
			data = null;
			result = false;
			message = "No existe una clase de documento con el id proporcionado.";
		} else {
			classDocument.setStatus_flag(0);
			classDocument.setMdfd_on(new Date());
			classDocumentDao.save(classDocument);

			data = null;
			result = true;
			message = "Registro eliminado.";
		}
		
		return new ResponseDTO(data, message, result);
	}
}
