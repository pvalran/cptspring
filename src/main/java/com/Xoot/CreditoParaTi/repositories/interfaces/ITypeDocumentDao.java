package com.Xoot.CreditoParaTi.repositories.interfaces;

import com.Xoot.CreditoParaTi.entity.TypeDocument;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import java.util.List;

public interface ITypeDocumentDao extends CrudRepository<TypeDocument, Integer> , JpaSpecificationExecutor<TypeDocument> {
    @Query(nativeQuery = true, value = "SELECT * FROM type_document WHERE status_flag = 1")
    public List<TypeDocument> findAllActive();

    @Query(nativeQuery = true, value = "SELECT * FROM type_document WHERE description=:description")
    public List<TypeDocument> findByDescription(@Param("description") String description);
}
