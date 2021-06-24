package com.Xoot.CreditoParaTi.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "customers")
public class Customer implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer idCustomer;

	@Column(name = "name", length = 150)
	private String name;

	@Column(name = "paternal_last_name", length = 100)
	private String paternalLastName;

	@Column(name = "mother_last_name", length = 100)
	private String motherLastName;

	@Column(name = "birthday")
	@Temporal(TemporalType.TIMESTAMP)
	private java.util.Date birthday;

	@Column(name = "age")
	private Integer age;

	@Column(name = "curp", length = 50)
	private String curp;

	@Column(name = "rfc", length = 50)
	private String rfc;

	@Column(name = "email", length = 150)
	private String email;

	@Column(name = "street_and_number", length = 255)
	private String streetAndNumber;

	@OneToOne
	@JoinColumn(name = "state_of_birth_id", referencedColumnName = "id")
	private LocationState stateOfBirth;

	@OneToOne
	@JoinColumn(name = "gender_id", referencedColumnName = "id")
	private CustomerGender gender;

	@OneToOne
	@JoinColumn(name = "suburb_id", referencedColumnName = "id")
	private LocationSuburb suburb;

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
	}

	public Integer getIdCustomer() {
		return idCustomer;
	}

	public void setIdCustomer(Integer idCustomer) {
		this.idCustomer = idCustomer;
	}

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

	public java.util.Date getBirthday() {
		return birthday;
	}

	public void setBirthday(java.util.Date birthday) {
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

	public LocationState getStateOfBirth() {
		return stateOfBirth;
	}

	public void setStateOfBirth(LocationState stateOfBirth) {
		this.stateOfBirth = stateOfBirth;
	}

	public CustomerGender getGender() {
		return gender;
	}

	public void setGender(CustomerGender gender) {
		this.gender = gender;
	}

	public LocationSuburb getSuburb() {
		return suburb;
	}

	public void setSuburb(LocationSuburb suburb) {
		this.suburb = suburb;
	}

	public Integer getStatus_flag() {
		return status_flag;
	}

	public void setStatus_flag(Integer status_flag) {
		this.status_flag = status_flag;
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
}