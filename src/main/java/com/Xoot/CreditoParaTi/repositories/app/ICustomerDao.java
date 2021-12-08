package com.Xoot.CreditoParaTi.repositories.app;

import java.util.List;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.Xoot.CreditoParaTi.entity.app.Customer;

public interface ICustomerDao extends CrudRepository<Customer, Integer>, JpaSpecificationExecutor<Customer> {

	@Query(nativeQuery = true, value = "SELECT * FROM customers WHERE status_flag = 1")
	public List<Customer> findAllActive();

	@Query(nativeQuery = true, value = "SELECT * FROM customers WHERE status_flag = 1 AND curp=:curp LIMIT 1")
	public Customer findByCurpActive(@Param("curp") String curp);
	
	@Query(nativeQuery = true, value = "SELECT * FROM customers WHERE curp=:curp LIMIT 1")
	public Customer findByCurp(@Param("curp") String curp);

	@Query(nativeQuery = true, value = "SELECT * FROM customers WHERE number_request=:creditId and status_flag = 1 limit 1")
	public Customer findByCreditId(@Param("creditId") Integer creditId);
}
