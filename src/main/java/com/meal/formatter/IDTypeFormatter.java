package com.meal.formatter;

import java.text.ParseException;
import java.util.Locale;

import org.springframework.format.Formatter;

import com.meal.enums.IDType;

public class IDTypeFormatter implements Formatter<IDType> {

	@Override
	public String print(IDType object, Locale locale) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public IDType parse(String text, Locale locale) throws ParseException {
		if(text.equals("AADHAR_NO")) return IDType.ADHAR_NO;
		else if(text.equals("VOTER_ID")) return IDType.VOTER_ID;
		else if(text.equals("DRIVING_LIECENCE")) return IDType.DRIVING_LIECENCE;
		else if(text.equals("PAN_NO")) return IDType.PAN_NO;
		
		return null;
	}

}
