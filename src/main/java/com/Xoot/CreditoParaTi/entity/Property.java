package com.Xoot.CreditoParaTi.entity;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "property")
public class Property {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Integer IdProperty;

    @Column(name = "use_property")
    private Integer useProperty;

    @Column(name = "type_property")
    private Double typeProperty;

    @Column(name = "estimated_value")
    private Double estimatedValue;

    @Column(name = "total_surface")
    private Double totalSurface;

    @Column(name = "construction_surface")
    private Double constructionSurface;

    @Column(name = "paternal_last_name", length = 75)
    private String paternalLastName;

    @Column(name = "maternal_last_name", length = 75)
    private String maternalLastName;

    @Column(name = "name", length = 75)
    private String name;

    @Column(name ="phone", length = 12)
    private String phone;

    @Column(name= "address")
    private String address;

    @Column(name= "postal_code", length = 5)
    private String postalCode;

    @Column(name= "suburb")
    private String suburb;

    @Column(name= "municipality")
    private String municipality;

    @Column(name= "city")
    private String city;

    @Column(name= "state")
    private String state;

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
        status_flag = 1;
    }

    public Integer getIdProperty() {
        return IdProperty;
    }

    public void setIdProperty(Integer idProperty) {
        IdProperty = idProperty;
    }

    public Integer getUseProperty() {
        return useProperty;
    }

    public void setUseProperty(Integer useProperty) {
        this.useProperty = useProperty;
    }

    public Double getTypeProperty() {
        return typeProperty;
    }

    public void setTypeProperty(Double typeProperty) {
        this.typeProperty = typeProperty;
    }

    public Double getEstimatedValue() {
        return estimatedValue;
    }

    public void setEstimatedValue(Double estimatedValue) {
        this.estimatedValue = estimatedValue;
    }

    public Double getTotalSurface() {
        return totalSurface;
    }

    public void setTotalSurface(Double totalSurface) {
        this.totalSurface = totalSurface;
    }

    public Double getConstructionSurface() {
        return constructionSurface;
    }

    public void setConstructionSurface(Double constructionSurface) {
        this.constructionSurface = constructionSurface;
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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
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

    public Integer getCreaditApplication() {
        return creaditApplication;
    }

    public void setCreaditApplication(Integer creaditApplication) {
        this.creaditApplication = creaditApplication;
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
