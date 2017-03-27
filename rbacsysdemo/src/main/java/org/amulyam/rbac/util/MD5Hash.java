package org.amulyam.rbac.util;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by amulyam on 27/03/17.
 */

/**
 * Class to calculate MD5 hash of a string
 */
public class MD5Hash {
    private static final String MD5_ALGO = "MD5";

    /**
     * Static function to calculate the MD5 hash of a String
     * @param message Parameter to convert into MD5 Hash
     * @return MD5 hash value of String
     * @throws NoSuchAlgorithmException
     */
    public static String getMd5Hash(String message) throws NoSuchAlgorithmException {
        MessageDigest m = MessageDigest.getInstance(MD5_ALGO);
        m.update(message.getBytes(),0,message.length());
        return new BigInteger(1,m.digest()).toString();
    }
}
