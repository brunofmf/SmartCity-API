package model.weather.owm;

import java.util.Date;

import model.weather.AbstractWeather;

public class WeatherForecast extends AbstractWeather{

	//Class Variables
	protected String forecastDateText;
	
	//Constructor
	public WeatherForecast(String weatherDescription, int temperature, int pressure, int humidity,
			int windSpeed, int clouds, int rain, String forecastDateText, String cityName, long forecastDate) {
		super(weatherDescription, temperature, pressure, humidity, windSpeed, clouds, rain, forecastDate*1000, cityName);
		this.forecastDateText = forecastDateText;
	}
	
	public String getForecastDate() {
		return forecastDateText;
	}
	
	public void setForecastDate(String forecastDateText) {
		this.forecastDateText = forecastDateText;
	}
	
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("******** Forecast Weather ********").append(System.getProperty("line.separator"));
		sb.append("Weather Description: ").append(this.weatherDescription).append(System.getProperty("line.separator"));
		sb.append("Temperature: ").append(this.temperature).append("ºC").append(System.getProperty("line.separator"));	
		sb.append("Atmospheric Pressure: ").append(this.pressure).append(" hPa").append(System.getProperty("line.separator"));	
		sb.append("Humidity: ").append(this.humidity).append("%").append(System.getProperty("line.separator"));	
		sb.append("Wind Speed: ").append(this.windSpeed).append(" meter/sec").append(System.getProperty("line.separator"));	
		sb.append("Cloudiness: ").append(this.clouds).append("%").append(System.getProperty("line.separator"));	
		sb.append("Rain volume: ").append(this.rain).append(System.getProperty("line.separator"));	
		sb.append("City: ").append(this.cityName).append(System.getProperty("line.separator"));		
		sb.append("Forecast Date Text: ").append(this.forecastDateText).append(System.getProperty("line.separator"));	
		sb.append("Forecast Date: ").append(new Date(this.calculationDate)).append(System.getProperty("line.separator"));
		return sb.toString();
	}
	
}
