	package com.Xoot.CreditoParaTi.repositories.interfaces;

import java.util.List;


import com.Xoot.CreditoParaTi.entity.DocStatusMap;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.Xoot.CreditoParaTi.entity.Document;

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

	@Query(nativeQuery = true, value = "SELECT * FROM documents WHERE number_request=:creditId" +
			" and (type_document_id >= 1 and type_document_id <= 11) and status_flag = 1")
	public List<Document> findByCreditDocCustomer(@Param("creditId") Integer creditId);

	@Query(nativeQuery = true, value = "select dt.id as type_document, case when COALESCE(d.number_request,true) = true then false else true end as status from documents_type dt " +
			"left join documents d on dt.id = d.type_document_id and d.number_request = :creditId and d.status_flag = 1 " +
			"order by dt.id")
	public List<DocStatusMap> findByCreditDocumentStatus(@Param("creditId") Integer creditId);

	@Query(nativeQuery = true, value = "SELECT * FROM documents WHERE number_request=:creditId" +
			" and type_document_id in (12,13,14) and status_flag = 1")
	public List<Document> findByCreditDocCoacredit(@Param("creditId") Integer creditId);


}
