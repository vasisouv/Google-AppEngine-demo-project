package com.vasisouv.webservices2017.aem17.utils;

import org.apache.commons.codec.binary.Base64;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;

public class DesEncrypter {

    public DesEncrypter(SecretKey key) throws Exception {
        Cipher ecipher = Cipher.getInstance("DES");
        Cipher dcipher = Cipher.getInstance("DES");
        ecipher.init(Cipher.ENCRYPT_MODE, key);
        dcipher.init(Cipher.DECRYPT_MODE, key);
    }

    public String encrypt(String str) throws Exception {
        return new String(Base64.encodeBase64(str.getBytes("UTF-8")), "UTF-8");
    }

    public String decrypt(String str) throws Exception {
        byte[] decoded = Base64.decodeBase64(str.getBytes("UTF-8"));
        return new String(decoded, "UTF8");
    }
}


