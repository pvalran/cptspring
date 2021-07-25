package com.Xoot.CreditoParaTi.models.dao;

import java.util.List;


import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.Xoot.CreditoParaTi.entity.Document;

public interface IDocumentDao extends CrudRepository<Document, Integer>, JpaSpecificationExecutor<Document> {
	
	@Query(nativeQuery = true, value = "SELECT * FROM documents WHERE status_flag = 1 AND credit_aplication_id=:idCreditAplication LIMIT 1;")
	public List<Document> findAllActive(@Param("idCreditAplication") Integer idCreditAplication);
	
	@Query(nativeQuery = true, value = "SELECT * FROM documents "
			+ "WHERE credit_aplication_id=:idCreditAplication "
			+"AND type_document_id=:idTypeDocument "
			+ "AND status_flag = 1 LIMIT 1;")
	public Document findAllIds(
			@Param("idCreditAplication") Integer idCreditAplication,
			@Param("idTypeDocument") Integer idTypeDocument);
}
