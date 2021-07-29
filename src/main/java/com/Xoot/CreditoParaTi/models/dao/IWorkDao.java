package com.Xoot.CreditoParaTi.models.dao;

import com.Xoot.CreditoParaTi.entity.DocumentClass;
import com.Xoot.CreditoParaTi.entity.Work;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface IWorkDao extends CrudRepository<Work, Integer>, JpaSpecificationExecutor<Work> {
    @Query(nativeQuery = true, value = "select * from work where status_flag = 1")
    public List<Work> findAllActive();

    @Query(nativeQuery = true, value = "SELECT * FROM work WHERE creadit_application_id=:creditId and status_flag = 1 LIMIT 1")
    public Work findByCreditId(@Param("creditId") Integer creditId);
}
