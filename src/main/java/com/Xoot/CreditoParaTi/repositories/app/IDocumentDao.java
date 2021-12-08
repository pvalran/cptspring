package com.Xoot.CreditoParaTi.repositories.app;

import java.util.List;


import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.Xoot.CreditoParaTi.entity.app.Document;

public interface IDocumentDao extends CrudRepository<Document, Integer>, JpaSpecificationExecutor<Document> {
	
	@Query(nativeQuery = true, value = "SELECT * FROM documents WHERE status_flag = 1 AND number_request=:idCreditAplication")
	public List<Document> findAllActive(@Param("idCreditAplication") Integer idCreditAplication);
	
	@Query(nativeQuery = true, value = "SELECT * FROM documents "
			+ "WHERE number_request=:idCreditAplication "
			+"AND type_document_id=:idTypeDocument "
			+ "AND status_flag = 1 LIMIT 1")
	public Document findAllIds(
			@Param("idCreditAplication") Integer idCreditAplication,
			@Param("idTypeDocument") Integer idTypeDocument);

	@Query(nativeQuery = true, value = "SELECT * FROM documents WHERE number_request=:creditId and status_flag = 1 order by type_document_id")
	public List<Document> findByCreditId(@Param("creditId") Integer creditId);

	@Query(nativeQuery = true, value = "SELECT * FROM documents WHERE number_request=:creditId" +
			" and type_document_id in (12,13,14) and status_flag = 1")
	public List<Document> findByCreditIdSpouse(@Param("creditId") Integer creditId);

	@Query(nativeQuery = true, value = "SELECT * FROM documents WHERE status_flag = 1 " +
			"AND number_request=:idCreditAplication and class_document_id = 2")
	public List<Document> findAllActiveCoacredit(@Param("idCreditAplication") Integer idCreditAplication);


}
