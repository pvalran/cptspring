package com.Xoot.CreditoParaTi.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "locations_counties")
public class LocationColonies implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer idCity;

	@Column(name = "name", length = 50)
	private String name;

	@Column(name = "status_flag")
	private Integer status_flag;

	//@OneToOne
	//@JoinColumn(name = "city_id", referencedColumnName = "id")
	@Column(name = "city_id")
	private Integer municipality_id;

	@Column(name = "city_code")
	private Integer city_code;


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

	public Integer getIdCity() {
		return idCity;
	}

	public void setIdCity(Integer idCity) {
		this.idCity = idCity;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getStatus_flag() {
		return status_flag;
	}

	public Integer getMunicipality_id() {
		return municipality_id;
	}

	public void setMunicipality_id(Integer municipality_id) {
		this.municipality_id = municipality_id;
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

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	public Integer getCity_code() {
		return city_code;
	}

	public void setCity_code(Integer city_code) {
		this.city_code = city_code;
	}
}
