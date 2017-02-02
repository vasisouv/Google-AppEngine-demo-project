package com.vasisouv.webservices2017.aem17.model;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.FetchOptions;
import com.google.appengine.api.datastore.KeyFactory;
import com.google.appengine.api.datastore.PreparedQuery;
import com.google.appengine.api.datastore.Query;
import com.google.appengine.repackaged.org.apache.commons.codec.digest.Crypt;
import com.vasisouv.webservices2017.aem17.utils.DesEncrypter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MessageDatasource {

    public static final String KIND_MESSAGE = "kind_message";
    public static final String PROPERTY_TEXT = "property_message";
    public static final String ENCRYPTION_KEY = "encryption_key";

    private static final Logger logger = Logger.getLogger(MessageDatasource.class.getName());

    public String saveMessageToDatastore(Message message, DatastoreService datastoreService) {
        Entity messageEntity = new Entity(KIND_MESSAGE);
        messageEntity.setProperty(PROPERTY_TEXT, message.getText());
        datastoreService.put(messageEntity);
        return KeyFactory.keyToString(messageEntity.getKey());
    }

    public List<Message> searchMessage(String text, String decryptionKey, DatastoreService datastoreService) throws Exception {
        List<Entity> msgEntities = getAllMessageEntities(datastoreService);
        MessageMapper mapper = new MessageMapper();
        List<Message> messages = new ArrayList<>(msgEntities.size());
        DesEncrypter decrypter = new DesEncrypter(decryptionKey.toUpperCase()+"ASJDBASHVFHBASBDASD");

        for (Entity entity : msgEntities) {
            Message message = mapper.fromEntity(entity);
            String msgText = message.getText();
            try{
                message.setDecryptedMessage(decrypter.decrypt(msgText));
                if (msgText != null &&  message.getDecryptedMessage().contains(text)) {
                    messages.add(message);
                }
            }catch (Exception error){
                error.printStackTrace();
            }
        }
        return messages;
    }
    public List<Message> findAnagram(String text,String decryptionKey, DatastoreService datastoreService) throws Exception {
        List<Entity> msgEntities = getAllMessageEntities(datastoreService);
        MessageMapper mapper = new MessageMapper();
        List<Message> messages = new ArrayList<>(msgEntities.size());
        DesEncrypter decrypter = new DesEncrypter(decryptionKey.toUpperCase()+"ASJDBASHVFHBASBDASD");

        for (Entity entity : msgEntities) {
            Message message = mapper.fromEntity(entity);
            String msgText = message.getText();
            try{
                message.setDecryptedMessage(decrypter.decrypt(msgText));
                if (msgText != null && isAnagram(message.getDecryptedMessage(),text)) {
                    messages.add(message);
                }
            }catch (Exception error){
                error.printStackTrace();
            }
        }
        return messages;
    }
    private boolean isAnagram(String firstWord, String secondWord) {
        char[] word1 = firstWord.replaceAll("[\\s]", "").toCharArray();
        char[] word2 = secondWord.replaceAll("[\\s]", "").toCharArray();
        Arrays.sort(word1);
        Arrays.sort(word2);
        return Arrays.equals(word1, word2);
    }
    private List<Entity> getAllMessageEntities(DatastoreService datastoreService) {
        Query query = new Query(KIND_MESSAGE);
        PreparedQuery preparedQuery = datastoreService.prepare(query);
        FetchOptions options = FetchOptions.Builder.withDefaults();
        return preparedQuery.asList(options);
    }
}