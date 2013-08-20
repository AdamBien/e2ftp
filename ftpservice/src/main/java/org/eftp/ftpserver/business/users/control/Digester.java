/*
 *
 */
package org.eftp.ftpserver.business.users.control;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 *
 * @author adam-bien.com
 */
public class Digester {

    private static final String CHARSET = "UTF-8";
    private static final String ALGORITHM = "SHA-256";

    public static String computeHash(String password) {
        MessageDigest messageDigest = null;
        final byte[] bytes;
        try {
            messageDigest = MessageDigest.getInstance(ALGORITHM);
        } catch (NoSuchAlgorithmException ex) {
            throw new IllegalStateException("Cannot find digest algorithm: " + ALGORITHM, ex);
        }
        try {
            bytes = password.getBytes(CHARSET);
        } catch (UnsupportedEncodingException ex) {
            throw new IllegalStateException("Charset: " + CHARSET + " is not supported", ex);
        }
        messageDigest.update(bytes);
        byte[] digest = messageDigest.digest();
        BigInteger bigInteger = new BigInteger(1, digest);
        return String.format("%x", bigInteger);
    }

}
