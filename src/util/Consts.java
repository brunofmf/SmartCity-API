package util;


public final class Consts {

	/**
	 * OPENWEATHERMAPS CONSTS 
	 * Ex.: https://openweathermap.org/api
	 * */
	//OpenWeatherMaps Types
	public final static String OPEN_WEATHER_STATIC_CALL 	= "http://api.openweathermap.org/data/2.5/";
	public final static String OPEN_WEATHER_OZONE		 	= "http://api.openweathermap.org/pollution/v1/o3/";
	public final static String OPEN_WEATHER_CARBON_MONOXIDE	= "http://api.openweathermap.org/pollution/v1/co/";
	public final static String OPEN_WEATHER_SULFUR_DIOXIDE	= "http://api.openweathermap.org/pollution/v1/so2/";
	public final static String OPEN_WEATHER_NITROGEN_DIOXIDE= "http://api.openweathermap.org/pollution/v1/no2/";

	//OpenWeatherMaps Parameters
	public final static String OPEN_WEATHER_TYPE_WEATHER 	= "weather?";
	public final static String OPEN_WEATHER_TYPE_FORESCAST 	= "forecast?";
	public final static String OPEN_WEATHER_TYPE_UV 		= "uvi?";
	public final static String OPEN_WEATHER_TYPE_UV_FORECAST= "uvi/forecast?";	
	public final static String OPEN_WEATHER_METRICS_CALL	= "&units=metric";
	public final static String OPEN_WEATHER_LANG_CALL		= "&lang=pt";
	
	//public final static String OPEN_WEATHER_BRAGA_ID		= "&id=8010448";
	//public final static String OPEN_WEATHER_BRAGA_LAT		= "&lat=41.54";
	//public final static String OPEN_WEATHER_BRAGA_LON		= "&lon=-8.43";
	//public final static String OPEN_WEATHER_POLL_BRAGA_0	= "41,-8/"; //No decimal point for ~80Kms search
	//public final static String OPEN_WEATHER_POLL_BRAGA_1	= "41.5,-8.4/"; //One decimal point for ~8Kms search
	public final static String OPEN_WEATHER_POLLUTION_DATE	= "current.json?"; 	//Use "2018-03Z" to look for all data on 03/2018; "current" searches for the latest available data point up until now

	//Current Weather Constants
	public final static String JSON_WEATHER 				= "weather";
	public final static String JSON_WEATHER_DESCRIPTION 	= "description";
	public final static String JSON_MAIN 					= "main";
	public final static String JSON_TEMPERATURE 			= "temp";
	public final static String JSON_PRESSURE 				= "pressure";
	public final static String JSON_HUMIDITY 				= "humidity";
	public final static String JSON_WIND 					= "wind";
	public final static String JSON_WIND_SPEED 				= "speed";
	public final static String JSON_CLOUDS 					= "clouds";
	public final static String JSON_CLOUDS_ALL				= "all";
	public final static String JSON_RAIN 					= "rain";
	public final static String JSON_RAIN_3H					= "3h";
	public final static String JSON_CALCULATION_DATE 		= "dt";
	public final static String JSON_CITY_NAME 				= "name";
	public final static String JSON_SYS 					= "sys";
	public final static String JSON_SYS_SUNRISE				= "sunrise";
	public final static String JSON_SYS_SUNSET 				= "sunset";

	//Forecast Weather Constants
	public final static String JSON_CITY 					= "city";
	public final static String JSON_LINES_COUNT				= "cnt";
	public final static String JSON_LIST 					= "list";
	public final static String JSON_FORECAST_DATE_TXT		= "dt_txt";
	
	//Current UV Constants
	public final static String JSON_VALUE					= "value";
	public final static String JSON_LAT						= "lat";
	public final static String JSON_LON						= "lon";
	public final static String JSON_DATE					= "date";	

	//Current Ozone Constants
	public final static String JSON_TIME					= "time";
	public final static String JSON_DATA					= "data";
	public final static String JSON_PRECISION				= "precision";
	public final static String JSON_COORDINATES				= "coordinates";
	public final static String JSON_LOCATION				= "location";
	public final static String JSON_LATITUDE				= "latitude";
	public final static String JSON_LONGITUDE				= "longitude";

	//Current Nitrogen Dioxide Constants
	public final static String JSON_NO2						= "no2";
	public final static String JSON_NO2_STRAT				= "no2_strat";
	public final static String JSON_NO2_TROP				= "no2_trop";
	
	/**
	 * OPEN AQ CONSTS 
	 * Ex.: https://docs.openaq.org/#api-Measurements
	 * */
	//OpenAQ Types
	public final static String OPEN_AQ_STATIC_CALL 			= "https://api.openaq.org/v1/";
	public final static String OPEN_AQ_LATEST			 	= "latest?";
	//public final static String OPEN_AQ_BRAGA_CITY			= "city=Braga";
	
	public final static String JSON_RESULTS					= "results";
	public final static String JSON_MEASUREMENTS			= "measurements";
	public final static String JSON_PARAMETER				= "parameter";
	public final static String JSON_LAST_UPDATED			= "lastUpdated";
	public final static String JSON_UNIT					= "unit";
	public final static String JSON_SOURCE_NAME				= "sourceName";
	
