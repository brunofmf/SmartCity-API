package controller.handlers;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import model.crowdsensing.CrowdData;
import model.pollution.openaq.PollutionAQ;
import model.pollution.owm.PollutionOWM;
import model.traffic.tomtom.TrafficFlow;
import model.traffic.tomtom.TrafficIncident;
import model.weather.owm.Weather;
import model.weather.owm.WeatherForecast;
import util.Consts;
import util.Functions;

public class JsonHandler {
	
	/*
	 * OPENWEATHERMAP HANDLERS
	 */
	
	public static Weather getWeather(JSONObject j) {
		String weatherDescription = "", cityName = "";
		int temperature = 0, pressure = 0, humidity = 0, windSpeed = 0, clouds = 0, rain = 0;
		long calculationDate = 0, sunrise = 0, sunset = 0;
		
		if(j != null) {
			JSONArray weatherArray = j.optJSONArray(Consts.JSON_WEATHER);
			if(weatherArray != null) {
				//For now only look to first element of the list
				JSONObject weatherArrayElem = weatherArray.optJSONObject(0);
				if(weatherArrayElem != null)
					weatherDescription = weatherArrayElem.optString(Consts.JSON_WEATHER_DESCRIPTION);			
			}
			JSONObject mainObj = j.optJSONObject(Consts.JSON_MAIN);
			temperature = mainObj != null ? mainObj.optInt(Consts.JSON_TEMPERATURE) : 0;
			pressure = mainObj != null ? mainObj.optInt(Consts.JSON_PRESSURE) : 0;
			humidity = mainObj != null ? mainObj.optInt(Consts.JSON_HUMIDITY) : 0;
			windSpeed = j.optJSONObject(Consts.JSON_WIND) != null ? j.optJSONObject(Consts.JSON_WIND).optInt(Consts.JSON_WIND_SPEED) : 0;
			clouds = j.optJSONObject(Consts.JSON_CLOUDS) != null ? j.optJSONObject(Consts.JSON_CLOUDS).optInt(Consts.JSON_CLOUDS_ALL) : 0;
			rain = j.optJSONObject(Consts.JSON_RAIN) != null ? j.optJSONObject(Consts.JSON_RAIN).optInt(Consts.JSON_RAIN_3H) : 0;
			calculationDate = j.optLong(Consts.JSON_CALCULATION_DATE);
			cityName = j.optString(Consts.JSON_CITY_NAME);
			sunrise = j.optJSONObject(Consts.JSON_SYS) != null ? j.optJSONObject(Consts.JSON_SYS).optLong(Consts.JSON_SYS_SUNRISE) : 0;
			sunset = j.optJSONObject(Consts.JSON_SYS) != null ? j.optJSONObject(Consts.JSON_SYS).optLong(Consts.JSON_SYS_SUNSET) : 0;
		}
		
		return new Weather(weatherDescription, temperature, pressure, humidity, windSpeed, clouds, rain, calculationDate, cityName, sunrise, sunset);
	}
	
