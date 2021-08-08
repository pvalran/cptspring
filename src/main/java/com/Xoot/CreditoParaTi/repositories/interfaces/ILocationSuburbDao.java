package com.Xoot.CreditoParaTi.repositories.interfaces;

import java.util.List;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.Xoot.CreditoParaTi.entity.LocationSuburb;

public interface ILocationSuburbDao
		extends CrudRepository<LocationSuburb, Integer>, JpaSpecificationExecutor<LocationSuburb> {
	
	@Query(nativeQuery = true, value = "SELECT * FROM locations_suburb")
    List<LocationSuburb> findAll();

	@Query(nativeQuery = true, value = "SELECT * FROM locations_suburb where (city_id = :id or city_code = :id)")
	List<LocationSuburb> getColonyToMun(@Param("id") Integer id);

	@Query(nativeQuery = true, value = "SELECT * FROM locations_suburb where zip_code = :zipcode")
	List<LocationSuburb> getDirectionToCp(@Param("zipcode") Integer zipcode);

	@Query( nativeQuery = true, value =  "SELECT * FROM locations_suburb where name in :names" )
	List<LocationSuburb> findByListName(@Param("names") List<String> lstNameSuburb);
}
