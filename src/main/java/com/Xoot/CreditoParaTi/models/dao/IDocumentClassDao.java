package com.Xoot.CreditoParaTi.models.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.Xoot.CreditoParaTi.entity.DocumentClass;

public interface IDocumentClassDao extends CrudRepository<DocumentClass, Integer>, JpaSpecificationExecutor<DocumentClass> {
	
	@Query(nativeQuery = true, value = "SELECT * FROM cpt.documents_class WHERE status_flag = 1;")
	public List<DocumentClass> findAllActive();

	@Query(nativeQuery = true, value = "SELECT * FROM cpt.documents_class WHERE status_flag = 1 AND name=:name LIMIT 1;")
	public DocumentClass findByNameActive(@Param("name") String name);
	
	@Query(nativeQuery = true, value = "SELECT * FROM cpt.documents_class WHERE name=:name LIMIT 1;")
	public DocumentClass findByName(@Param("name") String name);
}