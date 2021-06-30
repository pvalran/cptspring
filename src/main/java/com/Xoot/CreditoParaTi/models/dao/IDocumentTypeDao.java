package com.Xoot.CreditoParaTi.models.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.Xoot.CreditoParaTi.entity.DocumentType;

public interface IDocumentTypeDao extends CrudRepository<DocumentType, Integer>, JpaSpecificationExecutor<DocumentType> {
	
	@Query(nativeQuery = true, value = "SELECT * FROM cpt.documents_type WHERE status_flag = 1;")
	public List<DocumentType> findAllActive();
	
	@Query(nativeQuery = true, value = "SELECT * FROM cpt.documents_type WHERE status_flag = 1 AND name=:name LIMIT 1;")
	public DocumentType findByNameActive(@Param("name") String name);
	
	@Query(nativeQuery = true, value = "SELECT * FROM cpt.documents_type WHERE name=:name LIMIT 1;")
	public DocumentType findByName(@Param("name") String name);
	
	@Query(nativeQuery = true, value = "SELECT * FROM cpt.documents_type WHERE class_document_id=:id AND status_flag = 1;")
	public List<DocumentType> findByClass(@Param("id") Integer id);
}