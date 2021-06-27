package com.Xoot.CreditoParaTi.entity.DTO;

public class DocumentDTO {

	private String name;
	private Integer classDocumentId;
	private Integer typeDocumentId;
	private Integer creditAplicationId;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getClassDocumentId() {
		return classDocumentId;
	}

	public void setClassDocumentId(Integer classDocumentId) {
		this.classDocumentId = classDocumentId;
	}

	public Integer getTypeDocumentId() {
		return typeDocumentId;
	}

	public void setTypeDocumentId(Integer typeDocumentId) {
		this.typeDocumentId = typeDocumentId;
	}

	public Integer getCreditAplicationId() {
		return creditAplicationId;
	}

	public void setCreditAplicationId(Integer creditAplicationId) {
		this.creditAplicationId = creditAplicationId;
	}
}
