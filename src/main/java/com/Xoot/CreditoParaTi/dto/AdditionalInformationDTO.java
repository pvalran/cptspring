package com.Xoot.CreditoParaTi.dto;

import javax.persistence.Column;
import javax.persistence.PrePersist;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;

public class AdditionalInformationDTO {
    private Integer idAdditionalInformaction;
    private Integer countryOfBirth;
    private Integer countryOfResidence;
    private Integer nationality;
    private Integer civilState;
    private Integer maritalStatus;
    private String numberImss;
    private Integer scholarship;
    private String phone;
    private String mobile;
    private String otherPhone;
    private Integer nowResidenceSeniority;
    private Integer previousResidenceSeniority;
    private String address;
    private String external_number;
    private String interior_number;
    private String suburb;
    private String municipality;
    private String city;
    private String state;
    private String postal_code;
    private String country;
    private Integer creaditApplication;
    private String specifyNationality;

    public Integer getIdAdditionalInformaction() {
        return idAdditionalInformaction;
    }

    public void setIdAdditionalInformaction(Integer idAdditionalInformaction) {
        this.idAdditionalInformaction = idAdditionalInformaction;
    }

    public Integer getCountryOfBirth() {
        return countryOfBirth;
    }

    public void setCountryOfBirth(Integer countryOfBirth) {
        this.countryOfBirth = countryOfBirth;
    }

    public Integer getCountryOfResidence() {
        return countryOfResidence;
    }

    public void setCountryOfResidence(Integer countryOfResidence) {
        this.countryOfResidence = countryOfResidence;
    }

    public Integer getNationality() {
        return nationality;
    }

    public void setNationality(Integer nationality) {
        this.nationality = nationality;
    }

    public Integer getCivilState() {
        return civilState;
    }

    public void setCivilState(Integer civilState) {
        this.civilState = civilState;
    }

    public Integer getMaritalStatus() {
        return maritalStatus;
    }

    public void setMaritalStatus(Integer maritalStatus) {
        this.maritalStatus = maritalStatus;
    }

    public String getNumberImss() {
        return numberImss;
    }

    public void setNumberImss(String numberImss) {
        this.numberImss = numberImss;
    }

    public Integer getScholarship() {
        return scholarship;
    }

    public void setScholarship(Integer scholarship) {
        this.scholarship = scholarship;
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

    public String getOtherPhone() {
        return otherPhone;
    }

    public void setOtherPhone(String otherPhone) {
        this.otherPhone = otherPhone;
    }

    public Integer getNowResidenceSeniority() {
        return nowResidenceSeniority;
    }

    public void setNowResidenceSeniority(Integer nowResidenceSeniority) {
        this.nowResidenceSeniority = nowResidenceSeniority;
    }

    public Integer getPreviousResidenceSeniority() {
        return previousResidenceSeniority;
    }

    public void setPreviousResidenceSeniority(Integer previousResidenceSeniority) {
        this.previousResidenceSeniority = previousResidenceSeniority;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getExternal_number() {
        return external_number;
    }

    public void setExternal_number(String external_number) {
        this.external_number = external_number;
    }

    public String getInterior_number() {
        return interior_number;
    }

    public void setInterior_number(String interior_number) {
        this.interior_number = interior_number;
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

    public String getPostal_code() {
        return postal_code;
    }

    public void setPostal_code(String postal_code) {
        this.postal_code = postal_code;
    }

    public String getCountry() {
        return this.country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public Integer getCreaditApplication() {
        return creaditApplication;
    }

    public void setCreaditApplication(Integer creaditApplication) {
        this.creaditApplication = creaditApplication;
    }

    public String getSpecifyNationality() {
        return specifyNationality;
    }
}
