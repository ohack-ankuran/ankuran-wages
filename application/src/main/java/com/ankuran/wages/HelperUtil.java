package com.ankuran.wages;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.Calendar;
import java.util.Date;

import org.apache.commons.lang3.tuple.Pair;

public class HelperUtil {
	
	public static final int DEFAULT_TIME_RANGE_IN_DAYS = -30;

	public static Pair<Date, Date> getTimeRange(String lowerTimeCreated, String upperTimeCreated) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		if(lowerTimeCreated == null && upperTimeCreated == null) {
			Date upper = Date.from(Instant.now());
			Date lower = datefrom(upper, DEFAULT_TIME_RANGE_IN_DAYS);
			return Pair.of(lower, upper);
		} else if(lowerTimeCreated == null) {
			Date upper = sdf.parse(upperTimeCreated);
			Date lower = datefrom(upper, DEFAULT_TIME_RANGE_IN_DAYS);
			return Pair.of(lower, upper);
		} else if(upperTimeCreated == null) {
			Date lower = sdf.parse(lowerTimeCreated);
			Date upper = Date.from(Instant.now());
			return Pair.of(lower, upper);
		} else {
			return Pair.of(sdf.parse(lowerTimeCreated), sdf.parse(upperTimeCreated));
		}
	}
	
	public static Date datefrom(Date upper, int days) {
        Date myDate = Date.from(Instant.now());
        Calendar cal = Calendar.getInstance();
        cal.setTime(myDate);
        cal.add(Calendar.DATE, days);
        return cal.getTime();
	}
}
