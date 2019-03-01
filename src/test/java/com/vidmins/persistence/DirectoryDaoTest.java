package com.vidmins.persistence;

import com.vidmins.entity.Directory;
import com.vidmins.entity.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * The type Directory dao test.
 */
class DirectoryDaoTest {

    GenericDao<Directory> dao;

    /**
     * Creating the dao.
     */
    @BeforeEach
    void setUp() {
        dao = new GenericDao<>(Directory.class);

        // doesn't like mysql authentication or work well with command line passwords
        ProcessBuilder process = new ProcessBuilder("~/IdeaProjects/vidmins/src/test/resources/reset_test_db.sh");

        // would have to reenter my databases content into arrays
//        CleanTestDB cleanTestDB = new CleanTestDB();
//        cleanTestDB.testDbClean();

//        com.vidmins.test.util.Database database = com.vidmins.test.util.Database.getInstance();
//        database.runSQL("reset_db.sql");
    }

    /**
     * Verifies gets all orders successfully.
     */
    @Test
    void getAllDirectoriesSuccess() {
        List<Directory> directories = dao.getAll();
        assertEquals(4, directories.size());
    }

    /**
     * Verifies a order is returned correctly based on id search
     */
    @Test
    void getByIdSuccess() {
        Directory directory = dao.getById(1);
        assertNotNull(directory);
        assertEquals("default", directory.getName());
    }

    /**
     * Verifies a order is returned correctly based on id search
     */
    @Test
    void getByIdVerifyDirectorySuccess() {
        Directory directory = dao.getById(2);
        assertNotNull(directory);
        assertEquals("new dir", directory.getName());
    }

//
//    /**
//     * Verify successful delete of order
//     */
//    @Test
//    void deleteSuccess() {
//        int deleteId = 3;
//        Directory directory = dao.getById(deleteId);
//        assertNotNull(directory);
//
//        dao.delete(directory);
//        assertNull(dao.getById(deleteId));
//    }

    /**
     * Verify successful update of order
     */
    @Test
    void updateSuccess() {
        int updateId = 1;
        String newDirectoryName = "TestDirectoryModded";
        Directory directoryToUpdate = dao.getById(updateId);
        directoryToUpdate.setName(newDirectoryName);
        dao.saveOrUpdate(directoryToUpdate);
        Directory retrievedDirectory = dao.getById(updateId);
        assertEquals(newDirectoryName, retrievedDirectory.getName());
    }

    /**
     * Verify successful insert
     */
    @Test
    void insertSuccess() {
        User owner = new User(8, "DirectoryOwner");
        GenericDao<User> userDao = new GenericDao<>(User.class);

        assertNotNull(userDao.getById(userDao.insert(owner)));

        String directoryName = "TestDirectoryInsert";
        Directory newDirectory = new Directory();
        newDirectory.setName(directoryName);
        newDirectory.setUser(owner);

        int insertId = dao.insert(newDirectory);
        assertNotEquals(0, insertId);

        Directory insertedDirectory = dao.getById(insertId);
        assertNotNull(insertedDirectory);
        assertEquals(directoryName, insertedDirectory.getName());

        // Could continue comparing all values, but
        // it may make sense to use .equals()
        // TODO review .equals recommendations http://docs.jboss.org/hibernate/orm/5.2/directoryguide/html_single/Hibernate_Directory_Guide.html#mapping-model-pojo-equalshashcode
    }

    /**
     * Verify successful get by property (equal match)
     */
    @Test
    void getByPropertyEqualSuccess() {
        // TODO make for Directory
        List<Directory> directories = dao.findByPropertyEqual("name", "default");
        assertEquals(3, directories.size());
        assertEquals(1, directories.get(0).getId());
    }
//
//    /**
//     * Verify successful get by property (like match)
//     */
//    @Test
//    void getByPropertyLikeSuccess() {
//        List<Directory> directories = dao.findByPropertyLike("directoryName", "c");

//        assertEquals(4, directories.size());
//    }
}
