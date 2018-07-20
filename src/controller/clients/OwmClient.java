package controller.clients;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import controller.handlers.JsonHandler;
import model.pollution.owm.PollutionOWM;
import model.weather.owm.Weather;
import model.weather.owm.WeatherForecast;
import util.Consts;

public class OwmClient {
	
	public static boolean logInfo              = false;
	
	public static String cityId 			   = "8010448"; //by default, Braga ID; Override to get other city
	public static String cityLat 			   = "41.54"; 	//by default, Braga Latitude; Override to get other city's latitude
	public static String cityLon 			   = "-8.43"; 	//by default, Braga longitude; Override to get other city's longitude
	public static String cityPollutionCallLat  = "41";	    //by default, for pollution call get Braga latitude + longitude; Override to get other city's lat + lon
    public static String cityPollutionCallLon  = "-8";      //by default, for pollution call get Braga latitude + longitude; Override to get other city's lat + lon
	
    public static String owmApiKey             = "";        //Default value, if needed, may be API_KEY
		
	public static Weather getCurrentWeather() {
		try {
			return JsonHandler.getWeather((JSONObject)callAPI(Consts.OPEN_WEATHER_TYPE_WEATHER));
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public static List<WeatherForecast> getWeatherForecast() {
		try {
			return JsonHandler.getWeatherForecast((JSONObject)callAPI(Consts.OPEN_WEATHER_TYPE_FORESCAST), 1);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public static PollutionOWM getCurrentUltraviolet() {
		try {
			return JsonHandler.getUltraviolet((JSONObject)callAPI(Consts.OPEN_WEATHER_TYPE_UV));
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public static List<PollutionOWM> getUltravioletForecast() {
		try {
			return JsonHandler.getUltravioletForecast((JSONArray)callAPI(Consts.OPEN_WEATHER_TYPE_UV_FORECAST), 2);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public static PollutionOWM getCurrentOzone() {
		try {
			return JsonHandler.getOzone((JSONObject)callAPI(Consts.OPEN_WEATHER_OZONE));
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public static PollutionOWM getCurrentCarbonMonoxide() {
		try {
			return JsonHandler.getCarbonMonoxide((JSONObject)callAPI(Consts.OPEN_WEATHER_CARBON_MONOXIDE));
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public static PollutionOWM getCurrentSulfurDioxide() {
		try {
			return JsonHandler.getSulfurDioxide((JSONObject)callAPI(Consts.OPEN_WEATHER_SULFUR_DIOXIDE));
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public static PollutionOWM getCurrentNitrogenDioxide() {
		try {
			return JsonHandler.getNitrogenDioxide((JSONObject)callAPI(Consts.OPEN_WEATHER_NITROGEN_DIOXIDE));
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * @param type 			(ex.: weather? ...)
	 **/	
	private static Object callAPI(String type) throws Exception {
		String apiCall = "";
		
		if(type.compareTo(Consts.OPEN_WEATHER_TYPE_WEATHER) == 0 || type.compareTo(Consts.OPEN_WEATHER_TYPE_FORESCAST) == 0) //Weather
			apiCall = Consts.OPEN_WEATHER_STATIC_CALL + type + "APPID=" + owmApiKey + "&id=" + cityId + Consts.OPEN_WEATHER_METRICS_CALL + Consts.OPEN_WEATHER_LANG_CALL;
		else if(type.compareTo(Consts.OPEN_WEATHER_TYPE_UV) == 0 || type.compareTo(Consts.OPEN_WEATHER_TYPE_UV_FORECAST) == 0) //UV
			apiCall = Consts.OPEN_WEATHER_STATIC_CALL + type + "APPID=" + owmApiKey + "&lat=" + cityLat + "&lon=" + cityLon;
		else //Pollution
			apiCall = type + cityPollutionCallLat + "," + cityPollutionCallLon + "/" + Consts.OPEN_WEATHER_POLLUTION_DATE + "APPID=" + owmApiKey;

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
				throw new Exception(String.format("OpenWeatherMaps server responded with %d", statusCode));
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
		if(type.compareTo(Consts.OPEN_WEATHER_TYPE_UV_FORECAST) == 0)
			return new JSONArray(responseBody);
		else
			return new JSONObject(responseBody);
	}
}
