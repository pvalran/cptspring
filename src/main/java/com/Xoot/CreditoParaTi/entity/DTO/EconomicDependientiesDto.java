package com.Xoot.CreditoParaTi.entity.DTO;

public class EconomicDependientiesDto {
    private Integer IdEconomicDependent;
    private Integer age;
    private Integer typeDependent;
    private Integer typeOccupation;
    private Integer creditApplication;

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
}
