package com.Xoot.CreditoParaTi.repositories.app;

import com.Xoot.CreditoParaTi.entity.app.Reference;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface IReferenceDao extends CrudRepository<Reference, Integer>, JpaSpecificationExecutor<Reference> {
    @Query(nativeQuery = true, value = "select * from reference where status_flag = 1")
    public List<Reference> findAllActive();

    @Query(nativeQuery = true, value = "SELECT * FROM reference WHERE number_request=:creditId and status_flag = 1")
    public List<Reference> findByCreditId(@Param("creditId") Integer creditId);

}
