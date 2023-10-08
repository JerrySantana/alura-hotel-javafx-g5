package org.one.alura_hotel_g5.security;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Arrays;

public class Security {

    public static String getSHA256SecurePassword(String password) {
        String genPass;
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
            messageDigest.update(getSalt().getBytes());
            byte[] bytes = messageDigest.digest(password.getBytes());
            StringBuilder stringBuilder = new StringBuilder();
            for (byte aByte : bytes) {
                stringBuilder.append(Integer.toString((aByte & 0xff) + 0x100, 16)
                        .substring(1));
            }
            genPass = stringBuilder.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
        return genPass;
    }
    private static String getSalt() throws NoSuchAlgorithmException {
        var secureRandom = SecureRandom.getInstance("SHA1PRNG");
        var salt = new byte[16];
        secureRandom.nextBytes(salt);
        return Arrays.toString(salt);
    }
}
