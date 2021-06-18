package com.Xoot.CreditoParaTi.models.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.Xoot.CreditoParaTi.entity.CategoryUser;

public interface ICategoryUserDao extends CrudRepository<CategoryUser, Integer> {
	
	@Query(nativeQuery = true, value = "SELECT * FROM creditoparati.category_user WHERE status_flag = 1;")
    List<CategoryUser> findAllActive();
}
