package com.meal.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.meal.dao.ConsumedMealDao;
import com.meal.exception.InsufficientMealAccountBalance;
import com.meal.model.ConsumedMeal;

@Service
public class ConsumedMealService {

	@Autowired
	ConsumedMealDao consumedMealDao;
	@Autowired
	MealAccountService mealAccountService;
	
	public List<ConsumedMeal> getAllConsumedMeals(){
		return consumedMealDao.getAll();
	}
	
	@Transactional(rollbackOn = InsufficientMealAccountBalance.class)
	public void save(ConsumedMeal consumedMeal)throws InsufficientMealAccountBalance {
	
		consumedMealDao.save(consumedMeal);
		//updating account balance
		mealAccountService.updateConsumedBalance(consumedMeal.getMealAccount(), consumedMeal);	
	
	}


	public ConsumedMeal getConsumedMeal(long id) {
		return consumedMealDao.get(id);
	}
	
	@Transactional(rollbackOn = InsufficientMealAccountBalance.class)
	public void deleteConsumedMeal(ConsumedMeal consumedMeal) throws InsufficientMealAccountBalance {
		
		mealAccountService.reverseBalance(consumedMeal.getMealAccount(), consumedMeal);
		
		consumedMealDao.delete(consumedMeal);
	}
}
