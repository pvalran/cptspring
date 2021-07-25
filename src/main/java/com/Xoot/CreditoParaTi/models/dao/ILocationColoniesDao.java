package com.Xoot.CreditoParaTi.models.dao;

import com.Xoot.CreditoParaTi.entity.LocationColonies;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ILocationColoniesDao
		extends CrudRepository<LocationColonies, Integer>, JpaSpecificationExecutor<LocationColonies> {
	
	@Query(nativeQuery = true, value = "SELECT * FROM cpt.locations_colonies;")
    List<LocationColonies> findAll();

	@Query(nativeQuery = true, value = "SELECT * FROM cpt.locations_colonies where city_id = :id;")
	List<LocationColonies> getCityToMun(Integer id);
}
