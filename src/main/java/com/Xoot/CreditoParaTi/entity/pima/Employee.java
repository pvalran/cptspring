package com.Xoot.CreditoParaTi.entity.pima;

import com.Xoot.CreditoParaTi.entity.pima.UsuarioCategory;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "employee")
public class Employee  implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer idUser;

    @Column(name = "username", length = 16)
    private String username;

    @Column(name = "email", length = 255)
    private String email;

    @Column(name = "password", length = 60)
    private String password;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "users_categories_rel", joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "category_id"),
            uniqueConstraints = @UniqueConstraint(columnNames = { "user_id", "category_id" }))
    private List<UsuarioCategory> categoryUser;

    @Column(name = "status_flag")
    private Integer status_flag;

    @Column(name = "last_login")
    @Temporal(TemporalType.TIMESTAMP)
    private java.util.Date dtLastLogin;

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

    @Column(name = "name", length = 75)
    private String name;

    @Column(name = "paternal_last_name", length = 16)
    private String paternalLastName;

    @Column(name = "mother_last_name", length = 16)
    private String motherLastName;

    @Column(name = "profile_id")
    private String profileId;

    @Column(name = "type_user")
    private Integer typeUser;

    @Column(name = "phone")
    private String phone;

    @Column(name = "sucursal")
    private Integer sucursal;

    @PrePersist
    public void prePersist() {
        crtd_on = new java.util.Date();
        mdfd_on = new java.util.Date();
        status_flag = 1;
    }

    public Integer getIdUser() {
        return idUser;
    }

    public void setIdUser(Integer idUser) {
        this.idUser = idUser;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<UsuarioCategory> getCategoryUser() {
        return categoryUser;
    }

    public void setCategoryUser(List<UsuarioCategory> categoryUser) {
        this.categoryUser = categoryUser;
    }

    public Integer getStatus_flag() {
        return status_flag;
    }

    public void setStatus_flag(Integer status_flag) {
        this.status_flag = status_flag;
    }

    public java.util.Date getDtLastLogin() {
        return dtLastLogin;
    }

    public void setDtLastLogin(java.util.Date dtLastLogin) {
        this.dtLastLogin = dtLastLogin;
    }

    public java.util.Date getCrtd_on() {
        return crtd_on;
    }

    public void setCrtd_on(java.util.Date crtd_on) {
        this.crtd_on = crtd_on;
    }

    public String getCrtd_by() {
        return crtd_by;
    }

    public void setCrtd_by(String crtd_by) {
        this.crtd_by = crtd_by;
    }

    public java.util.Date getMdfd_on() {
        return mdfd_on;
    }

    public void setMdfd_on(java.util.Date mdfd_on) {
        this.mdfd_on = mdfd_on;
    }

    public String getMdfd_by() {
        return mdfd_by;
    }

    public void setMdfd_by(String mdfd_by) {
        this.mdfd_by = mdfd_by;
    }

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPaternalLastName() {
        return paternalLastName;
    }

    public void setPaternalLastName(String paternalLastName) {
        this.paternalLastName = paternalLastName;
    }

    public String getMotherLastName() {
        return motherLastName;
    }

    public void setMotherLastName(String motherLastName) {
        this.motherLastName = motherLastName;
    }

    public String getProfileId() {
        return profileId;
    }

    public void setProfileId(String profileId) {
        this.profileId = profileId;
    }

    public Integer getTypeUser() {
        return typeUser;
    }

    public void setTypeUser(Integer typeUser) {
        this.typeUser = typeUser;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Integer getSucursal() {
        return sucursal;
    }

    public void setSucursal(Integer sucursal) {
        this.sucursal = sucursal;
    }
}
