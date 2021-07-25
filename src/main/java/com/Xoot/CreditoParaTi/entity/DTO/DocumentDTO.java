package com.Xoot.CreditoParaTi.entity.DTO;

public class DocumentDTO {

	private Integer idDocumento;
	private String name;
	private Integer typeDocumentId;
	private Integer creditAplicationId;
	private Integer classDocumentId;
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

	public Integer getCreditAplicationId() {
		return creditAplicationId;
	}

	public void setCreditAplicationId(Integer creditAplicationId) {
		this.creditAplicationId = creditAplicationId;
	}

	public Integer getClassDocumentId() { return classDocumentId; }

	public void setClassDocumentId(Integer classDocumentId) { this.classDocumentId = classDocumentId; }

	public Integer getIdDocumento() {
		return idDocumento;
	}

	public void setIdDocumento(Integer idDocumento) {
		this.idDocumento = idDocumento;
	}

	public Integer getStatus_flag() {
		return status_flag;
	}

	public void setStatus_flag(Integer status_flag) {
		this.status_flag = status_flag;
	}
}
