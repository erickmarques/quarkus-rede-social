package br.com.erickmarques.rest.utils;

import jakarta.xml.bind.DatatypeConverter;

import java.security.MessageDigest;


public class Utils {

    public static String stringToMd5(String pass) {

        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(pass.getBytes());
            byte[] digest = md.digest();

            String hash = DatatypeConverter.printHexBinary(digest).toLowerCase();

            return hash;
        } catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
}
