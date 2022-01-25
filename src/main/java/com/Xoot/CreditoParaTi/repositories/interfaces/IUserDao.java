package com.Xoot.CreditoParaTi.repositories.interfaces;

import java.util.List;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.Xoot.CreditoParaTi.entity.Usuario;

public interface IUserDao extends CrudRepository<Usuario, Integer>, JpaSpecificationExecutor<Usuario>{
	
	@Query(nativeQuery = true, value = "SELECT * FROM users WHERE status_flag = 1")
	public List<Usuario> findAllActive();

	@Query(nativeQuery = true, value = "SELECT * FROM users WHERE type_user = 2")
	public List<Usuario> findAllBoard();

	@Query(nativeQuery = true, value = "SELECT * FROM users WHERE type_user = 1")
	public List<Usuario> findAllBoardApp();
	
	@Query(nativeQuery = true, value = "SELECT * FROM users WHERE status_flag = 1 AND username=:userName LIMIT 1")
	public Usuario findByUsername(@Param("userName") String userName);
	
	@Query(nativeQuery = true, value = "SELECT * FROM users WHERE status_flag = 1 AND email=:email LIMIT 1")
	public Usuario findByemail(@Param("email") String email);

	@Query(nativeQuery = true, value = "SELECT * FROM users WHERE status_flag = 1 AND username=:userName and password = :password LIMIT 1")
	public Usuario Login(@Param("userName") String userName,@Param("password") String password);

	@Query(nativeQuery = true, value = "SELECT * FROM users WHERE crtd_by=:promotor")
	public List<Usuario> findLeafletUser(@Param("promotor") String promotor);

	@Query(nativeQuery = true, value = "SELECT * FROM users WHERE (" +
			"concat(name,' ',paternal_last_name,' ',mother_last_name) LIKE %:search%  or email like %:search%) and crtd_by=:promotor")
	public List<Usuario> findLeafletSearch(@Param("promotor") String promotor, @Param("search") String search);
}
