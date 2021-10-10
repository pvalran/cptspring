package com.Xoot.CreditoParaTi.repositories.service;

import com.Xoot.CreditoParaTi.mapper.DocStatusMap;
import com.Xoot.CreditoParaTi.mapper.DocStatusMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.persistence.*;
import javax.sql.DataSource;

import java.util.logging.Logger;
import java.util.List;

@Repository
public class QueryData {
    private final static Logger LOGGER = Logger.getLogger(QueryData.class.getName());

    @PersistenceContext
    private EntityManager em;

    public List<DocStatusMap> findDocStatusMap(Integer creditID) {
        String sql = "select dt.id as typeDocument, case when COALESCE(d.number_request,1) = 1 then 0 else 1 end as status from documents_type dt " +
                "left join documents d on dt.id = d.type_document_id and d.number_request = " + creditID
                + " and d.status_flag = 1 order by dt.id";

        @SqlResultSetMapping(
           name="rstDocStatusMap",
           classes = @ConstructorResult(
                targetClass = DocStatusMap.class,
                columns = {
                    @ColumnResult(name="typeDocument",type = Long.class),
                    @ColumnResult(name="status",type = Long.class)
                }
           )
        )

        Query query = em.createNativeQuery(sql, "rstDocStatusMap");
        List<DocStatusMap> docStatusMap =  query.getResultList();
        return docStatusMap;
    }
}
