package com.meal.formatter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import org.springframework.format.Formatter;

public class DateFormatter implements Formatter<Date> {

	@Override
	public String print(Date object, Locale locale) {
		SimpleDateFormat formatter = new SimpleDateFormat("dd-mm-yyyy");  
	    String strDate= formatter.format(object);  
	    System.out.println("print date "+strDate);
	    return strDate;
	}

	@Override
	public Date parse(String text, Locale locale) throws ParseException {
		
		
		String[] d=text.split("-");
		int date=Integer.parseInt(d[2]);
		int month=Integer.parseInt(d[1]);
		int year=Integer.parseInt(d[0]);
		System.out.println("inside parse of date formatter "+text+" "+year+" "+month+" "+date);
		Date d1=new Date(year-1900, month-1, date); 
		return d1 ;
	}

}
