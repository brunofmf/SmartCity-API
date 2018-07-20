package model.traffic;

public abstract class AbstractTrafficIncident {

	//Class Variables
	protected double trafficLat;
	protected double trafficLon;
	protected long date;
    protected String cityName;
	
	//Constructor
	public AbstractTrafficIncident(double trafficLat, double trafficLon, long date, String cityName) {
		this.trafficLat = trafficLat;
		this.trafficLon = trafficLon;
		this.date = date;
		this.cityName = cityName;
	}
	
	public double getTrafficLat() {
		return trafficLat;
	}

	public double getTrafficLon() {
		return trafficLon;
	}
	
	public long getDate() {
		return date;
	}
    
    public String getCityName() {
        return cityName;
    }

	public void setTrafficLat(double trafficLat) {
		this.trafficLat = trafficLat;
	}

	public void setTrafficLon(double trafficLon) {
		this.trafficLon = trafficLon;
	}
	
	public void setDate(long date) {
		this.date = date;
	}
	
	public void setCityName(String cityName) {
	    this.cityName = cityName;
	}

	public abstract String toString();
}
