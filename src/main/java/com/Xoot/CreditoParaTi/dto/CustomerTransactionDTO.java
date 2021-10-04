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
    private String crtd_by;
    private Integer creditId;
    private String mobile;
    private String email;
    private String enrolment;


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

    public Integer getCreditId() {
        return creditId;
    }

    public void setCreditId(Integer creditId) {
        this.creditId = creditId;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getCrtd_by() {
        return crtd_by;
    }

    public void setCrtd_by(String crtd_by) {
        this.crtd_by = crtd_by;
    }

    public String getEnrolment() {
        return enrolment;
    }

    public void setEnrolment(String enrolment) {
        this.enrolment = enrolment;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
