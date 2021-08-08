package com.Xoot.CreditoParaTi.repositories.interfaces;

import com.Xoot.CreditoParaTi.entity.TypeMaritalStatus;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ITypeMaritalStatusDao extends CrudRepository<TypeMaritalStatus, Integer> , JpaSpecificationExecutor<TypeMaritalStatus> {
    @Query(nativeQuery = true, value = "SELECT * FROM type_marital_status WHERE status_flag = 1")
    public List<TypeMaritalStatus> findAllActive();

    @Query(nativeQuery = true, value = "SELECT * FROM type_marital_status WHERE description=:description")
    public List<TypeMaritalStatus> findByDescription(@Param("description") String description);
}
