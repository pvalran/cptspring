package com.Xoot.CreditoParaTi.models.dao;

import com.Xoot.CreditoParaTi.entity.EconomicDependents;
import com.Xoot.CreditoParaTi.entity.TypeCivil;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface IEconomicDependientiesDAO extends CrudRepository<EconomicDependents,Integer>, JpaSpecificationExecutor<EconomicDependents> {
    @Query(nativeQuery = true, value = "SELECT * FROM economic dependents WHERE status_flag = 1")
    public List<EconomicDependents> findAllActive();
}
