package model.pollution;

public abstract class AbstractPollution {

	//Class Variables
	protected double lat;
	protected double lon;
	protected long date;
	protected String unit;
	
	//Constructor
	public AbstractPollution(double lat, double lon, long date, String unit) {
		this.lat = lat;
		this.lon = lon;
		this.date = date;
		this.unit = unit;
	}
	
	public double getLat() {
		return lat;
	}
	
	public double getLon() {
		return lon;
	}
	
	public long getDate() {
		return date;
	}
	
	public String getUnit() {
		return unit;
	}
	
	public void setLat(double lat) {
		this.lat = lat;
	}
	
	public void setLon(double lon) {
		this.lon = lon;
	}
	
	public void setDate(long date) {
		this.date = date;
	}
	
	public void setUnit(String unit) {
		this.unit = unit;
	}
	
	public abstract String toString();
	
}
