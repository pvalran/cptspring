package com.Xoot.CreditoParaTi.models.dao;


import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.Xoot.CreditoParaTi.entity.LocationState;

public interface ILocationStateDao
		extends CrudRepository<LocationState, Integer>, JpaSpecificationExecutor<LocationState> {
	
	@Query(nativeQuery = true, value = "SELECT * FROM cpt.locations_states;")
    public List<LocationState> findAll();
}
