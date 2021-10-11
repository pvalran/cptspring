package com.Xoot.CreditoParaTi.repositories.stores;

import com.Xoot.CreditoParaTi.entity.DocStatusMap;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityManager;
import javax.persistence.ParameterMode;
import javax.persistence.StoredProcedureQuery;
import java.util.List;

public class DocumentStatusRequestStore {
    private final EntityManager entityManager;

    @Autowired
    public DocumentStatusRequestStore(final EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public List<DocStatusMap> storeProduce(Integer request_number) {
        StoredProcedureQuery storedProcedureQuery = entityManager.createStoredProcedureQuery("getDataRequest");

        storedProcedureQuery.registerStoredProcedureParameter("request_number", Integer.class, ParameterMode.IN);
        storedProcedureQuery.setParameter("request_number", request_number);
        storedProcedureQuery.execute();
        List<DocStatusMap> results = storedProcedureQuery.getResultList();
        return results;
    }
}
