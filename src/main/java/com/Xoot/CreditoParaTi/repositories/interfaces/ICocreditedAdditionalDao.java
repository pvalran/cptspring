package com.Xoot.CreditoParaTi.repositories.interfaces;

import com.Xoot.CreditoParaTi.entity.CocreditedAdditional;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ICocreditedAdditionalDao extends CrudRepository<CocreditedAdditional, Integer>,
        JpaSpecificationExecutor<CocreditedAdditional> {
    @Query(nativeQuery = true, value = "select * from cocredited_additional where status_flag = 1")
    public List<CocreditedAdditional> findAllActive();

    @Query(nativeQuery = true, value = "SELECT * FROM cocredited_additional WHERE credit_application_id=:creditId limit 1")
    public CocreditedAdditional findByCreditId(@Param("creditId") Integer creditId);
}
