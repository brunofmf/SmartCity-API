package model.traffic;

public abstract class AbstractTrafficIncident {

	//Class Variables
	protected double trafficLat;
	protected double trafficLon;
	protected long date;
	
	//Constructor
	public AbstractTrafficIncident(double trafficLat, double trafficLon, long date) {
		this.trafficLat = trafficLat;
		this.trafficLon = trafficLon;
		this.date = date;
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

	public void setTrafficLat(double trafficLat) {
		this.trafficLat = trafficLat;
	}

	public void setTrafficLon(double trafficLon) {
		this.trafficLon = trafficLon;
	}
	
	public void setDate(long date) {
		this.date = date;
	}

	public abstract String toString();
}
