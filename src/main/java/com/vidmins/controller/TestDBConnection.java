package com.vidmins.controller;

import com.google.api.services.youtube.YouTube;
import com.vidmins.entity.User;
import com.vidmins.persistence.GenericDao;
import com.vidmins.util.Database;
import com.vidmins.youtube_data_api.*;
import com.vidmins.youtube_data_api.data.*;

import org.apache.catalina.Realm;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.*;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "testDBServlet", urlPatterns = {"/test-db"})
public class TestDBConnection extends HttpServlet {

    Logger logger = LogManager.getLogger(this.getClass());

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        logger.debug("in TestDBConnection.doGet()");

        resp.setContentType("text/html");
        PrintWriter writer = resp.getWriter();

        GenericDao<User> userDao = new GenericDao<>(User.class);
        User user = userDao.getById(33);
        String msg = "users.get(0):" + user.toString();
        logger.debug(msg);

        try {
            Database database = Database.getInstance();
            logger.debug("database: " + database.toString());

            database.connect();

            Connection conn = database.getConnection();
            logger.debug("connection: " + conn.toString());

            String sql = "SELECT u.userName as userName, u.id as id, r.role as role FROM user u, role r WHERE u.userName = r.userName AND u.userName = ?";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1, "cheerful");

            ResultSet results = preparedStatement.executeQuery();

            while (results.next()) {
                logger.debug("TEST userName: " + results.getString("userName")
                        + ", role: " + results.getString("role"));
                msg += "TEST userName: " + results.getString("userName")
                        + ", role: " + results.getString("role");
            }
        } catch (SQLException sqle) {
            logger.debug("TEST find user/role FAILED: ", sqle);
        } catch (Exception e) {
            logger.debug("Something threw", e);
            logger.debug("Something threw cause", e.getCause());
        }
        writer.println("<p>" + msg + "</p>");
    }

}