package com.meal.model;

import javax.validation.constraints.NotBlank;

public class ChangePassword {

	@NotBlank
	private String password;
	@NotBlank
	private String confirmPassword;
	
	private User user;
	
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getConfirmPassword() {
		return confirmPassword;
	}
	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}
	
	
	
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	@Override
	public String toString() {
		return "ChangePassword [password=" + password + ", confirmPassword=" + confirmPassword + ", user=" + user + "]";
	}
	
	
	
}
