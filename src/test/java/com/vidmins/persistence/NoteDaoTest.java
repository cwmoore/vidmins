package com.vidmins.persistence;

import com.vidmins.entity.Note;
import com.vidmins.entity.User;
import com.vidmins.entity.Video;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

/**
 * The type Note dao test.
 */
@TestInstance(Lifecycle.PER_METHOD)
class NoteDaoTest {

    static GenericDao<Note> dao;
    Logger logger = LogManager.getLogger(this.getClass());
    static com.vidmins.test.util.Database database;

    /**
     * Creating the dao.
     */
    @BeforeAll
    static void setUp() {
        System.out.println("NoteDaoTest.setUp()");
        dao = new GenericDao<>(Note.class);

        database = com.vidmins.test.util.Database.getInstance();
    }

    /**
     * Resetting the DB.
     */
    @BeforeEach
    void resetDb() {
        System.out.println("NoteDaoTest.resetDb()");
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


        int insertId = dao.insert(newNote);
        assertNotEquals(0, insertId);

        Note insertedNote = dao.getById(insertId);
        assertNotNull(insertedNote);
        assertEquals(noteLabel, insertedNote.getLabel());
        // assertEquals(video.getTitle(), insertedNote.getVideo().getTitle());
//        insertSuccess()  Time elapsed: 0.064 sec  <<< ERROR!
//                org.hibernate.LazyInitializationException: could not initialize proxy - no Session
//        at com.vidmins.persistence.NoteDaoTest.insertSuccess(NoteDaoTest.java:118)
// TODO find out why this doesn't work right

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

    /**
     * Verify successful get by property equal with 'order by'
     */
    /*
    @Test
    void getByPropertyEqualOrderBySuccess() {
        // TODO make for Note
        GenericDao<Video> videoDao = new GenericDao<>(Video.getClass());
        Video testVideo = videoDao.getAll().get(0);
        List<Note> notes = dao.findByPropertyEqualOrderBy("video", testVideo, "startTime", true);
        for (Note note : notes) {
            logger.debug(note.toString());
        }
        assertEquals(5, notes.size());
    }
    // TODO GIVE UP https://stackoverflow.com/questions/11771198/generic-search-criteria-type
    */
}
