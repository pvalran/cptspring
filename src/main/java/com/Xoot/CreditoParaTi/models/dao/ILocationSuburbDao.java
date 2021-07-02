package com.Xoot.CreditoParaTi.models.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.Xoot.CreditoParaTi.entity.LocationSuburb;

public interface ILocationSuburbDao
		extends CrudRepository<LocationSuburb, Integer>, JpaSpecificationExecutor<LocationSuburb> {
	
	@Query(nativeQuery = true, value = "SELECT * FROM cpt.locations_Suburb;")
    List<LocationSuburb> findAll();
}
