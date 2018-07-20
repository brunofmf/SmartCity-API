package util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.TimeZone;

import controller.clients.OwmClient;
import model.weather.owm.Weather;

public class Functions {
	
	private final static long MILLISECONDS_LUMINOSITY_MARGIN = 1800000; //milliseconds of margin for brightness by the morning and evening - default 30 min
	
	public enum DayLuminosity {
	    DARK,
	    LOW_LIGHT,
	    LIGHT,
	    NON_AVAILABLE;
	}
	
	/**
	 * Function to model luminosity along the day 
	 * @throws Exception 
	 * */	
	public static DayLuminosity getCurrentLuminosity(Weather currWeather) {		
		long currTime = System.currentTimeMillis();
		if(currWeather != null) {
			if(currTime < currWeather.getSunrise() || currTime > currWeather.getSunset())
				return DayLuminosity.DARK;
			else if(currTime-currWeather.getSunrise() < MILLISECONDS_LUMINOSITY_MARGIN || currTime-currWeather.getSunset() > -MILLISECONDS_LUMINOSITY_MARGIN)
				return DayLuminosity.LOW_LIGHT;
			else if(currWeather.getRain() > 0 && currWeather.getClouds() > 0)
				return DayLuminosity.LOW_LIGHT;
			else
				return DayLuminosity.LIGHT;
		} else
			return DayLuminosity.NON_AVAILABLE;
	}
	
	/**
	 * Function to create a Date from an ISO8601UTC date
	 * */		
	public static long fromISO8601UTC(String dateStr) {
		TimeZone tz = TimeZone.getTimeZone("UTC");
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
		df.setTimeZone(tz);
  
		try {
			return df.parse(dateStr).getTime()/1000;
		} catch (ParseException e) {
			e.printStackTrace();
		}
  
		return 0;
	}
	
}
