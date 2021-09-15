package com.Xoot.CreditoParaTi.entity;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "spouse")
public class Spouse {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer idSpouse;

    @Column(name = "paternal_last_name", length = 75)
    private String paternalLastName;

    @Column(name = "maternal_last_name", length = 75)
    private String maternalLastName;

    @Column(name = "name", length = 75)
    private String name;

    @Column(name="rfc", length = 14)
    private String rfc;

    @Column(name = "curp",length = 25)
    private String curp;

    @Column(name = "oldpaternal_last_name", length = 75)
    private String oldPaternalLastName;

    @Column(name = "oldmaternal_last_name", length = 75)
    private String oldMaternalLastName;

    @Column(name = "oldname", length = 75)
    private String oldName;

    @Column(name = "nationality")
    private Integer nationality;

    @Column(name = "speticNationality", length = 75)
    private String speticNationality;

    @Column(name = "gender")
    private Integer gender;

    @Column(name="Country_of_residence")
    private Integer countryOfResidence;

    @Column(name="date_of_birth")
    private java.util.Date dateOfBirth;

    @Column(name= "creadit_application_id")
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

    public Integer getIdSpouse() {
        return idSpouse;
    }

    public void setIdSpouse(Integer idSpouse) {
        this.idSpouse = idSpouse;
    }

    public String getPaternalLastName() {
        return paternalLastName;
    }

    public void setPaternalLastName(String paternalLastName) {
        this.paternalLastName = paternalLastName;
    }

    public String getMaternalLastName() {
        return maternalLastName;
    }

    public void setMaternalLastName(String maternalLastName) {
        this.maternalLastName = maternalLastName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRfc() {
        return rfc;
    }

    public void setRfc(String rfc) {
        this.rfc = rfc;
    }

    public String getCurp() {
        return curp;
    }

    public void setCurp(String curp) {
        this.curp = curp;
    }

    public String getOldPaternalLastName() {
        return oldPaternalLastName;
    }

    public void setOldPaternalLastName(String oldPaternalLastName) {
        this.oldPaternalLastName = oldPaternalLastName;
    }

    public String getOldMaternalLastName() {
        return oldMaternalLastName;
    }

    public void setOldMaternalLastName(String oldMaternalLastName) {
        this.oldMaternalLastName = oldMaternalLastName;
    }

    public String getOldName() {
        return oldName;
    }

    public void setOldName(String oldName) {
        this.oldName = oldName;
    }

    public Integer getNationality() {
        return nationality;
    }

    public void setNationality(Integer nationality) {
        this.nationality = nationality;
    }

    public String getSpeticNationality() {
        return speticNationality;
    }

    public void setSpeticNationality(String speticNationality) {
        this.speticNationality = speticNationality;
    }

    public Integer getGender() {
        return gender;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
    }

    public Integer getCountryOfResidence() {
        return countryOfResidence;
    }

    public void setCountryOfResidence(Integer countryOfResidence) {
        this.countryOfResidence = countryOfResidence;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
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
