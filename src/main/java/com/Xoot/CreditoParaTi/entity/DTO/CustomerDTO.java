package com.Xoot.CreditoParaTi.entity.DTO;

import java.util.Date;

public class CustomerDTO {
	private Integer idCustomer;
	private String name;
	private String paternalLastName;
	private String motherLastName;
	private Date birthday;
	private Integer age;
	private String curp;
	private String rfc;
	private String email;
	private String streetAndNumber;
	private Integer stateOfBirth_id;
	private Integer gender_id;
	private Integer colony_id;
	private Integer creditsAplicationProducts;
	private Integer userId;



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

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public String getCurp() {
		return curp;
	}

	public void setCurp(String curp) {
		this.curp = curp;
	}

	public String getRfc() {
		return rfc;
	}

	public void setRfc(String rfc) {
		this.rfc = rfc;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getStreetAndNumber() {
		return streetAndNumber;
	}

	public void setStreetAndNumber(String streetAndNumber) {
		this.streetAndNumber = streetAndNumber;
	}

	public Integer getStateOfBirth_id() {
		return stateOfBirth_id;
	}

	public void setStateOfBirth_id(Integer stateOfBirth_id) {
		this.stateOfBirth_id = stateOfBirth_id;
	}

	public Integer getGender_id() {
		return gender_id;
	}

	public void setGender_id(Integer gender_id) {
		this.gender_id = gender_id;
	}

	public Integer getColony_id() {
		return colony_id;
	}

	public void setColony_id(Integer colony_id) {
		this.colony_id = colony_id;
	}

	public Integer getIdCustomer() {
		return idCustomer;
	}

	public void setIdCustomer(Integer idCustomer) {
		this.idCustomer = idCustomer;
	}

	public Integer getCreditsAplicationProducts() {
		return creditsAplicationProducts;
	}

	public void setCreditsAplicationProducts(Integer creditsAplicationProducts) {
		this.creditsAplicationProducts = creditsAplicationProducts;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}
}