	public static List<WeatherForecast> getWeatherForecast(JSONObject j, int days) {
		//by default get 1 day forecast
		if(days < 1 || days > 3)
			days = 1;
		
		List<WeatherForecast> result = new ArrayList<>();
		String weatherDescription = "", cityName = "", forecastDateText = "";
		int temperature = 0, pressure = 0, humidity = 0, windSpeed = 0, clouds = 0, rain = 0;
				
		int cnt = 0, intervals = days*8; //3hour intervals = 8 intervals/day
		long forecastDate = 0;
		
		if(j != null) {
			cityName = j.optJSONObject(Consts.JSON_CITY) != null ? j.optJSONObject(Consts.JSON_CITY).optString(Consts.JSON_CITY_NAME) : "0";
			cnt = j.optInt(Consts.JSON_LINES_COUNT);			
			JSONArray listArray = j.optJSONArray(Consts.JSON_LIST);
			if(listArray != null) {
				//Get intervals elements OR cnt - the smallest one
				for(int i=0; i <= intervals && i < cnt; i++) {
					JSONObject listArrayElem = listArray.optJSONObject(i);
					if(listArrayElem != null) {
						forecastDate = listArrayElem.optInt(Consts.JSON_CALCULATION_DATE);
						forecastDateText = listArrayElem.optString(Consts.JSON_FORECAST_DATE_TXT);
						JSONObject mainObj = listArrayElem.optJSONObject(Consts.JSON_MAIN);
						temperature = mainObj != null ? mainObj.optInt(Consts.JSON_TEMPERATURE) : 0;
						pressure = mainObj != null ? mainObj.optInt(Consts.JSON_PRESSURE) : 0;
						humidity = mainObj != null ? mainObj.optInt(Consts.JSON_HUMIDITY) : 0;						
						windSpeed = listArrayElem.optJSONObject(Consts.JSON_WIND) != null ? listArrayElem.optJSONObject(Consts.JSON_WIND).optInt(Consts.JSON_WIND_SPEED) : 0;
						clouds = listArrayElem.optJSONObject(Consts.JSON_CLOUDS) != null ? listArrayElem.optJSONObject(Consts.JSON_CLOUDS).optInt(Consts.JSON_CLOUDS_ALL) : 0;
						rain = listArrayElem.optJSONObject(Consts.JSON_RAIN) != null ? listArrayElem.optJSONObject(Consts.JSON_RAIN).optInt(Consts.JSON_RAIN_3H) : 0;
						JSONArray weatherArray = listArrayElem.optJSONArray(Consts.JSON_WEATHER);
						if(weatherArray != null) {
							//For now only look to first element of the list
							JSONObject weatherArrayElem = weatherArray.optJSONObject(0);
							if(weatherArrayElem != null)
								weatherDescription = weatherArrayElem.optString(Consts.JSON_WEATHER_DESCRIPTION);			
						}
						result.add(new WeatherForecast(weatherDescription, temperature, pressure, humidity, windSpeed, clouds, rain, forecastDateText, cityName, forecastDate));				
					}	
				}
			}
		}
		
		return result;
	}
	
	public static PollutionOWM getUltraviolet(JSONObject j) {
		double lat = 0.0, lon = 0.0, value = 0.0;
		long date = 0;
		
		if(j != null) {
			lat = j.optDouble(Consts.JSON_LAT);
			lon = j.optDouble(Consts.JSON_LON);
			date = j.optLong(Consts.JSON_DATE);
			value = j.optDouble(Consts.JSON_VALUE);
		}
		
		return new PollutionOWM(lat, lon, date, "Ultraviolet index", "UV", value, null);
	}
	
	public static List<PollutionOWM> getUltravioletForecast(JSONArray j, int days) {
		//by default get 1 day forecast
		if(days < 1 || days > 3)
			days = 1;
		
		List<PollutionOWM> result = new ArrayList<>();		
		double lat = 0.0, lon = 0.0, value = 0.0;
		long date = 0; //1 interval/day
				
		if(j != null) {
			for(int i=0; i < j.length() && i < days; i++) {
				JSONObject listArrayElem = j.optJSONObject(i);
				if(listArrayElem != null) {
					lat = listArrayElem.optDouble(Consts.JSON_LAT);
					lon = listArrayElem.optDouble(Consts.JSON_LON);
					date = listArrayElem.optLong(Consts.JSON_DATE);
					value = listArrayElem.optDouble(Consts.JSON_VALUE);
					result.add(new PollutionOWM(lat, lon, date, "Ultraviolet index", "UV", value, null));
				}
			}
		}
		
		return result;
	}
	
