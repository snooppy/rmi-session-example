package ua.cn.dmitrykrivenko.rmi.session.example.server.utils;

import java.nio.CharBuffer;
import java.nio.charset.Charset;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 *
 * @author Dmitry Krivenko <dmitrykrivenko@gmail.com>
 */
public class Encryptor {

    private Encryptor() {
    }
    private static final String ALGORITHM = "MD5";

    public static String encryptPassword(char[] password) {

        byte[] bytes = Charset.forName("UTF-8").encode(CharBuffer.wrap(password)).array();

        MessageDigest md;
        try {
            md = MessageDigest.getInstance(ALGORITHM);
        } catch (NoSuchAlgorithmException e) {
            System.out.println(e.getClass().getName() + ": " + e.getMessage());
            return null;
        }
        md.update(bytes);

        byte byteData[] = md.digest();

        StringBuilder hexString = new StringBuilder();
        for (int i = 0; i < byteData.length; i++) {
            String hex = Integer.toHexString(0xff & byteData[i]);
            if (hex.length() == 1) {
                hexString.append('0');
            }
            hexString.append(hex);
        }
        return hexString.toString();
    }

    public static boolean isEqualsPasswords(String encrypted, char[] password) {
        return encrypted.equals(encryptPassword(password));
    }
}
