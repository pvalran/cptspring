package com.Xoot.CreditoParaTi.repositories.interfaces;

import com.Xoot.CreditoParaTi.entity.Property;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface IPropertyDao extends CrudRepository<Property, Integer>, JpaSpecificationExecutor<Property> {
    @Query(nativeQuery = true, value = "select * from property where status_flag = 1")
    public List<Property> findAllActive();

    @Query(nativeQuery = true, value = "select * from property where creadit_application_id = :creditId " +
            "and status_flag = 1 limit 1")
    public Property findByCreditId(@Param("creditId") Integer creditId);
}
