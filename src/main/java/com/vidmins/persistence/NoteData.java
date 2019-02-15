package com.vidmins.persistence;

import com.vidmins.entity.*;
import org.apache.logging.log4j.*;

import java.sql.*;
import java.util.*;

/**
 * Handle notes CRUD
 *
 * @author cwmoore
 */
public class NoteData extends BaseData {

    public NoteData() {
        super();
    }

    /**
     * Create a user from search result
     *
     * @param results
     * @return the user data object
     * @throws SQLException
     */
    public Note createNoteFromResults(ResultSet results) throws SQLException {
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
            statement = connection.prepareStatement("SELECT * FROM note WHERE videoId=? ORDER BY createDateTime DESC, start ASC, end ASC");
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

    public Note fromId(int noteId) {
        Note note = null;

        Database database;
        PreparedStatement statement;
        ResultSet resultSet;
        String sqli = "SELECT id, label, text, start, end, createDateTime, videoId, userId FROM note WHERE id = ?";

        try {
            database = Database.getInstance();
            statement = database.getConnection().prepareStatement(sqli);
            statement.setInt(1, noteId);
            resultSet = statement.executeQuery();

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
            System.out.println("In NoteData.getNotesFromVideoId(): " + sqlException);
        } catch (Exception exception) {
            System.out.println("In NoteData.getNotesFromVideoId(): " + exception);
        }

        return notes;
    }

    public int setNewNoteFromAttributes(Map<String, String[]> noteFields) {
        int insertId = -1;

        ResultSet resultSet; // for insert ID
        newNoteCheckData(noteFields);

        try {
            //logger.debug("noteFields: ", noteFields.keySet(), noteFields.values());
            Database database = Database.getInstance();
            database.connect();
            String sqlNote = "INSERT INTO note (label, text, start, userId, videoId) VALUES " +
                    "(?, ?, ?, ?, ?)";
            // TODO: preprocess request parameters to pass in plain Strings, ![]s

            logger.debug("before getConnection()");
            // throws NullPointerException
            Connection connection = database.getConnection();
            logger.debug("before connection.prepareStatement()");
            PreparedStatement statement = connection.prepareStatement(sqlNote, Statement.RETURN_GENERATED_KEYS);
            logger.debug("after connection.prepareStatement()");

            String label = noteFields.get("label")[0];
            logger.debug("label: " + label);
            statement.setString(1, label);
            statement.setString(2, noteFields.get("note_text")[0]);
            statement.setInt(3, Integer.parseInt(noteFields.get("timeStampStart")[0]));
            /* statement.setInt(, Integer.parseInt(noteFields.get("timeStampEnd")[0])); */
            statement.setInt(4, Integer.parseInt(noteFields.get("userId")[0]));
            statement.setInt(5, Integer.parseInt(noteFields.get("videoId")[0]));
            logger.debug(statement.toString());

            if (1 == statement.executeUpdate()) {
                // insert seems to have worked
                // after: http://www.mysqltutorial.org/mysql-jdbc-insert/
                resultSet = statement.getGeneratedKeys();
                if (resultSet.next()) {
                    insertId = resultSet.getInt(1);
                }
                logger.debug("setNoteFromAttributes(): Insert note succeeded: ", insertId);
            } else {
                // insert failed, no rows affected
                logger.debug("setNoteFromAttributes(): Insert note failed: ", statement);
            }

            String sqlTags = "INSERT INTO tags " +
                    "(tag, objectId, objectType) VALUES " +
                    "(?, ?, 'note')";
            statement = database.getConnection().prepareStatement(sqlTags);
            // set tags on tags table
            for (String tag : noteFields.get("tags")) {
                statement.setString(1, tag);
                statement.setInt(2, insertId);
                statement.executeUpdate();
            }


            logger.debug("statement: ", statement);

            statement.close();
            database.disconnect();

        } catch (SQLException sqlException) {
            System.out.println("In NoteData.setNewNoteFromAttributes(): " + sqlException);
        } catch (Exception exception) {
            System.out.println("In NoteData.setNewNoteFromAttributes(): " + exception);
        }

        return insertId;
    }

    public Note setNewNote(Note newNote) {
        int insertId = -1;

        // tags go in another table
        String sql = "INSERT INTO note (label, text, start, userId, videoId) VALUES (?, ?, ?, ?, ?)";
        Database database = Database.getInstance();
        Connection connection;
        PreparedStatement statement;
        ResultSet resultSet;

        logger.debug("note: " + newNote);

        try {
            database.connect();
        } catch (Exception exception) {
            logger.debug("database.connect(): " + exception.toString());
        }

        try {
            connection = database.getConnection();

            //logger.debug("Check Database: " + database.toString());
            //logger.debug("Check Database Connection: " + database.getConnection().toString());

            logger.debug("Check Note: " + newNote);
            statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            logger.debug("Empty statement: " + sql + "\n" + statement.toString());

            statement.setString(1, newNote.getLabel());
            statement.setString(2, newNote.getText());
            statement.setInt(3, newNote.getStart());
            /*statement.setInt(, newNote.getEnd());*/
            statement.setInt(4, newNote.getUserId());
            statement.setInt(5, newNote.getVideoId());

            logger.debug("Complete statement: " + sql + statement.toString());

            if (1 == statement.executeUpdate()) {
                // insert seems to have worked
                // after: http://www.mysqltutorial.org/mysql-jdbc-insert/
                resultSet = statement.getGeneratedKeys();
                if (resultSet.next()) {
                    newNote.setId(resultSet.getInt(1));
                }
                logger.debug("setNewNote(): Insert note succeeded: ", insertId);
            } else {
                // insert failed, no rows affected
                logger.debug("setNewNote(): Insert note failed: ", statement);
            }

            statement.close();
            database.disconnect();

        } catch (SQLException sqlException) {
            logger.debug("In NoteData.setNewNote(): " + sql +  "\n" + sqlException);
        } catch (Exception exception) {
            logger.debug("In NoteData.setNewNote(): " + sql + "\n" + exception);
        }


        return newNote;
    }

    public void newNoteCheckData(Map<String, String[]> noteFields) {
        for (Map.Entry<String, String[]> entry : noteFields.entrySet()) {
            logger.debug(entry.getKey());
            String vals = "";
            for (String val : entry.getValue()) {
                vals += val + ", ";
            }
            logger.debug(vals + "\n");
        }
    }
}