package com.vasisouv.webservices2017.aem17.utils;

import org.json.JSONException;
import org.json.JSONObject;

public class JsonParser {

    public static String getString(JSONObject jsonObject, String key){
        String value = null;
        if(jsonObject == null){
            return null;
        }
        try {
            value = jsonObject.getString(key);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return value;
    }
}
