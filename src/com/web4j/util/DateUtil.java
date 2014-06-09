package com.web4j.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {

	private final static String DATE_FOMAT="yyyy-MM-dd HH:mm:ss";
	
	public static String formatDateToStr(Date time){
		SimpleDateFormat sd=new SimpleDateFormat(DATE_FOMAT);
		return sd.format(time);
	}
}
