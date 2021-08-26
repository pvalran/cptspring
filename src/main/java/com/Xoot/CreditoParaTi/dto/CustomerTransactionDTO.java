package com.Xoot.CreditoParaTi.dto;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CustomerTransactionDTO {
    private CustomerDTO customer;
    private String status;
    private String layerDocument;
    private String layerBiometric;
    private String layerGobernment;
    private Date crtd_on;

    public CustomerDTO getCustomer() {
        return customer;
    }

    public void setCustomer(CustomerDTO customer) {
        this.customer = customer;
    }

    public String getLayerDocument() {
        return layerDocument;
    }

    public void setLayerDocument(String layerDocument) {
        this.layerDocument = layerDocument;
    }

    public String getLayerBiometric() {
        return layerBiometric;
    }

    public void setLayerBiometric(String layerBiometric) {
        this.layerBiometric = layerBiometric;
    }

    public String getLayerGobernment() {
        return layerGobernment;
    }

    public void setLayerGobernment(String layerGobernment) {
        this.layerGobernment = layerGobernment;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getCrtd_on() {
        return crtd_on;
    }

    public void setCrtd_on(Date crtd_on)  {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        try {
            this.crtd_on = format.parse(crtd_on.toString());
        } catch (Exception ex) {
            this.crtd_on = crtd_on;
        }

    }
}
