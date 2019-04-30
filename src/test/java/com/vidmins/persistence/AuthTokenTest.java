//package com.vidmins.persistence;
//
//import com.vidmins.entity.AuthToken;
//import com.vidmins.entity.User;
//import org.apache.logging.log4j.LogManager;
//import org.apache.logging.log4j.Logger;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//import static org.junit.jupiter.api.Assertions.*;
//
///**
// * The type AuthToken dao test.
// */
//public class AuthTokenTest {
//
//    GenericDao<AuthToken> dao;
//    Logger logger = LogManager.getLogger(this.getClass());
//
//    /**
//     * Creating the dao.
//     */
//    @BeforeEach
//    void setUp() {
//        dao = new GenericDao<>(AuthToken.class);
//
//        com.vidmins.test.util.Database database = com.vidmins.test.util.Database.getInstance();
//        database.runSQL("reset_db.sql");
//    }
//
//    /**
//     * Verifies gets all authTokens successfully.
//     */
//    @Test
//    void getAllAuthTokensSuccess() {
//        List<AuthToken> authTokens = dao.getAll();
//        assertEquals(5, authTokens.size());
//    }
//
//    /**
//     * Verifies a authToken is returned correctly based on id search
//     */
//    @Test
//    void getByIdSuccess() {
//        AuthToken authToken = dao.getById(1);
//        assertNotNull(authToken);
//    }
//
//    /**
//     * Verifies a authToken is returned correctly based on id search
//     */
//    @Test
//    void getByIdVerifyAuthTokenSuccess() {
//        AuthToken authToken = dao.getById(2);
//        assertNotNull(authToken);
//        assertEquals("token2", authToken.getVerificationToken());
//    }
//
//
//    /**
//     * Verify successful delete of authToken
//     */
//    @Test
//    void deleteSuccess() {
//        int deleteId = 2;
//
//        // make this authToken
//        AuthToken authToken = dao.getById(deleteId);
//        assertNotNull(authToken);
//        // disappear
//        dao.delete(authToken);
//        assertNull(dao.getById(deleteId));
//    }
//
//    /**
//     * Verify successful update of authToken
//     */
//    @Test
//    void updateSuccess() {
//        int updateId = 1;
//        String authTokenToken = "NEW VIDEO TITLE";
//        AuthToken authTokenToUpdate = dao.getById(updateId);
//        authTokenToUpdate.setVerificationToken(authTokenToken);
//        dao.saveOrUpdate(authTokenToUpdate);
//
//        AuthToken retrievedAuthToken = dao.getById(updateId);
//        assertEquals(authTokenToken, retrievedAuthToken.getVerificationToken());
//    }
//
//    /**
//     * Verify successful insert
//     */
//    @Test
//    void insertSuccess() {
//
//        String authTokenToken = "Crun6hyNvm6er";
//        AuthToken newAuthToken = new AuthToken();
//        newAuthToken.setVerificationToken(authTokenToken);
//
//        GenericDao<User> userDao = new GenericDao<>(User.class);
//        User newUser = userDao.getById(1);
//
//        newAuthToken.setNewUser(newUser);
//        newAuthToken.setUserHash("23");
//
//        int insertId = dao.insert(newAuthToken);
//        assertNotEquals(0, insertId);
//
//        AuthToken insertedAuthToken = dao.getById(insertId);
//        assertNotNull(insertedAuthToken);
//        assertEquals(authTokenToken, insertedAuthToken.getVerificationToken());
//
//        // Could continue comparing all values, but
//        // it may make sense to use .equals()
//        // TODO review .equals recommendations http://docs.jboss.org/hibernate/orm/5.2/authTokenguide/html_single/Hibernate_AuthToken_Guide.html#mapping-model-pojo-equalshashcode
//    }
//
//    /**
//     * Verify successful get by property (equal match)
//     */
//    @Test
//    void getByPropertyEqualSuccess() {
//        // TODO make for AuthToken
//        List<AuthToken> authTokens = dao.findByPropertyEqual("status", "-1");
//        assertEquals(2, authTokens.size());
//        assertEquals(3, authTokens.get(0).getId());
//    }
//
//    /**
//     * Verify successful get by property (like match)
//     */
//    @Test
//    void getByPropertiesEqualSuccess() {
//        Map<String, Object> searchAuthToken = new HashMap<>();
//        searchAuthToken.put("id", "1");
//        searchAuthToken.put("userHash", "1");
//
//        List<AuthToken> authTokens = dao.findByPropertyEqual(searchAuthToken);
//
//        assertEquals(1, authTokens.size());
//    }
//}
