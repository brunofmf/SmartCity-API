package model.pollution;

public abstract class AbstractPollution {

	//Class Variables
	protected double lat;
	protected double lon;
	protected long date;
	protected String unit;
	protected String city;
    protected int citySeqNum;
	
	//Constructor
	public AbstractPollution(double lat, double lon, long date, String unit) {
		this.lat = lat;
		this.lon = lon;
		this.date = date;
		this.unit = unit;
		this.city = "";
        this.citySeqNum = 1;
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

    public String getCity() {
        return city;
    }
    
    public int getCitySeqNum() {
        return citySeqNum;
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

    public void setCity(String city) {
        this.city = city;
    }
    
    public void setCitySeqNum(int citySeqNum) {
        this.citySeqNum = citySeqNum;
    }
    
	public abstract String toString();
	
}