	public static PollutionOWM getOzone(JSONObject j) {
		double lat = 0.0, lon = 0.0, value = 0.0;
		long date = 0;
		
		if(j != null) {
			date = j.optLong(Consts.JSON_TIME);
			//If date is received as String in ISO8601 format
			if(date == 0) {
				String aux = j.optString(Consts.JSON_TIME);
				if(!aux.equals("")) {
					date = Functions.fromISO8601UTC(aux);
				}
			}
				
			value = j.optDouble(Consts.JSON_DATA);
			
			JSONObject jLocation = j.optJSONObject(Consts.JSON_LOCATION);
			if(jLocation != null) {
				lat = jLocation.optDouble(Consts.JSON_LATITUDE);
				lon = jLocation.optDouble(Consts.JSON_LONGITUDE);
			}
		}
		return new PollutionOWM(lat, lon, date, "Ozone layer thickness (DU)", "O3", value, null);
	}
	
	public static PollutionOWM getCarbonMonoxide(JSONObject j) {
		double lat = 0.0, lon = 0.0;
		long date = 0;
		List<List<Double>> data = new ArrayList<>();
		
		if(j != null) {
			date = j.optLong(Consts.JSON_TIME);
			//If date is received as String in ISO8601 format
			if(date == 0) {
				String aux = j.optString(Consts.JSON_TIME);
				if(!aux.equals("")) {
					date = Functions.fromISO8601UTC(aux);
				}
			}
			
			JSONObject jLocation = j.optJSONObject(Consts.JSON_LOCATION);
			if(jLocation != null) {
				lat = jLocation.optDouble(Consts.JSON_LATITUDE);
				lon = jLocation.optDouble(Consts.JSON_LONGITUDE);
			}

			JSONArray dataArray = j.optJSONArray(Consts.JSON_DATA);
			
			if(dataArray != null) {
				for(int i=0; i < dataArray.length(); i++) {
					JSONObject dataArrayElem = dataArray.optJSONObject(i);		
					if(dataArrayElem != null) {
						List<Double> aux = new ArrayList<Double>();
						aux.add(dataArrayElem.optDouble(Consts.JSON_VALUE));
						aux.add(dataArrayElem.optDouble(Consts.JSON_PRECISION));
						data.add(aux);						
					}
				}
			}
		}
		
		return new PollutionOWM(lat, lon, date, "Carbon Monoxide Volume Mixing Ratio", "CO", -1.0, data);
	}
	
	public static PollutionOWM getSulfurDioxide(JSONObject j) {
		double lat = 0.0, lon = 0.0;
		long date = 0;
		List<List<Double>> data = new ArrayList<>();
		
		if(j != null) {
			date = j.optLong(Consts.JSON_TIME);
			//If date is received as String in ISO8601 format
			if(date == 0) {
				String aux = j.optString(Consts.JSON_TIME);
				if(!aux.equals("")) {
					date = Functions.fromISO8601UTC(aux);
				}
			}
			
			JSONObject jLocation = j.optJSONObject(Consts.JSON_LOCATION);
			if(jLocation != null) {
				lat = jLocation.optDouble(Consts.JSON_LATITUDE);
				lon = jLocation.optDouble(Consts.JSON_LONGITUDE);
			}

			JSONArray dataArray = j.optJSONArray(Consts.JSON_DATA);
			
			if(dataArray != null) {
				for(int i=0; i < dataArray.length(); i++) {
					JSONObject dataArrayElem = dataArray.optJSONObject(i);		
					if(dataArrayElem != null) {
						List<Double> aux = new ArrayList<Double>();
						aux.add(dataArrayElem.optDouble(Consts.JSON_VALUE));
						aux.add(dataArrayElem.optDouble(Consts.JSON_PRECISION));
						data.add(aux);						
					}
				}
			}
		}
		
		return new PollutionOWM(lat, lon, date, "Sulfur Dioxide Volume Mixing Ratio", "SO2", -1.0, data);
	}
	
