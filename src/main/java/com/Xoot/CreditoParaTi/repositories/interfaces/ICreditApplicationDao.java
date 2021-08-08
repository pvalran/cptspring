package com.Xoot.CreditoParaTi.repositories.interfaces;

import java.util.List;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.Xoot.CreditoParaTi.entity.CreditApplication;

public interface ICreditApplicationDao extends CrudRepository<CreditApplication, Integer>, JpaSpecificationExecutor<CreditApplication> {

	@Query(nativeQuery = true, value = "SELECT * FROM credits_aplications WHERE status_flag = 1")
	public List<CreditApplication> findAllActive();
	
	@Query(nativeQuery = true, value = "SELECT * FROM credits_aplications WHERE id=:id AND status_flag = 1 LIMIT 1")
	public CreditApplication getById(@Param("id") Integer id);
	
	@Query(nativeQuery = true, value = "SELECT * FROM credits_aplications WHERE user_id=:idUser AND status_flag = 1")
	public List<CreditApplication> getAllByUser(@Param("idUser") Integer idUser);

	@Query(nativeQuery = true, value = "SELECT * FROM credits_aplications WHERE credit_id=:creditId limit 1")
	public CreditApplication FindByCreditUser(@Param("creditId") Integer creditId);

}
