package com.Xoot.CreditoParaTi.models.dao;

import com.Xoot.CreditoParaTi.entity.Reference;
import com.Xoot.CreditoParaTi.entity.Spouse;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface IReferenceDao extends CrudRepository<Reference, Integer>, JpaSpecificationExecutor<Reference> {
    @Query(nativeQuery = true, value = "select * from reference where status_flag = 1")
    public List<Reference> findAllActive();

    @Query(nativeQuery = true, value = "SELECT * FROM reference WHERE creadit_application_id=:creditId and status_flag = 1")
    public List<Reference> findByCreditId(@Param("creditId") Integer creditId);
}
