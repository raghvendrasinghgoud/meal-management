package com.meal.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Controller
@ControllerAdvice
public class GlobalController {
	
	@ExceptionHandler(RuntimeException.class)
	public String exceptionOccur() {
		return "error";
	}
	
}
