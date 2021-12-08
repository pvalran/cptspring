package com.Xoot.CreditoParaTi.repositories.app;

import com.Xoot.CreditoParaTi.entity.pima.StatisticsTransaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.ParameterMode;
import javax.persistence.StoredProcedureQuery;

import java.util.List;
@Repository
public class StatisticsTransactionStore {
    private final EntityManager entityManager;

    @Autowired
    public StatisticsTransactionStore(final EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public List<StatisticsTransaction> storeProduce(String dateInit, String dataEnd) {
        StoredProcedureQuery storedProcedureQuery = entityManager.createStoredProcedureQuery("getDataRequest");

        // Registrar los parámetros de entrada y salida
        storedProcedureQuery.registerStoredProcedureParameter("dateInit", String.class, ParameterMode.IN);
        storedProcedureQuery.registerStoredProcedureParameter("dateEnd", String.class, ParameterMode.IN);


        // Configuramos el valor de entrada
        storedProcedureQuery.setParameter("dateInit", dateInit);
        storedProcedureQuery.setParameter("dataEnd", dataEnd);

        // Realizamos la llamada al procedimiento
        storedProcedureQuery.execute();

        // Obtenemos el resultado del cursos en una lista
        List<StatisticsTransaction> results = storedProcedureQuery.getResultList();
        return results;
        // Recorremos la lista con map y devolvemos un List<BusinessObject>
        /*storedProcedureQuery.stream().map(result -> new BusinessObject(
                (String) result[0],
                (Long) result[1]
        )).collect(Collectors.toList());*/
    }
}
