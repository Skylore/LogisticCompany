package utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class SecurityUtils {

    private static MessageDigest messageDigest;

    private SecurityUtils() {
    }

    static {
        try {
            messageDigest = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }

    public static String hashMD5(String word) {
        byte[] hashed = messageDigest.digest(word.getBytes());
        return new String(hashed);
    }

}
