package com.meal.validation;

import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.meal.model.ChangePassword;

public class PasswordValidation implements Validator {

	
	
	@Override
	public boolean supports(Class<?> clazz) {
		
		return ChangePassword.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		ChangePassword changePassword=(ChangePassword)target;
		if(!changePassword.getPassword().equals(changePassword.getConfirmPassword())) {	

			errors.rejectValue("confirmPassword", "password.not.match", "password not match");
		
		}
		
	}

}
