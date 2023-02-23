package com.example.socialnetworkproject.security;


import java.nio.charset.StandardCharsets;
import java.util.Base64;

public class BaseTokenGenerator {
    public static String encrypt(String userId){
        return Base64.getEncoder().encodeToString(userId.getBytes(StandardCharsets.UTF_8));
    }
    public static String decrypt(String encodedId){
        byte[] decodedBytes = Base64.getDecoder().decode(encodedId);
        return new String(decodedBytes);
    }
}
