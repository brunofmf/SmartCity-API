package model.weather;

public abstract class AbstractWeather {
	
	//Class Variables
	protected String weatherDescription;
	protected int temperature;
	protected int pressure;
	protected int humidity;	
	protected int windSpeed;
	protected int clouds;
	protected int rain;
	protected long calculationDate;
	protected String cityName;
    protected int citySeqNum;
	
	public AbstractWeather(String weatherDescription, int temperature, int pressure, int humidity, int windSpeed,
			int clouds, int rain, long calculationDate, String cityName) {
		this.weatherDescription = weatherDescription;
		this.temperature = temperature;
		this.pressure = pressure;
		this.humidity = humidity;
		this.windSpeed = windSpeed;
		this.clouds = clouds;
		this.rain = rain;
		this.calculationDate = calculationDate;
		this.cityName = cityName;
		this.citySeqNum = 1;
	}
	
	public String getWeatherDescription() {
		return weatherDescription;
	}

	public int getTemperature() {
		return temperature;
	}
	
	public int getPressure() {
		return pressure;
	}
	
	public int getHumidity() {
		return humidity;
	}
	
	public int getWindSpeed() {
		return windSpeed;
	}
	
	public int getClouds() {
		return clouds;
	}
	
	public int getRain() {
		return rain;
	}
	
	public long getCalculationDate() {
		return calculationDate;
	}
	
	public String getCityName() {
		return cityName;
	}
    
    public int getCitySeqNum() {
        return citySeqNum;
    }
	
	public void setWeatherDescription(String weatherDescription) {
		this.weatherDescription = weatherDescription;
	}
	
	public void setTemperature(int temperature) {
		this.temperature = temperature;
	}
	
	public void setPressure(int pressure) {
		this.pressure = pressure;
	}
	
	public void setHumidity(int humidity) {
		this.humidity = humidity;
	}
	
	public void setWindSpeed(int windSpeed) {
		this.windSpeed = windSpeed;
	}
	
	public void setClouds(int clouds) {
		this.clouds = clouds;
	}
	
	public void setRain(int rain) {
		this.rain = rain;
	}
	
	public void setCalculationDate(long calculationDate) {
		this.calculationDate = calculationDate;
	}
	
	public void setCityName(String cityName) {
		this.cityName = cityName;
	}
    
    public void setCitySeqNum(int citySeqNum) {
        this.citySeqNum = citySeqNum;
    }
		
	public abstract String toString();
}
