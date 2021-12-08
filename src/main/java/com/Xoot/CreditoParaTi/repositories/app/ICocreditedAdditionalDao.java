package com.Xoot.CreditoParaTi.repositories.app;

import com.Xoot.CreditoParaTi.entity.app.CocreditedAdditional;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ICocreditedAdditionalDao extends CrudRepository<CocreditedAdditional, Integer>,
        JpaSpecificationExecutor<CocreditedAdditional> {
    @Query(nativeQuery = true, value = "select * from cocredited_additional where status_flag = 1")
    public List<CocreditedAdditional> findAllActive();

    @Query(nativeQuery = true, value = "SELECT * FROM cocredited_additional WHERE number_request=:creditId limit 1")
    public CocreditedAdditional findByCreditId(@Param("creditId") Integer creditId);
}
