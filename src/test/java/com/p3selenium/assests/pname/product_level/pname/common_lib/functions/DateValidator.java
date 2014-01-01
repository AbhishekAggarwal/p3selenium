package com.p3selenium.assests.pname.product_level.pname.common_lib.functions;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
 
public class DateValidator {
 
	public boolean isThisDateValid(String dateToValidate, String dateFromat){
 
		if(dateToValidate == null){
			return false;
		}
 
		SimpleDateFormat sdf = new SimpleDateFormat(dateFromat);
		sdf.setLenient(false);
 
		try {
 
			//if not valid, it will throw ParseException
			Date date = sdf.parse(dateToValidate);
			System.out.println(date);
 
		} catch (ParseException e) {
 
			e.printStackTrace();
			return false;
		}
 
		return true;
	}
 
}