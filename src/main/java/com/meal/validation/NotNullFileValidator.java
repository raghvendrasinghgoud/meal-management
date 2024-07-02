package com.meal.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.web.multipart.MultipartFile;

public class NotNullFileValidator implements ConstraintValidator<NotNullFile, MultipartFile> {
	

	@Override
	public boolean isValid(MultipartFile value, ConstraintValidatorContext context) {
		
		if(value.getSize()<1) return false;
		else return true;
	}

}