	/**
	 * TOMTOM TRAFFIC INCIDENTS API
	 * Ex.: https://developer.tomtom.com/online-traffic/online-traffic-documentation-online-traffic-incidents/traffic-incident-details
	 * */
	public final static String TOMTOM_STATIC_CALL 			= "https://api.tomtom.com/traffic/services/4/";
	public final static String TOMTOM_INCIDENT_DETAILS		= "incidentDetails/";
	public final static String TOMTOM_TRAFFIC_FLOW 			= "flowSegmentData/";
	//public final static String TOMTOM_API_KEY 				= "?key=iTiGqPRRsUAIU7DfMJafzT0BRdlCavV5"; 
	public final static String TOMTOM_STYLE					= "s3/"; 
	//public final static String TOMTOM_BRAGA_MIN_LAT			= "41.506531,"; 
	//public final static String TOMTOM_BRAGA_MIN_LON			= "-8.451247,"; 
	//public final static String TOMTOM_BRAGA_MAX_LAT			= "41.574115,"; 
	//public final static String TOMTOM_BRAGA_MAX_LON			= "-8.371253/"; 
	public final static String TOMTOM_PORTO_MIN_LAT			= "41.141069,"; 
	public final static String TOMTOM_PORTO_MIN_LON			= "-8.647017,"; 
	public final static String TOMTOM_PORTO_MAX_LAT			= "41.180244,"; 
	public final static String TOMTOM_PORTO_MAX_LON			= "-8.598716/"; 
	public final static String TOMTOM_ZOOM					= "11/";
	public final static String TOMTOM_TRAFFIC_MODEL_ID		= "-1/";
	public final static String TOMTOM_DATA_TYPE				= "json";	
	public final static String TOMTOM_PROJECTION			= "&projection=EPSG4326"; 
	public final static String TOMTOM_LANGUAGE				= "&language=en"; 
	public final static String TOMTOM_EXPAND_CLUSTER		= "&expandCluster=true";
	public final static String TOMTOM_ORIGINAL_POSITION		= "&originalPosition=true";

	//Traffic Flow Constants
	public final static String TOMTOM_FLOW_STYLE			= "absolute/";
	//public final static String TOMTOM_BRAGA_POINT			= "&point=41.546007,-8.419903";
	public final static String TOMTOM_PORTO_POINT			= "&point=41.158210,-8.631660";
	public final static String TOMTOM_FLOW_UNIT				= "&unit=KMPH";
	
	//TOMTOM Traffic Incident JSON tags
	public final static String JSON_TM 						= "tm";
	public final static String JSON_POI					 	= "poi";
	public final static String JSON_CPOI 					= "cpoi";
	public final static String JSON_ID						= "id";
	public final static String JSON_TRAFFIC_MODEL_ID		= "@id";
	public final static String JSON_P			 			= "p";
	public final static String JSON_OP			 			= "op";
	public final static String JSON_IC			 			= "ic";
	public final static String JSON_TY			 			= "ty";
	public final static String JSON_CS			 			= "cs";
	public final static String JSON_D			 			= "d";
	public final static String JSON_C			 			= "c";
	public final static String JSON_F			 			= "f";
	public final static String JSON_T			 			= "t";
	public final static String JSON_L			 			= "l";
	public final static String JSON_DL			 			= "dl";
	public final static String JSON_R			 			= "r";
	public final static String JSON_X			 			= "x";
	public final static String JSON_Y			 			= "y";
	
	//TOMTOM Traffic Flow JSON tags
	public final static String JSON_FLOW_SEGMENT_DATA		= "flowSegmentData";
	public final static String JSON_FRC						= "frc";
	public final static String JSON_CURRENT_SPEED			= "currentSpeed";
	public final static String JSON_FREE_FLOW_SPEED			= "freeFlowSpeed";
	public final static String JSON_CURRENT_TRAVEL_TIME		= "currentTravelTime";
	public final static String JSON_FREE_FLOW_TRAVEL_TIME	= "freeFlowTravelTime";
	public final static String JSON_CONFIDENCE				= "confidence";
	public final static String JSON_REALTIME_RATIO			= "realtimeRatio";
	public final static String JSON_COORDINATE				= "coordinate";
	
    /**
     * CROWD SENSING DATA
     * */
    //public final static double CS_BRAGA_LAT                 = 41.574115; 
    //public final static double CS_BRAGA_LON                 = -8.371253; 
    
    //Crowd Data JSON tags
    public final static String JSON_TYPE                    = "type";
    public final static String JSON_DEVICE_ID               = "deviceId";
    public final static String JSON_TIMESTAMP               = "timestamp";
    public final static String JSON_SV                      = ".sv";
    public final static String JSON_PROBES                  = "probes";
    public final static String JSON_MAC                     = "mac";
    public final static String JSON_RSSI                    = "rssi";
    public final static String JSON_PREVIOUS_MILL_DETECTED  = "previousMillisDetected";
	
}
