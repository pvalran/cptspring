package com.Xoot.CreditoParaTi.repositories.app;

import java.util.List;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.Xoot.CreditoParaTi.entity.app.CustomerGender;

public interface ICustomerGenderDao extends CrudRepository<CustomerGender, Integer>, JpaSpecificationExecutor<CustomerGender> {

	@Query(nativeQuery = true, value = "SELECT * FROM customer_genders WHERE status_flag = 1")
	public List<CustomerGender> findAllActive();

	@Query(nativeQuery = true, value = "SELECT * FROM customer_genders WHERE status_flag = 1 AND name=:name LIMIT 1")
	public CustomerGender findByNameActive(@Param("name") String name);
	
	@Query(nativeQuery = true, value = "SELECT * FROM customer_genders WHERE name=:name LIMIT 1")
	public CustomerGender findByName(@Param("name") String name);
}
