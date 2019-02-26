package com.vidmins.controller;

import com.vidmins.entity.User;
import com.vidmins.persistence.GenericDao;
import com.vidmins.auth.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Log in a user.
 * @author cwmoore
 */

@WebServlet(
        name = "login",
        urlPatterns = {"/login"}
)

public class Login extends HttpServlet {

    /**
     * Handle a POST request (login)
     *
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        GenericDao<User> userDao = new GenericDao<>(User.class);
        Auth auth = new Auth();
        if (req.getParameter("userName") != null && req.getParameter("password") != null) {
            try {
                User user = auth.authenticateUser(req.getParameter("userName"), req.getParameter("password"));
                req.getSession().setAttribute("user", user);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        resp.sendRedirect("/loadClient");
    }
}