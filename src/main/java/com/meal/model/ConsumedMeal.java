package com.meal.model;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Entity
public class ConsumedMeal extends Auditable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	@ManyToOne(cascade = CascadeType.PERSIST)
	private Meal meal;
	@ManyToOne(cascade = CascadeType.PERSIST)
	private MealAccount mealAccount;
	
	
			public long getId() {
				return id;
			}
			public void setId(long id) {
				this.id = id;
			}
			public Meal getMeal() {
				return meal;
			}
			public void setMeal(Meal meal) {
				this.meal = meal;
			}
			public MealAccount getMealAccount() {
				return mealAccount;
			}
			public void setMealAccount(MealAccount mealAccount) {
				this.mealAccount = mealAccount;
			}
			
			public void setModifiedBy(User modifiedBy) {
				this.modifiedBy = modifiedBy;
			}
			@Override
			public String toString() {
				return "ConsumedMeal [id=" + id + ",  mealAccount=" + mealAccount + ", createdDate="
						+ createdDate + ", createdBy=" + createdBy + ", modifiedDate=" + modifiedDate + ", modifiedBy="
						+ modifiedBy + "]";
			}
			
			
}
