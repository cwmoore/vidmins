package com.vidmins.controller;

import com.vidmins.entity.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.annotation.*;
import java.io.IOException;

/**
 * Log out signed in user.
 * @author cwmoore
 */

@WebServlet(
        urlPatterns = {"/logout"}
)

public class Logout extends HttpServlet {

    /**
     * Handle a GET log out request
     * @param req the request object
     * @param resp the response object
     * @throws ServletException indicates a servlet problem
     * @throws IOException indicates an IO problem
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        req.getSession().setAttribute("user", null);
        req.getSession().setAttribute("currentVideo", null);
        req.getSession().setAttribute("videos", null);
        req.getSession().setAttribute("directories", null);
        req.getSession().setAttribute("currentDirectory", null);
        req.getSession().setAttribute("notes", null);
        req.logout();

        resp.sendRedirect("logout.jsp");
    }
}