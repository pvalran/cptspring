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
@Table(name = "locations_Suburb")
public class LocationSuburb implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer idColony;

	@Column(name = "name", length = 50)
	private String name;

	@Column(name = "ZipCode")
	private Integer ZipCode;

	//@OneToOne
	//@JoinColumn(name = "city_id", referencedColumnName = "id")
	@Column(name = "city_id")
	private Integer municipality_id;

	@Column(name = "suburb_code")
	private Integer colony_code;

	@Column(name = "city_code")
	private Integer municipality_code;

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

	public Integer getIdColony() {
		return idColony;
	}

	public void setIdColony(Integer idColony) {
		this.idColony = idColony;
	}


	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getZipCode() {
		return ZipCode;
	}

	public void setZipCode(Integer zipCode) {
		ZipCode = zipCode;
	}

	public Integer getStatus_flag() {
		return status_flag;
	}

	public void setStatus_flag(Integer status_flag) {
		this.status_flag = status_flag;
	}

	public Integer getMunicipality_id() {
		return municipality_id;
	}

	public void setMunicipality_id(Integer municipality_id) {
		this.municipality_id = municipality_id;
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


	public Integer getColony_code() {
		return colony_code;
	}

	public void setColony_code(Integer colony_code) {
		this.colony_code = colony_code;
	}

	public Integer getMunicipality_code() {
		return municipality_code;
	}

	public void setMunicipality_code(Integer municipality_code) {
		this.municipality_code = municipality_code;
	}
}
