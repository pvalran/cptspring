package com.Xoot.CreditoParaTi.models.dao;

import com.Xoot.CreditoParaTi.entity.Reference;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface IReferenceDao extends CrudRepository<Reference, Integer>, JpaSpecificationExecutor<Reference> {
    @Query(nativeQuery = true, value = "select * from Reference where status_flag = 1")
    public List<Reference> findAllActive();
}
