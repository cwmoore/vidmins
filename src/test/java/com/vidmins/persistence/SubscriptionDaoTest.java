package com.vidmins.persistence;

import com.vidmins.entity.Subscription;
import com.vidmins.entity.Contract;
import com.vidmins.entity.User;
import com.vidmins.entity.Directory;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

/**
 * The type Subscription dao test.
 */
class SubscriptionDaoTest {

    GenericDao<Subscription> dao;
    Logger logger = LogManager.getLogger(this.getClass());

    /**
     * Creating the dao.
     */
    @BeforeEach
    void setUp() {
        dao = new GenericDao<>(Subscription.class);

        com.vidmins.test.util.Database database = com.vidmins.test.util.Database.getInstance();
        database.runSQL("reset_db.sql");
    }

    /**
     * Verifies gets all subscriptions successfully.
     */
    @Test
    void getAllSubscriptionsSuccess() {
        List<Subscription> subscriptions = dao.getAll();
        assertEquals(2, subscriptions.size());
    }

    /**
     * Verifies a subscription is returned correctly based on id search
     */
    @Test
    void getByIdSuccess() {
        Subscription subscription = dao.getById(1);
        assertNotNull(subscription);
    }

    /**
     * Verifies a subscription is returned correctly based on id search
     */
    @Test
    void getByIdVerifySubscriptionSuccess() {
        Subscription subscription = dao.getById(2);
        assertNotNull(subscription);
        assertEquals(3, subscription.getContract().getId());
    }

//
//    /**
//     * Verify successful delete of subscription
//     */
//    @Test
//    void deleteSuccess() {
//        int deleteId = 3;
//        Subscription subscription = dao.getById(deleteId);
//        assertNotNull(subscription);
//
//        dao.delete(subscription);
//        assertNull(dao.getById(deleteId));
//    }

    /**
     * Verify successful update of subscription
     */
    @Test
    void updateSuccess() {

        int updateId = 1;

        User subscriber = new GenericDao<>(User.class).getById(3);

        Subscription subscriptionToUpdate = dao.getById(updateId);
        subscriptionToUpdate.setSubscriber(subscriber);
        dao.saveOrUpdate(subscriptionToUpdate);

        Subscription retrievedSubscription = dao.getById(updateId);

        User retrievedSubscriber = retrievedSubscription.getSubscriber();
        logger.debug("Subscriber: " + subscriber.toString());
        logger.debug("Retrieved Subscriber" + retrievedSubscriber.toString());
        assertEquals(subscriber.getUserName(), retrievedSubscriber.getUserName());
    }

    /**
     * Verify successful insert
     */
    @Test
    void insertSuccess() {
        GenericDao<User> userDao = new GenericDao<>(User.class);

        // get a subscriber
        User subscriber = new User("dtillman");
        int subscriberId = userDao.insert(subscriber);
        subscriber = null;
        subscriber = userDao.getById(subscriberId);
        assertNotNull(subscriber);

        // get an author
        User author = new User("jgrisham");
        int authorId = userDao.insert(author);
        author = null;
        author = userDao.getById(authorId);
        assertNotNull(author);

        // get a contract
        Contract contract = new Contract("total access", "access all notes forever", 8.99, -1, true, true, true, author);
        GenericDao<Contract> contractDao = new GenericDao<>(Contract.class);
        int contractId = contractDao.insert(contract);
        contract = contractDao.getById(contractId);

        GenericDao<Directory> directoryDao = new GenericDao<>(Directory.class);
        Directory directory = directoryDao.getById(2);

        String name = "My Subscription";
        String description = "This subscription is only a test.";

        LocalDateTime start = LocalDateTime.now();
        LocalDateTime end = start.plusYears(1);

        Subscription newSubscription = new Subscription(name, description, start, end, subscriber, directory, contract);

        int insertId = dao.insert(newSubscription);
        assertNotEquals(0, insertId);

        Subscription insertedSubscription = dao.getById(insertId);
        assertNotNull(insertedSubscription);
        assertEquals(name, insertedSubscription.getName());

        // Could continue comparing all values, but
        // it may make sense to use .equals()
        // TODO review .equals recommendations http://docs.jboss.org/hibernate/orm/5.2/subscriptionguide/html_single/Hibernate_Subscription_Guide.html#mapping-model-pojo-equalshashcode
    }

    /**
     * Verify successful get by property (equal match)
     */
    @Test
    void getByPropertyEqualSuccess() {
        GenericDao<Contract> contractDao = new GenericDao<>(Contract.class);
        Contract contract = contractDao.getById(2);

        List<Subscription> subscriptions = dao.findByPropertyEqual("contract", contract);
        assertEquals(1, subscriptions.size());
        assertEquals(1, subscriptions.get(0).getId());
    }

    /**
     * Verify successful get by property (like match)
     */
    @Test
    void getByPropertiesEqualSuccess() {

        GenericDao<Contract> contractDao = new GenericDao<>(Contract.class);
        Contract contract = contractDao.getById(2);

        GenericDao<Directory> directoryDao = new GenericDao<>(Directory.class);
        Directory directory = directoryDao.getById(2);

        Map<String, Object> searchSubscription = new HashMap<>();
        searchSubscription.put("contract", contract);
        searchSubscription.put("directory", directory);

        List<Subscription> subscriptions = dao.findByPropertyEqual(searchSubscription);

        assertEquals(1, subscriptions.size());
    }
}
