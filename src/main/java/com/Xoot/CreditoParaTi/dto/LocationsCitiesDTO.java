package com.Xoot.CreditoParaTi.dto;

import com.Xoot.CreditoParaTi.entity.LocationState;

public class LocationsCitiesDTO {
    private Integer idMunicipality;
    private String Name;
    private Integer state;
    private Integer municipality_code;



    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }


    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public Integer getIdMunicipality() {
        return idMunicipality;
    }

    public void setIdMunicipality(Integer idMunicipality) {
        this.idMunicipality = idMunicipality;
    }

    public Integer getMunicipality_code() {
        return municipality_code;
    }

    public void setMunicipality_code(Integer municipality_code) {
        this.municipality_code = municipality_code;
    }
}
