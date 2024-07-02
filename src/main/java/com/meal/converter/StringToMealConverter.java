package com.meal.converter;

import java.text.ParseException;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.Formatter;

import com.meal.model.Meal;
import com.meal.service.MealService;

public class StringToMealConverter implements Formatter<Meal> {

	@Autowired
	MealService mealService;

	
public StringToMealConverter(MealService mealService) {
		
		this.mealService = mealService;
	}
	
	@Override
	public String print(Meal object, Locale locale) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Meal parse(String text, Locale locale) throws ParseException {

		return mealService.getMeal(Long.parseLong(text));
	}



	

}
