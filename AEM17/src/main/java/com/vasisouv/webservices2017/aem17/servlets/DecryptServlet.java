package com.vasisouv.webservices2017.aem17.servlets;

import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.vasisouv.webservices2017.aem17.model.Message;
import com.vasisouv.webservices2017.aem17.model.MessageDatasource;
import com.vasisouv.webservices2017.aem17.model.MessageMapper;
import com.vasisouv.webservices2017.aem17.model.SearchMapper;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DecryptServlet extends MainServlet {



    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        JSONObject jsonObject = readRequest(req);
        SearchMapper mapper = new SearchMapper();
        String searchText = mapper.getSearchFromJson(jsonObject);
        String decryptionKey = mapper.getDecryptionKeyFromJson(jsonObject);

        MessageDatasource datasource = new MessageDatasource();
        List<Message> messagesList = datasource.searchMessage(searchText,decryptionKey,DatastoreServiceFactory.getDatastoreService());

        buildAndSendResponse(resp, messagesList, KEY_MESSAGES);
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
}
