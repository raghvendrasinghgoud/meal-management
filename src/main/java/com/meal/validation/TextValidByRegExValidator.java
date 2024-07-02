package com.meal.validation;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class TextValidByRegExValidator implements ConstraintValidator<TextValidByRegEx, String> {

	private String regEx;
	
	@Override
	public void initialize(TextValidByRegEx constraintAnnotation) {
		regEx=constraintAnnotation.regEx();
	}

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		Pattern p=Pattern.compile(this.regEx);
		Matcher m=p.matcher(value);
		System.out.println("validation text is "+m.matches());
		return m.matches();
	}

}
