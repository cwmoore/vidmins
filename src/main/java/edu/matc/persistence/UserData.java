package edu.matc.persistence;

import edu.matc.entity.User;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * Access users in the user table.
 *
 * @author pwaite
 */
public class UserData {

    public List<User> getAllUsers() {
        List<User> users = new ArrayList<User>();
        Database database = Database.getInstance();
        Connection connection = null;
        String sql = "SELECT * FROM user";

        try {
            database.connect();
            connection = database.getConnection();
            Statement selectStatement = connection.createStatement();
            ResultSet results = selectStatement.executeQuery(sql);
            while (results.next()) {
                User employee = createUserFromResults(results);
                users.add(employee);
            }
            database.disconnect();
        } catch (SQLException e) {
            System.out.println("SearchUser.getAllUsers()...SQL Exception: " + e);
        } catch (Exception e) {
            System.out.println("SearchUser.getAllUsers()...Exception: " + e);
        }
        return users;
    }

    //TODO add a method or methods to return a users based on search criteria

    private User createUserFromResults(ResultSet results) throws SQLException {
        User user = new User();
        user.setId(Integer.parseInt(results.getString("id")));
        user.setFirstName(results.getString("first_name"));
        user.setLastName(results.getString("last_name"));
        user.setUserName(results.getString("user_name"));
        user.setPassword(results.getString("password"));
        user.setDateOfBirth(results.getString("date_of_birth"));
        return user;
    }

}