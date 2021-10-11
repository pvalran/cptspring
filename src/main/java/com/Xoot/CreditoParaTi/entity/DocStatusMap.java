package com.Xoot.CreditoParaTi.entity;

import javax.persistence.*;
import java.io.Serializable;

@NamedStoredProcedureQuery(
    name = "DocumentStatus",
    procedureName = "getDocumentStatusRequest",
    resultClasses = { DocStatusMap.class },
    parameters = {
        @StoredProcedureParameter(mode = ParameterMode.IN, name = "request_number", type = Integer.class),
   })

public class DocStatusMap  {
    private Integer typeDocument;
    private Integer status;

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
}
