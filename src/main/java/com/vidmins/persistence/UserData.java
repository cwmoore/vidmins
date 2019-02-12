package com.vidmins.persistence;

import com.vidmins.entity.*;

import java.sql.*;
import java.util.*;
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

            try {
                database.connect();
                connection = database.getConnection();
                PreparedStatement statement = connection.prepareStatement("SELECT * FROM user WHERE username=? AND enc_pass=?");
                statement.setString(1, username);
                statement.setString(2, password);
                ResultSet results = statement.executeQuery();

                if (results.next()) {
                    authenticatedUser = createUserFromResults(results);
                }

                results.close();
                statement.close();
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
        // load notes
        return video;
    }

    /**
     * Create a video from search result
     *
     * @param results the database results
     */
    private Directory createDirectoryFromResults(ResultSet results) throws SQLException {
        Directory directory = new Directory();
        directory.setId(Integer.parseInt(results.getString("id")));
        directory.setName(results.getString("name"));
        directory.setDescription(results.getString("description"));
        // load videos
        return directory;
    }

    /**
     * Create a user from search result
     *
     * @param results
     * @return the user data object
     * @throws SQLException
     */
    private User createUserFromResults(ResultSet results) throws SQLException {
        User user = new User(results);
//        user.setId(Integer.parseInt(results.getString("id")));
//        user.setFirstName(results.getString("firstName"));
//        user.setLastName(results.getString("lastName"));
//        user.setUserName(results.getString("username"));
//        user.setEmail(results.getString("email"));
//        user.setPassword(results.getString("enc_pass"));
//        user.setJoinDate(results.getString("joinDate"));
//        user.setDateOfBirth(results.getString("dateOfBirth"));
        // load directories
        return user;
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
        PreparedStatement statement = null;
        // consider GROUP BY youTubeId
        // duplication of a video may cause problems collecting user notes
        // if a user has entered the same video more than once
        //String sql = "SELECT * FROM video WHERE id IN (SELECT videoId FROM user_videos WHERE userId='" + user.getId() + "')";

        try {
            database.connect();
            connection = database.getConnection();
            statement = connection.prepareStatement("SELECT video.* FROM video JOIN user_videos ON video.id=user_videos.videoId WHERE user_videos.userId=?");
            statement.setInt(1, user.getId());
            ResultSet results = statement.executeQuery();

            while (results.next()) {
                videos.add(createVideoFromResults(results));
            }

            results.close();
            statement.close();
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
        PreparedStatement statement = null;

        try {
            database.connect();
            connection = database.getConnection();
            statement = connection.prepareStatement("SELECT * FROM note WHERE userId=? ORDER BY videoId DESC, start ASC, end ASC");
            statement.setInt(1, user.getId());
            ResultSet results = statement.executeQuery();

            while (results.next()) {
                notes.add(createNoteFromResults(results));
            }

            results.close();
            statement.close();
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
    public List<Note> getVideoNotes(int userId, int videoId) {
        List<Note> notes = new ArrayList<>();
        Database database = Database.getInstance();
        Connection connection = null;
        PreparedStatement statement = null;

        try {
            database.connect();
            connection = database.getConnection();
            statement = connection.prepareStatement("SELECT * FROM note WHERE userId=? AND videoId=? ORDER BY createDateTime DESC, start ASC, end ASC");
            statement.setInt(1, userId);
            statement.setInt(2, videoId);
            ResultSet results = statement.executeQuery();

            while (results.next()) {
                notes.add(createNoteFromResults(results));
            }

            results.close();
            statement.close();
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
    public Video getVideo(int videoId) {
        Video video = null;

        Database database = Database.getInstance();
        Connection connection = null;
        PreparedStatement statement = null;

        try {
            database.connect();
            connection = database.getConnection();
            statement = connection.prepareStatement("SELECT * FROM video WHERE id=? ORDER BY id ASC");
            statement.setInt(1, videoId);
            ResultSet results = statement.executeQuery();

            if (results.next()) {
                video = createVideoFromResults(results);
            }

            results.close();
            statement.close();
            database.disconnect();
        } catch (SQLException sqlException) {
            System.out.println("UserData.getVideoNotes():" + sqlException);
        } catch (Exception exception) {
            System.out.println("UserData.getVideoNotes():" + exception);
        }

        return video;
    }
}