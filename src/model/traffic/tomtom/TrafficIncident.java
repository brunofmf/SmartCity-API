package model.traffic.tomtom;

import java.util.Date;

import model.traffic.AbstractTrafficIncident;

/*
 * 
 * Traffic Incidents (https://developer.tomtom.com/online-traffic/online-traffic-documentation-online-traffic-incidents/traffic-incident-details):
 * https://api.tomtom.com/traffic/services/4/incidentDetails/s3/41.506531,-8.451247,41.574115,-8.371253/11/1526494296871/xml?key=<API_KEY>&projection=EPSG4326&language=en&expandCluster=true
 * 		
 * */

public class TrafficIncident extends AbstractTrafficIncident{

	//Incident Category Constants
	public static final int UNKNOWN 				= 0;
	public static final int ACCIDENT 				= 1;
	public static final int FOG 					= 2;
	public static final int DANGEROUS_CONDITIONS	= 3;
	public static final int RAIN 					= 4;
	public static final int ICE 					= 5;
	public static final int JAM 					= 6;
	public static final int LANE_CLOSED 			= 7;
	public static final int ROAD_CLOSED 			= 8;
	public static final int ROAD_WORKS 				= 9;
	public static final int WIND 					= 10;
	public static final int FLOODING				= 11;
	public static final int DETOUR 					= 12;
	public static final int CLUSTER 				= 13;
	
	//Magnitude of Delay Constants
	public static final int UNKNOWN_DELAY			= 0; //shown as grey on traffic tiles
	public static final int MINOR 					= 1; //shown as orange on traffic tiles
	public static final int MODERATE 				= 2; //shown as light red on traffic tiles
	public static final int MAJOR 					= 3; //shown as dark red on traffic tiles
	public static final int UNDEFINED 				= 4; //used for road closures and other indefinite delays - shown as grey on traffic tiles
	
	//Class Variables
	protected String trafficModelId;				//Current traffic model
	protected String id;							//ID of the traffic incident
	protected double iconLat;						//The point where an icon of the cluster or raw incident should be drawn
	protected double iconLon;						//The point where an icon of the cluster or raw incident should be drawn
	protected int incidentCategory;					//The category associated with this incident. Values are numbers in the range 0-13
	protected String incidentCategoryDesc;			//The category description associated with this incident
	protected int magnitudeOfDelay;					//The magnitude of delay associated with the incident
	protected String magnitudeOfDelayDesc;			//The magnitude of delay description associated with the incident
	protected int clusterSize;						//The number of incidents in the cluster
	protected String description;					//Description of the incident in the requested language
	protected String causeOfAccident;				//Cause of the incident, where available, in the requested language
	protected String from;							//The name of the intersection or location where the traffic due to the incident starts
	protected String to;							//The name of the intersection or location where the traffic due to the incident ends
	protected int length; 							//Length of the incident in meters
	protected int delayInSeconds;	 				//Delay caused by the incident in seconds (except in road closures)
	protected String affectedRoads; 				//The road number/s affected by the incident. Multiple road numbers will delimited by slashes.
	
	public TrafficIncident(double trafficLat, double trafficLon, long date, String trafficModelId, String id, double iconLat, double iconLon,
			int incidentCategory, int magnitudeOfDelay, int clusterSize, String description, String causeOfAccident,
			String from, String to, int length, int delayInSeconds, String affectedRoads) {
		super(trafficLat, trafficLon, date);
		this.trafficModelId = trafficModelId;
		this.id = id;
		this.iconLat = iconLat;
		this.iconLon = iconLon;
		this.incidentCategory = incidentCategory;		
		switch (this.incidentCategory) {
		    case CLUSTER:
				this.incidentCategoryDesc = "Cluster";
		    	break;
		    case DETOUR:
				this.incidentCategoryDesc = "Detour";
		    	break;
		    case FLOODING:
				this.incidentCategoryDesc = "Flooding";
		    	break;
		    case WIND:
				this.incidentCategoryDesc = "Wind";
		    	break;
		    case ROAD_WORKS:
				this.incidentCategoryDesc = "Road Works";
		    	break;
		    case ROAD_CLOSED:
				this.incidentCategoryDesc = "Road Closed";
		    	break;
		    case LANE_CLOSED:
				this.incidentCategoryDesc = "Lane Closed";
		    	break;
		    case JAM:
				this.incidentCategoryDesc = "Jam";
		    	break;
		    case ICE:
				this.incidentCategoryDesc = "Ice";
		    	break;
		    case RAIN:
				this.incidentCategoryDesc = "Rain";
		    	break;
		    case DANGEROUS_CONDITIONS:
				this.incidentCategoryDesc = "Dangerous Conditions";
		    	break;
		    case FOG:
				this.incidentCategoryDesc = "Fog";
		    	break;
		    case ACCIDENT:
				this.incidentCategoryDesc = "Accident";
		    	break;
		    case UNKNOWN:
				this.incidentCategoryDesc = "Unknown Incident";
		    	break;
		    default:
				this.incidentCategoryDesc = "Unknown Incident";
		    ;
		}
		this.magnitudeOfDelay = magnitudeOfDelay;
		switch (this.magnitudeOfDelay) {
		    case UNDEFINED:
				this.magnitudeOfDelayDesc = "Undefined";
		    	break;
		    case MAJOR:
				this.magnitudeOfDelayDesc = "Major";
		    	break;
		    case MODERATE:
				this.magnitudeOfDelayDesc = "Moderate";
		    	break;
		    case MINOR:
				this.magnitudeOfDelayDesc = "Minor";
		    	break;
		    case UNKNOWN_DELAY:
				this.magnitudeOfDelayDesc = "Unknown Delay";
		    	break;
		    default:
				this.magnitudeOfDelayDesc = "Unknown Delay";
		    ;
		}
		this.clusterSize = clusterSize;
		this.description = description;
		this.causeOfAccident = causeOfAccident;
		this.from = from;
		this.to = to;
		this.length = length;
		this.delayInSeconds = delayInSeconds;
		this.affectedRoads = affectedRoads;
	}

