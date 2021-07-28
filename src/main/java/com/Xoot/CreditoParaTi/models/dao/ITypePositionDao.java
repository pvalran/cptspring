package com.Xoot.CreditoParaTi.models.dao;


import com.Xoot.CreditoParaTi.entity.TypePosition;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ITypePositionDao extends CrudRepository<TypePosition, Integer> , JpaSpecificationExecutor<TypePosition> {
    @Query(nativeQuery = true, value = "SELECT * FROM type_position WHERE status_flag = 1")
    public List<TypePosition> findAllActive();

    @Query(nativeQuery = true, value = "SELECT * FROM type_position WHERE description=:description")
    public List<TypePosition> findByDescription(@Param("description") String description);
}
