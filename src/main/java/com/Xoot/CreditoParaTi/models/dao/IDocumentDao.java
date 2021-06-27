package com.Xoot.CreditoParaTi.models.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.Xoot.CreditoParaTi.entity.Document;

public interface IDocumentDao extends CrudRepository<Document, Integer> {
	
	@Query(nativeQuery = true, value = "SELECT * FROM cpt.documents WHERE status_flag = 1 AND credit_aplication_id=:idCreditAplication LIMIT 1;")
	public List<Document> findAllActive(@Param("id") Integer idCreditAplication);
	
	@Query(nativeQuery = true, value = "SELECT * FROM cpt.documents "
			+ "WHERE credit_aplication_id=:idCreditAplication "
			+"AND type_document_id=:idTypeDocument "
			+"AND class_document_id=:idClassDocument "
			+ "AND status_flag = 1 LIMIT 1;")
	public Document findAllIds(
			@Param("idCreditAplication") Integer idCreditAplication,
			@Param("idClassDocument") Integer idClassDocument,
			@Param("idTypeDocument") Integer idTypeDocument);
}