	public static PollutionOWM getNitrogenDioxide(JSONObject j) {
		double lat = 0.0, lon = 0.0;
		long date = 0;
		List<Double> aux = new ArrayList<Double>();
		List<List<Double>> data = new ArrayList<>();
		
		if(j != null) {
			date = j.optLong(Consts.JSON_TIME);
			//If date is received as String in ISO8601 format
			if(date == 0) {
				String auxD = j.optString(Consts.JSON_TIME);
				if(!auxD.equals("")) {
					date = Functions.fromISO8601UTC(auxD);
				}
			}
			
			JSONObject jLocation = j.optJSONObject(Consts.JSON_LOCATION);
			if(jLocation != null) {
				lat = jLocation.optDouble(Consts.JSON_LATITUDE);
				lon = jLocation.optDouble(Consts.JSON_LONGITUDE);
			}

			JSONObject dataArray = j.optJSONObject(Consts.JSON_DATA);
			
			if(dataArray != null) {
				
				JSONObject no2ArrayElem = dataArray.optJSONObject(Consts.JSON_NO2);		
				JSONObject no2StratArrayElem = dataArray.optJSONObject(Consts.JSON_NO2_STRAT);		
				JSONObject no2TropArrayElem = dataArray.optJSONObject(Consts.JSON_NO2_TROP);		

				aux.add(no2ArrayElem.optDouble(Consts.JSON_VALUE));
				aux.add(no2ArrayElem.optDouble(Consts.JSON_PRECISION));
				aux.add(no2StratArrayElem.optDouble(Consts.JSON_VALUE));
				aux.add(no2StratArrayElem.optDouble(Consts.JSON_PRECISION));
				aux.add(no2TropArrayElem.optDouble(Consts.JSON_VALUE));
				aux.add(no2TropArrayElem.optDouble(Consts.JSON_PRECISION));
				
				data.add(aux);						
			}
		}
		
		return new PollutionOWM(lat, lon, date,  "Nitrogen Dioxide Volume Mixing Ratio", "NO2", -1.0, data);
	}
	
	/*
	 * OPENAQ HANDLERS
	 */
	public static List<PollutionAQ> getMeasurements(JSONObject j) {		
		List<PollutionAQ> result = new ArrayList<>();
		double lat = 0.0, lon = 0.0, value = 0.0;
		String unit, parameter, sourceName, lastUpdate;
		
		if(j != null) {		
			JSONArray resultsArray = j.optJSONArray(Consts.JSON_RESULTS);
			if(resultsArray != null) {				
				for(int i=0; i < resultsArray.length(); i++) {					
					JSONObject resultsArrayElem = resultsArray.optJSONObject(i);
					if(resultsArrayElem != null) {
						JSONObject coordinates = resultsArrayElem.optJSONObject(Consts.JSON_COORDINATES);
						if(coordinates != null) {
							lat = coordinates.optDouble(Consts.JSON_LATITUDE);
							lon = coordinates.optDouble(Consts.JSON_LONGITUDE);
						}						
						JSONArray measurementsArray = resultsArrayElem.optJSONArray(Consts.JSON_MEASUREMENTS);						
						if(measurementsArray != null) {							
							for(int k=0; k < measurementsArray.length(); k++) {	
								JSONObject measurementsArrayElem = measurementsArray.optJSONObject(k);
								if(measurementsArrayElem != null) {
									parameter = measurementsArrayElem.optString(Consts.JSON_PARAMETER);
									value = measurementsArrayElem.optDouble(Consts.JSON_VALUE);
									lastUpdate =  measurementsArrayElem.optString(Consts.JSON_LAST_UPDATED);
									unit = measurementsArrayElem.optString(Consts.JSON_UNIT);
									sourceName = measurementsArrayElem.optString(Consts.JSON_SOURCE_NAME);
									result.add(new PollutionAQ(lat, lon, lastUpdate, unit, parameter, value, sourceName));		
								}
							}						
						}
					}					
				}
			}
		}
		
		return result;
	}
	
