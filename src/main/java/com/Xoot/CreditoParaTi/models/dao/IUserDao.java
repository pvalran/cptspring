package com.Xoot.CreditoParaTi.models.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.Xoot.CreditoParaTi.entity.Usuario;

public interface IUserDao extends CrudRepository<Usuario, Integer>, JpaSpecificationExecutor<Usuario>{
	
	@Query(nativeQuery = true, value = "SELECT * FROM cpt.users WHERE status_flag = 1 AND username=:userName AND password=:pass LIMIT 1;")
	public Usuario findByLogin(@Param("userName") String userName, @Param("pass") String pass);
	
	@Query(nativeQuery = true, value = "SELECT * FROM cpt.users WHERE status_flag = 1;")
	public List<Usuario> findAllActive();
	
	public Usuario findByUsername(String username);
	
	public Usuario findByemail(String email);
}
