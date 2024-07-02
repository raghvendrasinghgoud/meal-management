package com.meal.dao;

import java.util.List;

import com.meal.model.Address;
import com.meal.model.ConsumedMeal;

public interface ConsumedMealDao {


	public void save(ConsumedMeal consumedMeal);
	public ConsumedMeal get(long id);
	public List<ConsumedMeal> getAll();
	public void update(ConsumedMeal consumedMeal);
	public void delete(ConsumedMeal consumedMeal);
}
