package com.Xoot.CreditoParaTi.dto;

public class ReferenceDTO {
    private Integer IdReference;
    private Integer typeReference;
    private String paternalLastName;
    private String maternalLastName;
    private String name;
    private String email;
    private String phone;
    private String mobile;
    private Integer creditApplication;



    public Integer getIdReference() {
        return IdReference;
    }

    public void setIdReference(Integer idReference) {
        IdReference = idReference;
    }

    public Integer getTypeReference() {
        return typeReference;
    }

    public void setTypeReference(Integer typeReference) {
        this.typeReference = typeReference;
    }

    public String getPaternalLastName() {
        return paternalLastName;
    }

    public void setPaternalLastName(String paternalLastName) {
        this.paternalLastName = paternalLastName;
    }

    public String getMaternalLastName() {
        return maternalLastName;
    }

    public void setMaternalLastName(String maternalLastName) {
        this.maternalLastName = maternalLastName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public Integer getCreditApplication() {
        return creditApplication;
    }

    public void setCreditApplication(Integer creditApplication) {
        this.creditApplication = creditApplication;
    }
}