	/*
	 * TOMTOM HANDLERS
	 */
	public static List<TrafficIncident> getTrafficIncidents(JSONObject j) {		
		List<TrafficIncident> result = new ArrayList<>();
		String trafficModelId = "", id = "", description = "", causeOfAccident = "", from = "", to = "", affectedRoads = "";
		int incidentCategory = -1, magnitudeOfDelay = -1, clusterSize = -1, length = -1, delayInSeconds = -1;
		double iconLat = -1.0, iconLon = -1.0, trafficLat = -1.0, trafficLon = -1.0;
		
		if(j != null) {
			JSONObject tm = j.optJSONObject(Consts.JSON_TM);
			if(tm != null) {
				trafficModelId = tm.optString(Consts.JSON_TRAFFIC_MODEL_ID);
				JSONArray poiArray = tm.optJSONArray(Consts.JSON_POI);
				if(poiArray != null) {
					for(int i=0; i < poiArray.length(); i++) {
						JSONObject poiArrayElem = poiArray.optJSONObject(i);
						if(poiArrayElem != null) {
							//if cluster_size > 0 (has cpoi) then get cpoi otherwise use poi
							clusterSize = poiArrayElem.optInt(Consts.JSON_CS);
							if(clusterSize > 0) {
								JSONArray cpoiArray = poiArrayElem.optJSONArray(Consts.JSON_CPOI);
								if(cpoiArray != null) {
									for(int k=0; k < cpoiArray.length(); k++) {
										JSONObject cpoiArrayElem = cpoiArray.optJSONObject(k);
										if(cpoiArrayElem != null) {
											id = cpoiArrayElem.optString(Consts.JSON_ID);
											JSONObject p = cpoiArrayElem.optJSONObject(Consts.JSON_P);
											if(p != null) {
												iconLat = p.optDouble(Consts.JSON_Y);
												iconLon = p.optDouble(Consts.JSON_X);
											}
											JSONObject op = cpoiArrayElem.optJSONObject(Consts.JSON_OP);
											if(op != null) {
												trafficLat = op.optDouble(Consts.JSON_Y);
												trafficLon = op.optDouble(Consts.JSON_X);
											}
											incidentCategory = cpoiArrayElem.optInt(Consts.JSON_IC);
											magnitudeOfDelay = cpoiArrayElem.optInt(Consts.JSON_TY);
											clusterSize = cpoiArrayElem.optInt(Consts.JSON_CS);
											description = cpoiArrayElem.optString(Consts.JSON_D);
											from = cpoiArrayElem.optString(Consts.JSON_F);
											to = cpoiArrayElem.optString(Consts.JSON_T);
											causeOfAccident = cpoiArrayElem.optString(Consts.JSON_C);
											length = cpoiArrayElem.optInt(Consts.JSON_L);
											affectedRoads = cpoiArrayElem.optString(Consts.JSON_R);
											delayInSeconds = cpoiArrayElem.optInt(Consts.JSON_DL);								
											result.add(new TrafficIncident(trafficLat, trafficLon, System.currentTimeMillis(), trafficModelId, id, iconLat, iconLon,
													incidentCategory, magnitudeOfDelay, clusterSize, description, causeOfAccident, from, to, length, delayInSeconds, affectedRoads));
										}
									}
								}
							} else {
								id = poiArrayElem.optString(Consts.JSON_ID);
								JSONObject p = poiArrayElem.optJSONObject(Consts.JSON_P);
								if(p != null) {
									iconLat = p.optDouble(Consts.JSON_Y);
									iconLon = p.optDouble(Consts.JSON_X);
								}
								JSONObject op = poiArrayElem.optJSONObject(Consts.JSON_OP);
								if(op != null) {
									trafficLat = op.optDouble(Consts.JSON_Y);
									trafficLon = op.optDouble(Consts.JSON_X);
								}
								incidentCategory = poiArrayElem.optInt(Consts.JSON_IC);
								magnitudeOfDelay = poiArrayElem.optInt(Consts.JSON_TY);
								description = poiArrayElem.optString(Consts.JSON_D);
								from = poiArrayElem.optString(Consts.JSON_F);
								to = poiArrayElem.optString(Consts.JSON_T);
								causeOfAccident = poiArrayElem.optString(Consts.JSON_C);
								length = poiArrayElem.optInt(Consts.JSON_L);
								affectedRoads = poiArrayElem.optString(Consts.JSON_R);
								delayInSeconds = poiArrayElem.optInt(Consts.JSON_DL);									
								result.add(new TrafficIncident(trafficLat, trafficLon, System.currentTimeMillis(), trafficModelId, id, iconLat, iconLon,
										incidentCategory, magnitudeOfDelay, clusterSize, description, causeOfAccident, from, to, length, delayInSeconds, affectedRoads));
							}
						}
					}
				}
			}
		}
		
		return result;
	}

