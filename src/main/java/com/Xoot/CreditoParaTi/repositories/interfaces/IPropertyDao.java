package com.Xoot.CreditoParaTi.repositories.interfaces;

import com.Xoot.CreditoParaTi.entity.Property;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface IPropertyDao extends CrudRepository<Property, Integer>, JpaSpecificationExecutor<Property> {
    @Query(nativeQuery = true, value = "select * from property where status_flag = 1")
    public List<Property> findAllActive();
}

