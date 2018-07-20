package model.weather.owm;

import java.util.Date;

import model.weather.AbstractWeather;
import util.Functions;

public class Weather extends AbstractWeather{

	//Class Variables
	protected long sunrise;
	protected long sunset;
	protected String luminosityDegree;
	
	//Constructor
	public Weather(String weatherDescription, int temperature, int pressure, int humidity, int windSpeed,
			int clouds, int rain, long calculationDate, String cityName, long sunrise, long sunset) {
		super(weatherDescription, temperature, pressure, humidity, windSpeed, clouds, rain, calculationDate*1000, cityName);
		//Instead of time since epoch linux set milliseconds - thus avoiding Date.from(Instant.ofEpochSecond(...))
		this.sunrise = sunrise*1000;
		this.sunset = sunset*1000;
		this.luminosityDegree = Functions.getCurrentLuminosity(this).toString();
	}
	
	public long getSunrise() {
		return sunrise;
	}
	
	public long getSunset() {
		return sunset;
	}	
    
    public String getLuminosityDegree() {
        return luminosityDegree;
    }   
	
	public void setSunrise(long sunrise) {
		this.sunrise = sunrise;
	}
	
	public void setSunset(long sunset) {
		this.sunset = sunset;
	}
    
    public void setLuminosityDegree(String luminosityDegree) {
        this.luminosityDegree = luminosityDegree;
    }
	
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("********  Current Weather ********").append(System.getProperty("line.separator"));
		sb.append("Weather Description: ").append(this.weatherDescription).append(System.getProperty("line.separator"));
		sb.append("Temperature: ").append(this.temperature).append("ºC").append(System.getProperty("line.separator"));	
		sb.append("Atmospheric Pressure: ").append(this.pressure).append(" hPa").append(System.getProperty("line.separator"));	
		sb.append("Humidity: ").append(this.humidity).append("%").append(System.getProperty("line.separator"));	
		sb.append("Wind Speed: ").append(this.windSpeed).append(" meter/sec").append(System.getProperty("line.separator"));	
		sb.append("Cloudiness: ").append(this.clouds).append("%").append(System.getProperty("line.separator"));	
		sb.append("Rain volume: ").append(this.rain).append(System.getProperty("line.separator"));	
		sb.append("Data date: ").append(new Date(this.calculationDate)).append(System.getProperty("line.separator"));	
		sb.append("City: ").append(this.cityName).append(System.getProperty("line.separator"));	
		sb.append("Sunrise: ").append(new Date(this.sunrise)).append(System.getProperty("line.separator"));	
		sb.append("Sunset: ").append(new Date(this.sunset)).append(System.getProperty("line.separator"));		
		return sb.toString();
	}
	
}
