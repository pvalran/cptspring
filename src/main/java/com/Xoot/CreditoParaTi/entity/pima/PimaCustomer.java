package com.Xoot.CreditoParaTi.entity.pima;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "customers")
public class PimaCustomer implements Serializable {
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

	@Column(name = "state_of_birth_id")
	private Integer stateOfBirth_id;

	@Column(name = "gender_id")
	private Integer gender_id;

	@Column(name = "suburb_id")
	private Integer colony_id;

	@Column(name = "state_id")
	private Integer state_id;

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

	@Column(name = "number_request")
	private Integer creditId;

	@Column(name = "type_enrollment")
	private Integer typeEnrollment;

	@PrePersist
	public void prePersist() {
		crtd_on = new java.util.Date();
		mdfd_on = new java.util.Date();
		status_flag = 1;
		typeEnrollment = 1;
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

	public Integer getCreditId() {
		return creditId;
	}

	public void setCreditId(Integer creditId) {
		this.creditId = creditId;
	}

	public Integer getState_id() {
		return state_id;
	}

	public void setState_id(Integer state_id) {
		this.state_id = state_id;
	}

	public Integer getTypeEnrollment() {
		return typeEnrollment;
	}

	public void setTypeEnrollment(Integer typeEnrollment) {
		this.typeEnrollment = typeEnrollment;
	}
}
