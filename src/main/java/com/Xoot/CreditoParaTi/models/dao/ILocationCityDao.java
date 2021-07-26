package com.Xoot.CreditoParaTi.models.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.Xoot.CreditoParaTi.entity.LocationCity;

public interface ILocationCityDao extends CrudRepository<LocationCity, Integer> {
	
	@Query(nativeQuery = true, value = "SELECT * FROM locations_cities")
    List<LocationCity> findAll();

	@Query(nativeQuery = true, value = "SELECT * FROM locations_cities where state_id = :id")
	List<LocationCity> getMuntoState(@Param("id") Integer id);

	@Query( nativeQuery = true, value =  "SELECT * FROM locations_cities where name in :names" )
	List<LocationCity> findByListName(@Param("names") List<String> lstNameState);
}
