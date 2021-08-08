package com.Xoot.CreditoParaTi.repositories.interfaces;


import com.Xoot.CreditoParaTi.entity.TypeDatos;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ITypeDatosDao extends CrudRepository<TypeDatos, Integer> , JpaSpecificationExecutor<TypeDatos> {
    @Query(nativeQuery = true, value = "SELECT * FROM type_datos WHERE status_flag = 1")
    public List<TypeDatos> findAllActive();

    @Query(nativeQuery = true, value = "SELECT * FROM type_datos WHERE description=:description")
    public List<TypeDatos> findByDescription(@Param("description") String description);
}



