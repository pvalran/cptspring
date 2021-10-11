package com.Xoot.CreditoParaTi.entity;

import javax.persistence.*;
import java.io.Serializable;

@NamedStoredProcedureQuery(
    name = "DataRequest",
    procedureName = "getDataRequest",
   resultClasses = { StatisticsTransaction.class },
    parameters = {
            @StoredProcedureParameter(mode = ParameterMode.IN, name = "dateInit", type = String.class),
            @StoredProcedureParameter(mode = ParameterMode.IN, name = "dateEnd", type = String.class),
            @StoredProcedureParameter(mode = ParameterMode.IN, name = "filters", type = String.class)
    })
@Entity
public class StatisticsTransaction {
    @Id
    @Column(name = "id")
    private Integer Id;
    @Column(name = "status")
    private String status;
    @Column(name = "total")
    private Integer total;

    public Integer getId() {
        return Id;
    }

    public void setId(Integer id) {
        Id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }
}
