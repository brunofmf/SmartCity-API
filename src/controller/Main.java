package controller;
import java.util.List;

import controller.clients.OpenAQClient;
import controller.clients.OwmClient;
import controller.clients.TomtomClient;
import model.pollution.openaq.PollutionAQ;
import model.pollution.owm.PollutionOWM;
import model.traffic.tomtom.TrafficFlow;
import model.traffic.tomtom.TrafficIncident;
import model.weather.owm.Weather;
import model.weather.owm.WeatherForecast;
import util.Consts;
import util.Functions;

public class Main {

	public static void main(String[] args) {		
		
		/*Redefine default client variables*/
		OwmClient.logInfo = true;
		OpenAQClient.logInfo = true;
		TomtomClient.logInfo = true;
		
		TomtomClient.cityMinLat = Consts.TOMTOM_PORTO_MIN_LAT;
		TomtomClient.cityMinLon = Consts.TOMTOM_PORTO_MIN_LON;
		TomtomClient.cityMaxLat = Consts.TOMTOM_PORTO_MAX_LAT;
		TomtomClient.cityMaxLon = Consts.TOMTOM_PORTO_MAX_LON;	
		TomtomClient.flowCityLat = "41.158210";  
        TomtomClient.flowCityLon = "-8.63166";
		
        //OwmClient.owmApiKey = owmApiKey;
		
		/*Call APIs*/
		//Weather w = OwmClient.getCurrentWeather();							//OK - 20180517
		//System.out.println(w.toString());
		
		//List<WeatherForecast> wf = OwmClient.getWeatherForecast();			//OK - 20180517
		//System.out.println(wf.toString());
        
        //System.out.println(Functions.getCurrentLuminosity().toString());      //OK - 20180517
		
		//PollutionOWM uv = OwmClient.getCurrentUltraviolet();					//OK - 20180517
		//System.out.println(uv.toString());
		
		//List<PollutionOWM> uvL = OwmClient.getUltravioletForecast();			//OK - 20180517
		//System.out.println(uvL.toString());		
		
		//PollutionOWM o = OwmClient.getCurrentOzone();							//OK - 20180517
		//System.out.println(o.toString());		
		
		//PollutionOWM p1 = OwmClient.getCurrentCarbonMonoxide();				//OK - 20180517
		//System.out.println(p1.toString());
		
		//PollutionOWM p2 = OwmClient.getCurrentSulfurDioxide();				//OK - 20180517
		//System.out.println(p2.toString());
		
		//PollutionOWM p3 = OwmClient.getCurrentNitrogenDioxide();				//OK - 20180517 (but getting 504 Gateway Time-out)
		//System.out.println(p3.toString());
		
		//List<PollutionAQ> mes = OpenAQClient.getCurrentMeasurements();		//OK - 20180517
		//System.out.println(mes.toString());
		

		List<TrafficIncident> ti = TomtomClient.getTrafficIncidents();			//OK - 20180517
		System.out.println(ti.toString());

		//List<TrafficFlow> tf = TomtomClient.getTrafficFlow();					//OK - 20180517
		//System.out.println(tf.toString());
	}

}
