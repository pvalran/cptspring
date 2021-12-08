package com.Xoot.CreditoParaTi.repositories.app;


import com.Xoot.CreditoParaTi.entity.app.TypeContract;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ITypeContractDao extends CrudRepository<TypeContract, Integer> , JpaSpecificationExecutor<TypeContract> {
    @Query(nativeQuery = true, value = "SELECT * FROM type_contract WHERE status_flag = 1")
    public List<TypeContract> findAllActive();

    @Query(nativeQuery = true, value = "SELECT * FROM type_contract WHERE description=:description")
    public List<TypeContract> findByDescription(@Param("description") String description);
}
