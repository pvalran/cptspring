package com.Xoot.CreditoParaTi.repositories.app;

import com.Xoot.CreditoParaTi.entity.app.EconomicDependents;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface IEconomicDependientiesDAO extends CrudRepository<EconomicDependents,Integer>, JpaSpecificationExecutor<EconomicDependents> {
    @Query(nativeQuery = true, value = "SELECT * FROM economic_dependents WHERE status_flag = 1")
    public List<EconomicDependents> findAllActive();

    @Query(nativeQuery = true, value = "SELECT * FROM economic_dependents WHERE number_request=:creditId")
    public List<EconomicDependents> findByCreditId(@Param("creditId") Integer creditId);
}
