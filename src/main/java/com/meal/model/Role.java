package com.meal.model;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotBlank;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.validator.constraints.Length;
import org.springframework.security.core.GrantedAuthority;
	


@Entity
public class Role extends Auditable implements GrantedAuthority {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	@Length(min = 3,message = "length must be more than 3 char")
	@NotBlank(message = "name cannot be empty")
	private String name;
	@ManyToMany(mappedBy = "roles",cascade = CascadeType.PERSIST,fetch = FetchType.LAZY)
	private List<User> users;
	
			public Role(long id, String name, List<User> users, Date createdDate, User createdBy, Date modifiedDate,
					User modifiedBy) {
				super();
				this.id = id;
				this.name = name;
				this.users = users;
				this.createdDate = createdDate;
				this.createdBy = createdBy;
				this.modifiedDate = modifiedDate;
				this.modifiedBy = modifiedBy;
			}
			public Role(String name, List<User> users, Date createdDate, User createdBy, Date modifiedDate,
					User modifiedBy) {
				super();
				this.name = name;
				this.users = users;
				this.createdDate = createdDate;
				this.createdBy = createdBy;
				this.modifiedDate = modifiedDate;
				this.modifiedBy = modifiedBy;
			}
			public Role() {
				super();
			}
			public long getId() {
				return id;
			}
			public void setId(long id) {
				this.id = id;
			}
			public String getName() {
				return name;
			}
			public void setName(String name) {
				this.name = name;
			}
			public List<User> getUsers() {
				return users;
			}
			public void setUsers(List<User> users) {
				this.users = users;
			}
			public Date getCreatedDate() {
				return createdDate;
			}
			public void setCreatedDate(Date createdDate) {
				this.createdDate = createdDate;
			}
			public User getCreatedBy() {
				return createdBy;
			}
			public void setCreatedBy(User createdBy) {
				this.createdBy = createdBy;
			}
			public Date getModifiedDate() {
				return modifiedDate;
			}
			public void setModifiedDate(Date modifiedDate) {
				this.modifiedDate = modifiedDate;
			}
			public User getModifiedBy() {
				return modifiedBy;
			}
			public void setModifiedBy(User modifiedBy) {
				this.modifiedBy = modifiedBy;
			}
			@Override
			public String toString() {
				return getName()+", ";
			}
			
			
			@Override
			public String getAuthority() {
				
				return name;
			}
			
			
			
			
}
