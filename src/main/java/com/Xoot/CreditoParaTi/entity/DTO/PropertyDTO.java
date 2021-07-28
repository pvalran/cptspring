package com.Xoot.CreditoParaTi.entity.DTO;

public class PropertyDTO {
    private Integer IdProperty;
    private Integer useProperty;
    private Integer typeProperty;
    private Double estimatedValue;
    private Double totalSurface;
    private Double constructionSurface;
    private String paternalLastName;
    private String maternalLastName;
    private String name;
    private String phone;
    private String address;
    private String postalCode;
    private String suburb;
    private String municipality;
    private String city;
    private String state;
    private Integer creaditApplication;


    public Integer getIdProperty() {
        return IdProperty;
    }

    public void setIdProperty(Integer idProperty) {
        IdProperty = idProperty;
    }

    public Integer getUseProperty() {
        return useProperty;
    }

    public void setUseProperty(Integer useProperty) {
        this.useProperty = useProperty;
    }

    public Integer getTypeProperty() {
        return typeProperty;
    }

    public void setTypeProperty(Integer typeProperty) {
        this.typeProperty = typeProperty;
    }

    public Double getEstimatedValue() {
        return estimatedValue;
    }

    public void setEstimatedValue(Double estimatedValue) {
        this.estimatedValue = estimatedValue;
    }

    public Double getTotalSurface() {
        return totalSurface;
    }

    public void setTotalSurface(Double totalSurface) {
        this.totalSurface = totalSurface;
    }

    public Double getConstructionSurface() {
        return constructionSurface;
    }

    public void setConstructionSurface(Double constructionSurface) {
        this.constructionSurface = constructionSurface;
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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getSuburb() {
        return suburb;
    }

    public void setSuburb(String suburb) {
        this.suburb = suburb;
    }

    public String getMunicipality() {
        return municipality;
    }

    public void setMunicipality(String municipality) {
        this.municipality = municipality;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public Integer getCreaditApplication() {
        return creaditApplication;
    }

    public void setCreaditApplication(Integer creaditApplication) {
        this.creaditApplication = creaditApplication;
    }
}
