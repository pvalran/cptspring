package com.Xoot.CreditoParaTi.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.persistence.*;

@NamedStoredProcedureQuery(
        name = "DocumentStatus",
        procedureName = "getDocumentStatusRequest",
        resultClasses = { DocStatusMap.class },
        parameters = {
                @StoredProcedureParameter(mode = ParameterMode.IN, name = "request_number", type = Integer.class),
        })

@Entity
public class DocStatusMap  {
    @Column(name = "type_document")
    private Integer typeDocument;
    @Column(name = "status")
    private Integer status;
    @Id
    @JsonIgnore
    private Integer id;

    public Integer getTypeDocument() {
        return typeDocument;
    }

    public void setTypeDocument(Integer typeDocument) {
        this.typeDocument = typeDocument;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
