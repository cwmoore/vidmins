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
                System.out.println("UserData.authenticateUser():" + sqlException);
            } catch (Exception exception) {
                System.out.println("UserData.authenticateUser():" + exception);
            }
        }

        return authenticatedUser;
    }

    /**
     * Create a user from search result
     *
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
        user.setEmail(results.getString("email"));
        user.setPassword(results.getString("enc_pass"));
        user.setJoinDate(results.getString("joinDate"));
        user.setDateOfBirth(results.getString("dateOfBirth"));
        return user;
    }

    /**
     * Create a note from search result
     *
     * @param results the database results
     */
    private Note createNoteFromResults(ResultSet results) throws SQLException {
        Note note = new Note();
        note.setId(results.getInt("id"));
        note.setLabel(results.getString("label"));
        note.setText(results.getString("text"));
        note.setStart(results.getInt("start"));
        note.setEnd(results.getInt("end"));
        note.setCreateDatetime(results.getString("createDatetime"));
        note.setUserId(results.getInt("userId"));
        note.setVideoId(results.getInt("videoId"));
        return note;
    }

    /**
     * Create a video from search result
     *
     * @param results the database results
     */
    private Video createVideoFromResults(ResultSet results) throws SQLException {
        Video video = new Video();
        video.setId(Integer.parseInt(results.getString("id")));
        video.setYouTubeId(results.getString("youTubeId"));
        video.setTitle(results.getString("title"));
        video.setAddDate(results.getString("addDate"));
        video.setDuration(Integer.parseInt(results.getString("duration")));
        return video;
    }

    /**
     * Gets user videos.
     *
     * @param user the user
     * @return the user videos
     */
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
                videos.add(createVideoFromResults(results));
            }

            database.disconnect();
        } catch (SQLException sqlException) {
            System.out.println("UserData.getUserVideos():" + sqlException);
        } catch (Exception exception) {
            System.out.println("UserData.getUserVideos():" + exception);
        }
        return videos;
    }

    /**
     * Gets user notes.
     *
     * @param user the user
     * @return the user notes
     */
    public List<Note> getUserNotes(User user) {
        List<Note> notes = new ArrayList<>();
        Database database = Database.getInstance();
        Connection connection = null;

        String sql = "SELECT * FROM note WHERE userId=" + user.getId() + " ORDER BY videoId DESC, start ASC, end ASC";

        try {
            database.connect();
            connection = database.getConnection();
            Statement selectUserAuthStatement = connection.createStatement();
            ResultSet results = selectUserAuthStatement.executeQuery(sql);

            while (results.next()) {
                notes.add(createNoteFromResults(results));
            }

            database.disconnect();
        } catch (SQLException sqlException) {
            System.out.println("UserData.getUserNotes():" + sqlException);
        } catch (Exception exception) {
            System.out.println("UserData.getUserNotes():" + exception);
        }
        return notes;
    }

    /**
     * Gets video notes.
     *
     * @param videoId the video id
     * @return the video notes
     */
    public List<Note> getVideoNotes(String videoId) {
        List<Note> notes = new ArrayList<>();
        Database database = Database.getInstance();
        Connection connection = null;

        // !!!!!!!!!! TODO use prepared statement with wild data !!!!!!!!!!
        String sql = "SELECT * FROM note WHERE videoId='" + videoId + "' ORDER BY start ASC, end ASC";

        try {
            database.connect();
            connection = database.getConnection();
            Statement selectUserAuthStatement = connection.createStatement();
            ResultSet results = selectUserAuthStatement.executeQuery(sql);

            while (results.next()) {
                notes.add(createNoteFromResults(results));
            }

            database.disconnect();
        } catch (SQLException sqlException) {
            System.out.println("UserData.getVideoNotes():" + sqlException);
        } catch (Exception exception) {
            System.out.println("UserData.getVideoNotes():" + exception);
        }
        return notes;
    }

    /**
     * Gets video notes.
     *
     * @param videoId the video id
     * @return the video notes
     */
    public Video getVideo(String videoId) {
        Video video = null;

        Database database = Database.getInstance();
        Connection connection = null;

        String sql = "SELECT * FROM video WHERE id='" + videoId + "' ORDER BY id ASC";

        try {
            database.connect();
            connection = database.getConnection();
            Statement selectUserAuthStatement = connection.createStatement();
            ResultSet results = selectUserAuthStatement.executeQuery(sql);

            if (results.next()) {
                video = createVideoFromResults(results);
            }

            database.disconnect();
        } catch (SQLException sqlException) {
            System.out.println("UserData.getVideoNotes():" + sqlException);
        } catch (Exception exception) {
            System.out.println("UserData.getVideoNotes():" + exception);
        }

        return video;
    }
}