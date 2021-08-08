package com.Xoot.CreditoParaTi.dto;

public class CreditApplicationStatusDTO {
    private Integer idCreditAplicationStatus;
    private String name;
    private String color;

    public Integer getIdCreditAplicationStatus() {
        return idCreditAplicationStatus;
    }

    public void setIdCreditAplicationStatus(Integer idCreditAplicationStatus) {
        this.idCreditAplicationStatus = idCreditAplicationStatus;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }
}
