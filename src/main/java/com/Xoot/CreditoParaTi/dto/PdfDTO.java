package com.Xoot.CreditoParaTi.dto;

import schemasMicrosoftComOfficeOffice.STInsetMode;

public class PdfDTO {
    private boolean expediente = false;

    private boolean subcuenta = false;

    public boolean getExpediente() {
        return expediente;
    }

    public void setExpediente(boolean expediente) {
        this.expediente = expediente;
    }

    public boolean getSubcuenta() {
        return subcuenta;
    }

    public void setSubcuenta(boolean subcuenta) {
        this.subcuenta = subcuenta;
    }
}
