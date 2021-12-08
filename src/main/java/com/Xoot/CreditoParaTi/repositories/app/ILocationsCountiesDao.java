package com.Xoot.CreditoParaTi.repositories.app;

import com.Xoot.CreditoParaTi.entity.app.LocationCounty;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ILocationsCountiesDao
		extends CrudRepository<LocationCounty, Integer>, JpaSpecificationExecutor<LocationCounty> {
	
	@Query(nativeQuery = true, value = "SELECT * FROM locations_counties")
    List<LocationCounty> findAll();

	@Query(nativeQuery = true, value = "SELECT * FROM locations_counties where state_id = :id")
	List<LocationCounty> getMunToState(@Param("id") Integer id);

	@Query(nativeQuery = true, value = "SELECT * FROM locations_counties where code = :code")
	List<LocationCounty> getMunByCode(@Param("code") String code);

	@Query(nativeQuery = true, value = "SELECT * FROM locations_counties where name = :name")
	List<LocationCounty> getMunByName(@Param("name") String name);


}
