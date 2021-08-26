package com.Xoot.CreditoParaTi.dto;

import java.util.Date;
import java.util.List;

public class UserBoardDTO extends UserDTO{
	private Integer idUser;
	private String name;
	private String paternalLastName;
	private String motherLastName;
	private String profileId;
	private Integer typeUser;
	private Integer status_flag;
	private java.util.Date crtd_on;

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

	public Integer getIdUser() {
		return idUser;
	}

	public void setIdUser(Integer idUser) {
		this.idUser = idUser;
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
}
