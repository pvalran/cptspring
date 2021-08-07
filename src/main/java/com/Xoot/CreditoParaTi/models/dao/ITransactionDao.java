package com.Xoot.CreditoParaTi.models.dao;

import com.Xoot.CreditoParaTi.entity.transaction;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ITransactionDao extends CrudRepository<transaction,Integer>, JpaSpecificationExecutor<transaction> {
    @Query(nativeQuery = true,value="select * from transaction where status_flag = 1")
    public List<transaction> findAllActive();

    @Query(nativeQuery = true,value="select * from transaction where user_id = :userId and status_flag = 1")
    public List<transaction> findByUser(@Param("userId") Integer userId);

}
