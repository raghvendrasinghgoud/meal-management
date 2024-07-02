package com.meal.model;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Entity
public class MealAccount extends Auditable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long accNo;
	private double currentBalance;
	private double consumedBalance;
	@OneToMany(mappedBy = "mealAccount",cascade = CascadeType.PERSIST,fetch = FetchType.EAGER)
	private List<ConsumedMeal> consumedMeals;

	@OneToOne(mappedBy = "mealAccount",cascade = CascadeType.PERSIST,fetch = FetchType.EAGER)
	private User user;
	

			public MealAccount() {
				
				
				this.currentBalance = 0;
				this.consumedBalance = 0;		
				
			}
			
			
			public long getAccNo() {
				return accNo;
			}
			public void setAccNo(long accNo) {
				this.accNo = accNo;
			}
			public double getCurrentBalance() {
				return currentBalance;
			}
			public void setCurrentBalance(double currentBalance) {
				this.currentBalance = currentBalance;
			}
			public double getConsumedBalance() {
				return consumedBalance;
			}
			public void setConsumedBalance(double consumedBalance) {
				this.consumedBalance = consumedBalance;
			}
			public User getUser() {
				return user;
			}
			public void setUser(User user) {
				this.user = user;
			}
			


			public List<ConsumedMeal> getConsumedMeals() {
				return consumedMeals;
			}


			public void setConsumedMeals(List<ConsumedMeal> consumedMeals) {
				this.consumedMeals = consumedMeals;
			}


			@Override
			public String toString() {
				return "MealAccount [accNo=" + accNo + ", currentBalance=" + currentBalance + ", consumedBalance="
						+ consumedBalance + ", createdDate="
						+ createdDate + ", modifiedDate=" + modifiedDate + ", modifiedBy="
						+ "]";
			}
			
			
			
			
			
}
