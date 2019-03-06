package com.vidmins.persistence;

import com.vidmins.entity.Note;
import com.vidmins.entity.User;
import com.vidmins.entity.Video;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

/**
 * The type Note dao test.
 */
class NoteDaoTest {

    GenericDao<Note> dao;
    Logger logger = LogManager.getLogger(this.getClass());

    /**
     * Creating the dao.
     */
    @BeforeEach
    void setUp() {
        dao = new GenericDao<>(Note.class);
//        try {
//            ProcessBuilder process = new ProcessBuilder("~/IdeaProjects/vidmins/src/test/resources/reset_test_db.sh");
//            process.start();
//            logger.debug("ProcessBuilder started UserDaoTest");
//        } catch (IOException ioException) {
//            logger.debug("ProcessBuilder failed to run in UserDaoTest");
//        }
        // would have to reenter my databases content into arrays
//        CleanTestDB cleanTestDB = new CleanTestDB();
//        cleanTestDB.testDbClean();

        com.vidmins.test.util.Database database = com.vidmins.test.util.Database.getInstance();
        database.runSQL("reset_db.sql");
    }

    /**
     * Verifies gets all notes successfully.
     */
    @Test
    void getAllNotesSuccess() {
        List<Note> notes = dao.getAll();
        assertEquals(4, notes.size());
    }

    /**
     * Verifies a note is returned correctly based on id search
     */
    @Test
    void getByIdSuccess() {
        Note note = dao.getById(1);
        assertNotNull(note);
    }

    /**
     * Verifies a note is returned correctly based on id search
     */
    @Test
    void getByIdVerifyNoteSuccess() {
        Note note = dao.getById(3);
        assertNotNull(note);
        assertEquals("noticia", note.getLabel());
    }


    /**
     * Verify successful delete of note
     */
    @Test
    void deleteSuccess() {
        int deleteId = 2;

        // make this note
        Note note = dao.getById(deleteId);
        assertNotNull(note);
        // disappear
        dao.delete(note);
        assertNull(dao.getById(deleteId));
    }

    /**
     * Verify successful update of note
     */
    @Test
    void updateSuccess() {
        int updateId = 1;
        String newNoteLabel = "NEW NOTE LABEL";
        Note noteToUpdate = dao.getById(updateId);
        noteToUpdate.setLabel(newNoteLabel);
        dao.saveOrUpdate(noteToUpdate);

        Note retrievedNote = dao.getById(updateId);
        assertEquals(newNoteLabel, retrievedNote.getLabel());
    }

    /**
     * Verify successful insert
     */
    @Test
    void insertSuccess() {

        Note newNote = new Note();

        String noteLabel = "TestNoteInsert";
        newNote.setLabel(noteLabel);

        GenericDao<Video> videoDao = new GenericDao<>(Video.class);
        Video video = videoDao.getById(3);
        newNote.setVideo(video);

        GenericDao<User> userDao = new GenericDao<>(User.class);
        User author = userDao.getById(3);
        newNote.setAuthor(author);

        int insertId = dao.insert(newNote);
        assertNotEquals(0, insertId);

        Note insertedNote = dao.getById(insertId);
        assertNotNull(insertedNote);
        assertEquals(noteLabel, insertedNote.getLabel());
        assertEquals(video.getTitle(), insertedNote.getVideo().getTitle());
        assertEquals(author.getUserName(), insertedNote.getAuthor().getUserName());

        // Could continue comparing all values, but
        // it may make sense to use .equals()
        // TODO review .equals recommendations http://docs.jboss.org/hibernate/orm/5.2/noteguide/html_single/Hibernate_Note_Guide.html#mapping-model-pojo-equalshashcode
    }

    /**
     * Verify successful get by property (equal match)
     */
    @Test
    void getByPropertyEqualSuccess() {
        // TODO make for Note
        List<Note> notes = dao.findByPropertyEqual("label", "nota");
        assertEquals(1, notes.size());
        assertEquals(4, notes.get(0).getId());
    }

    /**
     * Verify successful get by property (like match)
     */
    @Test
    void getByPropertiesEqualSuccess() {
        Map<String, Object> searchNote = new HashMap<>();
        searchNote.put("label", "nota");

        List<Note> notes = dao.findByPropertyEqual(searchNote);

        assertEquals(1, notes.size());
    }
}
