package com.Xoot.CreditoParaTi.repositories.interfaces;

import java.util.List;

import com.Xoot.CreditoParaTi.entity.LocationCity;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.Xoot.CreditoParaTi.entity.LocationSuburb;

public interface ILocationSuburbDao
		extends CrudRepository<LocationSuburb, Integer>, JpaSpecificationExecutor<LocationSuburb> {
	
	@Query(nativeQuery = true, value = "SELECT * FROM locations_suburb")
    List<LocationSuburb> findAll();

	@Query(nativeQuery = true, value = "SELECT * FROM locations_suburb where state_code = :state " +
			" and counties_code = :counties")
	List<LocationSuburb> getSuburbToMun(@Param("state") String state, @Param("counties") String counties);

	@Query(nativeQuery = true, value = "SELECT * FROM locations_suburb where state_code = :state " +
			" and counties_code = :counties and cities_code = ''")
	List<LocationSuburb> getLocationToMun(@Param("state") String state, @Param("counties") String counties);


	@Query(nativeQuery = true, value = "SELECT * FROM locations_suburb where counties_id = :countiesId ")
	List<LocationSuburb> getSuburbByMun(@Param("countiesId") Integer countiesId);

	@Query(nativeQuery = true, value = "SELECT * FROM locations_suburb where city_id = :cityId ")
	List<LocationSuburb> getSuburbByCity(@Param("cityId") Integer cityId);

	@Query(nativeQuery = true, value = "SELECT * FROM locations_suburb where state_code = :state " +
			" and counties_code = :counties and cities_code = :city")
	List<LocationSuburb> getSuburbToCity(@Param("state") String state,
										 @Param("counties") String counties,
										 @Param("city") String city);

	@Query(nativeQuery = true, value = "SELECT * FROM locations_suburb where zip_code = :zipcode")
	List<LocationSuburb> getDirectionToCp(@Param("zipcode") Integer zipcode);
}
