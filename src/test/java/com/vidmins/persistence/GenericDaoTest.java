package com.vidmins.persistence;

import com.vidmins.persistence.GenericDao;
import com.vidmins.entity.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class GenericDaoTest {

    GenericDao<User> dao;

    @BeforeEach
    void setUp() {
        dao = new GenericDao<>(User.class);
    }

    @Test
    void getAllSuccess() {
        List<User> users = dao.getAll();

        assertNotNull(users);
        assertEquals(6, users.size());
    }

    @Test
    void getByIdSuccess() {
        User user = dao.getById(3); // TODO verify GenericDao returns correct type?
        assertNotNull(user);
        assertEquals("cwmoore", user.getUserName());
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