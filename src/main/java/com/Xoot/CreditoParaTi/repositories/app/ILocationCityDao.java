package com.Xoot.CreditoParaTi.repositories.app;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.Xoot.CreditoParaTi.entity.app.LocationCity;

public interface ILocationCityDao extends CrudRepository<LocationCity, Integer> {
	
	@Query(nativeQuery = true, value = "SELECT * FROM locations_cities")
    List<LocationCity> findAll();

	@Query(nativeQuery = true, value = "SELECT * FROM locations_cities where state_code = :state " +
			" and counties_code = :counties")
	List<LocationCity> getCityToMun(@Param("state") String state,@Param("counties") String counties);

	@Query(nativeQuery = true, value = "SELECT * FROM locations_cities where counties_id = :countiesId ")
	List<LocationCity> getCityByMun(@Param("countiesId") Integer countiesId);

}
