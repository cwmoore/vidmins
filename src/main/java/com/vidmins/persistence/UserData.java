package com.vidmins.persistence;

import com.vidmins.entity.*;

import java.util.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
// import java.security.*;

/**
 * Access users in the user table.
 *
 * @author pwaite
 *
 * Authenticate a valid user, get videos, notes
 * @author cwmoore
 */
public class UserData {

    /**
     * Encrypt a pass phrase with SHA-512
     * Adapted from: https://www.baeldung.com/java-password-hashing
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
     * @param username the username
     * @param password the password
     * @return an authenticated user
     */
    public User authenticateUser(String username, String password) {
        User authenticatedUser = null;

        if (username != "") {
            Database database = Database.getInstance();
            Connection connection = null;
            String sql = "SELECT * FROM user WHERE username = '" + username + "' AND enc_pass = '" + password + "'";

            try {
                database.connect();
                connection = database.getConnection();
                Statement selectUserAuthStatement = connection.createStatement();
                ResultSet results = selectUserAuthStatement.executeQuery(sql);

                if (results.next()) {
                    authenticatedUser = createUserFromResults(results);
                }

                database.disconnect();
            } catch (SQLException sqlException) {
                System.out.println("LoadClient.authenticateUser():" + sqlException);
            } catch (Exception exception) {
                System.out.println("LoadClient.authenticateUser():" + exception);
            }
        }

        return authenticatedUser;
    }

    /**
     * Create a user from search result
     * @param results
     * @return the user data object
     * @throws SQLException
     */
    private User createUserFromResults(ResultSet results) throws SQLException {
        User user = new User();
        user.setId(Integer.parseInt(results.getString("id")));
        user.setFirstName(results.getString("firstName"));
        user.setLastName(results.getString("lastName"));
        user.setUserName(results.getString("username"));
        user.setPassword(results.getString("enc_pass"));
        user.setDateOfBirth(results.getString("dateOfBirth"));
        return user;
    }

    public List<Video> getUserVideos(User user) {
        List<Video> videos = new ArrayList<>();
        Database database = Database.getInstance();
        Connection connection = null;
        // consider GROUP BY youTubeId
        // duplication of a video may cause problems collecting user notes
        // if a user has entered the same video more than once
        String sql = "SELECT * FROM video WHERE id IN (SELECT videoId FROM user_videos WHERE userId='" + user.getId() + "')";

        try {
            database.connect();
            connection = database.getConnection();
            Statement selectUserAuthStatement = connection.createStatement();
            ResultSet results = selectUserAuthStatement.executeQuery(sql);

            while (results.next()) {
                videos.add(new Video(results));
            }

            database.disconnect();
        } catch (SQLException sqlException) {
            System.out.println("LoadClient.getUserVideos():" + sqlException);
        } catch (Exception exception) {
            System.out.println("LoadClient.getUserVideos():" + exception);
        }
        return videos;
    }

    public List<Note> getUserNotes(User user) {
        List<Note> notes = new ArrayList<>();
        Database database = Database.getInstance();
        Connection connection = null;

        String sql = "SELECT * FROM note WHERE userId=" + user.getId() + " ORDER BY videoId DESC, start ASC";

        try {
            database.connect();
            connection = database.getConnection();
            Statement selectUserAuthStatement = connection.createStatement();
            ResultSet results = selectUserAuthStatement.executeQuery(sql);

            while (results.next()) {
                notes.add(new Note(results));
            }

            database.disconnect();
        } catch (SQLException sqlException) {
            System.out.println("LoadClient.getUserNotes():" + sqlException);
        } catch (Exception exception) {
            System.out.println("LoadClient.getUserNotes():" + exception);
        }
        return notes;
    }
}