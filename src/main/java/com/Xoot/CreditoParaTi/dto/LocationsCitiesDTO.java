package com.Xoot.CreditoParaTi.dto;

public class LocationsCitiesDTO {
    private Integer idCity;
    private String code;
    private String name;
    private Integer countiesId;
    private String stateCode;
    private String countiesCode;

    public Integer getIdCity() {
        return idCity;
    }

    public void setIdCity(Integer idCity) {
        this.idCity = idCity;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getCountiesId() {
        return countiesId;
    }

    public void setCountiesId(Integer countiesId) {
        this.countiesId = countiesId;
    }

    public String getStateCode() {
        return stateCode;
    }

    public void setStateCode(String stateCode) {
        this.stateCode = stateCode;
    }

    public String getCountiesCode() {
        return countiesCode;
    }

    public void setCountiesCode(String countiesCode) {
        this.countiesCode = countiesCode;
    }
}
