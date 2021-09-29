package com.Xoot.CreditoParaTi.entity;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="cocredited_additional")
public class CocreditedAdditional {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer idCocreditedAdditional;

    @Column(name = "country_of_birth")
    private Integer countryBirth;
    
    @Column(name = "country_of_residence")
    private Integer countryResidence;
    
    @Column(name = "nationality")
    private Integer nationality;
    
    @Column(name = "specify_nationality")
    private String specifyNationality;
    
    @Column(name = "civil_state")
    private Integer civilState;
    
    @Column(name = "marital_status")
    private Integer maritalStatus;
    
    @Column(name = "number_imss")
    private String numberImss;
    
    @Column(name = "scholarship")
    private Integer scholarship;
    
    @Column(name = "phone")
    private String phone;
    
    @Column(name = "mobile")
    private String mobile;
    
    @Column(name = "other_phone")
    private String otherPhone;
    
    @Column(name = "now_residence_seniority")
    private Integer nowResidenceSeniority;
    
    @Column(name = "previous_residence_seniority")
    private Integer previousResidenceSeniority;
    
    @Column(name = "address")
    private String address;
    
    @Column(name = "external_number")
    private String externalNumber;
    
    @Column(name = "interior_number")
    private String interiorNumber;
    
    @Column(name = "postal_code")
    private String postalCode;
    
    @Column(name = "suburb")
    private String suburb;
    
    @Column(name = "city")
    private String city;
    
    @Column(name = "county")
    private String county;
    
    @Column(name = "state")
    private String state;
    
    @Column(name = "country")
    private String country;

    @Column(name =  "number_request")
    private Integer creditApplication;

    @Column(name = "status_flag")
    private Integer status_flag;

    @Column(name = "crtd_on")
    @Temporal(TemporalType.TIMESTAMP)
    private java.util.Date crtd_on;

    @Column(name = "crtd_by", length = 50)
    private String crtd_by;

    @Column(name = "mdfd_on")
    @Temporal(TemporalType.TIMESTAMP)
    private java.util.Date mdfd_on;

    @Column(name = "mdfd_by", length = 50)
    private String mdfd_by;

    @Column(name = "name_spouse")
    private String nameSpouse;

    @Column(name = "paternal_spouse")
    private String paternalSpouse;

    @Column(name = "maternal_spouse")
    private String maternalSpouse;

    @PrePersist
    public void prePersist() {
        crtd_on = new java.util.Date();
        mdfd_on = new java.util.Date();
        status_flag = 1;
    }


    public Integer getIdCocreditedAdditional() {
        return idCocreditedAdditional;
    }

    public void setIdCocreditedAdditional(Integer idCocreditedAdditional) {
        this.idCocreditedAdditional = idCocreditedAdditional;
    }

    public Integer getCountryBirth() {
        return countryBirth;
    }

    public void setCountryBirth(Integer countryBirth) {
        this.countryBirth = countryBirth;
    }

    public Integer getCountryResidence() {
        return countryResidence;
    }

    public void setCountryResidence(Integer countryResidence) {
        this.countryResidence = countryResidence;
    }

    public Integer getNationality() {
        return nationality;
    }

    public void setNationality(Integer nationality) {
        this.nationality = nationality;
    }

    public String getSpecifyNationality() {
        return specifyNationality;
    }

    public void setSpecifyNationality(String specifyNationality) {
        this.specifyNationality = specifyNationality;
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

    public String getExternalNumber() {
        return externalNumber;
    }

    public void setExternalNumber(String externalNumber) {
        this.externalNumber = externalNumber;
    }

    public String getInteriorNumber() {
        return interiorNumber;
    }

    public void setInteriorNumber(String interiorNumber) {
        this.interiorNumber = interiorNumber;
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

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCounty() {
        return county;
    }

    public void setCounty(String county) {
        this.county = county;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public Integer getCreditApplication() {
        return creditApplication;
    }

    public void setCreditApplication(Integer creditApplication) {
        this.creditApplication = creditApplication;
    }

    public Integer getStatus_flag() {
        return status_flag;
    }

    public void setStatus_flag(Integer status_flag) {
        this.status_flag = status_flag;
    }

    public Date getCrtd_on() {
        return crtd_on;
    }

    public void setCrtd_on(Date crtd_on) {
        this.crtd_on = crtd_on;
    }

    public String getCrtd_by() {
        return crtd_by;
    }

    public void setCrtd_by(String crtd_by) {
        this.crtd_by = crtd_by;
    }

    public Date getMdfd_on() {
        return mdfd_on;
    }

    public void setMdfd_on(Date mdfd_on) {
        this.mdfd_on = mdfd_on;
    }

    public String getMdfd_by() {
        return mdfd_by;
    }

    public void setMdfd_by(String mdfd_by) {
        this.mdfd_by = mdfd_by;
    }

    public String getNameSpouse() {
        return nameSpouse;
    }

    public void setNameSpouse(String nameSpouse) {
        this.nameSpouse = nameSpouse;
    }

    public String getPaternalSpouse() {
        return paternalSpouse;
    }

    public void setPaternalSpouse(String paternalSpouse) {
        this.paternalSpouse = paternalSpouse;
    }

    public String getMaternalSpouse() {
        return maternalSpouse;
    }

    public void setMaternalSpouse(String maternalSpouse) {
        this.maternalSpouse = maternalSpouse;
    }
}
