package com.Xoot.CreditoParaTi.models.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.Xoot.CreditoParaTi.entity.UsuarioCategory;

public interface ICategoryUserDao extends CrudRepository<UsuarioCategory, Integer> {

	@Query(nativeQuery = true, value = "SELECT * FROM cpt.users_categories WHERE status_flag = 1;")
    List<UsuarioCategory> findAllActive();
}
