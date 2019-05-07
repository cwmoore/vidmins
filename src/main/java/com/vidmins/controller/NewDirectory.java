package com.vidmins.controller;

import com.vidmins.auth.Auth;
//import com.vidmins.entity.AuthToken;
import com.vidmins.entity.Directory;
import com.vidmins.entity.Role;
import com.vidmins.entity.User;
import com.vidmins.persistence.GenericDao;

import org.apache.catalina.realm.SecretKeyCredentialHandler;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.*;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Servlet template
 * @author cwmoore
 */

@WebServlet(
        name = "newDirectory",
        urlPatterns = {"/new-directory"}
)
public class NewDirectory extends HttpServlet {
    private Logger logger;

    /**
     * Initialize variables
     */
    public void init() {
        logger = LogManager.getLogger(this.getClass());
        logger.info("Starting servlet");
    }

    /**
     * Handle a POST request
     *
     * @param request the HttpServletRequest
     * @param response the HttpServletResponse
     * @throws ServletException indicates a servlet problem
     * @throws IOException indicates an IO problem
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        GenericDao<Directory> directoryDao = new GenericDao<>(Directory.class);
        Map<String, String> errors = new HashMap<>();

        // extract field data
        String directoryName = request.getParameter("directoryName");
        String directoryDescription = request.getParameter("directoryDescription");

        for (Map.Entry<String, String[]> param : request.getParameterMap().entrySet()) {
            logger.debug(param.getKey() + ":" + String.join(", ", param.getValue()));
        }

        // verify complete and correct
        if (directoryName != null) {
            if (directoryDao.findByPropertyEqual("name", directoryName).size() > 0) {
                // username is taken already
                errors.put("directoryName", "Directory name is taken.");
            }

            if (errors.size() == 0) {
                // get current user
                GenericDao<User> userDao = new GenericDao<>(User.class);
                List<User> users = userDao.findByPropertyEqual("userName", request.getRemoteUser());

                if (users.size() == 1) {
                    User user = users.get(0);

                    // add new directory
                    Directory directory = new Directory(directoryName, directoryDescription, user);
                    int insertId = directoryDao.insert(directory);
                    request.getSession().setAttribute("currentDirectory", directoryDao.getById(insertId));
                    logger.debug("Got THIS far");
                }
            } else {
                // loop through all request parameters, make error messages for missing fields
                for (Map.Entry<String, String[]> entry : request.getParameterMap().entrySet()) {
                    if (entry.getValue() == null || entry.getValue().length == 0 || entry.getValue()[0] == null) {
                        errors.put(entry.getKey(), entry.getKey() + " must be set.");
                    }
                }
            }
        }

        if (errors.size() > 0) {
            // return error messages to user
            request.getSession().setAttribute("errors", errors);

        }

        // return to signup and report errors
        for (Map.Entry<String, String> entry : errors.entrySet()) {
            logger.debug(entry.getKey() + ":" + entry.getValue());
        }


        // go to start page
        String url = "loadClient";
        response.sendRedirect(url);
    }

    private boolean isEmail(String email) {
        return email.matches("^\\w+@\\w+\\.\\w+$");
    }
}