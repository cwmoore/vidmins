package com.vidmins.controller;

import com.vidmins.entity.User;
import com.vidmins.persistence.UserData;

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
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        UserData userData = new UserData();

        if (req.getParameter("username") != null && req.getParameter("password") != null) {
            User user = userData.authenticateUser(req.getParameter("username"), req.getParameter("password"));
            req.getSession().setAttribute("user", user);
        }

        resp.sendRedirect("/loadClient");
    }
}