package com.vasisouv.webservices2017.aem17.model;

import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.KeyFactory;
import com.vasisouv.webservices2017.aem17.utils.JsonParser;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.security.spec.ECField;
import java.util.List;
import java.util.logging.Level;


public class MessageMapper {

    public static final String MESSAGE = "message";
    public static final String DECRYPTED_MESSAGE = "decrypted_message";
    public static final String ENCRYPTION_KEY = "encryption_key";

    public Message getMessageFromJson(JSONObject jsonObject) {
        String msg = JsonParser.getString(jsonObject, MESSAGE);
        String encryptionKey = JsonParser.getString(jsonObject,ENCRYPTION_KEY);
        return new Message(msg,encryptionKey);
    }

    public Message fromEntity(Entity entity) {
        Message message = new Message((String) entity.getProperty(MessageDatasource.PROPERTY_TEXT),(String) entity.getProperty(MessageDatasource.ENCRYPTION_KEY));
        message.setId(KeyFactory.keyToString(entity.getKey()));
        return message;
    }
    public JSONObject toJson(Message message){
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put(MESSAGE, message.getText());
            jsonObject.put(DECRYPTED_MESSAGE, message.getDecryptedMessage());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jsonObject;
    }
    public JSONArray toJsonArray(List<Message> messageList){
        JSONArray jsonArray = new JSONArray();
        for (Message message : messageList) {
            jsonArray.put(toJson(message));
        }
        return jsonArray;
    }
}