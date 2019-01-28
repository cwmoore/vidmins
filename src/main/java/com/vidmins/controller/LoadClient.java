package com.vidmins.controller;

import com.vidmins.persistence.UserData;
import com.vidmins.entity.User;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.annotation.*;
import java.io.IOException;

/**
 * A simple servlet to welcome the user.
 * @author pwaite
 *
 * Add search handling
 * @author cwmoore
 */

@WebServlet(
        urlPatterns = {"/loadClient"}
)

public class LoadClient extends HttpServlet {

    /**
     * Handle a GET request
     * @param req the request object
     * @param resp the response object
     * @throws ServletException indicates a servlet problem
     * @throws IOException indicates an IO problem
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        UserData userData = new UserData();
        if (req.getParameter("username") != null && req.getParameter("password") != null) {
            User user = userData.authenticateUser(req.getParameter("username"), req.getParameter("password"));
            req.setAttribute("user", user);
            req.setAttribute("videos", userData.getUserVideos(user));
            req.setAttribute("title", "The Video Minutes App");
        } else {
            req.setAttribute("title", "VidMins");
        }
        RequestDispatcher dispatcher = req.getRequestDispatcher("/index.jsp");
        dispatcher.forward(req, resp);
    }
}