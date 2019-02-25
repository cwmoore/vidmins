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

    public Auth() {
        super();
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

        Map<String, Object> propertyMap = new HashMap<>();
        propertyMap.put("userName", (Object) userName);
        propertyMap.put("password", (Object) password);

        authenticatedUser = userDao.findByPropertyEqual(propertyMap).get(0);

//        authenticatedUser = userDao.findByPropertyEqual("userName", userName);
        return authenticatedUser;
    }

}