	public String getTrafficModelId() {
		return trafficModelId;
	}

	public String getId() {
		return id;
	}

	public double getIconLat() {
		return iconLat;
	}

	public double getIconLon() {
		return iconLon;
	}

	public int getIncidentCategory() {
		return incidentCategory;
	}

	public String getIncidentCategoryDesc() {
		return incidentCategoryDesc;
	}

	public int getMagnitudeOfDelay() {
		return magnitudeOfDelay;
	}

	public String getMagnitudeOfDelayDesc() {
		return magnitudeOfDelayDesc;
	}

	public int getClusterSize() {
		return clusterSize;
	}

	public String getDescription() {
		return description;
	}

	public String getCauseOfAccident() {
		return causeOfAccident;
	}

	public String getFrom() {
		return from;
	}

	public String getTo() {
		return to;
	}

	public int getLength() {
		return length;
	}

	public int getDelayInSeconds() {
		return delayInSeconds;
	}

	public String getAffectedRoads() {
		return affectedRoads;
	}

	public void setTrafficModelId(String trafficModelId) {
		this.trafficModelId = trafficModelId;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setIconLat(double iconLat) {
		this.iconLat = iconLat;
	}

	public void setIconLon(double iconLon) {
		this.iconLon = iconLon;
	}

	public void setIncidentCategory(int incidentCategory) {
		this.incidentCategory = incidentCategory;
	}

	public void setIncidentCategoryDesc(String incidentCategoryDesc) {
		this.incidentCategoryDesc = incidentCategoryDesc;
	}

	public void setMagnitudeOfDelay(int magnitudeOfDelay) {
		this.magnitudeOfDelay = magnitudeOfDelay;
	}

	public void setMagnitudeOfDelayDesc(String magnitudeOfDelayDesc) {
		this.magnitudeOfDelayDesc = magnitudeOfDelayDesc;
	}

	public void setClusterSize(int clusterSize) {
		this.clusterSize = clusterSize;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setCauseOfAccident(String causeOfAccident) {
		this.causeOfAccident = causeOfAccident;
	}

	public void setFrom(String from) {
		this.from = from;
	}

	public void setTo(String to) {
		this.to = to;
	}

	public void setLength(int length) {
		this.length = length;
	}

	public void setDelayInSeconds(int delayInSeconds) {
		this.delayInSeconds = delayInSeconds;
	}

	public void setAffectedRoads(String affectedRoads) {
		this.affectedRoads = affectedRoads;
	}

	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("******** Traffic Incident Id - ").append(this.id).append(" ********").append(System.getProperty("line.separator"));
		sb.append("Date: ").append(new Date(this.date)).append(System.getProperty("line.separator"));	
		sb.append("Traffic Model Id: ").append(this.trafficModelId).append(System.getProperty("line.separator"));	
		sb.append("Incident Latitude: ").append(this.trafficLat).append(System.getProperty("line.separator"));
		sb.append("Incident Longitude: ").append(this.trafficLon).append(System.getProperty("line.separator"));	
		sb.append("Icon Latitude: ").append(this.iconLat).append(System.getProperty("line.separator"));
		sb.append("Icon Longitude: ").append(this.iconLon).append(System.getProperty("line.separator"));	
		sb.append("Incident Category Id: ").append(this.incidentCategory).append(System.getProperty("line.separator"));
		sb.append("Incident Category: ").append(this.incidentCategoryDesc).append(System.getProperty("line.separator"));
		sb.append("Magnitude of Delay Id: ").append(this.magnitudeOfDelay).append(System.getProperty("line.separator"));
		sb.append("Magnitude of Delay: ").append(this.magnitudeOfDelayDesc).append(System.getProperty("line.separator"));
		sb.append("Cluster Size: ").append(this.clusterSize).append(System.getProperty("line.separator"));
		sb.append("Description: ").append(this.description).append(System.getProperty("line.separator"));
		sb.append("Cause of Accident: ").append(this.causeOfAccident).append(System.getProperty("line.separator"));
		sb.append("From: ").append(this.from).append(System.getProperty("line.separator"));
		sb.append("To: ").append(this.to).append(System.getProperty("line.separator"));
		sb.append("Length in meters: ").append(this.length).append(System.getProperty("line.separator"));
		sb.append("Delay in seconds: ").append(this.delayInSeconds).append(System.getProperty("line.separator"));
		sb.append("Affected Roads: ").append(this.affectedRoads).append(System.getProperty("line.separator"));		
		return sb.toString();
	}
	
}
