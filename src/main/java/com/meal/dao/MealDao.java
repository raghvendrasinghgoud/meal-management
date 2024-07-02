package com.meal.dao;

import java.util.List;

import com.meal.model.Meal;

public interface MealDao {

	public void save(Meal meal);
	public Meal get(long id);
	public List<Meal> getAll();
	public void update(Meal meal);
	public void delete(Meal meal);

}
