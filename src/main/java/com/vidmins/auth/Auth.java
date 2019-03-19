package com.vidmins.auth;

import com.vidmins.entity.User;
import com.vidmins.persistence.GenericDao;

import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.security.crypto.bcrypt.BCrypt;

public class Auth {

    Logger logger = LogManager.getLogger(this.getClass());

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
            logger.debug("Using: " + userName + ", found: " + matchingUsers.get(0));
            accessUser = matchingUsers.get(0);

            BCrypt bcrypt = new BCrypt();

            if (!bcrypt.checkpw(password, accessUser.getPassword())) {
                logger.debug("Not a match: " + userName + "'s password isn't " + matchingUsers.get(0));
                accessUser = null;
            }

        } else {
            throw new Exception("Did not find a unique user for those credentials " + matchingUsers);
        }

        return accessUser;
    }

    public static boolean setUserHashPass(User user, String password) throws Exception {

        boolean isSet = false;

        User accessUser;
        GenericDao<User> userDao = new GenericDao<>(User.class);

        List<User> matchingUsers = userDao.findByPropertyEqual("userName", user.getUserName());

        if (matchingUsers.size() == 1) {
            accessUser = matchingUsers.get(0);
            // TODO and is current user
            if (accessUser.equals(user)) {

                BCrypt bcrypt = new BCrypt();
                user.setPassword(bcrypt.hashpw(password, bcrypt.gensalt()));
                userDao.saveOrUpdate(user);
                isSet = true;
            }

        } else {
            // TODO handle new user name collisions
            throw new Exception("Did not find a unique user for those credentials " + matchingUsers);
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
