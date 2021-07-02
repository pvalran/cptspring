package com.Xoot.CreditoParaTi.models.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.Xoot.CreditoParaTi.entity.LocationCity;

public interface ILocationCityDao extends CrudRepository<LocationCity, Integer> {
	
	@Query(nativeQuery = true, value = "SELECT * FROM cpt.locations_cities;")
    List<LocationCity> findAll();
}
