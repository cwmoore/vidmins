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
     * @param request the request object
     * @param response the response object
     * @throws ServletException indicates a servlet problem
     * @throws IOException indicates an IO problem
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
/*
// TODO reimplement login
        String username = request.getRemoteUser();
        request.isUserInRole("admin");
        request.isUserInRole("normal");
        request.isUserInRole("guest");
*/
        GenericDao<User> userDao = new GenericDao<>(User.class);
        Auth auth = new Auth();
        if (request.getParameter("userName") != null && request.getParameter("password") != null) {
            try {
                User user = auth.authenticateUser(request.getParameter("userName"), request.getParameter("password"));
                request.getSession().setAttribute("user", user);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        response.sendRedirect("loadClient");
    }
}