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
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotBlank;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Entity
public class Meal extends Auditable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	@NotBlank
	private String name;
	@DecimalMin(value = "1")
	private double price;
	@NotBlank
	private String description;
	@OneToMany(mappedBy = "meal",cascade = CascadeType.PERSIST)
	private List<ConsumedMeal> consumedMeals;
	
		
		public Meal() {
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
		public List<ConsumedMeal> getConsumedMeals() {
			return consumedMeals;
		}
		public void setConsumedMeals(List<ConsumedMeal> consumedMeals) {
			this.consumedMeals = consumedMeals;
		}
		public double getPrice() {
			return price;
		}
		public void setPrice(double price) {
			this.price = price;
		}
		public String getDescription() {
			return description;
		}
		public void setDescription(String description) {
			this.description = description;
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
			return "Meal [id=" + id + ", Name=" + name + ", price=" + price + ", description=" + description
					+ ", createdDate=" + super.createdDate + ", createdBy=" + super.createdBy + ", modifiedDate=" + super.modifiedDate
					+ ", modifiedBy=" + super.modifiedBy + "]";
		}
		
		
}
