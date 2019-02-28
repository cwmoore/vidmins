package com.vidmins.test.util;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import com.vidmins.persistence.SessionFactoryProvider;

import java.util.ArrayList;

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

        String[] tablesToClear = {"user", "directory", "video", "note"};

        for (String tableName : tablesToClear) {
            // Create an SQL query to truncate the table. This way the ID's start at one while
            // removing all of the data in the table.
            //session.createNativeQuery("TRUNCATE {tableName}").executeUpdate();
            session.createNativeQuery("DESCRIBE {tableName}").executeUpdate();
        }
        // Insert some default data to work with for each JUnit test
        /***********************************************************************/
        /* REPEAT THIS LINE FOR AS MANY DIFFERENT DEFAULT DATA POINTS YOU WANT */
        /***********************************************************************/
        //session.createNativeQuery("INSERT INTO {TABLE NAME} VALUES({YOUR VALUES TO INSERT})").executeUpdate();

        transaction.commit();
        session.close();
    }
}