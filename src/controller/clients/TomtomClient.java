package controller.clients;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

import org.json.JSONObject;

import controller.handlers.JsonHandler;
import model.traffic.tomtom.TrafficFlow;
import model.traffic.tomtom.TrafficIncident;
import util.Consts;

public class TomtomClient {
	
	public static boolean logInfo 		= false;
	//Variables for Traffic Incident
	public static String cityMinLat		= Consts.TOMTOM_BRAGA_MIN_LAT; 	//by default, Braga min lat
	public static String cityMinLon		= Consts.TOMTOM_BRAGA_MIN_LON; 	//by default, Braga min lon
	public static String cityMaxLat		= Consts.TOMTOM_BRAGA_MAX_LAT; 	//by default, Braga max lat
	public static String cityMaxLon		= Consts.TOMTOM_BRAGA_MAX_LON; 	//by default, Braga max lon
	//Variables for Traffic Flow
	public static String flowCityPoint	= Consts.TOMTOM_BRAGA_POINT; 	//by default, Braga max lon

	public static List<TrafficIncident> getTrafficIncidents() {
		try {
			return JsonHandler.getTrafficIncidents((JSONObject)callAPI(Consts.TOMTOM_INCIDENT_DETAILS));
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}	
	
	public static List<TrafficFlow> getTrafficFlow() {
		try {
			return JsonHandler.getTrafficFlow((JSONObject)callAPI(Consts.TOMTOM_TRAFFIC_FLOW));
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * @param type 			(ex.: incidentDetails/ or flowSegmentData/)
	 **/	
	private static Object callAPI(String type) throws Exception {
		String apiCall = "";
		
		if(type.compareTo(Consts.TOMTOM_INCIDENT_DETAILS) == 0)
			apiCall = Consts.TOMTOM_STATIC_CALL + type + Consts.TOMTOM_STYLE + cityMinLat + cityMinLon + cityMaxLat + cityMaxLon 
				+ Consts.TOMTOM_ZOOM + Consts.TOMTOM_TRAFFIC_MODEL_ID + Consts.TOMTOM_DATA_TYPE + Consts.TOMTOM_API_KEY 
				+ Consts.TOMTOM_PROJECTION + Consts.TOMTOM_LANGUAGE + Consts.TOMTOM_EXPAND_CLUSTER + Consts.TOMTOM_ORIGINAL_POSITION;
		else
			apiCall = Consts.TOMTOM_STATIC_CALL + type + Consts.TOMTOM_FLOW_STYLE + Consts.TOMTOM_ZOOM + Consts.TOMTOM_DATA_TYPE + Consts.TOMTOM_API_KEY 
			+ flowCityPoint + Consts.TOMTOM_FLOW_UNIT;
			

		if(logInfo)
			System.out.println("API CALL: " + apiCall);
		
		URL url = null;
		InputStream contentStream = null;
		String responseBody = null;
		Reader reader = null;
		try {
			//create the HttpURLConnection
			url = new URL(apiCall);
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();		      
			//just want to do an HTTP GET here
			connection.setRequestMethod("GET");
			//give it 15 seconds to respond
			connection.setReadTimeout(15*1000);
			connection.connect();
			//handle status code errors
			int statusCode = connection.getResponseCode();
			if (statusCode < 200 || statusCode >= 300) {
				throw new Exception(String.format("Tomtom server responded with %d", statusCode));
			}
			//Read the response content
			contentStream = connection.getInputStream();
			reader = new InputStreamReader(contentStream, "UTF-8");
			//Check content size and initialize reader
			int contentSize = connection.getContentLength();
			if (contentSize < 0)
				contentSize = 8*1024;
			StringWriter strWriter = new StringWriter(contentSize);
			char[] buffer = new char[contentSize];
			//Read
			int n = 0;
			while((n = reader.read(buffer)) != -1){
				strWriter.write(buffer, 0, n);
			}
			responseBody = strWriter.toString();
			contentStream.close();
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			if(contentStream != null)
				contentStream.close();
			if(reader != null)
				reader.close();
		}
		
		return new JSONObject(responseBody);
	}
}
