package com.vasisouv.webservices2017.aem17.model;


import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.KeyFactory;
import com.vasisouv.webservices2017.aem17.utils.JsonParser;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;


public class FindMapper {

    public static final String FIND = "find";
    public static final String DECRYPTION_KEY = "decryption_key";

    public String getFindFromJson(JSONObject jsonObject) {
        String text = JsonParser.getString(jsonObject, FIND);
        return text;
    }
    public String getDecryptionKeyFromJson(JSONObject jsonObject) {
        String text = JsonParser.getString(jsonObject, DECRYPTION_KEY);
        return text;
    }

}