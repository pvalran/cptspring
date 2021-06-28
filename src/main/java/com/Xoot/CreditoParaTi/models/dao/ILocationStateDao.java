package com.Xoot.CreditoParaTi.models.dao;


import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;

import com.Xoot.CreditoParaTi.entity.LocationState;

public interface ILocationStateDao
		extends CrudRepository<LocationState, Integer>, JpaSpecificationExecutor<LocationState> {
}
