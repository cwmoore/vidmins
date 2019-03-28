package com.vidmins.persistence;

import com.vidmins.entity.Role;
import com.vidmins.entity.User;
import com.vidmins.entity.Video;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.*;

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
        com.vidmins.test.util.Database database = com.vidmins.test.util.Database.getInstance();
        database.runSQL("reset_db.sql");
    }

    /**
     * Verifies gets all roles successfully.
     */
    @Test
    void getAllRolesSuccess() {
        List<Role> roles = dao.getAll();
        assertEquals(4, roles.size());
    }

    /**
     * Verifies a role is returned correctly based on id search
     */
    @Test
    void getByIdSuccess() {
        Role role = dao.getById(1);
        assertNotNull(role);
    }

    /**
     * Verifies a role is returned correctly based on id search
     */
    @Test
    void getByIdVerifyRoleSuccess() {
        Role role = dao.getById(3);
        assertNotNull(role);
        assertEquals("admin", role.getRole());
    }



    /**
     * Verify successful update of role
     */
    @Test
    void updateSuccess() {
        int updateId = 1;
        String newRole = "admin";
        Role roleToUpdate = dao.getById(updateId);
        roleToUpdate.setRole(newRole);
        dao.saveOrUpdate(roleToUpdate);

        Role retrievedRole = dao.getById(updateId);
        assertEquals(newRole, retrievedRole.getRole());
    }

    /**
     * Verify successful insert
     */
    @Test
    void insertSuccess() {

        Role newRole = new Role();

        String newRoleRole = "oauth";
        newRole.setRole(newRoleRole);

        GenericDao<User> userDao = new GenericDao<>(User.class);
        User user = userDao.getById(3);
        List<User> users = new ArrayList<>();
        users.add(user);
        newRole.setUsers(users);

        int insertId = dao.insert(newRole);
        assertNotEquals(0, insertId);

        Role insertedRole = dao.getById(insertId);
        assertNotNull(insertedRole);
        assertEquals(newRoleRole, insertedRole.getRole());
        assertTrue(insertedRole.getUsers().contains(user));

        // Could continue comparing all values, but
        // it may make sense to use .equals()
        // TODO review .equals recommendations http://docs.jboss.org/hibernate/orm/5.2/roleguide/html_single/Hibernate_Role_Guide.html#mapping-model-pojo-equalshashcode
    }

    /**
     * Verify successful get by property (equal match)
     */
    @Test
    void getByPropertyEqualSuccess() {
        // TODO make for Role
        GenericDao<User> userDao = new GenericDao<>(User.class);

        List<User> users = userDao.findByPropertyEqual("userName", "jcoyne");
        User user = users.get(0);

        List<Role> roles = dao.findByPropertyEqual("user", user);
        assertEquals(2, roles.size());
        assertEquals(2, roles.get(0).getId());
    }

    /**
     * Verify successful get by property (like match)
     */
    @Test
    void getByPropertiesEqualSuccess() {
        Map<String, Object> searchRole = new HashMap<>();
        searchRole.put("role", "oauth");

        List<Role> roles = dao.findByPropertyEqual(searchRole);

        assertEquals(1, roles.size());
    }
}
