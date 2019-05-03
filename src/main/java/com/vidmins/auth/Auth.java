package com.vidmins.auth;

import com.vidmins.entity.Role;
import com.vidmins.entity.User;
import com.vidmins.persistence.GenericDao;

import java.util.List;

import org.apache.catalina.realm.SecretKeyCredentialHandler;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * The type Auth.
 */
public class Auth {

    /**
     * The Logger.
     */
    Logger logger = LogManager.getLogger(this.getClass());

    /**
     * Instantiates a new Auth.
     */
    public Auth() {
        super();
    }
//
//    /**
//     * Change password boolean.
//     *
//     * @param userName    the user name
//     * @param oldPassword the old password
//     * @param newPassword the new password
//     * @return true if successfully changed, false otherwise
//     */
//    public boolean changePassword(String userName, String oldPassword, String newPassword) throws Exception {
//        if (authenticateUser(userName, oldPassword) != null) {
//            GenericDao<User> userDao = new GenericDao<>(User.class);
//
//            List<User> matchingUsers = userDao.findByPropertyEqual("userName", userName);
//            if (matchingUsers.size() == 1) {
//                User accessUser = matchingUsers.get(0);
//
//                return setUserHashPass(accessUser, newPassword);
//            }
//        }
//        return false;
//    }
//
//    /**
//     * Authenticate user credentials
//     *
//     * @param userName the user name
//     * @param password the password
//     * @return an authenticated user
//     * @throws Exception the exception
//     */
//    public User authenticateUser(String userName, String password)
//            throws Exception {
//        User accessUser = null;
//        GenericDao<User> userDao = new GenericDao<>(User.class);
//
//        List<User> matchingUsers = userDao.findByPropertyEqual("userName", userName);
//
//
//        if (matchingUsers.size() == 1) {
//            logger.debug("Using: " + userName + ", found: " + matchingUsers.get(0));
//            accessUser = matchingUsers.get(0);
//
//            SecretKeyCredentialHandler credentialHandler = new SecretKeyCredentialHandler();
//
//            if (!credentialHandler.matches(password, accessUser.getPassword())) {
//                logger.debug("Not a match: " + userName + "'s password isn't " + accessUser);
//                accessUser = null;
//            } else {
//                logger.debug("Got a match: " + accessUser);
//            }
//
//        } else {
//            throw new Exception("Did not find a unique user for those credentials " + matchingUsers);
//        }
//
//        return accessUser;
//    }

    /**
     * Sets user hash pass.
     *
     * @param user     the user
     * @param password the password
     * @return the user hash pass
     * @throws Exception the exception
     */
    public boolean setUserHashPass(User user, String password) {

        boolean isSet = false;

        GenericDao<User> userDao = new GenericDao<>(User.class);
        List<User> matchingUsers = userDao.findByPropertyEqual("userName", user.getUserName());

        if (matchingUsers.size() == 1) {

            try {
                SecretKeyCredentialHandler credentialHandler = new SecretKeyCredentialHandler();
                String enc_pass = credentialHandler.mutate(password);

                user.setPassword(enc_pass);
                userDao.saveOrUpdate(user);

                GenericDao<Role> roleDao = new GenericDao<>(Role.class);
                Role role = new Role(user, "local");
                roleDao.saveOrUpdate(role);

                isSet = true;

            } catch (Exception e) {
                logger.debug("Something wrong with the credential handler ", e);
            }

        } else {
            // TODO handle new username collisions
            logger.debug("A user with that name already exists ", matchingUsers);
        }

        return isSet;
    }

//  Utility method for converting dev plaintext passwords to secure hashes
//  Some of these classes are deprecated but they worked fine for one time use

            // uses these imports
            //import java.security.SecureRandom;
            //import java.util.HashMap;
            //import java.util.Map;
            //
            //import com.vidmins.persistence.SessionFactoryProvider;
            //import org.hibernate.SQLQuery;
            //import org.hibernate.Session;
            //import org.hibernate.SessionFactory;
            //import org.hibernate.Transaction;

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
