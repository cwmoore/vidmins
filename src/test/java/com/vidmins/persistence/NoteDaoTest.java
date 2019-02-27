package com.vidmins.persistence;

import com.vidmins.entity.Video;
import com.vidmins.persistence.GenericDao;
import com.vidmins.entity.Note;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class NoteDaoTest {

    GenericDao<Note> dao;

    @BeforeEach
    void setUp() {
        dao = new GenericDao<>(Note.class);
    }

    @Test
    void getAllSuccess() {
        List<Note> notes = dao.getAll();

        assertNotNull(notes);
        assertEquals(60, notes.size());
    }

    @Test
    void getByIdSuccess() {
        Note note = dao.getById(1);
        assertNotNull(note);
        assertEquals("MarkI", note.getLabel());
    }

    @Test
    void delete() {
    }

    @Test
    void insert() {
        GenericDao<Video> videoDao = new GenericDao<>(Video.class);
        Video video = videoDao.getById(2);
        Note newNote = new Note("Label", "Text text text", 0, LocalDateTime.now(), video);
        video.getNotes().add(newNote);

        int id = dao.insert(newNote);

        assertNotEquals(0,id);
        Note insertedNote = dao.getById(id);
        assertEquals("Label", insertedNote.getLabel());
        assertNotNull(insertedNote.getVideo());
        assertEquals("Week1Act5", insertedNote.getVideo().getTitle());
    }

    @Test
    void saveOrUpdate() {
    }

    @Test
    void findByPropertyEqual() {
    }

    @Test
    void findByPropertyEqual1() {
    }
}