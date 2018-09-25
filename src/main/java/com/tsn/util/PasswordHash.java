package com.tsn.util;

import org.apache.commons.codec.digest.DigestUtils;
import java.security.SecureRandom;

public class PasswordHash {

    private static final int SALT_BYTE_SIZE = 32;
    public static String currentSalt;

    public static void createNewSalt() {
        SecureRandom random = new SecureRandom();
        byte[] salt = new byte[SALT_BYTE_SIZE];
        random.nextBytes(salt);
        currentSalt =  new String(salt);
    }

    /**
     * Returns a salted MD5 hash of the password.
     *
     * @param   password    the password to hash
     * @return              a salted MD5 hash of the password
     */
    public static String createHash(String password) {
        return DigestUtils.md5Hex(password + currentSalt);
    }

    /**
     * Validates a password using a hash.
     *
     * @param   password        the password to check
     * @param   correctHash     the hash of the valid password
     * @param   salt            the salt used to generate hash
     * @return                  true if the password is correct, false if not
     */
    public static boolean validatePassword(String password, String correctHash, String salt) {
        return DigestUtils.md5Hex(password + salt).equals(correctHash);
    }

    public static String validatePasswordStr(String password, String correctHash, String salt) {
        return new String(DigestUtils.md5Hex(password + salt) + "    -    " + correctHash);
    }

}
