package com.meal.formatter;

import java.text.ParseException;
import java.util.Locale;

import org.springframework.format.Formatter;

import com.meal.enums.Gender;

public class GenderFormatter implements Formatter<Gender> {

	@Override
	public String print(Gender object, Locale locale) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Gender parse(String text, Locale locale) throws ParseException {
		if(text.equals("male")) return Gender.Male;
		else if(text.equals("female")) return Gender.Female;
		
		return null;
	}

}
