package com.Xoot.CreditoParaTi.repositories.pima;

import com.Xoot.CreditoParaTi.entity.pima.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IEmployeeDao extends JpaRepository<Employee, Integer>, JpaSpecificationExecutor<Employee>{
	
	@Query(nativeQuery = true, value = "SELECT * FROM employee WHERE status_flag = 1")
	public List<Employee> findAllActive();

	@Query(nativeQuery = true, value = "SELECT * FROM employee WHERE type_user = 2")
	public List<Employee> findAllBoard();

	@Query(nativeQuery = true, value = "SELECT * FROM employee WHERE type_user = 1")
	public List<Employee> findAllBoardApp();
	
	@Query(nativeQuery = true, value = "SELECT * FROM employee WHERE status_flag = 1 AND username=:userName LIMIT 1")
	public Employee findByUsername(@Param("userName") String userName);
	
	@Query(nativeQuery = true, value = "SELECT * FROM employee WHERE status_flag = 1 AND email=:email LIMIT 1")
	public Employee findByemail(@Param("email") String email);

	@Query(nativeQuery = true, value = "SELECT * FROM employee WHERE status_flag = 1 AND username=:userName and password = :password LIMIT 1")
	public Employee Login(@Param("userName") String userName,@Param("password") String password);
}
