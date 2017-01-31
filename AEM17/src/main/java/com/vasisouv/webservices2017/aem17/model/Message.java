package com.vasisouv.webservices2017.aem17.model;

public class Message {

    private String text;
    private String id;
    private String encryptionKey;
    private String decryptedMessage;

    Message(String msg,String encryptionKey) {
        this.text = msg;
        this.encryptionKey = encryptionKey;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
    public void setId(String id) {
        this.id = id;
    }
    String getId() {
        return id;
    }
    public String getKey() {
        return encryptionKey;
    }
    public void setKey(String key) {
        this.encryptionKey = key;
    }

    public String getDecryptedMessage() {
        return decryptedMessage;
    }
    public void setDecryptedMessage(String decryptedMessage) {
        this.decryptedMessage = decryptedMessage;
    }
}