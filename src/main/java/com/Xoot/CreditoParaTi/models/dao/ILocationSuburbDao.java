package com.Xoot.CreditoParaTi.models.dao;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;

import com.Xoot.CreditoParaTi.entity.LocationSuburb;

public interface ILocationSuburbDao
		extends CrudRepository<LocationSuburb, Integer>, JpaSpecificationExecutor<LocationSuburb> {
}
