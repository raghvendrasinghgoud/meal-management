package com.meal.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.meal.dao.MealDao;
import com.meal.model.Meal;

@Service
public class MealService {

	@Autowired
	MealDao mealDao;
	
	public void saveMeal(Meal meal) {
		mealDao.save(meal);
	}
	
	public void updateMeal(Meal meal) {
		mealDao.update(meal);
	}
	
	public Meal getMeal(long id) {
		return mealDao.get(id);
	}
	
	public List<Meal> getAllMeals(){
		return mealDao.getAll();	
	}
	public void deleteMeal(Meal meal) {
		mealDao.delete(meal);
	}
}
