package com.meal.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.meal.model.Meal;
import com.meal.model.Role;
import com.meal.service.MealService;

@Controller
@RequestMapping("/meal")
public class MealController {

	@Autowired
	MealService mealService;

	@RequestMapping("/meals")
	public String meals(@Valid @ModelAttribute Meal meal, BindingResult br, Model model) {
		
		BindingResult saveBr=(BindingResult)model.getAttribute("saveBr");
		
		//check if request coming from save controller
		if(saveBr!=null) {
			br.addAllErrors(saveBr);
		}

		System.out.println("update e " + model.getAttribute("hasError"));
//			System.out.println("role edxists = "+model.getAttribute("role"));
		if (model.getAttribute("hasError") == null) {
			model.addAttribute("hasError", false);
		}

		model.addAttribute("meals", mealService.getAllMeals());

		if (model.getAttribute("update") != null && (boolean) model.getAttribute("update")) {
			model.addAttribute("update", false);
		} else {
			if((boolean)model.getAttribute("hasError")==false)
				model.addAttribute("meal", new Meal());
		}

		return "meal-overview";
	}

	@RequestMapping("/save")
	public String saveOrUpdateMeal(@Valid @ModelAttribute("meal") Meal meal, BindingResult br, Model model,
			RedirectAttributes redirectAttributes) {

		redirectAttributes.addFlashAttribute("hasError", br.hasErrors());
		
		if (br.hasErrors()) {

			System.out.println(br.getAllErrors());
			
			redirectAttributes.addFlashAttribute("saveBr", br);
			redirectAttributes.addFlashAttribute(meal);
			
//			model.addAttribute("meals", mealService.getAllMeals());
//			return "meal-overview";

		} else {

			if (meal.getId() == 0)
				mealService.saveMeal(meal);

			else
				mealService.updateMeal(meal);
		}

		System.out.println(meal);

		return "redirect:/meal/meals";
	}

	@RequestMapping("/update/{id}")
	public String updateMeal(@PathVariable("id") String id, Model model, RedirectAttributes redirectAttributes) {

		Meal meal = mealService.getMeal(Long.parseLong(id));

		redirectAttributes.addFlashAttribute("meal", meal);

		// model.addAttribute("roles",roleService.getAllRoles());

		redirectAttributes.addFlashAttribute("update", true);

		// System.out.println("id= "+id+" ra "+model);

		return "redirect:/meal/meals";
	}

	@RequestMapping("/delete/{id}")
	public String deleteMeal(@PathVariable("id") String id, Model model) {

		// System.out.println("id= "+id);

		Meal meal = mealService.getMeal(Long.parseLong(id));

		mealService.deleteMeal(meal);

		return "redirect:/meal/meals";
	}
}
