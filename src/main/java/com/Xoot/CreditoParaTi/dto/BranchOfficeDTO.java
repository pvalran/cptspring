package com.Xoot.CreditoParaTi.dto;

public class BranchOfficeDTO {
    private Integer Id;
    private String numberBranch;
    private String name;
    private String city;
    private Integer state;
    private Integer status_flag;

    public Integer getId() {
        return Id;
    }

    public void setId(Integer id) {
        Id = id;
    }

    public String getNumberBranch() {
        return numberBranch;
    }

    public void setNumberBranch(String numberBranch) {
        this.numberBranch = numberBranch;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public Integer getStatus_flag() {
        return status_flag;
    }

    public void setStatus_flag(Integer status_flag) {
        this.status_flag = status_flag;
    }
}
