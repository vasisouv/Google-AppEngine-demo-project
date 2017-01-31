package com.vasisouv.webservices2017.aem17.servlets;

import com.vasisouv.webservices2017.aem17.model.Message;
import com.vasisouv.webservices2017.aem17.model.MessageDatasource;
import com.vasisouv.webservices2017.aem17.model.MessageMapper;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public abstract class MainServlet extends HttpServlet {

    static final String KEY_RESULT = "result";
    static final String KEY_MESSAGES = "messages";
    static final String KEY_ANAGRAM = "anagrams";
    private static final String RESULT_OK = "ok";
    static final String RESULT_NO_MESSAGES = "There are no messages";
    private static final String UTF_8 = "UTF-8";
    public static final Logger logger = Logger.getLogger(MessageDatasource.class.getName());

    public JSONObject readRequest(HttpServletRequest request) {
        BufferedReader bufferedReader = getBufferedReader(request);
        StringBuilder stringBuilder = getResult(bufferedReader);
        return convertResultToJson(stringBuilder);
    }

    public void sendResponse(String result, HttpServletResponse response) {
        try {
            response.setCharacterEncoding(UTF_8);
            PrintWriter printWriter = response.getWriter();
            printWriter.print(result);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private JSONObject convertResultToJson(StringBuilder stringBuilder) {
        JSONObject jsonObject = null;
        try {
            jsonObject = new JSONObject(stringBuilder.toString());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jsonObject;
    }

    private StringBuilder getResult(BufferedReader bufferedReader) {
        if (bufferedReader == null) {
            return null;
        }
        String message;
        StringBuilder stringBuilder = new StringBuilder();
        try {
            while ((message = bufferedReader.readLine()) != null) {
                stringBuilder.append(message);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return stringBuilder;
    }

    private BufferedReader getBufferedReader(HttpServletRequest request) {
        if (request == null) {
            return null;
        }
        BufferedReader bufferedReader = null;
        try {
            bufferedReader = request.getReader();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return bufferedReader;
    }

    protected String getJsonResultOk() {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put(KEY_RESULT, RESULT_OK);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jsonObject.toString();
    }
    protected String createJsonResultNoMessages() {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put(KEY_RESULT, RESULT_NO_MESSAGES);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jsonObject.toString();
    }
    protected void buildAndSendResponse(HttpServletResponse resp, List<Message> messageList, String objectKey) {
        JSONObject jsonObjectResponse = new JSONObject();
        MessageMapper mapper = new MessageMapper();
        JSONArray jsonArray = mapper.toJsonArray(messageList);
        if(jsonArray.length() == 0){
            sendResponse(createJsonResultNoMessages(), resp);
            return;
        }
        try {
            jsonObjectResponse.put(objectKey, jsonArray);
        } catch (JSONException e) {
            sendResponse(createJsonResultNoMessages(), resp);
        }
        sendResponse(jsonObjectResponse.toString(), resp);
    }

}