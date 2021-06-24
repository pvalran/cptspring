package com.Xoot.CreditoParaTi.models.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.Xoot.CreditoParaTi.entity.DocumentClass;

public interface IClassDocumentDao extends CrudRepository<DocumentClass, Integer>, JpaSpecificationExecutor<DocumentClass> {
	@Query(nativeQuery = true, value = "SELECT * FROM cpt.documents_class WHERE status_flag = 1;")
	List<DocumentClass> findAllActive();
}