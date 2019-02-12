package com.vidmins.persistence;

import com.vidmins.entity.Directory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DirectoryData extends BaseData {
    /**
     * Create a video from search result
     *
     * @param results the database results
     */
    private Directory createDirectoryFromResults(ResultSet results) throws SQLException {
        Directory directory = new Directory();
        directory.setId(Integer.parseInt(results.getString("id")));
        directory.setName(results.getString("name"));
        directory.setDescription(results.getString("description"));
        // load videos
        return directory;
    }

    public Directory fromId(int id) {

        String sql = "SELECT * FROM directory WHERE id=?";
        Directory dir = null;

        Database database = Database.getInstance();
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            database.connect();
        } catch (Exception exception) {
            logger.debug("database.connect(): " + exception.toString());
        }
        connection = database.getConnection();

        try {
            statement = connection.prepareStatement(sql);

            resultSet = statement.executeQuery();

            resultSet.next();

            dir = createDirectoryFromResults(resultSet);

        } catch (SQLException sqlException) {
            logger.debug("DirectoryData.fromId(): " + sqlException.toString());
        } catch (Exception exception) {
            logger.debug("DirectoryData.fromId(): " + exception.toString());
        } finally {
            try {
                if (database != null) {
                    logger.debug("Disconnecting database.");
                    database.disconnect();
                }
                if (connection != null) {
                    logger.debug("Closing connection.");
                    connection.close();
                }
                if (statement != null) {
                    logger.debug("Closing statement.");
                    statement.close();
                }
                if (resultSet != null) {
                    logger.debug("Closing result set.");
                    resultSet.close();
                }
            } catch (SQLException sqlException) {
                logger.debug("closing finally" + sqlException.toString());
            }
        }

        return dir;
    }

}
