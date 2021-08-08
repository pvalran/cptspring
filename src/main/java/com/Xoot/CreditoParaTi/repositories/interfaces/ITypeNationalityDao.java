package com.Xoot.CreditoParaTi.repositories.interfaces;


import com.Xoot.CreditoParaTi.entity.TypeNationality;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ITypeNationalityDao extends CrudRepository<TypeNationality, Integer> , JpaSpecificationExecutor<TypeNationality> {
    @Query(nativeQuery = true, value = "SELECT * FROM type_nationality WHERE status_flag = 1")
    public List<TypeNationality> findAllActive();

    @Query(nativeQuery = true, value = "SELECT * FROM type_nationality WHERE description=:description")
    public List<TypeNationality> findByDescription(@Param("description") String description);
}

