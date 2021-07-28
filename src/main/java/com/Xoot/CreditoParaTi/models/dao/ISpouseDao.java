package com.Xoot.CreditoParaTi.models.dao;

import com.Xoot.CreditoParaTi.entity.Spouse;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ISpouseDao extends CrudRepository<Spouse,Integer>, JpaSpecificationExecutor<Spouse> {
    @Query(nativeQuery = true,value="select * from spouse where status_flag = 1")
    public List<Spouse> findAllActive();
}
