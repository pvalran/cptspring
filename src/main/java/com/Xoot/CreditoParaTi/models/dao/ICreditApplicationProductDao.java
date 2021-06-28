package com.Xoot.CreditoParaTi.models.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.Xoot.CreditoParaTi.entity.CreditApplicationProduct;

public interface ICreditApplicationProductDao extends CrudRepository<CreditApplicationProduct, Integer>, JpaSpecificationExecutor<CreditApplicationProduct> {

	@Query(nativeQuery = true, value = "SELECT * FROM cpt.credits_aplication_products WHERE status_flag = 1;")
	public List<CreditApplicationProduct> findAllActive();

	@Query(nativeQuery = true, value = "SELECT * FROM  cpt.credits_aplication_products WHERE status_flag = 1 AND name=:name LIMIT 1;")
	public CreditApplicationProduct findByNameActive(@Param("name") String name);
	
	@Query(nativeQuery = true, value = "SELECT * FROM  cpt.credits_aplication_products WHERE name=:name LIMIT 1;")
	public CreditApplicationProduct findByName(@Param("name") String name);

}
