package com.vidmins.auth;

import com.vidmins.entity.User;
import com.vidmins.persistence.GenericDao;

import java.security.SecureRandom;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.security.crypto.bcrypt.BCrypt;

public class Auth {

    Logger logger = LogManager.getLogger(this.getClass());

    /**
     * Encrypt a pass phrase with SHA-512
     * Adapted from: https://www.baeldung.com/java-password-hashing
     *
     * @param password the password
     * @return the encrypted password
     */
    public String encryptPassword(String password) {

        BCrypt bcrypt = new BCrypt();
        String hashPass = bcrypt.hashpw(password, bcrypt.gensalt());

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
    public User authenticateUser(String userName, String password)
            throws Exception {
        User accessUser = null;
        GenericDao<User> userDao = new GenericDao<>(User.class);

        List<User> matchingUsers = userDao.findByPropertyEqual("userName", userName);


        if (matchingUsers.size() == 1) {
            logger.debug("Using: " + userName + password + ", found: " + matchingUsers.get(0));
            accessUser = matchingUsers.get(0);

//            BCrypt bcrypt = new BCrypt();
//            bcrypt.checkpw(password, accessUser.getPassword());
            if (accessUser.getPassword() != password) {
                logger.debug("Not a match: " + userName + password + " isn't " + matchingUsers.get(0));
                accessUser = null;
            }

        } else {
            throw new Exception("Did not find a unique user for those credentials " + matchingUsers);
        }

        return accessUser;
    }

    public boolean setUserHashPass(String userName, String password) throws Exception {

        boolean isSet = false;

        User accessUser = null;
        GenericDao<User> userDao = new GenericDao<>(User.class);

        List<User> matchingUsers = userDao.findByPropertyEqual("userName", userName);

        if (matchingUsers.size() == 1) {
            accessUser = matchingUsers.get(0);
            // TODO and is current user

            BCrypt bcrypt = new BCrypt();
            String hashPass = bcrypt.hashpw(password, bcrypt.gensalt());

            accessUser.setPassword(hashPass);
            userDao.saveOrUpdate(accessUser);
            isSet = true;

        } else {
            throw new Exception("Did not find a unique user for those credentials " + matchingUsers);
        }

        return isSet;
    }
}
