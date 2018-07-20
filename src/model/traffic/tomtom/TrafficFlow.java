package model.traffic.tomtom;

import java.util.Date;

import model.traffic.AbstractTrafficIncident;

/*
 * 		
 * Traffic Flow (https://developer.tomtom.com/online-traffic/online-traffic-documentation/flow-segment-data):
 * https://api.tomtom.com/traffic/services/4/flowSegmentData/absolute/10/json?key=<API_KEY>&point=41.503122,-8.480000
 * 
 * */

public class TrafficFlow extends AbstractTrafficIncident {

	//Functional Road Class Constants
	public static final int MOTORWAY 				= 0;
	public static final int MAJOR_ROAD 				= 1;
	public static final int OTHER_MAJOR_ROAD		= 2;
	public static final int SECONDARY_ROAD			= 3;
	public static final int LOCAL_CONNECTING_ROAD	= 4;
	public static final int LOCAL_HIGH_IMP_ROAD 	= 5;
	public static final int LOCAL_ROAD				= 6;

	//Class Variables
	protected String functionalRoadClass; 			//Indicates the road type
	protected String functionalRoadClassDesc; 		//Indicates the road type
	protected int currentSpeed; 					//Current speed
	protected int freeFlowSpeed; 					//Free flow speed expected under ideal conditions
	protected int currentTravelTime; 				//Current travel time in sec based on fused real-time measurements
	protected int freeFlowTravelTime;				//The travel time in sec which would be expected under ideal free flow conditions
	protected double confidence;					//Measure of the quality of the provided travel time and speed
	protected double realtimeRatio;					//The ratio between live and the historical data used to provide the response
	
	public TrafficFlow(double trafficLat, double trafficLon, long date, String functionalRoadClass, int currentSpeed,
			int freeFlowSpeed, int currentTravelTime, int freeFlowTravelTime, double confidence, double realtimeRatio) {
		super(trafficLat, trafficLon, date, "TBD");
		this.functionalRoadClass = functionalRoadClass;
		switch (this.functionalRoadClass) {
		    case "FRC0":
				this.functionalRoadClassDesc = "Motorway";
		    	break;
		    case "FRC1":
				this.functionalRoadClassDesc = "Major Road";
		    	break;
		    case "FRC2":
				this.functionalRoadClassDesc = "Other Major Road";
		    	break;
		    case "FRC3":
				this.functionalRoadClassDesc = "Secondary Road";
		    	break;
		    case "FRC4":
				this.functionalRoadClassDesc = "Local Connecting Road";
		    	break;
		    case "FRC5":
				this.functionalRoadClassDesc = "Local High Importance Road";
		    	break;
		    case "FRC6":
				this.functionalRoadClassDesc = "Local Road";
		    	break;
		    default:
				this.functionalRoadClassDesc = "Unknown Road";
		    ;
		}
		this.currentSpeed = currentSpeed;
		this.freeFlowSpeed = freeFlowSpeed;
		this.currentTravelTime = currentTravelTime;
		this.freeFlowTravelTime = freeFlowTravelTime;
		this.confidence = confidence;
		this.realtimeRatio = realtimeRatio;
	}

	public String getFunctionalRoadClass() {
		return functionalRoadClass;
	}

	public String getFunctionalRoadClassDesc() {
		return functionalRoadClassDesc;
	}

	public int getCurrentSpeed() {
		return currentSpeed;
	}

	public int getFreeFlowSpeed() {
		return freeFlowSpeed;
	}

	public int getCurrentTravelTime() {
		return currentTravelTime;
	}

	public int getFreeFlowTravelTime() {
		return freeFlowTravelTime;
	}

	public double getConfidence() {
	    if(!Double.isNaN(confidence))
	        return confidence;
	    else
	        return -99;
	}

	public double getRealtimeRatio() {
        if(!Double.isNaN(realtimeRatio))
            return realtimeRatio;
        else
            return -99;
	}

	public void setFunctionalRoadClass(String functionalRoadClass) {
		this.functionalRoadClass = functionalRoadClass;
	}

	public void setFunctionalRoadClassDesc(String functionalRoadClassDesc) {
		this.functionalRoadClassDesc = functionalRoadClassDesc;
	}

	public void setCurrentSpeed(int currentSpeed) {
		this.currentSpeed = currentSpeed;
	}

	public void setFreeFlowSpeed(int freeFlowSpeed) {
		this.freeFlowSpeed = freeFlowSpeed;
	}

	public void setCurrentTravelTime(int currentTravelTime) {
		this.currentTravelTime = currentTravelTime;
	}

	public void setFreeFlowTravelTime(int freeFlowTravelTime) {
		this.freeFlowTravelTime = freeFlowTravelTime;
	}

	public void setConfidence(double confidence) {
		this.confidence = confidence;
	}

	public void setRealtimeRatio(double realtimeRatio) {
		this.realtimeRatio = realtimeRatio;
	}

	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("******** Traffic Flow ********").append(System.getProperty("line.separator"));
		sb.append("Date: ").append(new Date(this.date)).append(System.getProperty("line.separator"));	
		sb.append("Incident Latitude: ").append(this.trafficLat).append(System.getProperty("line.separator"));
		sb.append("Incident Longitude: ").append(this.trafficLon).append(System.getProperty("line.separator"));	
		sb.append("Functional Road Class Id: ").append(this.functionalRoadClass).append(System.getProperty("line.separator"));
		sb.append("Functional Road Class: ").append(this.functionalRoadClassDesc).append(System.getProperty("line.separator"));	
		sb.append("Current Speed: ").append(this.currentSpeed).append(System.getProperty("line.separator"));	
		sb.append("Current Travel Time: ").append(this.currentTravelTime).append(System.getProperty("line.separator"));	
		sb.append("Free Flow Speed: ").append(this.freeFlowSpeed).append(System.getProperty("line.separator"));	
		sb.append("Free Flow Travel Time: ").append(this.freeFlowTravelTime).append(System.getProperty("line.separator"));			
		sb.append("Confidence: ").append(this.confidence).append(System.getProperty("line.separator"));		
		sb.append("Ratio between live and the historical data: ").append(this.realtimeRatio).append(System.getProperty("line.separator"));
		return sb.toString();
	}
	
}
