package com.Xoot.CreditoParaTi.entity;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "cocredited_work")
public class CocreditedWork {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer idCocreditedWork;

    @Column(name = "type_contract")
    private Integer typeContract;

    @Column(name = "laboral_activity")
    private Integer laboralActivity;

    @Column(name = "specify_laboral_activity")
    private String specifyLaboralActivity;

    @Column(name = "position")
    private Integer position;

    @Column(name = "specify_position")
    private String specifyPosition;

    @Column(name = "officePhone")
    private String officePhone;

    @Column(name = "officePhoneExtension")
    private String officePhoneExtension;

    @Column(name = "now_work_seniority")
    private Integer nowWorkWeniority;

    @Column(name = "previous_work_seniority")
    private Integer previousWorkSeniority;

    @Column(name = "name")
    private String name;

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

    @Column(name = "county")
    private String county;

    @Column(name = "city")
    private String city;

    @Column(name = "state")
    private String state;

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

    @PrePersist
    public void prePersist() {
        crtd_on = new java.util.Date();
        mdfd_on = new java.util.Date();
        status_flag = 1;
    }


    public Integer getIdCocreditedWork() {
        return idCocreditedWork;
    }

    public void setIdCocreditedWork(Integer idCocreditedWork) {
        this.idCocreditedWork = idCocreditedWork;
    }

    public Integer getTypeContract() {
        return typeContract;
    }

    public void setTypeContract(Integer typeContract) {
        this.typeContract = typeContract;
    }

    public Integer getLaboralActivity() {
        return laboralActivity;
    }

    public void setLaboralActivity(Integer laboralActivity) {
        this.laboralActivity = laboralActivity;
    }

    public String getSpecifyLaboralActivity() {
        return specifyLaboralActivity;
    }

    public void setSpecifyLaboralActivity(String specifyLaboralActivity) {
        this.specifyLaboralActivity = specifyLaboralActivity;
    }

    public Integer getPosition() {
        return position;
    }

    public void setPosition(Integer position) {
        this.position = position;
    }

    public String getSpecifyPosition() {
        return specifyPosition;
    }

    public void setSpecifyPosition(String specifyPosition) {
        this.specifyPosition = specifyPosition;
    }

    public String getOfficePhone() {
        return officePhone;
    }

    public void setOfficePhone(String officePhone) {
        this.officePhone = officePhone;
    }

    public String getOfficePhoneExtension() {
        return officePhoneExtension;
    }

    public void setOfficePhoneExtension(String officePhoneExtension) {
        this.officePhoneExtension = officePhoneExtension;
    }

    public Integer getNowWorkWeniority() {
        return nowWorkWeniority;
    }

    public void setNowWorkWeniority(Integer nowWorkWeniority) {
        this.nowWorkWeniority = nowWorkWeniority;
    }

    public Integer getPreviousWorkSeniority() {
        return previousWorkSeniority;
    }

    public void setPreviousWorkSeniority(Integer previousWorkSeniority) {
        this.previousWorkSeniority = previousWorkSeniority;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public String getCounty() {
        return county;
    }

    public void setCounty(String county) {
        this.county = county;
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
}
