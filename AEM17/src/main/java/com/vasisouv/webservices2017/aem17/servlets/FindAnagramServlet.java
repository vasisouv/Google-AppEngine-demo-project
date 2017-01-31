package com.vasisouv.webservices2017.aem17.servlets;

import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.vasisouv.webservices2017.aem17.model.FindMapper;
import com.vasisouv.webservices2017.aem17.model.Message;
import com.vasisouv.webservices2017.aem17.model.MessageDatasource;

import org.json.JSONObject;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class FindAnagramServlet extends MainServlet {


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        JSONObject jsonObject = readRequest(req);
        FindMapper mapper = new FindMapper();
        String searchText = mapper.getFindFromJson(jsonObject);
        String decryptionKey = mapper.getDecryptionKeyFromJson(jsonObject);

        MessageDatasource datasource = new MessageDatasource();
        List<Message> messagesList = datasource.findAnagram(searchText,decryptionKey, DatastoreServiceFactory.getDatastoreService());

        buildAndSendResponse(resp, messagesList, KEY_ANAGRAM);
    }
}
