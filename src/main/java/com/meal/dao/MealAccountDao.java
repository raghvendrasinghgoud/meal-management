package com.meal.dao;

import java.util.List;

import com.meal.model.MealAccount;

public interface MealAccountDao {

	public void save(MealAccount mealAccount);
	public MealAccount get(long id);
	public List<MealAccount> getAll();
	public void update(MealAccount mealAccount);
	public void delete(MealAccount mealAccount);
}
