package com.vidmins.auth;

import com.vidmins.entity.User;
import com.vidmins.persistence.GenericDao;

import java.security.SecureRandom;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.vidmins.persistence.SessionFactoryProvider;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
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
    public static String encryptPassword(String password) {

        BCrypt bcrypt = new BCrypt();
        String hashPass = bcrypt.hashpw(password, bcrypt.gensalt());

        return hashPass;
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

            BCrypt bcrypt = new BCrypt();

            //if (accessUser.getPassword() != password) {
            if (bcrypt.checkpw(password, accessUser.getPassword())) {
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

        User accessUser;
        GenericDao<User> userDao = new GenericDao<>(User.class);

        // TODO enforce unique usernames
        // TODO handle new user name collisions
        List<User> matchingUsers = userDao.findByPropertyEqual("userName", userName);

        if (matchingUsers.size() == 1) {
            accessUser = matchingUsers.get(0);
            // TODO and is current user

            accessUser.setPassword(this.encryptPassword(password));
            userDao.saveOrUpdate(accessUser);
            isSet = true;

        } else {
            throw new Exception("Did not find a unique user for those credentials " + matchingUsers);
        }

        return isSet;
    }

//  Utility method for converting dev plaintext passwords to secure hashes
//  Some of these classes are deprecated but they worked fine for one time use
//    public void hashCurrentPasswords() {
//        BCrypt bcrypt = new BCrypt();
//        int id;
//        String raw_pass;
//        String selectQuery = "SELECT id, enc_pass FROM user";
//        String updateQuery = "UPDATE user SET enc_pass=:enc_pass WHERE id=:id";
//
//        Session session = SessionFactoryProvider.getSessionFactory().openSession();
//        Transaction tx = session.beginTransaction();
//        SQLQuery select = session.createSQLQuery(selectQuery);
//        List<Object[]> rows = select.list();
//        for (Object[] row : rows) {
//            id = Integer.parseInt(row[0].toString());
//            raw_pass = row[1].toString();
//
//            String enc_pass = bcrypt.hashpw(raw_pass, bcrypt.gensalt());
//
//            Session sessionUpdate = SessionFactoryProvider.getSessionFactory().openSession();
//            Transaction txUpdate = sessionUpdate.beginTransaction();
//            SQLQuery update = sessionUpdate.createSQLQuery(updateQuery);
//            update.setInteger("id", id);
//            update.setString("enc_pass", enc_pass);
//            int result = update.executeUpdate();
//            sessionUpdate.getTransaction().commit();
//        }
//    }
}
