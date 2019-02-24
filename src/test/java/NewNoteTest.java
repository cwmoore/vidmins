package edu.matc.entjava;

import com.vidmins.controller.NewNote;

import com.vidmins.entity.Note;
import com.vidmins.persistence.NoteData;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.*;

/** Unit test class for the InverseCaptcha.
 *  This class is used to
 *  1. verify the individual methods of InverseCaptcha work properly using a few test string that were provided in the project specs, and
 *  2. determine the final answer to the Captcha for yourPuzzleInput.txt.
 */

class NoteDataTest {
    Logger logger = LogManager.getLogger(this.getClass());

    NoteData noteDao;

    /**
     * Create a new InverseCaptcha object before each test is run.
     */
    @BeforeEach
    void setUp() {
        noteDao = new NoteData();

    }

    /**
     * Determine that a note is returned from NoteData.fromId(id)
     */
    @Test
    void getNoteFromId() {
        // assumes note exists with id=1
        int id = 1;
        Note note = noteDao.fromId(id);
        logger.debug("noteeee" + note);//.getClass().toString());
//        assertNotNull(note);
//        assertEquals(id, note.getId());
    }
//
//    /**
//     * Test for NumberFormatException
//     */
//    @Test
//    void checkDigitsThrowsNumberFormatException() {
//        assertThrows(NumberFormatException.class, () -> {
//            captcha.stringToList("8134nnnnn9134");
//        });
//    }
}