package com.Xoot.CreditoParaTi.entity;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="additional_information")
public class AdditionalInformation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer idAdditionalInformaction;

    @Column(name="country_of_birth")
    private Integer countryOfBirth;

    @Column(name="Country_of_residence")
    private Integer countryOfResidence;

    @Column(name = "nationality")
    private Integer nationality;

    @Column(name = "civil_state")
    private Integer civilState;

    @Column(name = "marital_status")
    private Integer maritalStatus;

    @Column(name = "number_imss", length = 75)
    private String numberImss;

    @Column(name = "scholarship")
    private Integer scholarship;

    @Column(name = "phone", length = 15)
    private String phone;

    @Column(name = "mobile", length = 15)
    private String mobile;

    @Column(name = "other_phone", length = 15)
    private String otherPhone;

    @Column(name= "now_residence_seniority")
    private Integer nowResidenceSeniority;

    @Column(name = "previous_residence_seniority")
    private Integer previousResidenceSeniority;

    @Column(name= "address", length = 125)
    private String address;

    @Column(name= "external_number")
    private String external_number;

    @Column(name= "interior_number")
    private String interior_number;

    @Column(name= "suburb")
    private String suburb;

    @Column(name= "municipality")
    private String municipality;

    @Column(name= "city")
    private String city;

    @Column(name= "state")
    private String state;

    @Column(name= "postal_code")
    private String postal_code;

    @Column(name= "country")
    private Integer country;

    @Column(name= "creaditApplication_id")
    private Integer creaditApplication;

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

    @PrePersist
    public void prePersist() {
        crtd_on = new java.util.Date();
        mdfd_on = new java.util.Date();
    }

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

    public Integer getCountry() {
        return country;
    }

    public void setCountry(Integer country) {
        this.country = country;
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

    public Integer getCreaditApplication() {
        return creaditApplication;
    }

    public void setCreaditApplication(Integer creaditApplication) {
        this.creaditApplication = creaditApplication;
    }
}
