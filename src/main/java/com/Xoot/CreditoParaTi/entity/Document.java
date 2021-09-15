package com.Xoot.CreditoParaTi.entity;

import java.io.Serializable;

import javax.persistence.*;

@Entity
@Table(name = "documents")
public class Document implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer idDocument;

	@Column(name = "name")
	private String name;


	@Column(name = "type_document_id")
	private Integer typeDocumentId;

	@Column(name = "class_document_id")
	private Integer class_document_id;

	@Column(name = "credit_aplication_id")
	private Integer creditAplication;

	@Column(name = "user_id")
	private Integer userId;

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

	@Lob
	@Column(name = "document")
	private byte[] document;


	@PrePersist
	public void prePersist() {
		crtd_on = new java.util.Date();
		mdfd_on = new java.util.Date();
		status_flag = 1;
	}

	public Integer getIdDocument() {
		return idDocument;
	}

	public void setIdDocument(Integer idDocument) {
		this.idDocument = idDocument;
	}

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
	public Integer getCreditAplication() {
		return creditAplication;
	}

	public void setCreditAplication(Integer creditAplication) {
		this.creditAplication = creditAplication;
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


	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Integer getClass_document_id() {
		return class_document_id;
	}

	public void setClass_document_id(Integer class_document_id) {
		this.class_document_id = class_document_id;
	}

	public byte[] getDocument() {
		return document;
	}

	public void setDocument(byte[] document) {
		this.document = document;
	}
}
