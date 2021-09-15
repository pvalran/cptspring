package com.Xoot.CreditoParaTi.dto;

import java.sql.Date;
import java.util.List;

public class UserAuthDTO {
    protected Integer idUser;
    protected String username;
    protected String email;
    protected String password;
    protected List<Integer> idCategory;
    protected Integer status_flag;
    protected Date dtLastLogin;
    protected Date crtd_on;
    protected String crtd_by;
    protected Date mdfd_on;
    protected String mdfd_by;
    protected boolean changed;

    public Integer getIdUser() {return idUser;}
    public void setIdUser(Integer idUser) {this.idUser = idUser;}
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public List<Integer> getIdCategory() {
        return idCategory;
    }
    public void setIdCategory(List<Integer> idCategory) {
        this.idCategory = idCategory;
    }

    public Integer getStatus_flag() {return status_flag;}
    public void setStatus_flag(Integer status_flag) {this.status_flag = status_flag;}
    public Date getDtLastLogin() {return dtLastLogin;}
    public void setDtLastLogin(Date dtLastLogin) {this.dtLastLogin = dtLastLogin;}
    public Date getCrtd_on() {return crtd_on;}
    public void setCrtd_on(Date crtd_on) {this.crtd_on = crtd_on;}
    public String getCrtd_by() {return crtd_by;}
    public void setCrtd_by(String crtd_by) {this.crtd_by = crtd_by;}
    public Date getMdfd_on() {return mdfd_on;}
    public void setMdfd_on(Date mdfd_on) {this.mdfd_on = mdfd_on;}
    public String getMdfd_by() {return mdfd_by;}
    public void setMdfd_by(String mdfd_by) {this.mdfd_by = mdfd_by;}
    public boolean getChanged() { return changed; }
    public void setChanged(boolean changed) { this.changed = changed; }
}
