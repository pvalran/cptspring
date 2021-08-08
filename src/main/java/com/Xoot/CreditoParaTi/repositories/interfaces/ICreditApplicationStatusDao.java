package com.Xoot.CreditoParaTi.repositories.interfaces;

import java.util.List;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.Xoot.CreditoParaTi.entity.CreditApplicationStatus;

public interface ICreditApplicationStatusDao
		extends CrudRepository<CreditApplicationStatus, Integer>, JpaSpecificationExecutor<CreditApplicationStatus> {

	@Query(nativeQuery = true, value = "SELECT * FROM credits_aplication_status WHERE status_flag = 1")
	public List<CreditApplicationStatus> findAllActive();

	@Query(nativeQuery = true, value = "SELECT * FROM credits_aplication_status WHERE status_flag = 1 AND name=:name LIMIT 1")
	public CreditApplicationStatus findByNameActive(@Param("name") String name);
	
	@Query(nativeQuery = true, value = "SELECT * FROM credits_aplication_status WHERE name=:name LIMIT 1")
	public CreditApplicationStatus findByName(@Param("name") String name);
}
