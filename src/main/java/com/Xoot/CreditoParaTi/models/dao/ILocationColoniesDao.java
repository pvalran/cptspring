package com.Xoot.CreditoParaTi.models.dao;

import com.Xoot.CreditoParaTi.entity.LocationColonies;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ILocationColoniesDao
		extends CrudRepository<LocationColonies, Integer>, JpaSpecificationExecutor<LocationColonies> {
	
	@Query(nativeQuery = true, value = "SELECT * FROM locations_colonies")
    List<LocationColonies> findAll();

	@Query(nativeQuery = true, value = "SELECT * FROM locations_colonies where (city_id = :id or city_code = :id)")
	List<LocationColonies> getCityToMun(@Param("id") Integer id);
}
