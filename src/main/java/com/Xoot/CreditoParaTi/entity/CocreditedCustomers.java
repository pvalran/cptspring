package com.Xoot.CreditoParaTi.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "cocredited_customers")
public class CocreditedCustomers implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer idCocreditedCustomers;

	@Column(name = "paternal_last_name")
	private String paternalLastName;

	@Column(name = "mother_last_name")
	private String motherLastName;

	@Column(name = "name")
	private String name;

	@Column(name = "gender")
	private Integer gender;

	@Column(name = "birthday")
	@Temporal(TemporalType.TIMESTAMP)
	private java.util.Date birthday;

	@Column(name = "state_of_birth_id")
	private Integer stateBirth;

	@Column(name = "age")
	private Integer age;

	@Column(name = "rfc")
	private String rfc;

	@Column(name = "curp")
	private String curp;

	@Column(name = "email")
	private String email;

	@Column(name = "address_and_number")
	private String addressNumber;

	@Column(name = "postal_code")
	private String postalCode;

	@Column(name = "suburb")
	private String suburb;

	@Column(name = "city")
	private String city;

	@Column(name = "county")
	private String county;

	@Column(name = "state")
	private String state;

	@Column(name = "credit_application_id")
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

	public Integer getIdCocreditedCustomers() {
		return idCocreditedCustomers;
	}

	public void setIdCocreditedCustomers(Integer idCocreditedCustomers) {
		this.idCocreditedCustomers = idCocreditedCustomers;
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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getGender() {
		return gender;
	}

	public void setGender(Integer gender) {
		this.gender = gender;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public Integer getStateBirth() {
		return stateBirth;
	}

	public void setStateBirth(Integer stateBirth) {
		this.stateBirth = stateBirth;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAddressNumber() {
		return addressNumber;
	}

	public void setAddressNumber(String addressNumber) {
		this.addressNumber = addressNumber;
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

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getCounty() {
		return county;
	}

	public void setCounty(String county) {
		this.county = county;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
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
