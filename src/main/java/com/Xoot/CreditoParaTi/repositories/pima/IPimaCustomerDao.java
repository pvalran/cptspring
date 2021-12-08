package com.Xoot.CreditoParaTi.repositories.pima;

import com.Xoot.CreditoParaTi.entity.pima.PimaCustomer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface IPimaCustomerDao extends JpaRepository<PimaCustomer, Integer> {

	@Query(nativeQuery = true, value = "SELECT * FROM customers WHERE status_flag = 1")
	public List<PimaCustomer> findAllActive();

	@Query(nativeQuery = true, value = "SELECT * FROM customers WHERE status_flag = 1 AND curp=:curp LIMIT 1")
	public PimaCustomer findByCurpActive(@Param("curp") String curp);
	
	@Query(nativeQuery = true, value = "SELECT * FROM customers WHERE curp=:curp LIMIT 1")
	public PimaCustomer findByCurp(@Param("curp") String curp);

	@Query(nativeQuery = true, value = "SELECT * FROM customers WHERE number_request=:creditId and status_flag = 1 limit 1")
	public PimaCustomer findByCreditId(@Param("creditId") Integer creditId);
}
