package com.Xoot.CreditoParaTi.repositories.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.*;
import java.util.logging.Logger;
import java.util.List;




@Repository
public class QueryData {
    private final static Logger LOGGER = Logger.getLogger(QueryData.class.getName());
    private final EntityManager entityManager;

    @SqlResultSetMapping(
            name = "lstDocStatus",
            entities = {
                    @EntityResult(
                            entityClass = DocStatusMap.class,
                            fields = {
                                    @FieldResult(name = "typeDocument", column = "typeDocument"),
                                    @FieldResult(name = "status", column = "status"),

                            }
                    )
            }
    )

    class DocStatusMap {
        private Integer typeDocument;
        private Integer status;
    }

    @Autowired
    public QueryData(final EntityManager entityManager) {
        this.entityManager = entityManager;
    }



    public List<DocStatusMap> findDocStatusMap(Integer creditID) {
        String sql = "select dt.id as typeDocument, case when COALESCE(d.number_request,1) = 1 then 0 else 1 end as status from documents_type dt " +
                "left join documents d on dt.id = d.type_document_id and d.number_request = " + creditID
                + " and d.status_flag = 1 order by dt.id";
        Query query = entityManager.createNativeQuery(sql,"lstDocStatus");
        List<DocStatusMap> docStatusMap =  query.getResultList();
        return docStatusMap;
    }
}
