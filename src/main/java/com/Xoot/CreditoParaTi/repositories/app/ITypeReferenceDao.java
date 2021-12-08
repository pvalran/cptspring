package com.Xoot.CreditoParaTi.repositories.app;

import com.Xoot.CreditoParaTi.entity.app.TypeReference;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ITypeReferenceDao extends CrudRepository<TypeReference, Integer> , JpaSpecificationExecutor<TypeReference> {
    @Query(nativeQuery = true, value = "SELECT * FROM type_reference WHERE status_flag = 1")
    public List<TypeReference> findAllActive();

    @Query(nativeQuery = true, value = "SELECT * FROM type_reference WHERE description=:description")
    public List<TypeReference> findByDescription(@Param("description") String description);
}



