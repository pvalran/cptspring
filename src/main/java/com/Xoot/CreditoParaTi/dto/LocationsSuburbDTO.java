package com.Xoot.CreditoParaTi.dto;

public class LocationsSuburbDTO {
    private Integer idColony;
    private Integer zipCode;
    private String name;
    private Integer municipality_id;
    private Integer colony_code;
    private Integer municipality_code;



    public Integer getZipCode() {
        return zipCode;
    }

    public void setZipCode(Integer zipCode) {
        this.zipCode = zipCode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getIdColony() {
        return idColony;
    }

    public void setIdColony(Integer idColony) {
        this.idColony = idColony;
    }

    public Integer getMunicipality_id() {
        return municipality_id;
    }

    public void setMunicipality_id(Integer municipality_id) {
        this.municipality_id = municipality_id;
    }

    public Integer getColony_code() {
        return colony_code;
    }

    public void setColony_code(Integer colony_code) {
        this.colony_code = colony_code;
    }

    public Integer getMunicipality_code() {
        return municipality_code;
    }

    public void setMunicipality_code(Integer municipality_code) {
        this.municipality_code = municipality_code;
    }
}
