package com.vidmins.persistence;

import com.vidmins.entity.*;
import org.apache.logging.log4j.*;

import java.sql.*;
import java.util.*;
//import com.mysql.jdbc.*;
// import java.security.*;

/**
 * Handle notes CRUD
 *
 * @author cwmoore
 */
public class NoteData {
    private Logger logger;

    public NoteData() {
        logger = LogManager.getLogger(this.getClass());
        logger.info("NodeData logger");}

    /**
     * Create a user from search result
     *
     * @param results
     * @return the user data object
     * @throws SQLException
     */
    private Note createNoteFromResults(ResultSet results) throws SQLException {
        Note note = new Note();
        note.setId(Integer.parseInt(results.getString("id")));
        note.setLabel(results.getString("label"));
        note.setText(results.getString("text"));
        // note.setTags(results.getString("tags"));
        note.setStart(results.getInt("start"));
        note.setEnd(results.getInt("end"));
        note.setCreateDatetime(results.getString("createDateTime"));
        note.setUserId(results.getInt("userId"));
        note.setVideoId(results.getInt("videoId"));
        return note;
    }

    /**
     * Gets video notes.
     *
     * @param videoId the video id
     * @return the video notes
     */
    public List<Note> getVideoNotes(int videoId) {
        List<Note> notes = new ArrayList<>();
        Database database = Database.getInstance();
        Connection connection = null;
        PreparedStatement statement = null;

        try {
            database.connect();
            connection = database.getConnection();
            statement = connection.prepareStatement("SELECT * FROM note WHERE videoId=? ORDER BY start ASC, end ASC");
            statement.setInt(1, videoId);
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

    public Note getNoteFromId(int noteId) {
        Note note = null;

        try {
            Database database = Database.getInstance();
            String sqli = "SELECT id, label, text, start, end, createDateTime, videoId, userId FROM note WHERE id = ?";
            PreparedStatement statement = database.getConnection().prepareStatement(sqli);
            statement.setInt(1, noteId);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                note = createNoteFromResults(resultSet);
            }

            resultSet.close();
            statement.close();
            database.disconnect();

        } catch (SQLException sqlException) {
            System.out.println("In NoteData.noteFromId(): " + sqlException);
        } catch (Exception exception) {
            System.out.println("In NoteData.noteFromId(): " + exception);
        }

        return note;
    }

    public List<Note> getNotesFromVideoId(int videoId) {
        List<Note> notes = new ArrayList<>();

        try {
            Database database = Database.getInstance();
            String sqli = "SELECT id, label, text, start, end, createDateTime, videoId, userId FROM note WHERE videoId = ?";
            PreparedStatement statement = database.getConnection().prepareStatement(sqli);
            statement.setInt(1, videoId);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                notes.add(createNoteFromResults(resultSet));
            }

            resultSet.close();
            statement.close();
            database.disconnect();

        } catch (SQLException sqlException) {
            System.out.println("In NoteData.notesFromVideoId(): " + sqlException);
        } catch (Exception exception) {
            System.out.println("In NoteData.notesFromVideoId(): " + exception);
        }

        return notes;
    }

    public int setNewNote(Map<String, String[]> noteFields) {
        int insertId = -1;

        try {
            Database database = Database.getInstance();
            String sqli = "INSERT INTO note (label, text, tag, start, end, videoId, userId) VALUES " +
                    "(?, ?, ?, ?, ?, ?, ?)";
            // TODO: preprocess request parameters to pass in plain Strings, ![]s
            PreparedStatement statement = database.getConnection().prepareStatement(sqli);
            statement.setString(1, noteFields.get("label")[0]);
            statement.setString(2, noteFields.get("note_text")[0]);
            statement.setString(3, noteFields.get("tag")[0]);
            statement.setInt(4, Integer.parseInt(noteFields.get("timeStampStart")[0]));
            statement.setInt(5, Integer.parseInt(noteFields.get("timeStampEnd")[0]));
            statement.setInt(6, Integer.parseInt(noteFields.get("videoId")[0]));
            statement.setInt(7, Integer.parseInt(noteFields.get("userId")[0]));
            insertId = statement.executeUpdate();

            logger.debug(statement);

            statement.close();
            database.disconnect();

        } catch (SQLException sqlException) {
            System.out.println("In NoteData.notesFromVideoId(): " + sqlException);
        } catch (Exception exception) {
            System.out.println("In NoteData.notesFromVideoId(): " + exception);
        }

        return insertId;
    }
}