package ru.kupirozi.utils;

import javax.crypto.Cipher;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.CharBuffer;
import java.nio.charset.Charset;
import java.security.Key;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.KeySpec;
import java.util.Arrays;
import java.util.Base64;

/**
 * Created by fedorov on 28.02.2018.
 */
public class CryptUtils {

    private final String secretKey = "_#@(87-uhrfdsal;kjhf12908736A(S*&ghsajkldhl3b1234-097f89021734JKLASHDA";
    private Key key;
    private Cipher cipher;

    public CryptUtils() throws Exception {
        applySecretKey();
    }

    public CryptUtils(final String secretKey) throws Exception {
        applySecretKey(secretKey);
    }

    private String salt(final String str) {
        int qi = str.indexOf("=");
        return new StringBuilder(str.substring(0, qi)).reverse().append(str.substring(qi, str.length())).toString();
    }

    private void applySecretKey() throws Exception {
        applySecretKey(secretKey);
    }

    private void applySecretKey(String secretKey) throws Exception {
        key = new SecretKeySpec(Arrays.copyOf(secretKey.getBytes("UTF-8"), 16), "AES");
        cipher = Cipher.getInstance("AES");
    }

    public String encrypt(final String str) throws Exception {
        cipher.init(Cipher.ENCRYPT_MODE, key);
        return salt(new String(Base64.getEncoder().encode(cipher.doFinal(str.getBytes("UTF-8")))));
    }

    public String encrypt(final String str, final String secretKey) throws Exception {
        applySecretKey(secretKey);
        return encrypt(str);
    }

    public String decrypt(final String str) throws Exception {
        cipher.init(Cipher.DECRYPT_MODE, key);
        return new String(cipher.doFinal(Base64.getDecoder().decode(salt(str))));
    }

    public String decrypt(final String str, final String secretKey) throws Exception {
        applySecretKey(secretKey);
        return decrypt(str);
    }

    public static byte[] digest(final char[] str) throws Exception {
        if ((str == null) || (str.length == 0)) {
            return new byte[0];
        }
        MessageDigest md5 = MessageDigest.getInstance("MD5");
        md5.update(Charset.forName("UTF-8").encode(CharBuffer.wrap(str)));
        return md5.digest();
    }

}
