package com.vasisouv.webservices2017.aem17.model;

import com.vasisouv.webservices2017.aem17.utils.JsonParser;
import org.json.JSONObject;

public class SearchMapper {

    public static final String SEARCH = "search";
    public static final String DECRYPTION_KEY = "decryption_key";

    public String getSearchFromJson(JSONObject jsonObject) {
        return JsonParser.getString(jsonObject, SEARCH);
    }
    public String getDecryptionKeyFromJson(JSONObject jsonObject) {
        return JsonParser.getString(jsonObject, DECRYPTION_KEY);
    }

}