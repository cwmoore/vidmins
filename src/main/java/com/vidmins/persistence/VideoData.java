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
public class VideoData extends BaseData {


    /**
     * Create a video from search result
     *
     * @param results the database results
     */
    public Video createVideoFromResults(ResultSet results) throws SQLException {
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
     * Gets video notes.
     *
     * @param videoId the video id
     * @return the video notes
     */
    public List<Note> getVideoNotes(int userId, int videoId) {
        NoteData noteData = new NoteData();
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
                notes.add(noteData.createNoteFromResults(results));
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
    public Video fromId(int videoId) {
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