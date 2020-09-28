package com.vidmins.persistence;

import com.vidmins.entity.HashId;
import com.vidmins.entity.Note;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

/**
 * The type HashId dao test.
 */
@TestInstance(Lifecycle.PER_METHOD)
class HashIdDaoTest {

    static GenericDao<HashId> dao;
    Logger logger = LogManager.getLogger(this.getClass());
    static com.vidmins.test.util.Database database;

    /**
     * Creating the dao.
     */
    @BeforeAll
    static void setUp() {
        System.out.println("HashIdDaoTest.setUp()");
        dao = new GenericDao<>(HashId.class);
       
        database = com.vidmins.test.util.Database.getInstance();
    }

    /**
     * Resetting the DB.
     */
    @BeforeEach
    void resetDb() {
        System.out.println("HashIdDaoTest.resetDb()");
        database.runSQL("reset_db.sql");
    }

    /**
     * Verifies gets all hashIds successfully.
     */
    @Test
    void getAllHashIdsSuccess() {
        List<HashId> hashIds = dao.getAll();
        assertEquals(4, hashIds.size());
    }

    /**
     * Verifies a hashId is returned correctly based on id search
     */
    @Test
    void getByIdSuccess() {
        HashId hashId = dao.getById(2);
        assertNotNull(hashId);
    }

    /**
     * Verifies a hashId is returned correctly based on id search
     */
    @Test
    void getByIdVerifyHashIdSuccess() {
        HashId hashId = dao.getById(2);
        assertNotNull(hashId);
        assertEquals("note", hashId.getObjectType());
    }


    /**
     * Verify successful delete of hashId
     */
    @Test
    void deleteSuccess() {
        int deleteId = 2;

        // make this hashId
        HashId hashId = dao.getById(deleteId);
        assertNotNull(hashId);
        // disappear
        dao.delete(hashId);
        assertNull(dao.getById(deleteId));
    }

    /**
     * Verify successful update of hashId
     */
    @Test
    void updateSuccess() {
        int updateId = 3;
        int newHashIdObjectId = 3;
        HashId hashIdToUpdate = dao.getById(updateId);
        hashIdToUpdate.setObjectId(newHashIdObjectId);
        dao.saveOrUpdate(hashIdToUpdate);

        HashId retrievedHashId = dao.getById(updateId);
        assertEquals(newHashIdObjectId, retrievedHashId.getObjectId());
    }

    /**
     * Verify successful insert
     */
    @Test
    void insertSuccess() {

        int hashIdObjectId = 2;
        String hashIdObjectType = "video";

        HashId newHashId = new HashId();
        newHashId.setObjectId(hashIdObjectId);
        newHashId.setObjectType(hashIdObjectType);

        int insertId = dao.insert(newHashId);
        assertNotEquals(0, insertId);

        HashId insertedHashId = dao.getById(insertId);
        assertNotNull(insertedHashId);
        assertEquals(hashIdObjectId, insertedHashId.getObjectId());
}

    /**
     * Verify successful get by property (equal match)
     */
    @Test
    void getByPropertyEqualSuccess() {
        // TODO make for HashId
        List<HashId> hashIds = dao.findByPropertyEqual("objectType", "note");
        assertEquals(1, hashIds.size());
        assertEquals(2, hashIds.get(0).getId());
    }

    /**
     * Verify successful get by property (like match)
     */
    @Test
    void getByPropertiesEqualSuccess() {
        Map<String, Object> searchHashId = new HashMap<>();
        searchHashId.put("objectType", "note");

        List<HashId> hashIds = dao.findByPropertyEqual(searchHashId);

        assertEquals(1, hashIds.size());
    }

    /**
     * Test object constructor.
     */
    @Test
    void constructWithObjectSuccess() {
        GenericDao<Note> noteDao = new GenericDao<>(Note.class);
        Note note = noteDao.getById(2);
        HashId hashId = new HashId(note);
        logger.debug(hashId.toString());

        dao.saveOrUpdate(hashId);

        List<HashId> hashIds = dao.findByPropertyEqual("objectType", Note.class.getName());

        assertEquals(1, hashIds.size());
    }

    /**
     * Test find by hashId
     */
    @Test
    void findByHashIdSuccess() {
        List<HashId> hashIds = dao.findByPropertyEqual("hashId", "asdf");

        assertEquals(1, hashIds.get(0).getObjectId());
    }
}