package com.Xoot.CreditoParaTi.entity.app;

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
@Table(name = "credits_aplications")
public class CreditApplication implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer idCreditAplication;


	@Column(name = "user_id")
	private Integer user;


	@Column(name = "customer_id")
	private Integer customer;


	@Column(name = "product_id")
	private Integer product;


	@Column(name = "status_id")
	private Integer status;

	@Column (name ="number_request")
	private Integer creditId;

	@Column(name = "status_flag")
	private Integer status_flag;

	@Column(name = "crtd_on", columnDefinition= "TIMESTAMP WITH TIME ZONE")
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

	public Integer getIdCreditAplication() {
		return idCreditAplication;
	}

	public void setIdCreditAplication(Integer idCreditAplication) {
		this.idCreditAplication = idCreditAplication;
	}


	public Integer getCustomer() {
		return customer;
	}

	public void setCustomer(Integer customer) {
		this.customer = customer;
	}

	public Integer getProduct() {
		return product;
	}

	public void setProduct(Integer product) {
		this.product = product;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getCreditId() {
		return creditId;
	}

	public void setCreditId(Integer creditId) {
		this.creditId = creditId;
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

	public Integer getUser() {
		return user;
	}

	public void setUser(Integer user) {
		this.user = user;
	}


}
