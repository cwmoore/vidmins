package com.vidmins.test.util;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import com.vidmins.persistence.SessionFactoryProvider;

/**
 * A class used to clean the db for the JUnit tests
 *
 * @author Lucas Kostecki
 */
public class CleanTestDB {
    private final Logger logger = LogManager.getLogger(this.getClass());
    SessionFactory sessionFactory = SessionFactoryProvider.getSessionFactory();

    /**
     * Clean the database
     */
    public void cleanDB() {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        // Create an SQL query to truncate the table. This way the ID's start at one while
        // removing all of the data in the table.
        session.createNativeQuery("TRUNCATE {TABLE NAME}").executeUpdate();
        // Insert some default data to work with for each JUnit test
        /***********************************************************************/
        /* REPEAT THIS LINE FOR AS MANY DIFFERENT DEFAULT DATA POINTS YOU WANT */
        /***********************************************************************/
        session.createNativeQuery("INSERT INTO {TABLE NAME} VALUES({YOUR VALUES TO INSERT})").executeUpdate();

        transaction.commit();
        session.close();
    }



    /**
     * Clean the database
     */
    public void testDbClean() {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        String[] tablesToClear = {"user"};//, "directory", "video", "note"};

        for (String tableName : tablesToClear) {
            // Create an SQL query to truncate the table. This way the ID's start at one while
            // removing all of the data in the table.
            //session.createNativeQuery("TRUNCATE {tableName}").executeUpdate();
            session.createNativeQuery("INSERT INTO " + tableName + " (firstName, userName) VALUES ('TEST0', 'TESTCleanDBUser')").executeUpdate();
        }
        // Insert some default data to work with for each JUnit test
        /***********************************************************************/
        /* REPEAT THIS LINE FOR AS MANY DIFFERENT DEFAULT DATA POINTS YOU WANT */
        /***********************************************************************/
        session.createNativeQuery("INSERT INTO {TABLE NAME} VALUES({YOUR VALUES TO INSERT})").executeUpdate();

        transaction.commit();
        session.close();
    }

}