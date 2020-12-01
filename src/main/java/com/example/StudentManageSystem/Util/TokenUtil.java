package com.example.StudentManageSystem.Util;

import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.UUID;

public class TokenUtil {

    private static final int uuid_length = 32;
    public static String getToken(String username){
        String token = UUID.randomUUID().toString().replaceAll("-","") + username;
        return EncodeBase64(token);
    }

    public static String findUserNameByToken(String Token){
//        先用base64解码Token
        String DecodeToken = DecodeBase64(Token);
        StringBuilder result = new StringBuilder();
        for (int i = uuid_length; i < DecodeToken.length(); i++){
            result.append(DecodeToken.charAt(i));
        }
        return String.valueOf(result);
    }
    private static String EncodeBase64(String str){
        return Base64.getEncoder().encodeToString(str.getBytes(StandardCharsets.UTF_8));
    }

    private static String DecodeBase64(String str){
        byte [] b64 = Base64.getDecoder().decode(str);
        return new String(b64);
    }
}
