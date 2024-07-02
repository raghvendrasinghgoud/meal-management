package com.meal.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.meal.dao.MealAccountDao;
import com.meal.exception.InsufficientMealAccountBalance;
import com.meal.model.ConsumedMeal;
import com.meal.model.MealAccount;
import com.meal.model.Payment;

@Service
public class MealAccountService {

	@Autowired
	MealAccountDao mealAccountDao;

	
	public void updateCurrentBalance(MealAccount mealAccount,Payment payment) {
		
		System.out.println("meal acc serv "+mealAccount);
		double depositeCurrentBalance=depositeCurrentBalance(mealAccount.getCurrentBalance(), payment.getAmount());
		
		//update balance
		mealAccount.setCurrentBalance(depositeCurrentBalance);
		
		//saving meal account to db
		mealAccountDao.update(mealAccount);
	}
	
	public void updateConsumedBalance(MealAccount mealAccount,ConsumedMeal consumedMeal)throws InsufficientMealAccountBalance {
		
		double depositeConsumedBalance=depositeConsumedBalance(mealAccount.getConsumedBalance(), consumedMeal.getMeal().getPrice());

		double withdrawCurrentBalance=withdrawCurrentBalance(mealAccount.getCurrentBalance(), consumedMeal.getMeal().getPrice());
		//update balance
		mealAccount.setConsumedBalance(depositeConsumedBalance);
		mealAccount.setCurrentBalance(withdrawCurrentBalance);
		//saving meal account
		mealAccountDao.update(mealAccount);
	}
	/*
	 * reverse balance will withdraw the amount from consumed balance and deposite amount to current balance 
	 * */
	public void reverseBalance(MealAccount mealAccount,ConsumedMeal consumedMeal) throws InsufficientMealAccountBalance {
		
		double reversedCurrentBalance=depositeCurrentBalance(mealAccount.getCurrentBalance(),consumedMeal.getMeal().getPrice());
		double reverseConsumedBalance=withdrawConsumedBalance(mealAccount.getConsumedBalance(), consumedMeal.getMeal().getPrice());
		
		//updating balance
		mealAccount.setCurrentBalance(reversedCurrentBalance);
		mealAccount.setConsumedBalance(reverseConsumedBalance);
		
		//saving meal account
		mealAccountDao.update(mealAccount);
	}
	
	public double depositeCurrentBalance(double oldCurrentBalance,double depositeAmount) {
		return oldCurrentBalance+depositeAmount;
	}
	
	public double depositeConsumedBalance(double oldConsumedBalance,double depositeAmount) {
		return oldConsumedBalance+depositeAmount;
	}
	
	public double withdrawCurrentBalance(double oldCurrentBalance,double withdrawAmount)throws InsufficientMealAccountBalance {
		if(oldCurrentBalance<withdrawAmount) {
			throw new InsufficientMealAccountBalance("meal Account current balance is Insufficient");
		}
		return oldCurrentBalance-withdrawAmount;
	}
	
	public double withdrawConsumedBalance(double oldConsumedBalance,double withdrawAmount) throws InsufficientMealAccountBalance {
		if(oldConsumedBalance<withdrawAmount) {
			throw new InsufficientMealAccountBalance("meal Account consumed balance is Insufficient");
		}
		return oldConsumedBalance-withdrawAmount;
	}
	
	public MealAccount getMealAccount(long id) {
		return mealAccountDao.get(id);
	}
	
	
	
}
