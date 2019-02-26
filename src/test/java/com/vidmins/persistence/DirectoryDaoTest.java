package com.vidmins.persistence;

import com.vidmins.persistence.GenericDao;
import com.vidmins.entity.Directory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class DirectoryDaoTest {

    GenericDao<Directory> dao;

    @BeforeEach
    void setUp() {
        dao = new GenericDao<>(Directory.class);
    }

    @Test
    void getAllSuccess() {
        List<Directory> directories = dao.getAll();

        assertNotNull(directories);
        assertEquals(2, directories.size());
    }

    @Test
    void getByIdSuccess() {
        Directory directory = dao.getById(1);
        assertNotNull(directory);
        assertEquals("default", directory.getName());
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