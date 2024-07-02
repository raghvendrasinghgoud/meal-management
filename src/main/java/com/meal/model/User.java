package com.meal.model;

import java.util.Collection;
import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.multipart.MultipartFile;

import com.meal.enums.Gender;
import com.meal.helper.Utility;
import com.meal.validation.NotNullFile;
import com.meal.validation.TextValidByRegEx;
import com.meal.validationgroups.SaveUserGroup;
import com.meal.validationgroups.UpdateUserGroup;

@Entity
public class User extends Auditable implements UserDetails {
	
	//user personal info
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	@OneToOne(cascade = CascadeType.ALL)
	private Image profilePic;
	@TextValidByRegEx(regEx = "[a-zA-Z]{3,}")
	private String firstName;
	@TextValidByRegEx(regEx = "[a-zA-Z]{3,}")
	private String lastName;
	@NotNull
	@Enumerated(EnumType.STRING)
	private Gender gender;
	@NotNull
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date dob;
	//user contact details
	@Valid
	@OneToOne(cascade = CascadeType.ALL)
	private Contact contact;
	private String password;
	//user govt id proof
	@Valid
	@OneToOne(cascade = CascadeType.ALL)
	private IDProof idProof;
	//user's addresses
	@Valid
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn
	private Address currentAddress;
	@Valid
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn
	private Address permanentAddress;
	//business data
	
	@OneToOne(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
	private MealAccount mealAccount;
	@OneToMany(mappedBy = "user",cascade = CascadeType.ALL,fetch=FetchType.EAGER,targetEntity = Payment.class)
	private Set<Payment> payments;
	
	@NotNull
	@ManyToMany(cascade = CascadeType.PERSIST,fetch = FetchType.EAGER)
	private Set<Role> roles;
	
	
	//transient properties
	@NotNullFile(groups = {SaveUserGroup.class})
	@Transient
	private MultipartFile imageFile;
	@Transient
	private boolean sameAsCurrentAddress;
	
	public User() {
		super();
	}
	
	
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	
	public boolean isSameAsCurrentAddress() {
		return sameAsCurrentAddress;
	}



	public void setSameAsCurrentAddress(boolean sameAsCurrentAddress) {
		this.sameAsCurrentAddress = sameAsCurrentAddress;
	}



	public MultipartFile getImageFile() {
		return imageFile;
	}



	public void setImageFile(MultipartFile imageFile) {
		this.imageFile = imageFile;
	}




	public Image getProfilePic() {
		return profilePic;
	}
	public void setProfilePic(Image profilePic) {
		this.profilePic = profilePic;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public Gender getGender() {
		return gender;
	}
	public void setGender(Gender gender) {
		this.gender = gender;
	}
	public Date getDob() {
		return dob;
	}
	public void setDob(Date dob) {
		this.dob = dob;
	}
	public Contact getContact() {
		return contact;
	}
	public void setContact(Contact contact) {
		this.contact = contact;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public IDProof getIdProof() {
		return idProof;
	}
	public void setIdProof(IDProof idProof) {
		this.idProof = idProof;
	}
	public Address getCurrentAddress() {
		return currentAddress;
	}
	public void setCurrentAddress(Address currentAddress) {
		this.currentAddress = currentAddress;
	}
	public Address getPermanentAddress() {
		return permanentAddress;
	}
	public void setPermanentAddress(Address permanentAddress) {
		this.permanentAddress = permanentAddress;
	}
	public MealAccount getMealAccount() {
		return mealAccount;
	}
	public void setMealAccount(MealAccount mealAccount) {
		this.mealAccount = mealAccount;
	}
	
	



	public Set<Payment> getPayments() {
		return payments;
	}



	public void setPayments(Set<Payment> payments) {
		this.payments = payments;
	}



	public Set<Role> getRoles() {
		return roles;
	}
	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}


	@Override
	public String toString() {
		return "User [id=" + id + ", profilePic=" + profilePic + ", firstName=" + firstName + ", lastName=" + lastName
				+ ", gender=" + gender + ", dob=" + dob + ", contact=" + contact + ", password=" + password
				+ ", idProof=" + idProof + ", currentAddress=" + currentAddress + ", permanentAddress="
				+ permanentAddress + ", mealAccount=" + mealAccount + ", roles=" + roles + ", createdDate="
				+ super.createdDate + ", modifiedDate=" + super.modifiedDate + ", modifiedBy="
				 + ", imageFile=" + imageFile + ", sameAsCurrentAddress=" + sameAsCurrentAddress + "]";
	}



	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		
		return roles;
	}



	@Override
	public String getUsername() {
		
		return this.contact.getEmail();
	}



	@Override
	public boolean isAccountNonExpired() {
		
		return true;
	}



	@Override
	public boolean isAccountNonLocked() {
		
		return true;
	}



	@Override
	public boolean isCredentialsNonExpired() {

		return true;
	}



	@Override
	public boolean isEnabled() {
		
		return true;
	}
	
	
	
	
}
