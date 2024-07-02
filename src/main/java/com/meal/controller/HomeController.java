package com.meal.controller;

import java.security.Principal;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {
	
	Logger log=LogManager.getLogger(HomeController.class);
	
	@RequestMapping({"/home","/"})
	public String home(Principal principal) {
		log.debug("user info "+principal);
		
		return "index";
		
	}
	
	@RequestMapping("/accessDenied")
	public String accessDenied() {
		return "error";
	}
}
