package com.acme.challenge;

import java.util.Calendar;
import java.util.Date;

public class Helper {

	public static Date max(Date date1, Date date2) {
		if (date1.getTime() > date2.getTime()) {
			return date1;
		} else {
			return date2;
		}
	}
	
	public static Date addDate(Date date, double seconds) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.MILLISECOND, (int) (seconds * 1000));
		return calendar.getTime();
	}

}
