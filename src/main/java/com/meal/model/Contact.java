package com.meal.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotBlank;

import org.springframework.format.annotation.NumberFormat;

import com.meal.validation.TextValidByRegEx;

@Entity
public class Contact {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@TextValidByRegEx(regEx = "[0-9]{10}")
	private String primaryPhone;
	@TextValidByRegEx(regEx = "[0-9]{10}")
	private String alternatePhone;
	@Column(unique=true)
	@TextValidByRegEx(regEx = "[A-Za-z0-9.]+[@][a-z]+[.][a-z]+")
	private String email;
	@OneToOne(mappedBy = "contact",cascade = CascadeType.ALL)
	private User user;
	public Contact(String primaryPhone, String alternatePhone, String email, User user) {
		super();
		this.primaryPhone = primaryPhone;
		this.alternatePhone = alternatePhone;
		this.email = email;
		this.user = user;
	}
	public Contact() {
		super();
	}
	public String getPrimaryPhone() {
		return primaryPhone;
	}
	public void setPrimaryPhone(String primaryPhone) {
		this.primaryPhone = primaryPhone;
	}
	public String getAlternatePhone() {
		return alternatePhone;
	}
	public void setAlternatePhone(String alternatePhone) {
		this.alternatePhone = alternatePhone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	@Override
	public String toString() {
		return "Contact [id=" + id + ", primaryPhone=" + primaryPhone + ", alternatePhone=" + alternatePhone
				+ ", email=" + email + ", user=" + "]";
	}
	
	
	
	
}
