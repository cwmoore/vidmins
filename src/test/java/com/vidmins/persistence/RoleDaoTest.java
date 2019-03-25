package com.vidmins.persistence;

import com.vidmins.entity.Role;
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
 * The type Role dao test.
 */
class RoleDaoTest {

    GenericDao<Role> dao;
    Logger logger = LogManager.getLogger(this.getClass());

    /**
     * Creating the dao.
     */
    @BeforeEach
    void setUp() {
        dao = new GenericDao<>(Role.class);
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
//
//    /**
//     * Verifies gets all roles successfully.
//     */
//    @Test
//    void getAllRolesSuccess() {
//        List<Role> roles = dao.getAll();
//        assertEquals(4, roles.size());
//    }
//
//    /**
//     * Verifies a role is returned correctly based on id search
//     */
//    @Test
//    void getByIdSuccess() {
//        Role role = dao.getById(1);
//        assertNotNull(role);
//    }
//
//    /**
//     * Verifies a role is returned correctly based on id search
//     */
//    @Test
//    void getByIdVerifyRoleSuccess() {
//        Role role = dao.getById(3);
//        assertNotNull(role);
//        assertEquals("noticia", role.getLabel());
//    }
//
//
//    /**
//     * Verify successful delete of role
//     */
//    @Test
//    void deleteSuccess() {
//        int deleteId = 2;
//
//        // make this role
//        Role role = dao.getById(deleteId);
//        assertNotNull(role);
//        // disappear
//        dao.delete(role);
//        assertNull(dao.getById(deleteId));
//    }
//
//    /**
//     * Verify successful update of role
//     */
//    @Test
//    void updateSuccess() {
//        int updateId = 1;
//        String newRoleLabel = "NEW NOTE LABEL";
//        Role roleToUpdate = dao.getById(updateId);
//        roleToUpdate.setLabel(newRoleLabel);
//        dao.saveOrUpdate(roleToUpdate);
//
//        Role retrievedRole = dao.getById(updateId);
//        assertEquals(newRoleLabel, retrievedRole.getLabel());
//    }
//
//    /**
//     * Verify successful insert
//     */
//    @Test
//    void insertSuccess() {
//
//        Role newRole = new Role();
//
//        String roleLabel = "TestRoleInsert";
//        newRole.setLabel(roleLabel);
//
//        GenericDao<Video> videoDao = new GenericDao<>(Video.class);
//        Video video = videoDao.getById(3);
//        newRole.setVideo(video);
//
//
//        int insertId = dao.insert(newRole);
//        assertNotEquals(0, insertId);
//
//        Role insertedRole = dao.getById(insertId);
//        assertNotNull(insertedRole);
//        assertEquals(roleLabel, insertedRole.getLabel());
//        assertEquals(video.getTitle(), insertedRole.getVideo().getTitle());
//
//        // Could continue comparing all values, but
//        // it may make sense to use .equals()
//        // TODO review .equals recommendations http://docs.jboss.org/hibernate/orm/5.2/roleguide/html_single/Hibernate_Role_Guide.html#mapping-model-pojo-equalshashcode
//    }
//
//    /**
//     * Verify successful get by property (equal match)
//     */
//    @Test
//    void getByPropertyEqualSuccess() {
//        // TODO make for Role
//        List<Role> roles = dao.findByPropertyEqual("label", "nota");
//        assertEquals(1, roles.size());
//        assertEquals(4, roles.get(0).getId());
//    }
//
//    /**
//     * Verify successful get by property (like match)
//     */
//    @Test
//    void getByPropertiesEqualSuccess() {
//        Map<String, Object> searchRole = new HashMap<>();
//        searchRole.put("label", "nota");
//
//        List<Role> roles = dao.findByPropertyEqual(searchRole);
//
//        assertEquals(1, roles.size());
//    }
}
