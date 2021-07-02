package com.Xoot.CreditoParaTi.models.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.Xoot.CreditoParaTi.entity.CreditApplication;

public interface ICreditAplication extends CrudRepository<CreditApplication, Integer>, JpaSpecificationExecutor<CreditApplication> {

	@Query(nativeQuery = true, value = "SELECT * FROM cpt.credits_aplications WHERE status_flag = 1;")
	public List<CreditApplication> findAllActive();
}