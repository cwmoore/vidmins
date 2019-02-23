package com.vidmins.persistence;

import com.vidmins.entity.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
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
public class UserData extends BaseData {
    SessionFactory sessionFactory = new SessionFactoryProvider.getSessionFactory();
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

        if (userName != "") {
            Database database = Database.getInstance();
            Connection connection = null;

            try {
                database.connect();
                connection = database.getConnection();
                PreparedStatement statement = connection.prepareStatement("SELECT * FROM user WHERE userName=? AND enc_pass=?");
                statement.setString(1, userName);
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
//        user.setUserName(results.getString("userName"));
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
        VideoData videoData = new VideoData();
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
                videos.add(videoData.createVideoFromResults(results));
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
        NoteData noteData = new NoteData();
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
                notes.add(noteData.createNoteFromResults(results));
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

    public User fromId(int id) {
        return new User();
    }

    public List<User> getAllUsers() {
        Session session = sessionFactory.openSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<User> query = builder.createQuery(User.class);
        Root<User> root = query.from(User.class);
        List<User> users = session.createQuery(query).getResultList();
        session.close();
        return users;
    }
}