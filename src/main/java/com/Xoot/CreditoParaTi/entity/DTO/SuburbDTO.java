package com.Xoot.CreditoParaTi.entity.DTO;

public class SuburbDTO {
	private String SuburbName;

	public String getSuburbName() {
		return SuburbName;
	}

	public void setSuburbName(String suburbName) {
		SuburbName = suburbName;
	}

	public SuburbDTO(String suburbName) {
		super();
		SuburbName = suburbName;
	}
}
