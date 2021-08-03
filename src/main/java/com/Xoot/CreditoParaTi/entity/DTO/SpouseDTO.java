package com.Xoot.CreditoParaTi.entity.DTO;

import java.util.Date;

public class SpouseDTO {
    private Integer idSpouse;
    private String paternalLastName;
    private String maternalLastName;
    private String name;
    private String rfc;
    private String curp;
    private String oldPaternalLastName;
    private String oldMaternalLastName;
    private String oldName;
    private Integer nationality;
    private String speticNationality;
    private Integer gender;
    private Integer countryOfResidence;
    private Date dateOfBirth;
    private Integer creditApplication;
    private DocumentDTO img1;
    private DocumentDTO img2;
    private DocumentDTO img3;

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

    public DocumentDTO getImg1() {
        return img1;
    }

    public void setImg1(DocumentDTO img1) {
        this.img1 = img1;
    }

    public DocumentDTO getImg2() {
        return img2;
    }

    public void setImg2(DocumentDTO img2) {
        this.img2 = img2;
    }

    public DocumentDTO getImg3() {
        return img3;
    }

    public void setImg3(DocumentDTO img3) {
        this.img3 = img3;
    }
}
