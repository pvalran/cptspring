package com.Xoot.CreditoParaTi.models.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;


import com.Xoot.CreditoParaTi.entity.DocumentType;

public interface ITypeDocumentDao extends CrudRepository<DocumentType, Integer>, JpaSpecificationExecutor<DocumentType> {
	@Query(nativeQuery = true, value = "SELECT * FROM cpt.documents_type WHERE status_flag = 1;")
	List<DocumentType> findAllActive();
}