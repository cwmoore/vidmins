package com.vidmins.controller;

import com.google.api.services.youtube.YouTube;
import com.vidmins.entity.User;
import com.vidmins.persistence.GenericDao;
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

        writer.println("<p>" + msg + "</p>");
    }

}