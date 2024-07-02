package com.meal.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.meal.service.MealAccountService;

@Controller
@RequestMapping("mealAccount")
public class MealAccountController {

	@Autowired
	MealAccountService mealAccountService;
	
}
