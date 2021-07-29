package com.Xoot.CreditoParaTi.models.dao;

import com.Xoot.CreditoParaTi.entity.EconomicDependents;
import com.Xoot.CreditoParaTi.entity.Reference;
import com.Xoot.CreditoParaTi.entity.TypeCivil;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface IEconomicDependientiesDAO extends CrudRepository<EconomicDependents,Integer>, JpaSpecificationExecutor<EconomicDependents> {
    @Query(nativeQuery = true, value = "SELECT * FROM economic_dependents WHERE status_flag = 1")
    public List<EconomicDependents> findAllActive();

    @Query(nativeQuery = true, value = "SELECT * FROM economic_dependents WHERE credit_application_id=:creditId")
    public List<EconomicDependents> findByCreditId(@Param("creditId") Integer creditId);
}
