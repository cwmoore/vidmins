package com.vidmins.persistence;

import com.vidmins.entity.Directory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DirectoryData {
    private final Logger logger;

    public DirectoryData() {
        logger = LogManager.getLogger(this.getClass());
        logger.info("DirectoryData logger");
    }

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

        try (
                Object whatever = database.connect();
                Connection connection = database.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql);
                ResultSet resultSet = statement.executeQuery();
        ) {
            resultSet.next();
            dir = createDirectoryFromResults(resultSet);

        } catch (SQLException sqlException) {
            logger.debug("DirectoryData.fromId(): " + sqlException.toString());
        } catch (Exception exception) {
            logger.debug("DirectoryData.fromId(): " + exception.toString());
        }

        return dir;
    }

}
