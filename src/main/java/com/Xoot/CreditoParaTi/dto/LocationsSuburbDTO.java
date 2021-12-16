package com.Xoot.CreditoParaTi.dto;

import com.Xoot.CreditoParaTi.entity.LocationCity;
import com.Xoot.CreditoParaTi.entity.LocationCounty;
import com.Xoot.CreditoParaTi.entity.LocationState;
import com.Xoot.CreditoParaTi.repositories.interfaces.ILocationCityDao;
import com.Xoot.CreditoParaTi.repositories.interfaces.ILocationStateDao;
import com.Xoot.CreditoParaTi.repositories.interfaces.ILocationsCountiesDao;

import org.springframework.beans.factory.annotation.Autowired;


public class LocationsSuburbDTO {

    private Integer idSuburb;
    private String code;
    private String name;
    private String zipCode;
    private String typeSuburb;
    private Integer countiesId;
    private Integer cityId;
    private String stateCode;
    private String countiesCode;
    private String citiesCode;
    private String state;
    private String county;
    private String city;

    public LocationsSuburbDTO(
         Integer idSuburb,
         String code,
         String name,
         String zipCode,
         String typeSuburb,
         Integer countiesId,
         Integer cityId,
         String stateCode,
         String countiesCode,
         String citiesCode,
         String state,
         String county,
         String city
    ){
        this.idSuburb = idSuburb;
        this.code = code;
        this.name = name;
        this.zipCode = zipCode;
        this.typeSuburb = typeSuburb;
        this.countiesId = countiesId;
        this.cityId = cityId;
        this.stateCode = stateCode;
        this.countiesCode = countiesCode;
        this.citiesCode = citiesCode;
        this.state = state;
        this.county = county;
        this.city = city;
    }


    public Integer getIdSuburb() {
        return idSuburb;
    }

    public void setIdSuburb(Integer idSuburb) {
        this.idSuburb = idSuburb;
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

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getTypeSuburb() {
        return typeSuburb;
    }

    public void setTypeSuburb(String typeSuburb) {
        this.typeSuburb = typeSuburb;
    }

    public Integer getCountiesId() {
        return countiesId;
    }

    public void setCountiesId(Integer countiesId) {
        this.countiesId = countiesId;
    }

    public Integer getCityId() {
        return cityId;
    }

    public void setCityId(Integer cityId) {
        this.cityId = cityId;
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

    public String getCitiesCode() {
        return citiesCode;
    }

    public void setCitiesCode(String citiesCode) {
        this.citiesCode = citiesCode;
    }


    public String getState() {

        return this.state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCounty() {
        return this.county;
    }

    public void setCounty(String county) {
        this.county = county;
    }

    public String getCity() {
        return this.city;
    }

    public void setCity(String city) {
        this.city = city;
    }
}
