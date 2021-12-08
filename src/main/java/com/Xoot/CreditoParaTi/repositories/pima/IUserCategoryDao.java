package com.Xoot.CreditoParaTi.repositories.pima;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.Xoot.CreditoParaTi.entity.pima.UsuarioCategory;

public interface IUserCategoryDao extends CrudRepository<UsuarioCategory, Integer> {
	
	@Query(nativeQuery = true, value = "SELECT * FROM users_categories WHERE status_flag = 1")
    List<UsuarioCategory> findAllActive();
	
	@Query(nativeQuery = true, value = "SELECT * FROM users_categories WHERE status_flag = 1 AND name=:name LIMIT 1")
	public UsuarioCategory findByNameActive(@Param("name") String name);
	
	@Query(nativeQuery = true, value = "SELECT * FROM users_categories WHERE name=:name LIMIT 1")
	public UsuarioCategory findByName(@Param("name") String name);
}
