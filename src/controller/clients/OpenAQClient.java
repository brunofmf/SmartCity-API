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
import model.pollution.openaq.PollutionAQ;
import util.Consts;

public class OpenAQClient {
	
	public static boolean logInfo 	= false;
	public static String cityId		= Consts.OPEN_AQ_BRAGA_CITY; 	//by default, Braga ID; Override to get other city

	public static List<PollutionAQ> getCurrentMeasurements() {
		try {
			return JsonHandler.getMeasurements((JSONObject)callAPI(Consts.OPEN_AQ_LATEST));
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * @param type 			(ex.: latest? ...)
	 **/	
	private static Object callAPI(String type) throws Exception {
		String apiCall = Consts.OPEN_AQ_STATIC_CALL + type + cityId;

		if(logInfo)
			System.out.println(apiCall);

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
				throw new Exception(String.format("OpenAQ server responded with %d", statusCode));
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
