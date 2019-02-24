package com.vidmins.auth;

import com.vidmins.entity.User;
import com.vidmins.persistence.GenericDao;

import java.util.HashMap;
import java.util.Map;

public class Auth {
    /**
     * Encrypt a pass phrase with SHA-512
     * Adapted from: https://www.baeldung.com/java-password-hashing
     *
     * @param password the password
     * @return the encrypted password
     */
    public String encryptPassword(String password) {
//        String encryptedPassword;
//        SecureRandom random = new SecureRandom();
//        byte[] salt = new byte[16];
//        random.nextBytes(salt);
//
//        try {
//            MessageDigest md = MessageDigest.getInstance("SHA-512");
//            md.update(salt);
//            String hashedPassword = md.digest(password);
//
//            encryptedPassword = new String(salt) + "|" + hashedPassword;
//
//        } catch (NoSuchAlgorithmException noAlgException) {
//            System.out.println("UserData.encryptPassword");
//            noAlgException.printStackTrace();
//        }

        return password;
    }

    /**
     * Authenticate user credentials
     *
     * @param userName the user name
     * @param password the password
     * @return an authenticated user
     */
    public User authenticateUser(String userName, String password) {
        User authenticatedUser = null;
        GenericDao<User> userDao = new GenericDao<>(User.class);

        Map<String, String> propertyMap = new HashMap<>();
        propertyMap.put("userName", userName);
        propertyMap.put("password", password);

        userDao.findByPropertyEqual(propertyMap);

        return authenticatedUser;
    }

}
