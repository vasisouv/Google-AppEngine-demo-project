package com.vasisouv.webservices2017.aem17.model;

public class Message {

    private String text;
    private String id;
    private String decryptedMessage;

    Message(String msg) {
        this.text = msg;
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

    public String getDecryptedMessage() {
        return decryptedMessage;
    }
    public void setDecryptedMessage(String decryptedMessage) {
        this.decryptedMessage = decryptedMessage;
    }
}