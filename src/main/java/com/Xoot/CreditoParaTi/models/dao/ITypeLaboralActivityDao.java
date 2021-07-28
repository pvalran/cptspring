package com.Xoot.CreditoParaTi.models.dao;


import com.Xoot.CreditoParaTi.entity.TypeLaboralActivity;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ITypeLaboralActivityDao extends CrudRepository<TypeLaboralActivity, Integer> , JpaSpecificationExecutor<TypeLaboralActivity> {
    @Query(nativeQuery = true, value = "SELECT * FROM type_reference WHERE status_flag = 1")
    public List<TypeLaboralActivity> findAllActive();

    @Query(nativeQuery = true, value = "SELECT * FROM type_reference WHERE description=:description")
    public List<TypeLaboralActivity> findByDescription(@Param("description") String description);
}



