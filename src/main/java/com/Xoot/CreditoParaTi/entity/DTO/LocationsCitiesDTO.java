package com.Xoot.CreditoParaTi.entity.DTO;

public class LocationsCitiesDTO {
    private String Name;
    private Integer stateId;
    private Integer cityCode;

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public Integer getStateId() {
        return stateId;
    }

    public void setStateId(Integer stateId) {
        this.stateId = stateId;
    }

    public Integer getCityCode() {
        return cityCode;
    }

    public void setCityCode(Integer cityCode) {
        this.cityCode = cityCode;
    }
}
