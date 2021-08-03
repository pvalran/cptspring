package com.Xoot.CreditoParaTi.entity.DTO;

import javax.persistence.PrePersist;
import java.util.Date;

public class EconomicDependientiesDto {
    private Integer IdEconomicDependent;
    private Integer age;
    private Integer typeDependent;
    private Integer typeOccupation;
    private Integer creditApplication;

    private Integer status_flag;
    private java.util.Date crtd_on;
    private String crtd_by;
    private java.util.Date mdfd_on;
    private String mdfd_by;

    @PrePersist
    public void prePersist() {
        crtd_on = new java.util.Date();
        mdfd_on = new java.util.Date();
        status_flag = 1;
    }


    public Integer getIdEconomicDependent() {
        return IdEconomicDependent;
    }

    public void setIdEconomicDependent(Integer idEconomicDependent) {
        IdEconomicDependent = idEconomicDependent;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Integer getTypeDependent() {
        return typeDependent;
    }

    public void setTypeDependent(Integer typeDependent) {
        this.typeDependent = typeDependent;
    }

    public Integer getTypeOccupation() {
        return typeOccupation;
    }

    public void setTypeOccupation(Integer typeOccupation) {
        this.typeOccupation = typeOccupation;
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
