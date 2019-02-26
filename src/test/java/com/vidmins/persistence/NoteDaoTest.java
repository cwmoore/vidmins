package com.vidmins.persistence;

import com.vidmins.persistence.GenericDao;
import com.vidmins.entity.Note;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

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