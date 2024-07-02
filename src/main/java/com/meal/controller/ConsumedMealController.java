package com.meal.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.meal.converter.StringToMealConverter;
import com.meal.exception.InsufficientMealAccountBalance;
import com.meal.model.ConsumedMeal;
import com.meal.model.MealAccount;
import com.meal.model.User;
import com.meal.service.ConsumedMealService;
import com.meal.service.MealAccountService;
import com.meal.service.MealService;
import com.meal.service.UserServiceImpl;

@Controller
@RequestMapping("/consumedMeal")
public class ConsumedMealController {

	@Autowired
	ConsumedMealService consumedMealService;
	@Autowired
	MealService mealService;
	@Autowired
	UserServiceImpl userServiceImpl;
	@Autowired
	MealAccountService mealAccountService;
	
	@RequestMapping("/consumedMeals/{username}")
	@PreAuthorize("hasAnyAuthority('admin', 'manager') or #username==authentication.principal.username")
	public String showConsumedMeals(@PathVariable("username") String username,@ModelAttribute ConsumedMeal consumedMeal, Model model) {
		
		User user=(User)userServiceImpl.loadUserByUsername(username);
		consumedMeal.setMealAccount(user.getMealAccount());
		model.addAttribute("meals",mealService.getAllMeals());
		model.addAttribute("consumedMeals",user.getMealAccount().getConsumedMeals() );
		return "consumed-meal-overview";
	}
	
	@RequestMapping("/save")
	public String saveConsumedMeal(@ModelAttribute("consumedMeal") ConsumedMeal consumedMeal) {

		String userId="";
		try {
		MealAccount mealAccount=mealAccountService.getMealAccount(consumedMeal.getMealAccount().getAccNo());
		consumedMeal.setMealAccount(mealAccount);
		System.out.println("saving consumed Meal "+consumedMeal);
		userId+=mealAccount.getUser().getId();
			consumedMealService.save(consumedMeal);
		} catch (InsufficientMealAccountBalance e) {
			
			e.printStackTrace();
			
		}catch(Exception e) {
			
			e.printStackTrace();
			
		}
		
		return "redirect:/consumedMeal/consumedMeals/"+userId;
	}
	
	@RequestMapping("/delete/{id}")
	public String deleteConsumedMeal(@PathVariable("id") String id) {
		
		ConsumedMeal consumedMeal=consumedMealService.getConsumedMeal(Long.parseLong(id));
		
		try {
			consumedMealService.deleteConsumedMeal(consumedMeal);
		} catch (InsufficientMealAccountBalance e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return "redirect:/consumedMeal/consumedMeals/"+consumedMeal.getMealAccount().getUser().getId();
	}
	
	@InitBinder
	public void binder(WebDataBinder binder) {
		binder.addCustomFormatter(new StringToMealConverter(mealService));
	}
}
