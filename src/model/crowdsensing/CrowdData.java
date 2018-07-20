package model.crowdsensing;

public class CrowdData {
    
    //Class Variables
    protected String type;
    protected String deviceId;
    protected int rssi;
    protected String mac;
    protected long previousMillisDetected;
    protected long dataCreated;
    protected double latitude;
    protected double longitude;
    
    //Constructor
    public CrowdData(String type, String deviceId, int rssi, String mac, long previousMillisDetected, long dataCreated, double latitude, double longitude) {
        this.type = type;
        this.deviceId = deviceId;
        this.rssi = rssi;
        this.mac = mac;
        this.previousMillisDetected = previousMillisDetected;
        this.dataCreated = dataCreated;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public String getType() {
        return type;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public int getRssi() {
        return rssi;
    }

    public String getMac() {
        return mac;
    }

    public long getPreviousMillisDetected() {
        return previousMillisDetected;
    }

    public long getDateCreated() {
        return dataCreated;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public void setRssi(int rssi) {
        this.rssi = rssi;
    }

    public void setMac(String mac) {
        this.mac = mac;
    }

    public void setPreviousMillisDetected(long previousMillisDetected) {
        this.previousMillisDetected = previousMillisDetected;
    }

    public void setDateCreated(long dataCreated) {
        this.dataCreated = dataCreated;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public String toString() {      
        StringBuilder sb = new StringBuilder();
        sb.append("********  Crowd Sensing ********").append(System.getProperty("line.separator"));
        sb.append("Type: ").append(this.type).append(System.getProperty("line.separator"));
        sb.append("Device Identifier: ").append(this.deviceId).append(System.getProperty("line.separator"));
        sb.append("RSSI: ").append(this.rssi).append(System.getProperty("line.separator"));  
        sb.append("MAC Address: ").append(this.mac).append(System.getProperty("line.separator"));  
        sb.append("Previous Millis Timestamp: ").append(this.previousMillisDetected).append(System.getProperty("line.separator")); 
        sb.append("Data Created Timestamp: ").append(this.dataCreated).append(System.getProperty("line.separator")); 
        sb.append("Latitude: ").append(this.latitude).append(System.getProperty("line.separator")); 
        sb.append("Longitude: ").append(this.longitude).append(System.getProperty("line.separator"));          
        return sb.toString();
    }
    
}
