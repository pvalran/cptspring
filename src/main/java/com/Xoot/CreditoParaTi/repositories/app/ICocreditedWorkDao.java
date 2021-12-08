package com.Xoot.CreditoParaTi.repositories.app;

import com.Xoot.CreditoParaTi.entity.app.CocreditedWork;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ICocreditedWorkDao extends CrudRepository<CocreditedWork, Integer>,
        JpaSpecificationExecutor<CocreditedWork> {
    @Query(nativeQuery = true, value = "select * from cocredited_work where status_flag = 1")
    public List<CocreditedWork> findAllActive();

    @Query(nativeQuery = true, value = "SELECT * FROM cocredited_work WHERE number_request=:creditId limit 1 ")
    public CocreditedWork findByCreditId(@Param("creditId") Integer creditId);
}
