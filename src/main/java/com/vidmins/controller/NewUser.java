package com.vidmins.controller;

import com.vidmins.auth.Auth;
import com.vidmins.entity.User;
import com.vidmins.persistence.GenericDao;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.*;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

/**
 * Servlet template
 * @author cwmoore
 */

@WebServlet(
        name = "newUser",
        urlPatterns = {"/new-user"}
)

public class NewUser extends HttpServlet {
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
        GenericDao<User> userDao = new GenericDao<>(User.class);
        Map<String, String> errors = new HashMap<>();

        // extract field data
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String organization = request.getParameter("organization");
        String introduction = request.getParameter("introduction");
        String username = request.getParameter("username");
        String email = request.getParameter("email");
        String password0 = request.getParameter("password0");
        String password1 = request.getParameter("password1");
        String dateOfBirth = request.getParameter("dateOfBirth");
        
        // verify complete and correct
        if (firstName != null
                && lastName != null
                && username != null
                && email != null
                && password0 != null
                && password1 != null
        ) {
            if (password0 != password1) {
                // password mismatch
                errors.put("password", "Password mismatch.");
            }
            if (!isEmail(email)) {
                // bad address
                errors.put("email", "Invalid email address.");
            }
            if (userDao.findByPropertyEqual("userName", username).size() > 0) {
                // username is taken already
                errors.put("username", "Username is taken.");
            }
        }

        if (errors.size() == 0) {
            // add new user
            User user = new User(firstName, lastName, username, Auth.encryptPassword(password0), LocalDate.parse(dateOfBirth));
            int insertId = userDao.insert(user);
            user = userDao.getById(insertId);
            request.getSession().setAttribute("user", user);
            // redirect to new user profile
        } else {
            // return to signup and report errors
        }

        // go to start page
        String url = "/";
        response.sendRedirect(url);
    }

    private boolean isEmail(String email) {
        return email.matches("^\\w+@\\w+\\.\\w+$");
    }
}