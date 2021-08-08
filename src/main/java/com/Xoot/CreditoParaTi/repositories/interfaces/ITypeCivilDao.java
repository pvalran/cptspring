package com.Xoot.CreditoParaTi.repositories.interfaces;


import com.Xoot.CreditoParaTi.entity.TypeCivil;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ITypeCivilDao extends CrudRepository<TypeCivil, Integer> , JpaSpecificationExecutor<TypeCivil> {
    @Query(nativeQuery = true, value = "SELECT * FROM type_civil WHERE status_flag = 1")
    public List<TypeCivil> findAllActive();

    @Query(nativeQuery = true, value = "SELECT * FROM type_civil WHERE description=:description")
    public List<TypeCivil> findByDescription(@Param("description") String description);
}


