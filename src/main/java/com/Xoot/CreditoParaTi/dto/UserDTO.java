package com.Xoot.CreditoParaTi.dto;

import java.util.List;

public class UserDTO {
	private String username;
	private String password;
	private String email;
	private List<Integer> idCategory;
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public List<Integer> getIdCategory() {
		return idCategory;
	}
	public void setIdCategory(List<Integer> idCategory) {
		this.idCategory = idCategory;
	}
}
