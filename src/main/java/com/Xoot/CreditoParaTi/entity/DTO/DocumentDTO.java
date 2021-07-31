package com.Xoot.CreditoParaTi.entity.DTO;

public class DocumentDTO {

	private Integer idDocument;
	private String name;
	private Integer typeDocumentId;
	private Integer creditAplication;
	private Integer classDocumentId;
	private Integer userId;
	private Integer status_flag;


	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getTypeDocumentId() {
		return typeDocumentId;
	}

	public void setTypeDocumentId(Integer typeDocumentId) {
		this.typeDocumentId = typeDocumentId;
	}


	public Integer getClassDocumentId() { return classDocumentId; }

	public void setClassDocumentId(Integer classDocumentId) { this.classDocumentId = classDocumentId; }

	public Integer getStatus_flag() {
		return status_flag;
	}

	public void setStatus_flag(Integer status_flag) {
		this.status_flag = status_flag;
	}

	public Integer getIdDocument() {
		return idDocument;
	}

	public void setIdDocument(Integer idDocument) {
		this.idDocument = idDocument;
	}

	public Integer getCreditAplication() {
		return creditAplication;
	}

	public void setCreditAplication(Integer creditAplication) {
		this.creditAplication = creditAplication;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}
}
