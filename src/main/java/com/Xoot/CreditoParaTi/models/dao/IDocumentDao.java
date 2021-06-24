package com.Xoot.CreditoParaTi.models.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.Xoot.CreditoParaTi.entity.Document;

public interface IDocumentDao extends CrudRepository<Document, Integer> {
	@Query(nativeQuery = true, value = "SELECT * FROM cpt.documents WHERE status_flag = 1;")
    List<Document> findAllActive();
}
