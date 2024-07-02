package com.meal.helper;

import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItem;
import org.springframework.lang.Nullable;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.meal.enums.Gender;
import com.meal.model.Address;
import com.meal.model.Contact;
import com.meal.model.IDProof;
import com.meal.model.Image;
import com.meal.model.MealAccount;
import com.meal.model.Role;
import com.meal.model.User;
import com.meal.validation.NotNullFile;
import com.meal.validation.TextValidByRegEx;


public class UserFormObj {
	
	//user personal info
	private long id;
	//@NotNullFile
	private MultipartFile profilePic;
	
	private Image pic;
	
	@TextValidByRegEx(regEx = "[a-zA-Z]{3,}")
	private String firstName;
	@TextValidByRegEx(regEx = "[a-zA-Z]{3,}")
	private String lastName;
	@NotNull
	private Gender gender;
	@NotNull
	private Date dob;
	//user contact details
	@Valid
	private Contact contact;
	@NotBlank
	private String password;
	@NotBlank
	private String confirmPassword;
	//user govt id proof
	@Valid
	private IDProof idProof;
	//user's addresses
	@Valid
	private Address currentAddress;
	private boolean sameAsCurrentAddress;
	@Valid
	private Address permanentAddress;
	//business data
	@Valid
	private MealAccount mealAccount;
	// instance meta info
	@NotNull
	private Set<Role> roles;
	
	
	public Image getPic() {
		return pic;
	}


	public void setPic(Image pic) {
		this.pic = pic;
	}


	public UserFormObj() {
		super();
	}


	public long getId() {
		return id;
	}


	public void setId(long id) {
		this.id = id;
	}


	public UserFormObj(MultipartFile profilePic, String firstName, String lastName, Gender gender, Date dob, Contact contact,
			String password, String confirmPassword, IDProof idProof, Address currentAddress,
			boolean sameAsCurrentAddress, Address permanentAddress, MealAccount mealAccount, Set<Role> roles) {
		super();
		this.profilePic = profilePic;
		this.firstName = firstName;
		this.lastName = lastName;
		this.gender = gender;
		this.dob = dob;
		this.contact = contact;
		this.password = password;
		this.confirmPassword = confirmPassword;
		this.idProof = idProof;
		this.currentAddress = currentAddress;
		this.sameAsCurrentAddress = sameAsCurrentAddress;
		this.permanentAddress = permanentAddress;
		this.mealAccount = mealAccount;
		this.roles = roles;
	}

	public UserFormObj(User user) {
		super();
		
		
		this.pic=user.getProfilePic();
		this.id=user.getId();
		this.firstName = user.getFirstName();
		this.lastName = user.getLastName();
		this.gender = user.getGender();
		this.dob = user.getDob();
		this.contact = user.getContact();
		this.password = user.getPassword();
		this.confirmPassword = user.getPassword();
		this.idProof = user.getIdProof();
		this.currentAddress = user.getCurrentAddress();
		this.permanentAddress = user.getPermanentAddress();
		this.mealAccount = user.getMealAccount();
		this.roles = user.getRoles();
	}

	public MultipartFile getProfilePic() {
		return profilePic;
	}


	public void setProfilePic(MultipartFile profilePic) {
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


	public String getConfirmPassword() {
		return confirmPassword;
	}


	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
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


	public boolean isSameAsCurrentAddress() {
		return sameAsCurrentAddress;
	}


	public void setSameAsCurrentAddress(boolean sameAsCurrentAddress) {
		this.sameAsCurrentAddress = sameAsCurrentAddress;
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


	public Set<Role> getRoles() {
		return roles;
	}


	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}


	@Override
	public String toString() {
		return "UserFormObj [id=" + id + ", profilePic=" + profilePic + ", firstName=" + firstName + ", lastName=" + lastName
				+ ", gender=" + gender + ", dob=" + dob + ", contact=" + contact + ", password=" + password
				+ ", idProof=" + idProof + ", currentAddress=" + currentAddress + ", permanentAddress="
				+ permanentAddress + ", mealAccount=" + mealAccount + ", roles=" + roles + "]";
	}


	
	
	
	
	
}
