package com.Xoot.CreditoParaTi.dto;

public class LocationsCountiesDTO {
    private Integer idCity;
    private String name;
    private Integer municipality_id;
    private Integer city_code;


    public Integer getIdCity() {
        return idCity;
    }

    public void setIdCity(Integer idCity) {
        this.idCity = idCity;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getMunicipality_id() {
        return municipality_id;
    }

    public void setMunicipality_id(Integer municipality_id) {
        this.municipality_id = municipality_id;
    }

    public Integer getCity_code() {
        return city_code;
    }

    public void setCity_code(Integer city_code) {
        this.city_code = city_code;
    }
}