	public static List<TrafficFlow> getTrafficFlow(JSONObject j) {		
		List<TrafficFlow> result = new ArrayList<>();		
		String functionalRoadClass = "";
		int currentSpeed = -1, freeFlowSpeed = -1, currentTravelTime = -1, freeFlowTravelTime = -1;
		double confidence = -1.0, realtimeRatio = -1.0, trafficLat = -1.0, trafficLon = -1.0;

		if(j != null) {
			JSONObject flowSegmentData = j.optJSONObject(Consts.JSON_FLOW_SEGMENT_DATA);
			if(flowSegmentData != null) {
				functionalRoadClass = flowSegmentData.optString(Consts.JSON_FRC);
				currentSpeed = flowSegmentData.optInt(Consts.JSON_CURRENT_SPEED);
				freeFlowSpeed = flowSegmentData.optInt(Consts.JSON_FREE_FLOW_SPEED);
				currentTravelTime = flowSegmentData.optInt(Consts.JSON_CURRENT_TRAVEL_TIME);
				freeFlowTravelTime = flowSegmentData.optInt(Consts.JSON_FREE_FLOW_TRAVEL_TIME);
				confidence = flowSegmentData.optDouble(Consts.JSON_CONFIDENCE);
				realtimeRatio = flowSegmentData.optDouble(Consts.JSON_REALTIME_RATIO);
				JSONObject coordinates = flowSegmentData.optJSONObject(Consts.JSON_COORDINATES);			
				if(coordinates != null) {
					JSONArray coordinateArray = coordinates.optJSONArray(Consts.JSON_COORDINATE);
					if(coordinateArray != null) {
						for(int i=0; i < coordinateArray.length(); i++) {
							JSONObject coordinateArrayElem = coordinateArray.optJSONObject(i);
							if(coordinateArrayElem != null) {
								trafficLat = coordinateArrayElem.optDouble(Consts.JSON_LATITUDE);
								trafficLon = coordinateArrayElem.optDouble(Consts.JSON_LONGITUDE);
								result.add(new TrafficFlow(trafficLat, trafficLon, System.currentTimeMillis(), functionalRoadClass, currentSpeed, freeFlowSpeed, currentTravelTime, 
										freeFlowTravelTime, confidence, realtimeRatio));
							}
						}
					}
				}				
			}
		}
		
		return result;
	}

    public static List<CrowdData> getCrowdData(JSONObject j) {      
        List<CrowdData> result = new ArrayList<>();

        String type = "", deviceId = "", mac = "";
        int rssi = -1;
        long previousMillisDetected = -1, dataCreated = -1;

        if(j != null) {
            type = j.optString(Consts.JSON_TYPE);
            deviceId = j.optString(Consts.JSON_DEVICE_ID);
            
            JSONObject timestamp = j.optJSONObject(Consts.JSON_TIMESTAMP);
            if(timestamp != null) {
                dataCreated = timestamp.optLong(Consts.JSON_SV);
            }
            
            JSONArray probesArray = j.optJSONArray(Consts.JSON_PROBES);
            if(probesArray != null) {
                for(int i=0; i < probesArray.length(); i++) {
                    JSONObject probesArrayElem = probesArray.optJSONObject(i);
                    if(probesArrayElem != null) {
                        mac = probesArrayElem.optString(Consts.JSON_MAC);
                        rssi = probesArrayElem.optInt(Consts.JSON_RSSI);
                        previousMillisDetected = probesArrayElem.optLong(Consts.JSON_PREVIOUS_MILL_DETECTED);
                        result.add(new CrowdData(type, deviceId, rssi, mac, previousMillisDetected, dataCreated, -99, -99));
                    }
                }
            }
        }
        
        return result;
    }
}
