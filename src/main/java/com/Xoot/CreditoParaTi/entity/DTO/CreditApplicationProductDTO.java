package com.Xoot.CreditoParaTi.entity.DTO;

import java.util.List;

public class CreditApplicationProductDTO {
	private String name;
	private List<Integer> idDocumentType;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Integer> getIdDocumentType() {
		return idDocumentType;
	}

	public void setIdDocumentType(List<Integer> idDocumentType) {
		this.idDocumentType = idDocumentType;
	}

}
