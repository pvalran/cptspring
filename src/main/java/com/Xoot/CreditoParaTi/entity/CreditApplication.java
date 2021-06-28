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
@Table(name = "credits_aplications")
public class CreditApplication implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer idCreditAplication;
	
	@OneToOne
	@JoinColumn(name = "user_id", referencedColumnName = "id")
	private Usuario usuario;

	@OneToOne
	@JoinColumn(name = "customer_id", referencedColumnName = "id")
	private Customer customer;
	
	@OneToOne
	@JoinColumn(name = "product_id", referencedColumnName = "id")
	private CreditApplicationProduct product;
	
	@OneToOne
	@JoinColumn(name = "status_id", referencedColumnName = "id")
	private CreditApplicationStatus status;

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

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
}