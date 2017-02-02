package com.vasisouv.webservices2017.aem17.utils;

import org.apache.commons.codec.DecoderException;
import org.apache.commons.codec.binary.Base64;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;

public class DesEncrypter {
    Cipher ecipher;
    Cipher dcipher;

    public DesEncrypter(String key) {
        try {
            DESKeySpec keySpec = new DESKeySpec(key.getBytes("UTF8"));
            SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
            SecretKey secret = keyFactory.generateSecret(keySpec);
            ecipher = Cipher.getInstance("DES");
            dcipher = Cipher.getInstance("DES");
            ecipher.init(Cipher.ENCRYPT_MODE, secret);
            dcipher.init(Cipher.DECRYPT_MODE, secret);
        } catch (NoSuchPaddingException e) {
        } catch (InvalidKeySpecException e) {
        } catch (IOException e) {
        } catch (NoSuchAlgorithmException e) {
        } catch (InvalidKeyException e) {
        }
    }

    public String encrypt(String str) throws Exception {
        return new String(Base64.encodeBase64(ecipher.doFinal(str.getBytes("ISO-8859-1"))), "ISO-8859-1");
    }

    public String decrypt(String str) {
        try {
            // Decode base64 to get bytes
            byte[] dec = Base64.decodeBase64(str.getBytes("UTF-8"));
            // Decrypt
            byte[] utf8 = dcipher.doFinal(dec);
            // Decode using utf-8
            return new String(utf8, "UTF8");
        } catch (BadPaddingException e) {
            e.printStackTrace();
        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (java.io.IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}

