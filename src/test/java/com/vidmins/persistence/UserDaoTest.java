package com.vidmins.persistence;

import com.vidmins.entity.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * The type User dao test.
 */
class UserDaoTest {

    GenericDao<User> dao;
    Logger logger = LogManager.getLogger(this.getClass());

    /**
     * Creating the dao.
     */
    @BeforeEach
    void setUp() {
        dao = new GenericDao<>(User.class);
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
     * Verifies gets all orders successfully.
     */
    @Test
    void getAllUsersSuccess() {
        List<User> users = dao.getAll();
        assertEquals(6, users.size());
    }

    /**
     * Verifies a order is returned correctly based on id search
     */
    @Test
    void getByIdSuccess() {
        User user = dao.getById(6);
        assertNotNull(user);
        assertEquals("dtillman", user.getUserName());
    }

    /**
     * Verifies a order is returned correctly based on id search
     */
    @Test
    void getByIdVerifyUserSuccess() {
        User user = dao.getById(3);
        assertNotNull(user);
        assertEquals("bcurry", user.getUserName());
        //assertEquals(1, user.getDirectories().size());
    }


    /**
     * Verify successful delete of order
     */
    @Test
    void deleteSuccess() {
        int deleteId = 4;

        // make this user
        User user = dao.getById(deleteId);
        assertNotNull(user);
        // disappear
        dao.delete(user);
        assertNull(dao.getById(deleteId));
    }

    /**
     * Verify successful update of order
     */
    @Test
    void updateSuccess() {
        int updateId = 5;
        String newUserName = "DKLEIN";
        User userToUpdate = dao.getById(updateId);
        userToUpdate.setUserName(newUserName);
        dao.saveOrUpdate(userToUpdate);

        User retrievedUser = dao.getById(updateId);
        assertEquals(newUserName, retrievedUser.getUserName());
    }

    /**
     * Verify successful insert
     */
    @Test
    void insertSuccess() {

        String userName = "TestUserInsert";
        User newUser = new User();
        newUser.setUserName(userName);

        int insertId = dao.insert(newUser);
        assertNotEquals(0, insertId);

        User insertedUser = dao.getById(insertId);
        assertNotNull(insertedUser);
        assertEquals(userName, insertedUser.getUserName());

        // Could continue comparing all values, but
        // it may make sense to use .equals()
        // TODO review .equals recommendations http://docs.jboss.org/hibernate/orm/5.2/userguide/html_single/Hibernate_User_Guide.html#mapping-model-pojo-equalshashcode
    }

    /**
     * Verify successful get by property (equal match)
     */
    @Test
    void getByPropertyEqualSuccess() {
        // TODO make for User
        List<User> users = dao.findByPropertyEqual("firstName", "Fred");
        assertEquals(1, users.size());
        assertEquals(2, users.get(0).getId());
    }
//
//    /**
//     * Verify successful get by property (like match)
//     */
//    @Test
//    void getByPropertyLikeSuccess() {
//        List<User> users = dao.findByPropertyLike("userName", "c");

//        assertEquals(4, users.size());
//    }
}
