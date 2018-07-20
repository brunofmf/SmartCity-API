package controller.clients;

import java.util.List;

import org.json.JSONObject;

import controller.handlers.JsonHandler;
import model.crowdsensing.CrowdData;

public class CrowdSensingClient {

    //Variables for Crowd Sensing

    public static List<CrowdData> getCrowdData(JSONObject j) {
        try {
            return JsonHandler.getCrowdData(j);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }   
    
}
