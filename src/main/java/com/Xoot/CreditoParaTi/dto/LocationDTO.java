package com.Xoot.CreditoParaTi.dto;

import java.util.List;

public class LocationDTO {
	
	private String State;

	private String City;
	
	private String ZipCode;
	
	private List<SuburbDTO> lstSuburb;

	public String getState() {
		return State;
	}

	public void setState(String state) {
		State = state;
	}

	public String getCity() {
		return City;
	}

	public void setCity(String city) {
		City = city;
	}

	public String getZipCode() {
		return ZipCode;
	}

	public void setZipCode(String zipCode) {
		ZipCode = zipCode;
	}

	public List<SuburbDTO> getLstSuburb() {
		return lstSuburb;
	}

	public void setLstSuburb(List<SuburbDTO> lstSuburb) {
		this.lstSuburb = lstSuburb;
	}
}
