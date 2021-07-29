package com.Xoot.CreditoParaTi.entity;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "work")
public class Work {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer idWork;

    @Column(name = "name", length = 128)
    private String companyName;

    @Column(name = "type_contract")
    private Integer typeContract;

    @Column(name="position")
    private Integer position;

    @Column(name="specify_position")
    private String specifyPosition;

    @Column(name="laboral_activity")
    private Integer laboral_activity;

    @Column(name="specify_laboral_activity")
    private String specifyLaboralActivity;

    @Column(name= "address", length = 125)
    private String address;

    @Column(name= "external_number")
    private String external_number;

    @Column(name= "interior_number")
    private String interior_number;

    @Column(name= "suburb")
    private String suburb;

    @Column(name= "postal_code")
    private String postalCode;

    @Column(name= "municipality")
    private String municipality;

    @Column(name= "city")
    private String city;

    @Column(name= "state")
    private String state;

    @Column(name = "office_phone", length = 15)
    private String officePhone;

    @Column(name = "office_phone_extension", length = 15)
    private String officePhoneExtension;

    @Column(name= "now_work_seniority")
    private Integer nowWorkSeniority;

    @Column(name = "previous_work_seniority")
    private Integer previousWorkSeniority;

    @Column(name= "creaditApplication_id")
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

    public Integer getIdWork() {
        return idWork;
    }

    public void setIdWork(Integer idWork) {
        this.idWork = idWork;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public Integer getTypeContract() {
        return typeContract;
    }

    public void setTypeContract(Integer typeContract) {
        this.typeContract = typeContract;
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

    public Integer getLaboral_activity() {
        return laboral_activity;
    }

    public void setLaboral_activity(Integer laboral_activity) {
        this.laboral_activity = laboral_activity;
    }

    public String getSpecifyLaboralActivity() {
        return specifyLaboralActivity;
    }

    public void setSpecifyLaboralActivity(String specifyLaboralActivity) {
        this.specifyLaboralActivity = specifyLaboralActivity;
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

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
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

    public Integer getCreditApplication() {
        return creditApplication;
    }

    public void setCreditApplication(Integer creditApplication) {
        this.creditApplication = creditApplication;
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

    public Integer getNowWorkSeniority() {
        return nowWorkSeniority;
    }

    public void setNowWorkSeniority(Integer nowWorkSeniority) {
        this.nowWorkSeniority = nowWorkSeniority;
    }

    public Integer getPreviousWorkSeniority() {
        return previousWorkSeniority;
    }

    public void setPreviousWorkSeniority(Integer previousWorkSeniority) {
        this.previousWorkSeniority = previousWorkSeniority;
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
