package com.vasisouv.webservices2017.aem17.utils;

import java.security.NoSuchAlgorithmException;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;


public class GeneralUtils {
    public DesEncrypter generateEncrypter() {
        SecretKey key = null;
        DesEncrypter encrypter = null;
        try {
            key = KeyGenerator.getInstance("DES").generateKey();

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        try {
            encrypter = new DesEncrypter(key);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return encrypter;
    }
}
