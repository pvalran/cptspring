package com.Xoot.CreditoParaTi.entity.DTO;

public class CreditApplicationDTO {

	private Integer idCreditAplication;
	private Integer user;
	private Integer customer;
	private Integer product;
	private Integer status;
	private Integer creditId;




	public Integer getUser() {
		return user;
	}

	public void setUser(Integer user) {
		this.user = user;
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

	public Integer getIdCreditAplication() {
		return idCreditAplication;
	}

	public void setIdCreditAplication(Integer idCreditAplication) {
		this.idCreditAplication = idCreditAplication;
	}
}
