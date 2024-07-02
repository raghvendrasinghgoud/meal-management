package com.meal.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import com.meal.enums.IDType;
import com.meal.validation.TextValidByRegEx;

@Entity
public class IDProof {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	@Enumerated(EnumType.STRING)
	private IDType idType;
	@TextValidByRegEx(regEx = "[0-9]{7}")
	private String idProofNum;
	@OneToOne(mappedBy = "idProof",cascade = CascadeType.ALL)
	private User user;
	public IDProof(long id, IDType idType, String idProofNum, User user) {
		super();
		this.id = id;
		this.idType = idType;
		this.idProofNum = idProofNum;
		this.user = user;
	}
	public IDProof(IDType idType, String idProofNum, User user) {
		super();
		this.idType = idType;
		this.idProofNum = idProofNum;
		this.user = user;
	}
	
	
	
	
	public IDProof() {
		super();
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public IDType getIdType() {
		return idType;
	}
	public void setIdType(IDType idType) {
		this.idType = idType;
	}
	public String getIdProofNum() {
		return idProofNum;
	}
	public void setIdProofNum(String idProofNum) {
		this.idProofNum = idProofNum;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	
	@Override
	public String toString() {
		return "IDProof [id=" + id + ", idType=" + idType + ", idProofNum=" + idProofNum +  "]";
	}
	
	
	
}
