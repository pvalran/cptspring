package com.Xoot.CreditoParaTi.repositories.app;

import com.Xoot.CreditoParaTi.entity.app.CocreditedCustomers;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ICocreditedCustomersDao extends CrudRepository<CocreditedCustomers, Integer>,
        JpaSpecificationExecutor<CocreditedCustomers> {
    @Query(nativeQuery = true, value = "select * from cocredited_customers where status_flag = 1")
    public List<CocreditedCustomers> findAllActive();

    @Query(nativeQuery = true, value = "SELECT * FROM cocredited_customers WHERE number_request=:creditId limit 1")
    public CocreditedCustomers findByCreditId(@Param("creditId") Integer creditId);
}

