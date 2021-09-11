package com.Xoot.CreditoParaTi.dto;

import java.util.List;

public class UserDTO {
	protected String username;
	protected String password;
	protected String email;
	protected List<Integer> idCategory;
	protected boolean changed;
	
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
	public boolean getChanged() { return changed; }
	public void setChanged(boolean changed) { this.changed = changed; }
}
