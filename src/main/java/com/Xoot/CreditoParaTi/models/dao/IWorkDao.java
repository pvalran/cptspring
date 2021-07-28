package com.Xoot.CreditoParaTi.models.dao;

import com.Xoot.CreditoParaTi.entity.Work;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface IWorkDao extends CrudRepository<Work, Integer>, JpaSpecificationExecutor<Work> {
    @Query(nativeQuery = true, value = "select * from work where status_flag = 1")
    public List<Work> findAllActive();
}
