package com.Xoot.CreditoParaTi.repositories.app;

import com.Xoot.CreditoParaTi.entity.app.TypeDependent;
import com.Xoot.CreditoParaTi.entity.app.TypeReference;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ITypeDependentDao extends CrudRepository<TypeDependent, Integer> , JpaSpecificationExecutor<TypeReference> {
    @Query(nativeQuery = true, value = "SELECT * FROM type_dependent WHERE status_flag = 1")
    public List<TypeDependent> findAllActive();

    @Query(nativeQuery = true, value = "SELECT * FROM type_dependent WHERE description=:description")
    public List<TypeDependent> findByDescription(@Param("description") String description);

    @Query(nativeQuery = true, value = "SELECT td.* FROM type_dependent td inner join civil_dependent cv on cv.dependent_id = td.id" +
            " WHERE cv.civil_id=:civil")
    public List<TypeDependent> findByCivil(@Param("civil") Integer civil);
}




